package com.zoeeasy.cloud.axino.constant;

/**
 * 诺诺状态码常量定义
 *
 * @author walkman
 * @since 2019-01-16
 */
public class JssResultConstant {

    public static final String SUCCESS = "0000";

    /**
     * 参数错误 修改参数重新传入
     */
    public static final String PARAMETER_ERROR = "9999";

    /**
     * 9009 系统错误 联系技服排查
     */
    public static final String SYSTEM_ERROR = "9009";

    /**
     * 4000 检查是否加密
     */
    public static final String ENCRYPTED_ERROR = "4000";

    /**
     * 400 检测是否传入参数名为 order，参数值为密文
     */
    public static final String ORDER_ERROR = "400";

    /**
     * 9301 企业信息备案错误,请联系诺诺网
     */
    public static final String FILING_ERROR = "9301";

    /**
     * 9144 该发票已提交作废
     */
    public static final String INVOICE_INVALID_ERROR = "9144";

    /**
     * 9145  该发票不能重复作废
     */
    public static final String REPEATED_INVALID_ERROR = "9145";

    /**
     * 9148 该发票存在未作废的红票
     */
    public static final String EXIST_ERROR = "9148";

    /**
     * 9151 非当月纸票不能作废
     */
    public static final String NOT_INVALID_ERROR = "9151";
}
