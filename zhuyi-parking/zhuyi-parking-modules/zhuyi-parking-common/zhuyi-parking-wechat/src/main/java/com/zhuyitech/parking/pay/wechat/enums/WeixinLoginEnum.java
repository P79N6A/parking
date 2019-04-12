package com.zhuyitech.parking.pay.wechat.enums;


/**
 * 微信支付状态枚举
 *
 * @author zwq
 * @date 2018-01-11
 */
public enum WeixinLoginEnum {

    //微信登录
    OPENID_NOT_EXISTS(10800, "openId为空"),
    CODE_NOT_VAILD(10801, "code无效"),
    ACCESSTOKEN_NOT_EXISTS(10802, "accessToken为空"),
    ACCESSTOKEN_NOT_VAILD(10803, "accessToken无效"),
    CHECK_ACCESSTOKEN_ERR(10804, "检查accessToken失败"),
    GRT_ACCESSTOKEN_ERR(10805, "获取accessToken失败"),
    GRT_USERINFO_ERR(10806, "获取用户信息失败"),
    REFRESHTOKEN_NOT_EXISTS(10807, "refreshToken为空"),
    WEIXIN_RESPONSE_NULL(10808, "微信返回为空"),
    REFRESH_ACCESSTOKEN_RRR(10809, "刷新AccessToken失败"),;

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

    WeixinLoginEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static WeixinLoginEnum parse(Integer value) {
        if (value != null) {
            WeixinLoginEnum[] array = values();
            for (WeixinLoginEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
