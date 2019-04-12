package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取微信支付记录请求参数
 *
 * @Date: 2018/09/12
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeiXinPayRecordGetRequestDto", description = "获取微信支付记录请求参数")
public class WeiXinPayRecordGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
