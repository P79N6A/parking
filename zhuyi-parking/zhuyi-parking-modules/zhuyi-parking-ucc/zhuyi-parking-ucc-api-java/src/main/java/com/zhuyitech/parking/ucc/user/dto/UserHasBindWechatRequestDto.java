package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 是否绑定微信
 *
 * @author walkman
 */
@ApiModel(value = "UserHasBindWechatRequestDto", description = "是否绑定微信")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasBindWechatRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
