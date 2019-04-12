package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 当前登录用户积分获取参数
 *
 * @author walkman
 */
@ApiModel(value = "UserPacketPointGetRequestDto", description = "当前登录用户积分获取参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPacketPointGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}