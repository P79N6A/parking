package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车图像结果模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingImageViewResultDto", description = "停车图像结果模型")
public class ParkingImageViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

}
