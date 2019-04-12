package com.zhuyitech.parking.integration.order.dto.result;

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
@ApiModel(value = "UserParkingImageViewResultDto", description = "停车图像结果模型")
public class UserParkingImageViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

}
