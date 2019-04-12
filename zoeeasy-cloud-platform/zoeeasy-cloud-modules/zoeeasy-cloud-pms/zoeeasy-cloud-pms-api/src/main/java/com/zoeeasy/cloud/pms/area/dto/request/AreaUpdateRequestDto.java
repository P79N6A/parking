package com.zoeeasy.cloud.pms.area.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import com.zoeeasy.cloud.pms.area.validator.AreaLevelValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 修改区域请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaUpdateRequestDto", description = "修改区域请求参数")
public class AreaUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域代码
     */
    @ApiModelProperty("区域代码")
    @NotBlank(message = AreaConstant.AREA_CODE_NOT_NULL)
    @Pattern(regexp = AreaConstant.AREA_CODE_PATTERN,
            message = AreaConstant.AREA_CODE_ILLEGAL)
    private String code;

    /**
     * 地区名称
     */
    @ApiModelProperty("区域名称")
    @NotBlank(message = AreaConstant.AREA_NAME_NOT_NULL)
    @Length(min = AreaConstant.AREA_NAME_MIN_LENGTH,
            max = AreaConstant.AREA_NAME_MAX_LENGTH,
            message = AreaConstant.AREA_NAME_LENGTH_RANGE)
    @Pattern(regexp = AreaConstant.AREA_NAME_PATTERN,
            message = AreaConstant.AREA_NAME_ILLEGAL)
    private String name;

    /**
     * 区域层级
     */
    @ApiModelProperty("区域层级")
    @NotNull(message = AreaConstant.AREA_LEVEL_NOT_NULL)
    @FluentValidate({AreaLevelValidator.class})
    private Integer level;

}
