package com.zhuyitech.parking.pay.alipay.enums;


/**
 * 支付宝错误枚举
 *
 * @author zwq
 * @date 2018-01-11
 */
public enum AlipayErrEnum {

    //获取accessToken
    PARAM_EMPTY(11000, "参数为空"),
    GET_ACCESSTOKEN_ERR(11001, "获取AccessToken失败"),
    ALIPAY_RETURN_FALSE(11002, "支付宝请求失败"),
    PARAM_ERR(11003, "参数错误"),
    GET_USERINFO_ERR(11004, "获取用户信息失败"),
    MANAGE_REQUEST_STR_ERR(11005, "处理授权请求参数失败"),;

    /**
     * 值
     */
    private int value;

    /**
     * 注释
     */
    private String comment;

    public int getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    AlipayErrEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static AlipayErrEnum parse(Integer value) {
        if (value != null) {
            AlipayErrEnum[] array = values();
            for (AlipayErrEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
