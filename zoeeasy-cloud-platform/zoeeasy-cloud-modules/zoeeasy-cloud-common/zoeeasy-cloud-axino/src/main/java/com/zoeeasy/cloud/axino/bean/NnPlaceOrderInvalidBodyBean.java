package com.zoeeasy.cloud.axino.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @date: 2019/1/17.
 * @author：zm
 */
@Data
public class NnPlaceOrderInvalidBodyBean implements Serializable {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = 8243127099991355176L;

    /**
     * 发票请求流水号  是  20
     */
    private String fpqqlsh;

    /**
     * 对应发票代码 是  12
     */
    private String fpdm;

    /**
     * 对应发票号码 是 12
     */
    private String fphm;
}
