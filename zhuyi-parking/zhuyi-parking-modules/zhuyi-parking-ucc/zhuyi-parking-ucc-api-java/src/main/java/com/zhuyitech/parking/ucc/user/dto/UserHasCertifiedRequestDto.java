package com.zhuyitech.parking.ucc.user.dto;


import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 当前登录用户用户否已实名认证请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserHasCertifiedRequestDto", description = "用户否已实名认证请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasCertifiedRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}
