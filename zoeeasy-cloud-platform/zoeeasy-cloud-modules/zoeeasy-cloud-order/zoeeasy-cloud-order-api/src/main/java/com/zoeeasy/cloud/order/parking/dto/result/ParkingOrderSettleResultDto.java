package com.zoeeasy.cloud.order.parking.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车订单结算结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderSettleResultDto", description = "停车订单结算结果")
public class ParkingOrderSettleResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 收费时长
     */
    @ApiModelProperty("收费时长(秒)")
    private Long chargeDuration;

    /**
     * 免费时长
     */
    @ApiModelProperty("免费时长(秒)")
    private Long freeDuration;

    /**
     * 结算金额
     */
    @ApiModelProperty(value = "结算金额(分)")
    private Integer settleAmount;
}
