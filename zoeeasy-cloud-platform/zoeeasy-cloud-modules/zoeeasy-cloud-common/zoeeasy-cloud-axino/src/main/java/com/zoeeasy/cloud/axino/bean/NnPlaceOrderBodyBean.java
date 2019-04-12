package com.zoeeasy.cloud.axino.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 开票请求内容
 *
 * @author walkman
 */
@Data
public class NnPlaceOrderBodyBean implements Serializable {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = 8243127099991355176L;

    /**
     * 购方名称 是 100
     */
    @JsonProperty("buyername")
    private String buyerName;

    /**
     * 购方税号 否 ,企业要填，个人可为空,20
     */
    @JsonProperty("taxnum")
    private String taxNum;

    /**
     * 是,购方手机(开票成功会短信提醒购方),20
     */
    private String phone;

    /**
     * 否 浙江省杭州,购方地址,企业要填，个人可为空,100
     */
    private String address;

    /**
     * 否,银行账号,企业要填，
     * 个人可为空购方银行账号,100
     */
    private String account;

    /**
     * 否购方电话 20
     */
    private String telephone;

    /**
     * 是,每个企业唯一订单号 20
     */
    @JsonProperty("orderno")
    private String orderNo;

    /**
     * 是 2016-01-13,开票时间
     */
    @JsonProperty("invoicedate")
    private String invoiceDate;

    /**
     * 是,开票员,8
     */
    private String clerk;

    /**
     * 否,销方银行账号,100
     */
    @JsonProperty("saleaccount")
    private String saleAccount;

    /**
     * 是,销方电话,20
     */
    @JsonProperty("salephone")
    private String salePhone;

    /**
     * 是,销方地址,80
     */
    @JsonProperty("saleaddress")
    private String saleAddress;

    /**
     * 是,销方税号,20
     */
    @JsonProperty("saletaxnum")
    private String saleTaxNum;

    /**
     * 是,1开票类型:1,正票;2, 红票,1
     */
    @JsonProperty("kptype")
    private String kpType;

    /**
     * 否 备注信息,130
     * 冲红时，必须在
     * 备注中注明“
     * 对应正数发票代
     * 码:XXXXXXXXX
     * 号码:YYYYYYYY”
     * 文案，其中“X”
     * 为发票代码，“Y”为发票号码，
     * 否则接口会自动添加该文案
     */
    private String message;

    /**
     * 否,收款人,8
     */
    private String payee;

    /**
     * 否,    复核人, 8
     */
    private String checker;

    /**
     * 否,红票必填，不满12位请左补 0, 对应蓝票发票代码 12
     */
    private String fpdm;

    /**
     * 否 红票必填，不满8位请左补 0,对应蓝票发票号码 8
     */
    private String fphm;

    /**
     * 否 1    推 送
     * 方 式 :-1,不推送;0,邮箱;1,手机(默认);2,邮箱、手机 2
     */
    private String tsfs;

    /**
     * 否,推送邮箱（tsfs 为0或 2时，此项为必填）,50
     */
    private String email;

    /**
     * 否 0默认为 0
     * 清单标志:0,根据项目名称数自动产生清单;
     * 1, 将项目信息打印至清单
     */
    private String qdbz;

    /**
     * 否 详见销货清单,90
     * qdbz 为 1是，
     * 此项为必填
     * 清单项目名称:
     * 打印清单时对应发票票面
     * 项目名称，注意： 税总要求清单项目名称
     * 为（详见销货清单）
     */
    private String qdxmmc;

    /**
     * 否 0默认为 0代开标志:0非代开;1 代开。1
     * 代开蓝票备注
     * 文案要求包含：代开
     * 企业税号:***,
     * 代开企业名称:***；
     * 代开红票备注文案要求：对应正数发票代码:***
     * 号码:***代开企业税
     * 号 :***
     * 代 开
     * 企 业
     * 名
     * 称:***。
     */
    private String dkbz;

    /**
     * 否  部门门店 id（诺诺系统中的 id）,32
     */
    @JsonProperty("deptid")
    private String deptId;

    /**
     * 否,开票员 id（诺诺系统中的 id）,32
     */
    @JsonProperty("clerkid")
    private String clerkId;

    /**
     * 否,默认为电票 p,
     * 发票种类，
     * p 电子增值税普通发票，
     * c 增值税普通发票(纸票)，
     * s 增值税专用发票，
     * e 收购发票(电子)，
     * f 收购发票(纸质)
     */
    @JsonProperty("invoiceLine")
    private String invoiceLine;

    /**
     * 否,0,默认为0非成品
     * 油
     * 成品油标志： 0 非成
     * 品油， 1 成品油，
     */
    private String cpybz;

    /**
     * 电子发票明细
     */
    private List<NnInvoiceGoodDetailBean> detail;

}
