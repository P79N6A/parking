package com.zoeeasy.cloud.order.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 海康平台账单同步请求参数
 *
 * @author walkman
 * @date 2018-08-08
 */
@ApiModel(value = "HikvisionParkingOrderPlaceRequestDto", description = "海康平台账单同步请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class HikvisionParkingOrderPlaceRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long tenantId;
    private Long parkingId;

    private String recordNo;

    @ApiModelProperty(value = "平台订单号")
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

}
