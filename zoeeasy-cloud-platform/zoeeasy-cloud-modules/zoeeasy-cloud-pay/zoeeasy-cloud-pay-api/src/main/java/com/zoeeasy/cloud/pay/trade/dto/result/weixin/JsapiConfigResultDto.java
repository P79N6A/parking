package com.zoeeasy.cloud.pay.trade.dto.result.weixin;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取微信公众号必要参数
 *
 * @author zwq
 * @date 2018-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "JsapiConfigResultDto", description = "获取微信公众号必要参数")
public class JsapiConfigResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String appId;
}
