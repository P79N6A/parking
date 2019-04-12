package com.zhuyitech.sms.aliyun.config;

import com.zhuyitech.sms.aliyun.constant.PropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunSmsProperties {

    @Value("${" + PropertyKeys.ALIYUN_SMS_ACCESS_KEY_ID + ":}")
    @Getter
    @Setter
    private String accessKeyId;

    @Value("${" + PropertyKeys.ALIYUN_SMS_ACCESS_KEY_SECRETE + ":}")
    @Getter
    @Setter
    private String accessKeySecret;

    @Value("${" + PropertyKeys.ALIYUN_SMS_URL_KEY + ":}")
    @Getter
    @Setter
    private String url;

}
