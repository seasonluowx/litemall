package www.qingxiangyx.litemall.core.wxclient.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * @author wenxing.luo
 * @date 2022/2/7 9:53 下午
 * @Description:
 */
@Data
@Builder
public class WxOrderResult {
    private String appId;
    private String timeStamp;
    private String nonceStr;
    /**
     * 由于package为java保留关键字，因此改为packageValue. 前端使用时记得要更改为package
     */
    @XStreamAlias("package")
    private String packageValue;
    private String signType;
    private String paySign;
    public String getSignStrand(){
        return this.appId+"\n"+timeStamp+"\n"+nonceStr+"\n"+packageValue+"\n";
    }
}
