package com.zoeeasy.cloud.axino.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 开票请求发票明细
 *
 * @author walkman
 */
@Data
public class NnInvoiceGoodDetailBean implements Serializable {

    /**
     * 是,商品名称，90
     * 如 FPHXZ=1，则
     * 此商品行为折
     * 扣行，此版本折
     * 扣行不允许多
     * 行折扣，折扣行
     * 必须紧邻被折
     * 扣行，项目名称
     * 必须与被折扣
     * 行一致。
     */
    @JsonProperty("goodsname")
    private String goodsName;

    /**
     * 否,冲红时项目数
     * 量为负数
     * 数量；数量、单价必
     * 须都不填，或者都必
     * 填，不可只填一个；
     * 当数量、单价都不填
     * 时，不含税金额、税
     * 额、含税金额都必填。
     * 建议保留小数点后 8
     * 位。 16
     */
    private String num;

    /**
     * 否,冲红时项目单价为正数,16
     * 单价；数量、单价必
     * 须都不填，或者都必
     * 填，不可只填一个；
     * 当数量、单价都不填
     * 时，不含税金额、税
     * 额、含税金额都必填。
     * 建议保留小数点后 8
     * 位。
     */
    private String price;

    /**
     * 是,单价含税标志， 0:不含税,1:含税
     */
    private String hsbz;

    /**
     * 是,税率,10
     */
    @JsonProperty("taxrate")
    private String taxRate;

    /**
     * 否,规格型号,40
     */
    private String spec;

    /**
     * 否,单位,20
     */
    private String unit;

    /**
     * 是,税收分类编码,19
     * 签订免责协 议
     * 客户可不传入，
     * 由接口进行匹
     * 配，如对接口速
     * 度敏感的企业，
     * 建议传入该字
     * 段
     */
    private String spbm;

    /**
     * 否,自行编码,20
     */
    private String zsbm;

    /**
     * 是,发票行性质,1
     * 发票行性质:
     * 0,正常行;
     * 1,折扣行;
     * 2,被折扣行
     */
    private String fphxz;

    /**
     * 否,优惠政策标识:0,不使
     * 用;1,使用,1
     */
    private String yhzcbs;

    /**
     * 否,当 yhzcbs 为 1时，此项必填,50
     * 增值税特殊管理，如：
     * 即征即退、免税、简
     * 易征收 等
     */
    private String zzstsgl;

    /**
     * 否,零税率标识:空,非零
     * 税率;1,免税;2,不征
     * 税;3,普通零税率,1
     */
    private String lslbs;

    /**
     * 否,16.2
     * 扣除额，小数点后两
     * 位。差额征收的发票
     * 目前 只支持一行 明
     * 细。不含税差额 = 不
     * 含税金额 - 扣除额；
     * 税额 = 不含税差额*
     * 税率
     */
    private String kce;

    /**
     * 不含税金额,16.2
     * 精确到小数点
     * 后面两位， 红票
     * 为负。不含税金
     * 额、税额、含税
     * 金额任何一个
     * 不传时，会根据
     * 传入的单价，数
     * 量进行计算，可
     * 能和实际数值
     * 存在误差，建议
     * 都传入
     */
    @JsonProperty("taxfreeamt")
    private String taxFreeAmt;

    /**
     * 否,税额,16.2
     * 精确到小数点
     * 后面两位， 红票
     * 为负。 不含税金
     * 额、税额、含税
     * 金额任何一个
     * 税额 16.2
     * 不传时，会根据
     * 传入的单价，数
     * 量进行计算，可
     * 能和实际数值
     * 存在误差，建议
     * 都传入
     */
    private String tax;

    /**
     * 否,含税金额,16.2
     * 精确到小数点
     * 后面两位， 红票
     * 为负。不含税金
     * 额、税额、含税
     * 金额任何一个
     * 不传时，会根据
     * 传入的单价，数
     * 量进行计算，可
     * 能和实际数值
     * 存在误差，建议
     * 都传入
     */
    @JsonProperty("taxamt")
    private String taxAmt;

}
