package com.zoeeasy.cloud.collect.msgbody.request;

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
@ApiModel(value = "BaseRequestBody", description = "请求的公共参数")
public class BaseBody {

    /**
     * 接口名称
     */
    @ApiModelProperty("接口名称")
    private String service;

}
