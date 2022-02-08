package www.qingxiangyx.litemall.core.wxclient.domain;

import lombok.Data;

/**
 * @author wenxing.luo
 * @date 2022/2/8 11:21 下午
 * @Description:
 */
@Data
public class WxPayAmount {
    //订单总金额，单位为分。
    private int total;
    //用户支付金额，单位为分。
    private int payerTotal;
//    CNY：人民币，境内商户号仅支持人民币。
    private String currency;
//    用户支付币种
    private String payerCurrency;
}
