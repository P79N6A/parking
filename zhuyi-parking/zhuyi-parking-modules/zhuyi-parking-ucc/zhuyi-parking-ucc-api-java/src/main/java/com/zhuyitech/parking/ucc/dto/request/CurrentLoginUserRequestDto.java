package com.zhuyitech.parking.ucc.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录用户信息获取请求参数
 *
 * @author walkman
 */
@ApiModel(value = "CurrentLoginUserRequestDto", description = "登录用户信息获取请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentLoginUserRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}