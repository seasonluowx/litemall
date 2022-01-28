package www.qingxiangyx.litemall.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import www.qingxiangyx.litemall.admin.util.Permission;
import www.qingxiangyx.litemall.admin.util.PermissionUtil;
import www.qingxiangyx.litemall.admin.vo.PermVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PermissionTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void test() {
        final String basicPackage = "www.qingxiangyx.litemall.admin";
        List<Permission> permissionList = PermissionUtil.listPermission(context, basicPackage);
        List<PermVo> permVoList = PermissionUtil.listPermVo(permissionList);
        permVoList.stream().forEach(System.out::println);
    }
}
