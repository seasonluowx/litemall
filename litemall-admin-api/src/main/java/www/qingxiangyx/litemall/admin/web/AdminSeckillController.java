package www.qingxiangyx.litemall.admin.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import www.qingxiangyx.litemall.admin.annotation.RequiresPermissionsDesc;
import www.qingxiangyx.litemall.core.util.ResponseUtil;
import www.qingxiangyx.litemall.db.cache.SeckillCacheService;
import www.qingxiangyx.litemall.db.domain.SeckillVo;
import www.qingxiangyx.litemall.db.service.SeckillService;

import java.util.List;

/**
 * @author wenxing.luo
 * @date 2022/1/29 4:33 下午
 * @Description:
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/seckill")
public class AdminSeckillController {
    @Autowired
    private SeckillCacheService seckillCacheService;
    @Autowired
    private SeckillService seckillService;

    @RequiresPermissions("admin:seckill:init")
    @RequiresPermissionsDesc(menu = {"初始化秒杀缓存"}, button = "查询")
    @GetMapping("/init")
    public Object init() {
        seckillCacheService.initAllGoods();
        return ResponseUtil.ok();
    }

    /**
     * 秒杀产品列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(Model model) {
        //获取列表页
        List<SeckillVo> list = seckillService.getSeckillList();
        return ResponseUtil.okList(list);
    }
}
