package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询泊位配置相关请求参数
 *
 * @Date: 2018/10/16
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorByParkingLotQueryPageRequestDto", description = "新增设备请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorByParkingLotQueryPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号")
    private String areaCode;

    /**
     * 停车场
     */
    @ApiModelProperty(value = "停车场Id")
    private Long parkingId;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String code;

    /**
     * 泊位号
     */
    @ApiModelProperty(value = "泊位号")
    private String number;
}
