package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户登出请求参数
 *
 * @author walkman
 */
@ApiModel(description = "用户登出请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLogoutRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
