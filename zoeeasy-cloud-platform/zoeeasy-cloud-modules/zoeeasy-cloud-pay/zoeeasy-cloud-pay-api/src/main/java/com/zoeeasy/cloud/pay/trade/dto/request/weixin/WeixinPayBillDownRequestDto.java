package com.zoeeasy.cloud.pay.trade.dto.request.weixin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 微信支付对账单下载请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeixinPayBillDownRequestDto", description = "支付宝对账单下载请求参数")
public class WeixinPayBillDownRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 账单类型(trade,signcustomer)
     */
    @ApiModelProperty(value = "billType")
    private String billType;

    /**
     * 账单日期
     */
    @ApiModelProperty(value = "billDate")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date billDate;
}
