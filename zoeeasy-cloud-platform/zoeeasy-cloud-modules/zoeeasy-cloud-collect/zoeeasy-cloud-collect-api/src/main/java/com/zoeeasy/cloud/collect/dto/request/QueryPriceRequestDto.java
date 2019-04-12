package com.zoeeasy.cloud.collect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.RequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019-03-01
 * @author: wf
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "QueryPriceRequestDto", description = "订单价格查询请求参数")
public class QueryPriceRequestDto extends RequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场Code
     */
    @ApiModelProperty("本地停车场Code")
    private String localCode;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private String plateColor;

    /**
     * 云平台订单号
     */
    @ApiModelProperty("云平台订单号")
    private String orderNo;

}
