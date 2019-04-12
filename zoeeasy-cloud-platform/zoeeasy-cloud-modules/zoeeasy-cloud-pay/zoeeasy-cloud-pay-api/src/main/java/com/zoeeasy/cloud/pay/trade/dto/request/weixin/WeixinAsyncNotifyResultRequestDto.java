package com.zoeeasy.cloud.pay.trade.dto.request.weixin;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 微信异步通知参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeixinAsyncNotifyResultRequestDto", description = "微信异步通知参数")
public class WeixinAsyncNotifyResultRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * return_code
     */
    private String returnCode;

    /**
     * return_msg
     */
    private String returnMsg;

    /**
     * appid
     */
    private String appId;

    /**
     * mchId
     */
    private String mchId;

    /**
     * deviceInfo
     */
    private String deviceInfo;

    /**
     * nonceStr
     */
    private String nonceStr;

    /**
     * sign
     */
    private String sign;

    /**
     * result_code
     */
    private String resultCode;

    /**
     * err_code
     */
    private String errCode;

    /**
     * err_code_des
     */
    private String errCodeDes;

    /**
     * openid
     */
    private String openId;

    /**
     * is_subscribe
     */
    private String isSubscribe;

    /**
     * trade_type
     */
    private String tradeType;

    /**
     * bank_type
     */
    private String bankType;

    /**
     * total_fee
     */
    private Integer totalFee;

    /**
     * fee_type
     */
    private String feeType;

    /**
     * cash_fee
     */
    private Integer cashFee;

    /**
     * cash_fee_type
     */
    private String cashFeeType;

    /**
     * coupon_fee
     */
    private Integer couponFee;

    /**
     * coupon_count
     */
    private String couponCount;

    /**
     * transaction_id
     */
    private String transactionId;

    /**
     * out_trade_no
     */
    private String outTradeNo;

    /**
     * attach
     */
    private String attach;

    /**
     * time_start
     */
    private Date timeStart;

    /**
     * time_end
     */
    private Date timeEnd;

    /**
     * ip
     */
    private String ip;

    /**
     * url
     */
    private String url;

    /**
     * content
     */
    private String content;

    /**
     * weixinReturnXml
     */
    private String weixinReturnXml;

    /**
     * coupon_type
     */
    private String couponType;

    /**
     * sign_type
     */
    private String signType;

    /**
     * settlement_total_fee
     */
    private String settlementTotalFee;
}