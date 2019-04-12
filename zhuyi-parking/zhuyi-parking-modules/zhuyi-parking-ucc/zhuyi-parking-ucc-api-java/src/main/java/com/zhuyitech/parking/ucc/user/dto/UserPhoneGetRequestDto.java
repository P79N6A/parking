package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取用户手机号
 *
 * @author zwq
 */
@ApiModel(description = "获取用户手机号")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPhoneGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
