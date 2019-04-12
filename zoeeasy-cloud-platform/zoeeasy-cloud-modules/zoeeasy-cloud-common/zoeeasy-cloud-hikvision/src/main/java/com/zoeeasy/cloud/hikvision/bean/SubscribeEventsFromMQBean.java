package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 事件订阅返回
 * @author: AkeemSuper
 * @date: 2018/3/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscribeEventsFromMQBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * mq的地址和端口
     */
    private String mqURL;

    /**
     * 订阅名称
     */
    private String destination;

}
