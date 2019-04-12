package com.zoeeasy.cloud.sys.parameter.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取参数类型返回参数
 *
 * @author walkman
 */
@ApiModel(value = "ParamTypeResultDto", description = "获取参数类型返回参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamTypeResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 参数编码
     */
    @ApiModelProperty(value = "参数编码")
    private String paramCode;

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String paramName;

    /**
     * 父参数编码
     */
    @ApiModelProperty(value = "父参数编码")
    private String parentCode;

    /**
     * 子参数类型
     */
    @ApiModelProperty(value = "子参数类型")
    private List<ParamTypeResultDto> child;
}