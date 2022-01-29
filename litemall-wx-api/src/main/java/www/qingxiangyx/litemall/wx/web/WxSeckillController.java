package www.qingxiangyx.litemall.wx.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import www.qingxiangyx.litemall.core.util.ResponseUtil;
import www.qingxiangyx.litemall.db.domain.LitemallGoods;
import www.qingxiangyx.litemall.db.domain.Seckill;
import www.qingxiangyx.litemall.db.domain.SeckillVo;
import www.qingxiangyx.litemall.db.dto.Exposer;
import www.qingxiangyx.litemall.db.dto.SeckillExecution;
import www.qingxiangyx.litemall.db.enums.SeckillStateEnum;
import www.qingxiangyx.litemall.db.exception.SeckillException;
import www.qingxiangyx.litemall.db.service.LitemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import www.qingxiangyx.litemall.db.service.SeckillService;
import www.qingxiangyx.litemall.wx.annotation.LoginUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenxing.luo
 * @date 2022/1/28 9:10 上午
 * @Description:秒杀服务
 */
@Slf4j
@RestController
@RequestMapping("/wx/seckill")
@Validated
public class WxSeckillController {
    @Autowired
    private LitemallGoodsService goodsService;
    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        long seckillId = 1000L;
        SeckillVo seckill = seckillService.getById(seckillId);
        Thread currentThread = Thread.currentThread();
        log.info("thread.hashCode={},id={},name={}"
                , currentThread.hashCode(), currentThread.getId(), currentThread.getName());
        return JSON.toJSONString(seckill);
    }

    /**
     * 秒杀产品列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取列表页
        List<SeckillVo> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/detail/{seckillId}", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        SeckillVo seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    //ajax json
    @GetMapping(value = "/exposer/{seckillId}")
    public Object exposer(@PathVariable Long seckillId) {
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            return ResponseUtil.ok(exposer);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseUtil.fail(502, e.getMessage());
        }
    }

    @RequestMapping(value = "/execution/{seckillId}/{md5}",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object execute(@PathVariable("seckillId") Long seckillId,
                                                   @LoginUser Integer userId,
                                                   @PathVariable("md5") String md5) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, userId.longValue(), md5);
            return ResponseUtil.ok(execution);
        } catch (SeckillException e1) {
            SeckillExecution execution = new SeckillExecution(seckillId, e1.getSeckillStateEnum());
            return ResponseUtil.fail(e1.getSeckillStateEnum().getState(), e1.getSeckillStateEnum().getStateInfo(),execution);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            SeckillStateEnum innerError = SeckillStateEnum.INNER_ERROR;
            SeckillExecution execution = new SeckillExecution(seckillId, innerError);
            return ResponseUtil.fail(innerError.getState(), innerError.getStateInfo(),execution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public Object time() {
        Date now = new Date();
        return ResponseUtil.ok(now.getTime());
    }

    /**
     * @param seckillId
     * @param phone
     * @return 返回代码的含义0： 排队中; 1: 秒杀成功; 2： 秒杀失败
     * @TODO String boughtKey = RedisKeyPrefix.BOUGHT_USERS + seckillId
     * 还有一个redisKey存放已经入队列了的userPhone，   ENQUEUED_USER
     * 进队列的时候sadd ENQUEUED_USER , 消费成功的时候，sdel ENQUEUED_USER
     * 查询这个isGrab接口的时候，先查sismembles boughtKey, true则表明秒杀成功.
     * 否则，ismembles ENQUEUED_USER, 如果在队列中，说明排队中， 如果不在，说明秒杀失败
     */
    @RequestMapping(value = "/isGrab/{seckillId}/{phone}")
    @ResponseBody
    public String isGrab(@PathVariable("seckillId") Long seckillId,
                         @PathVariable("phone") Long phone) {
        int result = seckillService.isGrab(seckillId, phone);
        return result + "";
    }
}
