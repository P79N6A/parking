package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 根据停车记录号车牌号找到停车账单
 *
 * @author walkman
 * @date 2018/10/8 0008
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderGetByRecordNoPlateRequestDto", description = "根据停车记录号车牌号找到停车账单")
public class ParkingOrderGetByRecordNoPlateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户Id
     */
    @ApiModelProperty(value = "租户Id", required = true)
    @NotNull(message = OrderConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;

    /**
     * 平台停车记录编号
     */
    @ApiModelProperty(value = "平台停车记录编号", required = true)
    @NotBlank(message = OrderConstant.RECORD_NO_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String recordNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotNull(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
//    @NotNull(message = OrderConstant.PLATE_COLOR_NOT_EMPTY)
    private Integer plateColor;

}