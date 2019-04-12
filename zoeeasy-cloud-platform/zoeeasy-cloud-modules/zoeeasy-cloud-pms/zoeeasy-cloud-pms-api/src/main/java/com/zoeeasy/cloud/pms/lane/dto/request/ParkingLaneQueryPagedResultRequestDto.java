package com.zoeeasy.cloud.pms.lane.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车道列表分页请求参数表
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneQueryPagedResultRequestDto", description = "车道列表分页请求参数表")
public class ParkingLaneQueryPagedResultRequestDto extends SignedSessionPagedRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @ApiModelProperty(value = "区域code")
    private String areaCode;

    /**
     * 车道名称
     */
    @ApiModelProperty("车道名称")
    private String name;

    /**
     * 车道编号
     */
    @ApiModelProperty("车道编号")
    private String code;

    /**
     * 车道方向：，1-入车道，2-出车道 3 出入车道
     */
    @ApiModelProperty("车道类型")
    private Integer direction;

    /**
     * 停车场类型
     */
    @ApiModelProperty("lotType")
    private String lotType;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

}
