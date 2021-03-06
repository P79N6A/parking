package com.zoeeasy.cloud.pms.area.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 删除区域请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaDeleteRequestDto", description = "删除区域请求参数")
public class AreaDeleteRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地区代码
     */
    @ApiModelProperty("地区代码")
    @NotNull(message = AreaConstant.AREA_CODE_NOT_NULL)
    @Pattern(regexp = AreaConstant.AREA_CODE_PATTERN,
            message = AreaConstant.AREA_CODE_ILLEGAL)
    private String code;

}
