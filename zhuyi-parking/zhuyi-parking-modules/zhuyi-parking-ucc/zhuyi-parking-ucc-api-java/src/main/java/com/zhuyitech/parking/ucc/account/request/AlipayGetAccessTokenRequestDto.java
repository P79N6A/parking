package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝获取accessToken请求参数
 *
 * @author zwq
 */
@ApiModel(value = "AlipayGetAccessTokenRequestDto", description = "支付宝获取accessToken请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayGetAccessTokenRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * code
     */
    @ApiModelProperty(value = "code")
    private String code;

}
