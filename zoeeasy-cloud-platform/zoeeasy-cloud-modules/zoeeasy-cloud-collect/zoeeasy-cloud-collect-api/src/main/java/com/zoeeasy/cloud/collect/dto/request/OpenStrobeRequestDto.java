package com.zoeeasy.cloud.collect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.RequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @Date: 2019-03-01
 * @author: wf
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OpenStrobeRequestDto", description = "请求远程开闸请求参数")
public class OpenStrobeRequestDto extends RequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场Code
     */
    @ApiModelProperty("本地停车场Code")
    private String localCode;

    /**
     * 本地停车场通道Code
     */
    @ApiModelProperty("本地停车场通道Code")
    private String gateCode;
}
