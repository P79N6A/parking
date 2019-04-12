package com.zoeeasy.cloud.integration.open.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 任意平台客戶端创建订单请求参数
 *
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AnyParkingOrderPlaceRequestDto", description = "任意平台创建订单请求参数")
public class AnyParkingOrderPlaceRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id")
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 卡号
     */
    @ApiModelProperty(value = "卡号")
    private String cardNo;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型", required = true)
    @NotNull(message = "车辆类型不能为空")
    private Integer carType;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    /**
     * 云平台订单号
     */
    @ApiModelProperty(value = "云平台订单号")
    private String orderNo;

    /**
     * 停车记录号
     */
    @ApiModelProperty(value = "停车记录号")
    private String recordNo;

}
