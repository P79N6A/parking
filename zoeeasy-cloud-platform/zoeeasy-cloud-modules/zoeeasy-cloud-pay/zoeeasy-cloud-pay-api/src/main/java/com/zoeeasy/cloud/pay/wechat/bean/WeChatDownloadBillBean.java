package com.zoeeasy.cloud.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     微信对账单
 * </pre>
 *
 * @author walkman
 * @date 2017-07-20-16:18
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
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

}
