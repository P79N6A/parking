package com.zoeeasy.cloud.pms.garage.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场列表分页请求参数
 * Created by song on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarageQueryPagedRequestDto", description = "车库列表分页请求参数")
public class GarageQueryPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域code
     */
    @ApiModelProperty("区域code")
    private String areaCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 车库名称
     */
    @ApiModelProperty("车库名称")
    private String name;

    /**
     * 车库编号
     */
    @ApiModelProperty("code")
    private String code;

}
