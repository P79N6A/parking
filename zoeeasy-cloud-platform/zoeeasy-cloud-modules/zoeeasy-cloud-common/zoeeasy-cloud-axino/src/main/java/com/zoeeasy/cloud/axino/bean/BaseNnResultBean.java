package com.zoeeasy.cloud.axino.bean;

import lombok.Data;

import java.io.Serializable;


/**
 * 诺诺请求参数基类
 *
 * @author walkman
 */
@Data
public abstract class BaseNnResultBean implements Serializable {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = 8243127659991355176L;

    /**
     * 状态
     */
    private String status;

    /**
     * 详细信息
     */
    private String message;

    /**
     * 请求结果
     */
    private String result;

    /**
     * 错误信息
     */
    private String errorMsg;


}