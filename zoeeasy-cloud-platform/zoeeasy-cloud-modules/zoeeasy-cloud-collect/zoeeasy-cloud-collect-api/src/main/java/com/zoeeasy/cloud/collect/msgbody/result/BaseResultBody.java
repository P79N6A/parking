package com.zoeeasy.cloud.collect.msgbody.result;

import com.zoeeasy.cloud.collect.msgbody.request.BaseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019/3/1
 * @author: wf
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BaseResultBody", description = "返回的公共结果")
public class BaseResultBody extends BaseBody {

    /**
     * 接口名称
     */
    @ApiModelProperty("接口名称")
    private String service;

    /**
     * 返回代码
     */
    @ApiModelProperty("返回代码")
    private Integer code;

    /**
     * 返回描述
     */
    @ApiModelProperty("返回描述")
    private String message;

}
