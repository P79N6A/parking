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
@ApiModel(value = "BizRequestBody", description = "业务查询请求参数")
public class BizRequestBody {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端业务请求的Url
     */
    @ApiModelProperty("客户端业务请求的Url")
    private String bizUrl;

    /**
     * 客户端业务请求的内容
     */
    @ApiModelProperty("客户端业务请求的内容")
    private String bizContent;

}
