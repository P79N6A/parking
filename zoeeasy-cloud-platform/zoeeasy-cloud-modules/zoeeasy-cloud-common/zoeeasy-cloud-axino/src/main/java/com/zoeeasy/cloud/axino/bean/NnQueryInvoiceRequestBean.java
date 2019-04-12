package com.zoeeasy.cloud.axino.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据订单号查询发票请求流水号请求参数
 *
 * @date: 2019/1/17.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NnQueryInvoiceRequestBean extends BaseNnRequestBean {

    /**
     * 订单编号
     */
    @JsonProperty("orderno")
    private String[] orderNo;
}
