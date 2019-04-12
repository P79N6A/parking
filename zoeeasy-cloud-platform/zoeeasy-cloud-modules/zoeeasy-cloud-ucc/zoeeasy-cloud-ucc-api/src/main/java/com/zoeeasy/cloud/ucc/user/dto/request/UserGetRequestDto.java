package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserGetRequestDto", description = "获取用户请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
