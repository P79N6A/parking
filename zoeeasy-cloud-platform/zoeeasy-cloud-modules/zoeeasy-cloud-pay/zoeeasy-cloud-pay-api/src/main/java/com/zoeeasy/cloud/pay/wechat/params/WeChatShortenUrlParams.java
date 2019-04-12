package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


/**
 * <pre>
 *     转换短链接
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatShortenUrlParams extends WeChatPayBaseParam {

    /**
     * 商户订单号
     */
    @NotEmpty
    private String longUrl;

}