package com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 推送车位状态数据请求参数
 *
 * @Date: 2018/8/10
 * @author: wh
 */
@ApiModel(value = "InMotionParkStatusRequestDto", description = "推送车位状态数据请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class InMotionParkStatusRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 指令编码
     */
    @ApiModelProperty(value = "指令编码")
    private String cmd;

    /**
     * 主体
     */
    @ApiModelProperty(value = "主体")
    private InMotionParkStatusBodyRequestDto body;
}
