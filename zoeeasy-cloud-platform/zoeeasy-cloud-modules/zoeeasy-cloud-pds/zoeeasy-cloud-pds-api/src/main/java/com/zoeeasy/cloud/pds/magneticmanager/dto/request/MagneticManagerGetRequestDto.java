package com.zoeeasy.cloud.pds.magneticmanager.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取设备管理器请求参数
 *
 * @Date: 2018/8/23
 * @author: wh
 */
@Data
@ApiModel(value = "MagneticManagerGetRequestDto", description = "获取设备管理器请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticManagerGetRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

}

