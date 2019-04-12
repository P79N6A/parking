package com.zoeeasy.cloud.pms.gate.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出入口列表分页请求参数表
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateQueryPagedResultRequestDto", description = "出入口列表分页请求参数表")
public class ParkingGateQueryPagedResultRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;
    /**
     * 区域ID
     */
    @ApiModelProperty(value = "区域code")
    private String areaCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;
    /**
     * 车库ID
     */

    @ApiModelProperty("出入口编号")
    private String code;

    /**
     * 出入口名称
     */
    @ApiModelProperty("出入口名称")
    private String name;

    /**
     * 出入口方向:
     * 1.入口
     * 2.出口
     * 3.出入口
     */
    @ApiModelProperty("出入口类型")
    private Integer direction;

}
