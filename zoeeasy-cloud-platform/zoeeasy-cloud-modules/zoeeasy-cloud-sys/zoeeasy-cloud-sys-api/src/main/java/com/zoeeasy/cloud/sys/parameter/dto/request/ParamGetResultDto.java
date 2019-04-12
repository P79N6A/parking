package com.zoeeasy.cloud.sys.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取参数列表返回参数
 *
 * @author walkman
 */
@ApiModel(value = "ParamGetResultDto", description = "获取参数列表返回参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamGetResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    private String paramValue;
}