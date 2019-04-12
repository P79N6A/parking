package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车位列表请求参数表
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotListGetRequestDto", description = "车位列表请求参数表")
public class ParkingLotListGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long id;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String number;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String name;

}