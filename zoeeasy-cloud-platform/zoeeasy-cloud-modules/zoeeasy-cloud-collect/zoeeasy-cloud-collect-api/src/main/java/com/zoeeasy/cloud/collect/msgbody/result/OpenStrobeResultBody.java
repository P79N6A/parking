package com.zoeeasy.cloud.collect.msgbody.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019-03-03
 * @author: wf
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OpenStrobeResultBody", description = "远程开闸返回参数")
public class OpenStrobeResultBody extends BaseResultBody {

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
