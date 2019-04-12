package com.zoeeasy.cloud.open.client.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("ApiClientGetByAccessKeyRequestDto")
public class ApiClientGetByAccessKeyRequestDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * accessKey
     */
    @ApiModelProperty(value = "accessKey", required = true)
    @NotNull(message = "accessKey不能为空")
    private String accessKey;
}
