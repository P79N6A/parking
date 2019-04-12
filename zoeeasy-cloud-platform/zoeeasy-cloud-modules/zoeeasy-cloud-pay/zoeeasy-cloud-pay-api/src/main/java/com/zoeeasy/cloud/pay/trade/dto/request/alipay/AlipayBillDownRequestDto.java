package com.zoeeasy.cloud.pay.trade.dto.request.alipay;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 支付宝对账单下载请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayBillDownRequestDto", description = "支付宝对账单下载请求参数")
public class AlipayBillDownRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 账单类型(trade,signcustomer)
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
     */
    @ApiModelProperty(value = "billType")
    private String billType;

    /**
     * 账单日期：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
     */
    @ApiModelProperty(value = "billDate")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date billDate;
}
