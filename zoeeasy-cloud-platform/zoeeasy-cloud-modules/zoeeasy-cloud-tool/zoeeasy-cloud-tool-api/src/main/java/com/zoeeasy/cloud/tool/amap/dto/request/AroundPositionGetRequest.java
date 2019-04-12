package com.zoeeasy.cloud.tool.amap.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 逆地理获取adcode请求参数
 *
 * @author zwq
 * @date 2018/4/12
 */
@Data
@ApiModel(value = "AroundPositionGetRequest", description = "逆地理获取adcode请求参数")
@EqualsAndHashCode(callSuper = false)
public class AroundPositionGetRequest extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @ApiModelProperty(value = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private Double longitude;

    @ApiModelProperty(value = "距离半径", required = true)
    @NotNull(message = "距离半径不能为空")
    private Double maxDistance;

}