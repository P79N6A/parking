package com.zoeeasy.cloud.sys.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.sys.parameter.cst.ParamConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2019/2/23 0023
 */
@ApiModel(value = "ParamItemGetByIdRequestDto", description = "根据参数项Id获取参数项")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamItemGetByIdRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数项Id", required = true)
    @NotNull(message = ParamConstant.PARAM_ITEM_ID_NOT_EMPTY)
    private Long id;
}
