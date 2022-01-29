package www.qingxiangyx.litemall.db.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import www.qingxiangyx.litemall.db.cache.BoughtUserCacheService;
import www.qingxiangyx.litemall.db.cache.InventoryCacheService;
import www.qingxiangyx.litemall.db.cache.SeckillCacheService;
import www.qingxiangyx.litemall.db.dao.SeckillMapper;
import www.qingxiangyx.litemall.db.dao.SeckillOrderMapper;
import www.qingxiangyx.litemall.db.domain.Seckill;
import www.qingxiangyx.litemall.db.domain.SeckillExample;
import www.qingxiangyx.litemall.db.domain.SeckillOrder;
import www.qingxiangyx.litemall.db.domain.SeckillVo;
import www.qingxiangyx.litemall.db.dto.Exposer;
import www.qingxiangyx.litemall.db.dto.SeckillExecution;
import www.qingxiangyx.litemall.db.enums.SeckillOrderStateEnum;
import www.qingxiangyx.litemall.db.enums.SeckillStateEnum;
import www.qingxiangyx.litemall.db.exception.SeckillException;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liushaoming
 */
@Slf4j
@Service
public class SeckillService {

    //md5盐值字符串,用于混淆MD5
    private final String salt = "eFm58eS7AKHH0Ie9f50F";
    @Resource
    private SeckillMapper seckillMapper;
    @Resource
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private SeckillCacheService seckillCacheService;
    @Autowired
    private InventoryCacheService inventoryCacheService;
    @Autowired
    private BoughtUserCacheService boughtUserCacheService;
    @Autowired
    private AccessLimitService accessLimitService;

