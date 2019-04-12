package com.zhuyitech.parking.inpark.config;

import com.zhuyitech.parking.inpark.constant.InParkPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Inpack配置参数
 *
 * @author walkman
 */
@Configuration
public class InparkConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    @Value("${" + InParkPropertyKeys.INPACK_SIGN_KEY + ":1f1e7f89b62755ce47403d8745d7bfec}")
    @Getter
    @Setter
    private String signKey;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    @Value("${" + InParkPropertyKeys.INPACK_PARKING_LIST_URL + ":https://servi.in-park.cn/test/easily_inpark/user/rytcLogin}")
    @Getter
    @Setter
    private String parkingListUrl;

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    @Value("${" + InParkPropertyKeys.INPACK_ORDER_LIST_URL + ":https://servi.in-park.cn/test/easily_inpark/user/rytcOrderLog}")
    @Getter
    @Setter
    private String orderListUrl;

}
