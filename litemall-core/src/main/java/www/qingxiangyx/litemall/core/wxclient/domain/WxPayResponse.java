package www.qingxiangyx.litemall.core.wxclient.domain;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.thoughtworks.xstream.XStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * @author wenxing.luo
 * @date 2022/2/8 10:41 下午
 * @Description:
 */
@Data
@AllArgsConstructor
public class WxPayResponse {
    private transient static final String FAIL = "FAIL";
    private transient static final String SUCCESS = "SUCCESS";
    //错误码，SUCCESS为清算机构接收成功，其他错误码为失败。
    //示例值：SUCCESS
    private String code;
//    返回信息，如非空，为错误原因。
//    示例值：系统错误
    private String message;

    public static WxPayResponse fail(String msg) {
        WxPayResponse response = new WxPayResponse(FAIL, msg);
        return response;
    }

    public static WxPayResponse success(String msg) {
        WxPayResponse response = new WxPayResponse(SUCCESS, msg);
        return response;
    }
}
