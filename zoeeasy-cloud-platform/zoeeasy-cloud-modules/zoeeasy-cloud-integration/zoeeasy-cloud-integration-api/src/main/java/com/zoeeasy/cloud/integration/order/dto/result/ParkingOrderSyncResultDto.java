package com.zoeeasy.cloud.integration.order.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 第三方平台停车账单下单同步结果
 *
 * @author walkman
 * @since 2018-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ParkingOrderSyncResultDto", description = "第三方平台停车账单下单同步结果")
public class ParkingOrderSyncResultDto extends BaseDto {

    /**
     * 第三方平台账单编号
     */
    @ApiModelProperty(value = "第三方平台账单编号")
    private String thirdBillNo;

    /**
     * 第三方平台账单来源类型
     */
    @ApiModelProperty(value = "第三方平台账单来源类型")
    private Integer thirdBillSourceType;

    /**
     * 第三方平台账单来源类型
     */
    @ApiModelProperty(value = "第三方平台账单状态")
    private Integer thirdBillSyncStatus;

    /**
     * 结算时间
     */
    @ApiModelProperty(value = "结算时间")
    private Date costTime;

    /**
     * 停车时长(分钟)
     */
    @ApiModelProperty(value = "停车时长(分钟)")
    private Integer parkingLength;

    /**
     * 总收费金额(分)
     */
    @ApiModelProperty(value = "总收费金额(分)")
    private Integer totalCost;

}