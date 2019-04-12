package com.zoeeasy.cloud.axino.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 请求体中公共参数model
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class PublicData {

    /**
     * 调用方法
     */
    @Getter
    @Setter
    private String method;

    /**
     * API版本
     */
    @Getter
    @Setter
    private String version;

    /**
     * 时间戳
     */
    @Getter
    @Setter
    private String timestamp;

}
