package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除设备请求参数
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorDeleteRequestDto", description = "删除设备请求参数")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorDeleteRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;
}
