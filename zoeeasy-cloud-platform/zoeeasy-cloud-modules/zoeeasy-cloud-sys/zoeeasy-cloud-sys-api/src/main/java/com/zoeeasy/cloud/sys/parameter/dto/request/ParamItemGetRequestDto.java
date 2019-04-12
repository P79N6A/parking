package com.zoeeasy.cloud.sys.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.sys.parameter.cst.ParamConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2019/2/22 0022
 */
@ApiModel(value = "ParamItemGetRequestDto", description = "根据参数类型获取参数项列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamItemGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数编码", required = true)
    @NotBlank(message = ParamConstant.PARAM_CODE_NOT_EMPTY)
    private String paramCode;
}
