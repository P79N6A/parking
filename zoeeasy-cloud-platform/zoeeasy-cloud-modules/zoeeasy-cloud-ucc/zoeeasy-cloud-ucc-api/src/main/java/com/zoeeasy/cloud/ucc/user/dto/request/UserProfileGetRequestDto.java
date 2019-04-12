package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;

/**
 * 当前登录用户基本信息获取参数
 *
 * @author walkman
 */
@ApiModel(value = "UserProfileGetRequestDto", description = "当前登录用户基本信息获取参数")
public class UserProfileGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
}