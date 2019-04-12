package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangfeng
 * @date 2018/12/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingInfoExistDto", description = "查询停车场信息是否存在")
public class ParkingInfoExistDto extends BaseDto {

    private static final long serialVersionUID = 1L;
    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String name;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

}
