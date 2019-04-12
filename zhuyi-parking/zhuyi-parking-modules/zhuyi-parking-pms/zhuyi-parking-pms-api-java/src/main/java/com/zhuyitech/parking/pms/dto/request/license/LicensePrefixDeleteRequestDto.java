package com.zhuyitech.parking.pms.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 删除车牌前缀请求参数
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixDeleteRequestDto", description = "删除车牌前缀请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixDeleteRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}