    private static ExecutorService syncDBExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(10000), new ThreadPoolExecutor.CallerRunsPolicy());
    /**
     * 优先从缓存中获取数据
     * @return
     */
    public List<SeckillVo> getSeckillList() {
        List<SeckillVo> list = seckillCacheService.getAllGoods();
        if (list == null || list.size()<1) {
            return new ArrayList<>();
        }
        return list;
    }

    public SeckillVo getById(long seckillId) {
        return seckillCacheService.getSeckill(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        // 优化点:缓存优化:超时的基础上维护一致性
        //1.访问Redis
        SeckillVo seckill = seckillCacheService.getSeckill(seckillId);
        if (seckill == null) { //没有秒杀商品
            return new Exposer(false, seckillId);
        }

        LocalDateTime startTime = seckill.getStartTime();
        LocalDateTime endTime = seckill.getEndTime();
        //系统当前时间
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(startTime)  || now.isAfter(endTime)) {
            return new Exposer(false, seckillId, Timestamp.valueOf(now).getTime(),
                    Timestamp.valueOf(startTime).getTime(),
                    Timestamp.valueOf(endTime).getTime());
        }
        //转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 执行秒杀
     */
    public SeckillExecution executeSeckill(long seckillId, long userId, String md5) throws SeckillException {
        if (accessLimitService.tryAcquireSeckill()) {
            // 如果没有被限流器限制，则执行秒杀处理
            return handleSeckillAsync(seckillId, userId, md5);
        } else {    //如果被限流器限制，直接抛出访问限制的异常
            log.info("--->ACCESS_LIMITED-->seckillId={},userId={}", seckillId, userId);
            throw new SeckillException(SeckillStateEnum.ACCESS_LIMIT);
        }
    }

    /**
     * @param seckillId
     * @param userId
     * @param md5
     * @return
     * @throws SeckillException
     */
    private SeckillExecution handleSeckillAsync(long seckillId, long userId, String md5)
            throws SeckillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            // 遇到黑客攻击，检测到了数据篡改
            log.info("seckill_DATA_REWRITE!!!. seckillId={},userId={}", seckillId, userId);
            throw new SeckillException(SeckillStateEnum.DATA_REWRITE);
        }

        int inventory = inventoryCacheService.getInventory(seckillId);
        if (inventory <= 0) {
            log.info("SECKILLSOLD_OUT. seckillId={},userId={}", seckillId, userId);
            throw new SeckillException(SeckillStateEnum.SOLD_OUT);
        }
        if (boughtUserCacheService.isBought(seckillId,userId)) {
            //重复秒杀
            log.info("SECKILL_REPEATED. seckillId={},userId={}", seckillId, userId);
            throw new SeckillException(SeckillStateEnum.REPEAT_KILL);
        } else {
            boughtUserCacheService.addQueue(seckillId,userId);
            syncDBExecutor.submit(()->handleInRedis(seckillId,userId));
            // 立即返回给客户端，说明秒杀成功了
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setUserId(userId);
            seckillOrder.setSeckillId(seckillId);
            seckillOrder.setState(SeckillStateEnum.ENQUEUE_PRE_SECKILL.getState());
            log.info("ENQUEUE_PRE_SECKILL>>>seckillId={},userId={}", seckillId, userId);
            return new SeckillExecution(seckillId, SeckillStateEnum.ENQUEUE_PRE_SECKILL, seckillOrder);
        }
    }

    /**
     * 在Redis中真正进行秒杀操作
     * @param seckillId
     * @param userId
     * @throws SeckillException
     */
    public void handleInRedis(long seckillId, long userId) throws SeckillException {
        int inventory = inventoryCacheService.getInventory(seckillId);
        if (inventory <= 0) {
            log.info("handleInRedis SECKILLSOLD_OUT. seckillId={},userId={}", seckillId, userId);
            throw new SeckillException(SeckillStateEnum.SOLD_OUT);
        }
        if (boughtUserCacheService.isBought(seckillId,userId)) {
            log.info("handleInRedis SECKILL_REPEATED. seckillId={},userId={}", seckillId, userId);
            throw new SeckillException(SeckillStateEnum.REPEAT_KILL);
        }
        updateInventory(seckillId,userId);
        inventoryCacheService.outOfWarehouse(seckillId);
        boughtUserCacheService.bought(seckillId,userId);
        log.info("handleInRedis_done");
    }

    /**
     * 先插入秒杀记录再减库存
     */
    @Transactional
    public SeckillExecution updateInventory(long seckillId, long userId)
            throws SeckillException {
        //执行秒杀逻辑:减库存 + 记录购买行为
        LocalDateTime nowTime = LocalDateTime.now();
        try {
            //插入秒杀记录(记录购买行为)
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setSeckillId(seckillId);
            seckillOrder.setUserId(userId);
            seckillOrder.setCreateTime(nowTime);
            seckillOrder.setState(SeckillOrderStateEnum.SUCCESS.getState());
            int insertCount = seckillOrderMapper.insert(seckillOrder);
            //唯一:seckillId,userId
            if (insertCount <= 0) {
                //重复秒杀
                log.info("seckill REPEATED. seckillId={},userId={}", seckillId, userId);
                throw new SeckillException(SeckillStateEnum.REPEAT_KILL);
            } else {
                //减库存,热点商品竞争
                // reduceNumber是update操作，开启作用在表seckill上的行锁
                Seckill currentSeckill = seckillMapper.selectByPrimaryKey(seckillId);
                boolean validTime = false;
                if (currentSeckill != null) {
                    if (nowTime.isAfter(currentSeckill.getStartTime())&& nowTime.isBefore(currentSeckill.getEndTime())
                            && currentSeckill.getInventory() > 0 && currentSeckill.getVersion() > -1) {
                        validTime = true;
                    }
                }

                if (validTime) {
                    long oldVersion = currentSeckill.getVersion();
                    // update操作开始，表seckill的seckill_id等于seckillId的行被启用了行锁,   其他的事务无法update这一行， 可以update其他行
                    int updateCount = seckillMapper.reduceInventory(seckillId, oldVersion, oldVersion + 1);
                    if (updateCount <= 0) {
                        //没有更新到记录，秒杀结束,rollback
                        log.info("seckill_DATABASE_CONCURRENCY_ERROR!!!. seckillId={},userId={}", seckillId, userId);
                        throw new SeckillException(SeckillStateEnum.DB_CONCURRENCY_ERROR);
                    } else {
                        //秒杀成功 commit
//                        SeckillOrder seckillOrdr = payOrderDAO.queryByIdWithSeckill(seckillId, userId);
                        log.info("seckill SUCCESS->>>. seckillId={},userId={}", seckillId, userId);
                        return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, seckillOrder);
                        //return后，事务结束，关闭作用在表seckill上的行锁
                        // update结束，行锁被取消  。reduceInventory()被执行前后数据行被锁定, 其他的事务无法写这一行。
                    }
                } else {
                    log.info("seckill_END. seckillId={},userId={}", seckillId, userId);
                    throw new SeckillException(SeckillStateEnum.END);
                }
            }
        } catch (SeckillException e1) {
            throw e1;
        } catch (Exception e) {
            log.error("updateInventory for {} {} happend {} error ",seckillId,userId,e.getMessage(), e);
            //  所有编译期异常 转化为运行期异常
            throw new SeckillException(SeckillStateEnum.INNER_ERROR);
        }
    }

    /**
     *
     * @param seckillId
     * @param userId
     * @return 0： 排队中; 1: 秒杀成功; 2： 秒杀失败
     */
    public int isGrab(long seckillId, long userId) {
        int result = 0 ;
        try {
            result = boughtUserCacheService.isBought(seckillId, userId) ? 1 : 0;
        } catch (Exception e) {
            log.error("get {} isBought {} from redis error",userId,seckillId,e);
            result = 0;
        }

        if (result == 0) {
            if (!boughtUserCacheService.inQueue(seckillId,userId)) {
                result =2;
            }
        }
        return result;
    }
}
