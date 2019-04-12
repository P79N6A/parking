package com.zhuyitech.parking.pay.wechat.result;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * <pre>
 *     微信检验AccessToken
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-14:14
 */
public class WeChatCheckAccessTokenResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * errcode
     */
    private String errCode;

    /**
     * errmsg
     */
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WeChatCheckAccessTokenResult{" +
                "errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
