package org.linlinjava.litemall.wx.web;

import io.swagger.annotations.ApiOperation;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenxing.luo
 * @date 2022/1/28 9:10 上午
 * @Description:秒杀服务
 */
@RestController
@RequestMapping("/wx/seckill")
@Validated
public class WxSeckillController {
    @Autowired
    private LitemallGoodsService goodsService;

    /**
     * 秒杀产品列表
     */
    @GetMapping(value = "list")
    public Object kill(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
                       String sort, String order) {
        //查询列表数据
        Map params = new HashMap();
        params.put("page", page);
        params.put("limit", size);
        params.put("is_secKill", "2");// 秒杀
        params.put("sidx", "start_time");
        params.put("order", "asc");

        Query query = new Query(params);
        List<GoodsVo> killlist = goodsService.querySeckillByPage(query);
        int total = goodsService.queryKillTotal(query);

        ApiPageUtils goodsData = new ApiPageUtils(killlist, total, query.getLimit(), query.getPage());
        goodsData.setGoodsList(goodsData.getData());
        return toResponsSuccess(goodsData);
    }
}
