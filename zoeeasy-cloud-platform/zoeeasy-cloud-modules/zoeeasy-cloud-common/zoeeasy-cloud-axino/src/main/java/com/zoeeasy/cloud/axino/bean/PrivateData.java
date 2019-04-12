package com.zoeeasy.cloud.axino.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 请求体中业务参数model
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class PrivateData<T> {

    /**
     * 请求数据
     */
    @Getter
    @Setter
    private Object servicedata;

}
