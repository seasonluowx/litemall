package www.qingxiangyx.litemall.wx.web;

import com.github.pagehelper.PageInfo;
import www.qingxiangyx.litemall.core.util.ResponseUtil;
import www.qingxiangyx.litemall.db.domain.LitemallGoods;
import www.qingxiangyx.litemall.db.service.LitemallGoodsService;
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
    public Object kill(@RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size) {
        List<LitemallGoods> seckilllist = goodsService.querySeckill(page,size);
        PageInfo<LitemallGoods> pagedList = PageInfo.of(seckilllist);
        Map<String, Object> entity = new HashMap<>();
        entity.put("list", seckilllist);
        entity.put("total", pagedList.getTotal());
        entity.put("page", pagedList.getPageNum());
        entity.put("limit", pagedList.getPageSize());
        entity.put("pages", pagedList.getPages());
        return ResponseUtil.ok(entity);
    }
}
