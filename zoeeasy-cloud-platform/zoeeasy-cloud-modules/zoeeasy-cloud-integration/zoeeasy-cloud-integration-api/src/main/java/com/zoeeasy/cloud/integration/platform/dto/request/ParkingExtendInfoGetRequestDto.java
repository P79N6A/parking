package com.zoeeasy.cloud.integration.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 获取停车场扩展信息请求参数
 *
 * @author walkman
 */
@ApiModel(value = "ParkingExtendInfoGetRequestDto", description = "获取停车场扩展信息请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingExtendInfoGetRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场ID不能为空")
    private Long id;

}