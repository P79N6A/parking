package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据状态查询车位参数
 *
 * @Date: 2018/10/10
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotListGetRequestDto", description = "车位列表请求参数表")
public class ParkingLotInfoByStatus extends SignedSessionEntityDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车位状态
     */
    @ApiModelProperty(value = "车位状态")
    public Integer status;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    public Long parkingId;
}
