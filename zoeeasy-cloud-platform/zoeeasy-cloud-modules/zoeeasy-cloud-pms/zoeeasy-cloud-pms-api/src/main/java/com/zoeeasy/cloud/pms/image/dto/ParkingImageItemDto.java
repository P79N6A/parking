package com.zoeeasy.cloud.pms.image.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车过车图像保存请求参数
 *
 * @author walkman
 */
@ApiModel(value = "", description = "")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingImageItemDto extends BaseDto {

    /**
     * 图像Uuid
     */
    @ApiModelProperty
    private String uuid;

    /**
     * 图像Uuid
     */
    private String url;

    /**
     * 图像Uuid
     */
    private String type;
}
