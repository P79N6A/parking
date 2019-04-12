package com.zhuyitech.sms.ruanwei.config;

/**
 */
public class IdCheckResult {
    private String callbackUrl;
    private String sessionId;
    private String state;
    private String statcode;
    private String msg;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatcode() {
        return statcode;
    }

    public void setStatcode(String statcode) {
        this.statcode = statcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
