package www.qingxiangyx.litemall.allinone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import www.qingxiangyx.litemall.wx.service.WxOrderService;

/**
 * @author wenxing.luo
 * @date 2022/2/8 10:18 下午
 * @Description:
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WxOrderTest {

    @Autowired
    private WxOrderService wxOrderService;

    @Test
    public void test() {
        String notifyResult = "{\"id\":\"4e4159e2-f6dd-5222-8517-92b0f7945b42\",\"create_time\":\"2022-02-08T22:15:14+08:00\",\"resource_type\":\"encrypt-resource\",\"event_type\":\"TRANSACTION.SUCCESS\",\"summary\":\"支付成功\",\"resource\":{\"original_type\":\"transaction\",\"algorithm\":\"AEAD_AES_256_GCM\",\"ciphertext\":\"cuASMp2Hz5RQz8Y8xa+m2st+iG0J2ZwClTvLvY8bnsd0QMFqQ26FgzxEOfx3IUEhU2OtTMg6cnw3LVZKU1sX0EKQ6uWNbVEoxZ2KB+zOGL5L4GGVE1dmkC4ife+5yozCfT1uguPKT/kgwNvKMkuN7CCiEulYz7NGd7es3jpM6iHUr7pwAwgjQ05B8BDSVWrCb9Gu18UlZzoQXy2VQw75g5n79JFVDxTbDtDHcQHBt67YcN8PeBIy2ekL8YwIMKdhPVpOnL355b+oEc4VfWNicM0TDPVYAs0NQKVYJ7zw+7pJj21cPvbmfq7kOPaiRbw2YQVlAo1sVHDep0kE8ownh+R6GXtGtnUO7cen2Fzk1xs4M1aQWdqzW9Ri26Na02SPrSvCbt2T86mSgjOr4T7csTmd1vuj+dt+qx5pA1DH7YmUkAOkWLqir+czI6P67xhBwCM4DYFmm1ZJSzWWB0eYKhIVMWGhKPitYYAQpZicH9zVkKMCGF9cCxdvUaotI2kZ2rw6u+7thWwvbPcaZ7whm/TOkEvqPtLY1WvEt30h32C+faORfceVyPPgJJ8oXlH4kw==\",\"associated_data\":\"transaction\",\"nonce\":\"VZr1EHeLK6Kr\"}}";
        wxOrderService.parseAndProcessNotify(notifyResult);
    }

}
