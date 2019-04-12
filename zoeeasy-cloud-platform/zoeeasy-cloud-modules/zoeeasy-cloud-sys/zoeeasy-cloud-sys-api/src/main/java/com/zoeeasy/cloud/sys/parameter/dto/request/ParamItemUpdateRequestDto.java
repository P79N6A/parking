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
 * @date 2019/2/23 0023
 */
@ApiModel(value = "ParamItemUpdateRequestDto", description = "根据参数KEY更新参数项")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamItemUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 参数键
     */
    @ApiModelProperty(value = "参数键", required = true)
    @NotBlank(message = ParamConstant.PARAM_KEY_NOT_EMPTY)
    private String paramKey;

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值", required = true)
    @NotBlank(message = ParamConstant.PARAM_VALUE_NOT_EMPTY)
    private String paramValue;

    /**
     * 参数备注
     */
    @ApiModelProperty("参数备注")
    private String remark;
}
