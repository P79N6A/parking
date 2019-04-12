package com.zoeeasy.cloud.axino.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 开票请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NnPlaceOrderRequestBean extends BaseNnRequestBean {

    @JsonProperty("order")
    private NnPlaceOrderBodyBean order;

}
