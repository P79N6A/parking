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
@ApiModel(value = "HeartBeatRequestBody", description = "心跳请求参数")
public class HeartBeatBody extends BaseBody {

    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场Code
     */
    @ApiModelProperty("本地停车场Code")
    private String localCode;

    /**
     * 当前时间
     */
    @ApiModelProperty("当前时间")
    private String time;
}
