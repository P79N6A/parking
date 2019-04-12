package com.zhuyitech.sms.constant;

import com.scapegoat.infrastructure.config.FundamentalConfigProvider;

public class SuDunConstants {

    public static final String SuDun_Send_SMS_URL = FundamentalConfigProvider.getString(SuDunPropertyKeys.SuDun_SINGLE_SMS_URL_KEY);
    public static final String SuDun_SMS_PRODUCT = FundamentalConfigProvider.getString(SuDunPropertyKeys.SuDun_SMS_PRODUCT_KEY);
    public static final String SuDun_SMS_ACCOUNT = FundamentalConfigProvider.getString(SuDunPropertyKeys.SuDun_SMS_ACCOUNT_KEY);
    public static final String SuDun_SMS_PASSWORD = FundamentalConfigProvider.getString(SuDunPropertyKeys.SuDun_SMS_PASSWORD_KEY);
    public static final String SuDun_SMS_STATUS = FundamentalConfigProvider.getString(SuDunPropertyKeys.SuDun_SMS_STATUS_KEY);

    public static final String VALIDATE_CODE_EXPIRE_TIME = FundamentalConfigProvider.getString(SuDunPropertyKeys.VALIDATE_CODE_EXPIRE_TIME_KEY);

    public static final String DAY_SEND_COUNT = FundamentalConfigProvider.getString(SuDunPropertyKeys.DAY_SEND_COUNT_KEY);
    public static final String HOUR_SEND_COUNT = FundamentalConfigProvider.getString(SuDunPropertyKeys.HOUR_SEND_COUNT_KEY);
    public static final String IP_SEND_COUNT = FundamentalConfigProvider.getString(SuDunPropertyKeys.IP_SEND_COUNT_KEY);
    public static final Integer SMS_SEND_INTERVAL = FundamentalConfigProvider.getInt(SuDunPropertyKeys.SMS_SEND_INTERVAL_KEY);
}
