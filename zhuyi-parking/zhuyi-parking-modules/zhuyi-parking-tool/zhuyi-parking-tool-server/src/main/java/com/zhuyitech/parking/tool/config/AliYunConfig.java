package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.AliYunPropertyKeys;
import com.zhuyitech.parking.tool.constant.SharePropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun配置属性
 *
 * @author zwq
 */
@Configuration
public class AliYunConfig {

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_IDENTITY_APPCODE + ":}")
    @Getter
    @Setter
    private String appcode;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_IDENTITY_APPKEY + ":}")
    @Getter
    @Setter
    private String appkey;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_IDENTIRY_APPSECETE + ":}")
    @Getter
    @Setter
    private String appsecrete;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_IDENTITY_URL + ":}")
    @Getter
    @Setter
    private String identityUrl;

    @Value("${" + AliYunPropertyKeys.ALIYUN_IDCARD_CERT_URL + ":}")
    @Getter
    @Setter
    private String idCardCertUrl;

    @Value("${" + AliYunPropertyKeys.ALIYUN_IDCARD_URL + ":}")
    @Getter
    @Setter
    private String idCardUrl;

    @Value("${" + AliYunPropertyKeys.IDCARD_PLAN_SELECT + ":}")
    @Getter
    @Setter
    private String planSelect;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_LICENSE_APPKEY + ":}")
    @Getter
    @Setter
    private String licenseAppkey;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_LICENSE_APPSECRETE + ":}")
    @Getter
    @Setter
    private String licenseAppsecrete;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_LICENSE_APPCODE + ":}")
    @Getter
    @Setter
    private String licenseAppcode;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_LICENSE_URL + ":}")
    @Getter
    @Setter
    private String licenseUrl;

    @Value("${" + AliYunPropertyKeys.ALIYUN_VIOLATION_URL + ":}")
    @Getter
    @Setter
    private String violationUrl;

    @Value("${" + AliYunPropertyKeys.ALIYUN_VIOLATION_APPKEY + ":}")
    @Getter
    @Setter
    private String violationAppkey;

    @Value("${" + AliYunPropertyKeys.ALIYUN_VIOLATION_APPSECRETE + ":}")
    @Getter
    @Setter
    private String violationAppsecrete;

    @Value("${" + AliYunPropertyKeys.ALIYUN_VIOLATION_APPCODE + ":}")
    @Getter
    @Setter
    private String violationAppcode;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_ENDPOINT_KEY + ":}")
    @Getter
    @Setter
    private String endpoint;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_ACCESSKEYID_KEY + ":}")
    @Getter
    @Setter
    private String accessKeyId;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_ACCESSKEYSECRET_KEY + ":}")
    @Getter
    @Setter
    private String accessKeySecret;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_BUCKET_KEY + ":}")
    @Getter
    @Setter
    private String bucket;

}
