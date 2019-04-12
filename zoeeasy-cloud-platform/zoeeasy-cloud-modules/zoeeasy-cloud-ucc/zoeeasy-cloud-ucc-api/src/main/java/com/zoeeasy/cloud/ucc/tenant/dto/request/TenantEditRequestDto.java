package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantTypeValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户编辑请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantEditRequestDto", description = "租户编辑请求参数")
public class TenantEditRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户类型
     */
    @ApiModelProperty(value = "租户类型")
    @FluentValidate({TenantTypeValidator.class})
    private Integer type;

    /**
     * 租户URL
     */
    @ApiModelProperty(value = "url")
    private String url;

    /**
     * 租户域名
     */
    @ApiModelProperty(value = "租户域名")
    private String domain;

    /**
     * 租户LOGO
     */
    @ApiModelProperty(value = "租户LOGO")
    private String logo;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String description;

}
