package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车位列表分页请求参数表
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotQueryPagedResultRequestDto", description = "车位列表分页请求参数表")
public class ParkingLotQueryPagedResultRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域code
     */
    @ApiModelProperty("区域code")
    private String areaCode;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String code;

    /**
     * 泊位号
     */
    @ApiModelProperty("泊位号")
    private String number;

}