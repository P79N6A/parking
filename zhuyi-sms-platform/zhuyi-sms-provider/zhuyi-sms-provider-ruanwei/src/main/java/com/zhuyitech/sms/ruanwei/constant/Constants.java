package com.zhuyitech.sms.ruanwei.constant;


import com.scapegoat.infrastructure.config.FundamentalConfigProvider;

/**
 */
public interface Constants {

    /**
     * 短信接口部分
     */
    public static final String RUANWEI_SMS_URL = FundamentalConfigProvider.getString(PropertyKeys.SMS_RUANWEI_SEND_URL_KEY);
    public static final String RUANWEI_SMS_USER_ID = FundamentalConfigProvider.getString(PropertyKeys.SMS_RUANWEI_SEND_USER_ID_KEY);
    public static final String RUANWEI_SMS_ACCOUNT = FundamentalConfigProvider.getString(PropertyKeys.SMS_RUANWEI_SEND_ACCOUNT_KEY);
    public static final String RUANWEI_SMS_PASSWORD = FundamentalConfigProvider.getString(PropertyKeys.SMS_RUANWEI_SEND_PASSWORD_KEY);
    public static final String RUANWEI_SMS_CONTENT = FundamentalConfigProvider.getString(PropertyKeys.SMS_RUANWEI_SEND_CONTENT_KEY);
}
