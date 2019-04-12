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
 * 添加区域请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaAddRequestDto", description = "添加区域请求参数")
public class AreaAddRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("上级代码")
    @NotBlank(message = AreaConstant.AREA_PARENT_CODE_NOT_NULL)
    @Pattern(regexp = AreaConstant.AREA_CODE_PATTERN,
            message = AreaConstant.AREA_CODE_ILLEGAL)
    private String parentCode;

    /**
     * 地区代码
     */
    @ApiModelProperty("区域代码")
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
     * 层级
     */
    @ApiModelProperty("层级")
    @NotNull(message = AreaConstant.AREA_LEVEL_NOT_NULL)
    @FluentValidate({AreaLevelValidator.class})
    private Integer level;

    /**
     *
     */
    @ApiModelProperty("排序")
    private Integer order;

}
