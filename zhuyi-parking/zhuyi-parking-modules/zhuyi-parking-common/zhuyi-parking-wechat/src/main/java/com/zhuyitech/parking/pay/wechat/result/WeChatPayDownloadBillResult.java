package com.zhuyitech.parking.pay.wechat.result;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.List;

/**
 * <pre>
 *     微信对账单返回结果
 * </pre>
 *
 * @author walkman
 * @date 2017-07-20-16:23
 */
public class WeChatPayDownloadBillResult extends BaseDto {

    /**
     * 对账返回对象列表
     */
    private List<WeChatPayBaseBillResult> weChatPayBaseBillResults;

    /**
     * 总交易单数
     */
    private String totalRecord;

    /**
     * 总交易额
     */
    private String totalFee;

    /**
     * 总退款金额
     */
    private String totalRefundFee;

    /**
     * 总代金券或立减优惠退款金额
     */
    private String totalCouponFee;

    /**
     * 手续费总金额
     */
    private String totalPoundageFee;

    public List<WeChatPayBaseBillResult> getWeChatPayBaseBillResults() {
        return weChatPayBaseBillResults;
    }

    public void setWeChatPayBaseBillResults(List<WeChatPayBaseBillResult> weChatPayBaseBillResults) {
        this.weChatPayBaseBillResults = weChatPayBaseBillResults;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTotalRefundFee() {
        return totalRefundFee;
    }

    public void setTotalRefundFee(String totalRefundFee) {
        this.totalRefundFee = totalRefundFee;
    }

    public String getTotalCouponFee() {
        return totalCouponFee;
    }

    public void setTotalCouponFee(String totalCouponFee) {
        this.totalCouponFee = totalCouponFee;
    }

    public String getTotalPoundageFee() {
        return totalPoundageFee;
    }

    public void setTotalPoundageFee(String totalPoundageFee) {
        this.totalPoundageFee = totalPoundageFee;
    }
}
