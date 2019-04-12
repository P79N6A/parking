package com.zoeeasy.cloud.sys.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页获取参数列表
 *
 * @author zwq
 */
@ApiModel(value = "ParamPageListGetRequestDto", description = "分页获取参数列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamListGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 参数类型
     */
    @ApiModelProperty(value = "参数类型")
    private Integer type;

}
