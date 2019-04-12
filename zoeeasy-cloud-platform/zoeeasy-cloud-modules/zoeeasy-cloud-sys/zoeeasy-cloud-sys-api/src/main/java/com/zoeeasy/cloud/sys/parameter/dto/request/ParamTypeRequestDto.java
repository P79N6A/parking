package com.zoeeasy.cloud.sys.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取参数菜单列表
 *
 * @author zwq
 */
@ApiModel(value = "ParamTypeRequestDto", description = "获取参数类型列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamTypeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

}
