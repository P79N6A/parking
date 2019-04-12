package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 校验支付码是否存在请求参数
 * @Autor: AkeemSuper
 * @Date: 2018/2/26 0011
 */
@ApiModel(value = "UserTradePasswordExistRequestDto", description = "校验支付码是否存在请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTradePasswordExistRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
