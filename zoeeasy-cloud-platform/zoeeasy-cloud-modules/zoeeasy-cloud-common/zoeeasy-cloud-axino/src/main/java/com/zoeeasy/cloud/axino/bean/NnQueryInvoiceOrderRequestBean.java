package com.zoeeasy.cloud.axino.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 开票结果查询请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NnQueryInvoiceOrderRequestBean extends BaseNnRequestBean {

    /**
     * 发票请求流水号
     */
    @JsonProperty("fpqqlsh")
    private String[] fpqqlsh;

}
