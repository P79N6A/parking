package com.zoeeasy.cloud.sys.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.sys.parameter.cst.ParamConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * 修改参数
 *
 * @author zwq
 */
@ApiModel(value = "ParamUpdateRequestDto", description = "修改参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 参数秘钥
     */
    @ApiModelProperty(value = "参数秘钥")
    @Length(min = ParamConstant.MIN, max = ParamConstant.MAX, message = ParamConstant.PARAM_KEY_ILLEGAL)
    @Pattern(regexp = ParamConstant.CODE_REGEXP, message = ParamConstant.PARAM_KEY_ILLEGAL)
    private String paramKey;

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    private String paramValue;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(max = ParamConstant.MAX, message = ParamConstant.PARAM_REMARK_ILLEGAL)
    @Pattern(regexp = ParamConstant.CODE_REGEXP, message = ParamConstant.PARAM_REMARK_ILLEGAL)
    private String remark;

    /**
     * 参数类型
     */
    @ApiModelProperty(value = "参数类型")
    private Integer type;
}
