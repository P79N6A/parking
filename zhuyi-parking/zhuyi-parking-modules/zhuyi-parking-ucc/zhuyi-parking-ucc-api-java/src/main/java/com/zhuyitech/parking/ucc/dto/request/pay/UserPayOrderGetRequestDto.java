package com.zhuyitech.parking.ucc.dto.request.pay;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户支付记录请求参数
 *
 * @Date: 2018/3/13
 * @author: yuzhicheng
 */
@ApiModel(value = "UserPayOrderGetRequestDto", description = "获取用户支付记录请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPayOrderGetRequestDto extends SessionEntityDto<Long> {

    public static final long serialVersionUID = 1L;
}
