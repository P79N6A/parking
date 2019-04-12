package com.zhuyitech.parking.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *     微信对账单
 * </pre>
 *
 * @author walkman
 * @date 2017-07-20-16:18
 */
@XStreamAlias("xml")
public class WeChatDownloadBillBean extends WeChatPayBaseBean {

    /**
     * 对账单日期
     */
    @XStreamAlias("bill_date")
    private String billDate;

    /**
     * 账单类型
     */
    @XStreamAlias("bill_type")
    private String billType;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
