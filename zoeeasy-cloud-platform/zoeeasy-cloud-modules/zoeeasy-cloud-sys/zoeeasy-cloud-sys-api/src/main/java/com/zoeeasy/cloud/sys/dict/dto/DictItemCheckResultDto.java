package com.zoeeasy.cloud.sys.dict.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项返回结果
 *
 * @date: 2019/02/26.
 * @author：zm
 */
@ApiModel(value = "DictItemCheckResultDto", description = "字典项返回结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictItemCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态是否可用")
    private Integer status;

    @ApiModelProperty("提示信息")
    private String message;

}
