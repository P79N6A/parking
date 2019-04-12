package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 微信支付请求参数基类
 *
 * @author walkman
 */
@Data
public abstract class WeChatPayBaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公众账号ID
     */
    @NotNull
    protected String appid;

    /**
     * 商户号
     */
    @NotNull
    protected String mchId;

    /**
     * 签名秘钥
     */
    @NotNull
    protected String signKey;
}
