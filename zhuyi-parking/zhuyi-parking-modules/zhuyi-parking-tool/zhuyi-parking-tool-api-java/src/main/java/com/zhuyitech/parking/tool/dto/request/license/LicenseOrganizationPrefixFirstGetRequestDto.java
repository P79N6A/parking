package com.zhuyitech.parking.tool.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LicenseOrganizationPrefixFirstGetRequestDto", description = "车牌前缀首字母请求参数表")
public class LicenseOrganizationPrefixFirstGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID", required = true)
    @NotNull(message = "父ID不能为空")
    private Long parentId;

}
