package com.zoeeasy.cloud.pms.parkingarea.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 泊车区域列表分页请求参数
 * Created by song on 2018/9/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAreaQueryPagedRequestDto", description = "泊车区域列表分页请求参数")
public class ParkingAreaQueryPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域code
     */
    @ApiModelProperty("区域code")
    private String areaCode;

    /**
     * 停车场类型
     */
    @ApiModelProperty("lotType")
    private String lotType;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 泊位区域编号
     */
    @ApiModelProperty("泊位区域编号")
    private String code;

    /**
     * 泊位区域名称
     */
    @ApiModelProperty("泊位区域名称")
    private String name;

}
