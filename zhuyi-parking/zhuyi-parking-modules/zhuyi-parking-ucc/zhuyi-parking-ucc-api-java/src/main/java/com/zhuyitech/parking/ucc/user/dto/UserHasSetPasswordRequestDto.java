package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 是否设置登录密码
 *
 * @author walkman
 */
@ApiModel(value = "HasSetPasswordRequestDto", description = "是否设置密码")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasSetPasswordRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
