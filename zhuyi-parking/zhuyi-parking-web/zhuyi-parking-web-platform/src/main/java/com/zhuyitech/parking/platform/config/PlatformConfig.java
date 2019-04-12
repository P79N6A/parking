package com.zhuyitech.parking.platform.config;

import com.zhuyitech.parking.platform.contants.PropertiesKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * platform配置属性
 *
 * @author zwq
 */
@Configuration
public class PlatformConfig {

    @Value("${" + PropertiesKeys.PLATFORM_ANDROID_CLIENT_ID + ":}")
    @Getter
    @Setter
    private String androidClientId;

    @Value("${" + PropertiesKeys.PLATFORM_ANDROID_CLIENT_SECRETE + ":}")
    @Getter
    @Setter
    private String androidClientSecrete;

    @Value("${" + PropertiesKeys.PLATFORM_APPLE_CLIENT_ID + ":}")
    @Getter
    @Setter
    private String appleClientId;

    @Value("${" + PropertiesKeys.PLATFORM_APPLE_CLIENT_SECRETE + ":}")
    @Getter
    @Setter
    private String appleClientSecrete;

    @Value("${" + PropertiesKeys.PLATFORM_H5_CLIENT_ID + ":}")
    @Getter
    @Setter
    private String h5ClientId;

    @Value("${" + PropertiesKeys.PLATFORM_H5_CLIENT_SECRETE + ":}")
    @Getter
    @Setter
    private String h5ClientSecrete;

    @Value("${" + PropertiesKeys.PLATFORM_PC_CLIENT_ID + ":}")
    @Getter
    @Setter
    private String pcClientId;

    @Value("${" + PropertiesKeys.PLATFORM_PC_CLIENT_SECRETE + ":}")
    @Getter
    @Setter
    private String pcClientSecrete;

    @Value("${" + PropertiesKeys.PLATFORM_WEB_CLIENT_ID + ":}")
    @Getter
    @Setter
    private String webClientId;

    @Value("${" + PropertiesKeys.PLATFORM_WEB_CLIENT_SECRETE + ":}")
    @Getter
    @Setter
    private String webClientSecrete;

}