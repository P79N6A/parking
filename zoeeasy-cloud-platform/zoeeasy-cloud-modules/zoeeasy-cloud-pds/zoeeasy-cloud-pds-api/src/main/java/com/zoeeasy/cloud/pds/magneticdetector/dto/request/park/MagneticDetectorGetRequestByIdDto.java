package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据ID获取设备请求参数
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorGetRequestByIdDto", description = "根据ID获取设备请求参数")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorGetRequestByIdDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

}
