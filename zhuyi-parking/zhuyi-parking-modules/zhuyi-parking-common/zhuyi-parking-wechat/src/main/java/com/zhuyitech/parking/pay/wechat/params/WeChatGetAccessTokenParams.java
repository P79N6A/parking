package com.zhuyitech.parking.pay.wechat.params;


/**
 * <pre>
 *     微信获取accessToken
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-09:44
 */
public class WeChatGetAccessTokenParams {

    /**
     * code
     */
    private String code;

    /**
     * goWay
     */
    private Boolean goWay;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getGoWay() {
        return goWay;
    }

    public void setGoWay(Boolean goWay) {
        this.goWay = goWay;
    }
}
