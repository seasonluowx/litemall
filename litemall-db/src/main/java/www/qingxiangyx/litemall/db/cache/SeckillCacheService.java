package www.qingxiangyx.litemall.db.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import www.qingxiangyx.litemall.db.dao.SeckillMapper;
import www.qingxiangyx.litemall.db.domain.LitemallGoods;
import www.qingxiangyx.litemall.db.domain.Seckill;
import www.qingxiangyx.litemall.db.domain.SeckillExample;
import www.qingxiangyx.litemall.db.domain.SeckillVo;
import www.qingxiangyx.litemall.db.service.LitemallGoodsService;
import www.qingxiangyx.litemall.db.util.DateUtil;
import www.qingxiangyx.litemall.db.util.RedisKey;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wenxing.luo
 * @date 2022/1/28 6:07 下午
 * @Description:
 */
@Slf4j
@Service
public class SeckillCacheService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private SeckillMapper seckillMapper;
    @Resource
    private LitemallGoodsService goodsService;

    public SeckillVo getSeckill(long seckillId) {
        String key = RedisKey.SECKILL_GOODS + seckillId;
        SeckillVo seckill = (SeckillVo) redisTemplate.opsForValue().get(key);
        return seckill;
    }

    public void setSeckill(SeckillVo seckillVo) {
        String key = RedisKey.SECKILL_GOODS + seckillVo.getId();
        Long days = DateUtil.getIntervalDays(LocalDate.now(),seckillVo.getEndTime().toLocalDate());
        redisTemplate.opsForValue().set(key,seckillVo,days+1, TimeUnit.DAYS);
    }

    /**
     * 从缓存中获取所有的实时商品数据(包括实时库存量)
     * @return
     */
    public List<SeckillVo> getAllGoods() {
        List<SeckillVo> result = new ArrayList<>();
        Set<Object> idSet = redisTemplate.opsForSet().members(RedisKey.SECKILL_ID_SET);
        if (idSet != null || idSet.size() > 0) {
            for (Object seckillId : idSet) {
                SeckillVo seckill = getSeckill((long)seckillId);
                if (seckill != null) {
                        // goodsKey获取到的库存量是初始值，并不是当前值，所以需要从RedisKeyPrefix.SECKILL_INVENTORY+seckillID
                        // 获取到的库存，再设置到结果中去
                        String inventoryStr = (String) redisTemplate.opsForValue().get(RedisKey.SECKILL_INVENTORY + seckillId);
                        if (!StringUtils.isEmpty(inventoryStr)) {
                            seckill.setInventory(Integer.valueOf(inventoryStr));
                        }
                    result.add(seckill);
                }
            }
        }
        return result;
    }

    public void setAllGoods(List<SeckillVo> list) {
        if (list == null || list.size()< 1) {
            log.info("--FatalError!!! seckill_list_data is empty");
            return;
        }
        redisTemplate.delete(RedisKey.SECKILL_ID_SET);

        for (SeckillVo seckill : list) {
            redisTemplate.opsForSet().add(RedisKey.SECKILL_ID_SET, seckill.getId());
            setSeckill(seckill);
        }
        log.info("数据库Goods数据同步到Redis完毕！");
    }

    public void initAllGoods(){
        SeckillExample example = new SeckillExample();
        SeckillExample.Criteria criteria = example.createCriteria();
        criteria.andEndTimeGreaterThan(LocalDateTime.now());
        List<Seckill> seckills = seckillMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(seckills)){
            log.warn("no valid seckill list");
            return ;
        }
        List<Integer> goodIds = seckills.stream().map(Seckill::getGoodId).collect(Collectors.toList());
        List<LitemallGoods> goods = goodsService.queryByIdList(goodIds);
        if(CollectionUtils.isEmpty(goods)){
            log.warn("no valid seckill goods");
            return;
        }
        Map<Integer,LitemallGoods> goodsMap = goods.stream().collect(Collectors.toMap(e->e.getId(),e->e));
        List<SeckillVo> seckillVoList = seckills.stream().map(e->{
            SeckillVo vo = new SeckillVo();
            BeanUtils.copyProperties(e,vo);
            LitemallGoods good = goodsMap.get(e.getGoodId());
            if(good!=null){
                vo.setGoods(good);
            }
            return vo;
        }).collect(Collectors.toList());
        setAllGoods(seckillVoList);
    }
}
