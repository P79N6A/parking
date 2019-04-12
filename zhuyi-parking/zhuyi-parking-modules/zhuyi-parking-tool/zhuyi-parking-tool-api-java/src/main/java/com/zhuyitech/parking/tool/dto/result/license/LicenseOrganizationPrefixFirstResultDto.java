package com.zhuyitech.parking.tool.dto.result.license;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;

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
@ApiModel(value = "LicenseOrganizationPrefixFirstResultDto", description = "车牌前缀首字母模型")
public class LicenseOrganizationPrefixFirstResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

}
