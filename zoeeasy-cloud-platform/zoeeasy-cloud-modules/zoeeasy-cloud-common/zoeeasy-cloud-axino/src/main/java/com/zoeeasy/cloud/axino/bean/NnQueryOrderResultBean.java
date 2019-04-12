package com.zoeeasy.cloud.axino.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 开票结果查询
 *
 * @date: 2019/1/17.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NnQueryOrderResultBean extends BaseNnResultBean {

    @JsonProperty("list")
    private List<NnInvoiceListBodyBean> list;
}
