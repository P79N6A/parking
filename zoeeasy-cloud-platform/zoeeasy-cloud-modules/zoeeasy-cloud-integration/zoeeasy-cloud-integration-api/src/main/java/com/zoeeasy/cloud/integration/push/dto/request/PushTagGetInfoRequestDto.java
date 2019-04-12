package com.zoeeasy.cloud.integration.push.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获去用户tag的请求参数
 *
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@ApiModel(value = "PushTagGetInfoRequestDto", description = "获去用户tag的请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class PushTagGetInfoRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
}
