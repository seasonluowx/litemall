package www.qingxiangyx.litemall.core.wxclient.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author wenxing.luo
 * @date 2022/2/8 8:11 上午
 * @Description:
 */
@Data
public abstract class WxPayResult implements Serializable {
    /**
     * 返回状态码.
     */
    protected String returnCode;
    /**
     * 返回信息.
     */
    protected String returnMsg;
    /**
     * 交易状态，枚举值：
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（付款码支付）
     * USERPAYING：用户支付中（付款码支付）
     * PAYERROR：支付失败(其他原因，如银行返回失败)
     */
    private String tradeState;
    /**
     * 错误代码.
     */
    private String errCode;
    /**
     * 交易状态描述
     */
    private String tradeStateDesc;
    /**
     * 公众账号ID.
     */
    private String appid;
    /**
     * 商户号.
     */
    private String mchId;
    /**
     * 服务商模式下的子公众账号ID.
     */
    private String subAppId;
    /**
     * 服务商模式下的子商户号.
     */
    private String subMchId;

}
