package com.zoeeasy.cloud.collect.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询第三方账单价格，结果回传处理RequestDto
 *
 * @author Inmier
 * @date 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentNotifyCallBackRequestDto", description = "查询第三方账单价格，结果回传处理RequestDto")
public class QueryPriceCallBackRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场订单流水号
     */
    @ApiModelProperty("本地停车场订单流水号")
    private String parkingOrderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 进场时间
     */
    @ApiModelProperty("进场时间")
    private String passInTime;

    /**
     * 停车时长(分钟)
     */
    @ApiModelProperty("停车时长(分钟)")
    private Integer duration;

    /**
     * 应收金额
     */
    @ApiModelProperty("应收金额")
    private String price;

    /**
     * 免费离场时间
     */
    @ApiModelProperty("免费离场时间")
    private Integer freeOutTime;

    /**
     * 云平台订单号
     */
    @ApiModelProperty("云平台订单号")
    private String orderNo;

}
