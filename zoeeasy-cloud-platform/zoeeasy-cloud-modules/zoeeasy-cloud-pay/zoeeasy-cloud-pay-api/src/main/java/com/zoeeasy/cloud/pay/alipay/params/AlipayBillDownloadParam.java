package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 支付宝账单下载参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayBillDownloadParam extends AlipayPayBaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单类型(trade,signcustomer)
     */
    private String billType;

    /**
     * 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
     */
    private String billDate;
}
