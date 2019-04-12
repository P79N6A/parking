package com.zoeeasy.cloud.tool.license.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车牌前缀视图请求参数表
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LicensePrefixViewGetRequestDto", description = "车牌前缀视图请求参数表")
public class LicensePrefixViewGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", notes = "名称")
    private String name;

}
