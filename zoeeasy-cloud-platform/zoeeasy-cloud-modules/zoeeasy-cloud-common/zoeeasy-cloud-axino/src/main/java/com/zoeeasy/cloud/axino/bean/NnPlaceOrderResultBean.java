package com.zoeeasy.cloud.axino.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 开票结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NnPlaceOrderResultBean extends BaseNnResultBean {

    /**
     * 提交成功则返回发票请求流水号
     */
    @JsonProperty("fpqqlsh")
    private String fpqqlsh;

}