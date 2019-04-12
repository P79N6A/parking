package com.zhuyitech.parking.pms.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取车牌前缀请求参数
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixGetRequestDto", description = "获取车牌前缀请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}