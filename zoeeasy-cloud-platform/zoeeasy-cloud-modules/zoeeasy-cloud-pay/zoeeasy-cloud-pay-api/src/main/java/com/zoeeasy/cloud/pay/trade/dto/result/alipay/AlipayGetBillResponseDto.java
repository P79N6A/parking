package com.zoeeasy.cloud.pay.trade.dto.result.alipay;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝获取对账单响应参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayGetBillResponseDto", description = "支付宝获取对账单响应参数")
public class AlipayGetBillResponseDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 网关返回码
     */
    @ApiModelProperty(value = "code", required = true)
    private String code;

    /**
     * 网关返回码描述
     */
    @ApiModelProperty(value = "msg", required = true)
    private String msg;

    /**
     * 业务返回码
     */
    @ApiModelProperty(value = "subCode")
    private String subCode;

    /**
     * 业务返回码描述
     */
    @ApiModelProperty(value = "subMsg")
    private String subMsg;

    /**
     * 账单下载地址链接
     */
    @ApiModelProperty(value = "billDownloadUrl", required = true)
    private String billDownloadUrl;
}
