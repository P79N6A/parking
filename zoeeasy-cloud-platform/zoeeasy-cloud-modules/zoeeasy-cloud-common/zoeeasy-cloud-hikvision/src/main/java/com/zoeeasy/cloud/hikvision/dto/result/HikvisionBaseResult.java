package com.zoeeasy.cloud.hikvision.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 请求返回参数共同属性类
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HikvisionBaseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误消息
     */
    private String errorMessage;

    /**
     * 返回数据
     */
    private T data;

}
