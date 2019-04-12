package com.zoeeasy.cloud.pay.trade.dto.result.weixin;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信异步通知处理结果
 *
 * @author zwq
 * @date 2018-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeixinAsyncNotifyResultDto", description = " 微信异步通知处理结果")
public class WeixinAsyncNotifyResultDto extends BaseDto {

    /**
     * return_code
     */
    private String returnCode;

    /**
     * return_msg
     */
    private String returnMsg;
}
