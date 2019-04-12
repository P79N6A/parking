package com.zhuyitech.parking.ucc.dto.request.realname;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取当前用户实名认证请求参数
 *
 * @author walkman
 * @date 2018-01-10
 */
@ApiModel(value = "CurrentUserAuthApplyGetRequestDto", description = "获取当前用户实名认证请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentUserAuthApplyGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}