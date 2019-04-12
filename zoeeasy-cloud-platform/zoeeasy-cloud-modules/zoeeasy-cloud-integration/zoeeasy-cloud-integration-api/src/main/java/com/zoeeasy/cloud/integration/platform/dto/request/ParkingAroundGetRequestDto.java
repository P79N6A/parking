package com.zoeeasy.cloud.integration.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 获取附近的停车场请求参数
 *
 * @author walkman
 * @since 2017-12-10
 */
@ApiModel(value = "ParkingAroundGetRequestDto", description = "获取附近的停车场请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingAroundGetRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private Double latitude;

}
