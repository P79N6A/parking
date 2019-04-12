package com.zoeeasy.cloud.tool.config;

import com.zoeeasy.cloud.tool.constant.AliYunPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS配置属性
 *
 * @author walkman
 */
@Configuration
@Getter
@Setter
public class AliyunOssConfig {

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_ACCESSKEYID_KEY + ":}")
    private String accessKeyId;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_ACCESSKEYSECRET_KEY + ":}")
    private String accessKeySecret;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_ENDPOINT_KEY + ":}")
    private String endpoint;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OSS_BUCKET_KEY + ":}")
    private String bucket;

    @Value("${" + AliYunPropertyKeys.ALIYUN_OCR_LICENSE_APPCODE + ":}")
    private String appCode;
}
