package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户支付宝支付记录请求参数
 *
 * @Date: 2018/09/12
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AliPayRecordGetRequestDto", description = "获取用户支付宝支付记录请求参数")
public class AliPayOrderGetRequestDto extends BaseDto {

    public static final long serialVersionUID = 1L;

    private String outOrderNo;

    private String tradeNo;
}
