package com.zoeeasy.cloud.axino.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 发票信息列表
 *
 * @date: 2019/1/17.
 * @author：zm
 */
@Data
public class NnInvoiceListBodyBean implements Serializable {

    /**
     * 订单编号
     */
    @JsonProperty("c_orderno")
    private String cOrderNo;

    /**
     * 发票请求流水号
     */
    @JsonProperty("c_fpqqls")
    private String cFpqqls;

    /**
     * 开票状态：2 为开票并签章成功，其他
     * 状态分别为 20:未开票;21:提交服务器
     * 开票成功;22:提交服务器开票失败;24:
     * 签章失败;3 为发票已作废 31：发票作
     * 废中
     * 备注：22、24 状态时，无需再查询，
     * 请确认开票失败原因以及签章失败原
     * 因；3、31 只针对纸票
     */
    @JsonProperty("c_status")
    private String cStatus;

    /**
     * 开票信息，成功或者失败的信息
     */
    @JsonProperty("c_msg")
    private String cMsg;

    /**
     * 发票 pdf
     */
    @JsonProperty("c_url")
    private String cUrl;

    /**
     * 发票详情地址
     */
    @JsonProperty("c_jpg_url")
    private String cJpgUrl;

    /**
     * 开票日期
     */
    @JsonProperty("c_kprq")
    private Long cKprq;

    /**
     * 发票代码
     */
    @JsonProperty("c_fpdm")
    private String cFpdm;

    /**
     * 发票号码
     */
    @JsonProperty("c_fpdm")
    private String cFphm;

    /**
     * 不含税金额
     */
    @JsonProperty("c_bhsje")
    private String cBhsje;

    /**
     * 合计税额
     */
    @JsonProperty("c_hjse")
    private String cHjse;

    /**
     * 结果信息
     */
    @JsonProperty("c_resultmsg")
    private String cResultMsg;

    /**
     * 发票主键
     */
    @JsonProperty("c_invoiceid")
    private String cInvoiceId;

    /**
     * 购方名称
     */
    @JsonProperty("c_buyername")
    private String cBuyerName;

    /**
     * 购方税号
     */
    @JsonProperty("c_taxnum")
    private String cTaxNum;

    /**
     * 效验码
     */
    @JsonProperty("c_jym")
    private String cJym;

}
