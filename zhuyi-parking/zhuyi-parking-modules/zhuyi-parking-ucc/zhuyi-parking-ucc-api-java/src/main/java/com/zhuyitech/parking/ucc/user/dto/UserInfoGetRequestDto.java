package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/7/11 0011
 */
@ApiModel(value = "UserInfoGetRequestDto", description = "获取用户信息的请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}
