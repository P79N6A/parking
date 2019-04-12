package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 是否绑定支付宝
 *
 * @author walkman
 */
@ApiModel(value = "UserHasBindAlipayRequestDto", description = "是否绑定支付宝")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasBindAlipayRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
