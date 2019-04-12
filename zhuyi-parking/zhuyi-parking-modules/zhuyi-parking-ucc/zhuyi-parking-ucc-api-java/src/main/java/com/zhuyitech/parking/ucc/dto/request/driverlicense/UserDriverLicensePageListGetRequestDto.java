package com.zhuyitech.parking.ucc.dto.request.driverlicense;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取已认证驾驶证列表请求参数
 *
 * @Date: 2018/4/14
 * @author: yuzhicheng
 */
@ApiModel(value = "UserDriverLicenseAlreadyBoundPageListGetRequestDto", description = "获取已认证驾驶证列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDriverLicensePageListGetRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

}
