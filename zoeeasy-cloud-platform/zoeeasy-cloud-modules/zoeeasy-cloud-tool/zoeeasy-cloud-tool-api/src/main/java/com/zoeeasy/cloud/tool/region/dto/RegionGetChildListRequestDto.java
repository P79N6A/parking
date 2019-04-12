package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.tool.region.cst.RegionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 获取子区域列表Dto
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionGetChildListRequestDto", description = "获取子区域列表Dto")
public class RegionGetChildListRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("父区域编码")
    @NotBlank(message = RegionConstant.REGION_PARENT_CODE_NOT_NULL)
    @Pattern(regexp = RegionConstant.REGION_CODE_PATTERN,
            message = RegionConstant.REGION_CODE_ILLEGAL)
    private String parentCode;

    @ApiModelProperty("父区域层级")
    @NotNull(message = RegionConstant.REGION_PARENT_LEVEL_NOT_NULL)
    private Integer level;
}
