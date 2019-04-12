package com.zhuyitech.parking.tool.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取车牌前缀请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LicenseOrganizationPrefixRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
