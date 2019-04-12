package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注销用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserCloseRequestDto", description = "注销用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCloseRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}