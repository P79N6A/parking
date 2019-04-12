package com.zoeeasy.cloud.axino.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 诺诺请求参数基类
 *
 * @author walkman
 */
@Data
public abstract class BaseNnRequestBean implements Serializable {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = 8243127099991355176L;

    /**
     * 身份认证， 在诺诺网备案后，由诺
     * 诺网提供，每个企业一个
     */
    private String identity;

}
