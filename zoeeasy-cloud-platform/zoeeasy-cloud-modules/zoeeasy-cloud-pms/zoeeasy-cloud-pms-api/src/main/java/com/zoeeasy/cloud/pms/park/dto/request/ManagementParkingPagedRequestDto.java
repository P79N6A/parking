package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询已审核通过停车场请求参数
 */
@Data
@ApiModel(value = "ManagementParkingPagedRequestDto", description = "分页查询已审核通过停车场请求参数")
@EqualsAndHashCode(callSuper = false)
public class ManagementParkingPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域code
     */
    @ApiModelProperty("区域code")
    private String areaCode;

    /**
     * 停车场类型
     */
    @ApiModelProperty("停车场类型")
    private String lotType;

    /**
     * 上下架状态
     */
    @ApiModelProperty("上下架状态")
    private Integer runStatus;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    private String tenantName;

}
