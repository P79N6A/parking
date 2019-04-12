package com.zoeeasy.cloud.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zoeeasy.cloud.pay.wechat.params.WeChatPayBaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;


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
@EqualsAndHashCode(callSuper = false)
public class WeChatShortenUrlBean extends WeChatPayBaseBean {

    /**
     * URL链接
     */
    @XStreamAlias("long_url")
    private String longUrl;

}