package com.zoeeasy.cloud.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <pre>
 *     转换短链接
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:53
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatShortenUrlResult extends WeChatPayBaseResult {

    /**
     * URL链接
     */
    @XStreamAlias("short_url")
    private String shortUrl;

}