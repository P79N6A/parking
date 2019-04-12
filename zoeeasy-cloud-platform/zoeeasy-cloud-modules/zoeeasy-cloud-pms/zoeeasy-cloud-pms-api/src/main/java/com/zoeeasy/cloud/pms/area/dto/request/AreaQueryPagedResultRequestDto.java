package com.zoeeasy.cloud.pms.area.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 分页获取区域请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaQueryPageRequestDto", description = "分页获取区域请求参数")
public class AreaQueryPagedResultRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 上级区域代码
     */
    @ApiModelProperty("上级区域代码")
    @NotBlank(message = AreaConstant.AREA_PARENT_CODE_NOT_NULL)
    @Pattern(regexp = AreaConstant.AREA_PARENT_CODE_PATTERN,
            message = AreaConstant.AREA_CODE_ILLEGAL)
    private String parentCode;

}

