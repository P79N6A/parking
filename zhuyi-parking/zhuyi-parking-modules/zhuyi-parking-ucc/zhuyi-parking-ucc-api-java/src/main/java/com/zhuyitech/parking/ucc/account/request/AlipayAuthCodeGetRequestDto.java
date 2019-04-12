package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@ApiModel(value = "AlipayAuthCodeGetRequestDto", description = "支付宝获取accessToken请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthCodeGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

}