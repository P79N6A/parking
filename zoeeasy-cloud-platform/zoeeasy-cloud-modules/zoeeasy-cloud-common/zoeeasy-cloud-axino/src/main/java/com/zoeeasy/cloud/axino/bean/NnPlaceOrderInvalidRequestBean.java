package com.zoeeasy.cloud.axino.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date: 2019/1/17.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NnPlaceOrderInvalidRequestBean extends BaseNnRequestBean{

    @JsonProperty("order")
    private NnPlaceOrderInvalidBodyBean order;
}
