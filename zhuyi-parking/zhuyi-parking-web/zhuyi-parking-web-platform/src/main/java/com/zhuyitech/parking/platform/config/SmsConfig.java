package com.zhuyitech.parking.platform.config;

import com.zhuyitech.parking.platform.contants.SmsPropertiesKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 短信配置属性
 *
 * @author walkman
 */
@Configuration
public class SmsConfig {

    @Value("${" + SmsPropertiesKeys.SMS_CLIENT_ID + ":}")
    @Getter
    @Setter
    private String clientId;

    @Value("${" + SmsPropertiesKeys.SMS_COMMON_TEMPLATE_ID + ":}")
    @Getter
    @Setter
    private String templateId;

    @Value("${" + SmsPropertiesKeys.SMS_REGISTER_TEMPLATE_ID + ":}")
    @Getter
    @Setter
    private String registerTemplateId;

    @Value("${" + SmsPropertiesKeys.SMS_LOGIN_TEMPLATE_ID + ":}")
    @Getter
    @Setter
    private String loginTemplateId;

    @Value("${" + SmsPropertiesKeys.SMS_FORGET_PASSWORD_TEMPLATE_ID + ":}")
    @Getter
    @Setter
    private String forgetTemplateId;

    @Value("${" + SmsPropertiesKeys.SMS_SET_PASSWORD_TEMPLATE_ID + ":}")
    @Getter
    @Setter
    private String setTemplateId;

    @Value("${" + SmsPropertiesKeys.SMS_PAY_PASSWORD_TEMPLATE_ID + ":}")
    @Getter
    @Setter
    private String payTemplateId;

}