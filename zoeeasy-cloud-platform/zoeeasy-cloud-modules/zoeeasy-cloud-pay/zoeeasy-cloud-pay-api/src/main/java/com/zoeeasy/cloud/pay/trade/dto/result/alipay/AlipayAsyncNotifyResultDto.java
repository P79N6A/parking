package com.zoeeasy.cloud.pay.trade.dto.result.alipay;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝异步通知处理结果
 *
 * @author walkman
 * @date 2018-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayAsyncNotifyResultDto", description = " 支付宝异步通知处理结果")
public class AlipayAsyncNotifyResultDto extends BaseDto {

}
