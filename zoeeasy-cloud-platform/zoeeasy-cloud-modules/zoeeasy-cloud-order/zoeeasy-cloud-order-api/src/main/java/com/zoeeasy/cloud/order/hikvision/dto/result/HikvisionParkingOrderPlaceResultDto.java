package com.zoeeasy.cloud.order.hikvision.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 海康平台停车账单下单请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HikvisionParkingOrderPlaceRequestDto", description = "海康平台停车账单请求参数")
public class HikvisionParkingOrderPlaceResultDto extends BaseDto implements Serializable {

    /**
     * 海康平台账单编号
     */
    @ApiModelProperty(value = "海康平台账单编号")
    private String hikBillNo;

    /**
     * 结算时间
     */
    private Date costTime;

    /**
     * 停车时长(分钟)
     */
    private Integer parkingLength;

    /**
     * 总收费金额(分)
     */
    private Integer totalCost;

}
