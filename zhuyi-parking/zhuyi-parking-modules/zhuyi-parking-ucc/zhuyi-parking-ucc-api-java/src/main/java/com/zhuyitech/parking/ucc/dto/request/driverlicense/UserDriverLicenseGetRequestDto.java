package com.zhuyitech.parking.ucc.dto.request.driverlicense;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户驾驶证请求参数
 *
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
@ApiModel(value = "UserDriverLicenseGetRequestDto", description = "获取用户驾驶证请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDriverLicenseGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
