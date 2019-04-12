package com.zhuyitech.parking.tool.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LicenseOrganizationPrefixListGetRequestDto", description = "车牌前缀列表请求参数表")
public class LicenseOrganizationPrefixListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
