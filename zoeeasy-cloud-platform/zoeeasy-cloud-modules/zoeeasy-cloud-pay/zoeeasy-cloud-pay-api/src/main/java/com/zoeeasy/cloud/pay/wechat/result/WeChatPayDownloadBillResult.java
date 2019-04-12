package com.zoeeasy.cloud.pay.wechat.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <pre>
 *     微信对账单返回结果
 * </pre>
 *
 * @author walkman
 * @date 2017-07-20-16:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
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
}
