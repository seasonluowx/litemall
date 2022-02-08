package www.qingxiangyx.litemall.core.wxclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import www.qingxiangyx.litemall.core.config.WxProperties;
import www.qingxiangyx.litemall.core.util.AesUtil;
import www.qingxiangyx.litemall.core.util.RsaUtils;
import www.qingxiangyx.litemall.core.wxclient.domain.WxOrderResult;
import www.qingxiangyx.litemall.core.wxclient.domain.WxPayNotifyResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author wenxing.luo
 * @date 2022/2/6 10:31 下午
 * @Description: 使用微信支付API v3的Apache HttpClient扩展
 */
@Slf4j
@Service
public class WxHttpPayService {
    public static final String JSAPI = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private WxPayConfig wxPayConfig;
    @Autowired
    private WxProperties properties;

    public WxOrderResult jsApi(WxPayUnifiedOrderRequest orderRequest) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        HttpPost httpPost = new HttpPost(JSAPI);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type","application/json; charset=utf-8");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode rootNode = objectMapper.createObjectNode();
        String appId = wxPayConfig.getAppId();
        rootNode.put("mchid",wxPayConfig.getMchId())
                .put("appid", appId)
                .put("description", orderRequest.getBody())
                .put("notify_url", wxPayConfig.getNotifyUrl())
                .put("out_trade_no", orderRequest.getOutTradeNo());
        rootNode.putObject("amount")
                .put("total", orderRequest.getTotalFee());
        rootNode.putObject("payer")
                .put("openid", orderRequest.getOpenid());

        objectMapper.writeValue(bos, rootNode);

        httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);

        String bodyAsString = EntityUtils.toString(response.getEntity());
        System.out.println(bodyAsString);
        JSONObject bodyJson = JSON.parseObject(bodyAsString);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = UUID.randomUUID().toString().replaceAll("-","");
        WxOrderResult payResult = WxOrderResult.builder()
                .appId(appId)
                .timeStamp(timestamp)
                .nonceStr(nonceStr)
                .packageValue("prepay_id=" + bodyJson.getString("prepay_id"))
                .signType("RSA")
                .build();
        Signature sign = Signature.getInstance("SHA256withRSA");
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new ClassPathResource(properties.getKeyPath()).getInputStream());
        sign.initSign(merchantPrivateKey);
        sign.update(payResult.getSignStrand().getBytes(StandardCharsets.UTF_8));
        payResult.setPaySign(Base64.getEncoder().encodeToString(sign.sign()));
        return payResult;
    }

    public WxPayNotifyResult parseOrderNotifyResult(String notifyResult) {
        WxPayNotifyResult wxPayNotifyResult = new WxPayNotifyResult();
        JSONObject result = JSON.parseObject(notifyResult);
        String eventType = result.getString("event_type");
        if(!"TRANSACTION.SUCCESS".equals(eventType)){
            log.error("pay order {}",eventType);
            wxPayNotifyResult.setReturnCode(eventType);
            return wxPayNotifyResult;
        }
        String resource = result.getString("resource");
        JSONObject resourceData = JSON.parseObject(resource);
        String associatedData = resourceData.getString("associated_data");
        String nonce = resourceData.getString("nonce");
        String ciphertext = resourceData.getString("ciphertext");
        byte[] associatedDataByte = StringUtils.isNotBlank(associatedData)?associatedData.getBytes(StandardCharsets.UTF_8):null;
        AesUtil aesUtil = new AesUtil(properties.getMchKey().getBytes(StandardCharsets.UTF_8));
        try {
            String decryptText = aesUtil.decryptToString(associatedDataByte,nonce.getBytes(StandardCharsets.UTF_8),ciphertext);
            log.info("[decryptText]:{}",decryptText);
            wxPayNotifyResult = JSON.parseObject(decryptText,WxPayNotifyResult.class);
            wxPayNotifyResult.setReturnCode("SUCCESS");
            log.info("[parse result]:{}",wxPayNotifyResult);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxPayNotifyResult;
    }
}
