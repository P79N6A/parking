package com.zhuyitech.parking.ucc.dto.request.driverlicense;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户是否添加驾驶证请求参数
 *
 * @author walkamn
 * @Date: 2018/1/15
 */
@ApiModel(value = "UserDriverLicenseExistRequestDto", description = "用户是否添加驾驶证请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDriverLicenseExistRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
