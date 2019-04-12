package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.AuthzPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * authz配置属性
 *
 * @author zwq
 */
@Configuration
public class AuthzConfig {

    @Value("${" + AuthzPropertyKeys.AUTHENTICATION_SERVER_URL_KEY + ":}")
    @Getter
    @Setter
    private String serverUrl;

    @Value("${" + AuthzPropertyKeys.JISHUAPI_APPKEY_KEY + ":}")
    @Getter
    @Setter
    private String appkey;

    @Value("${" + AuthzPropertyKeys.JISHUAPI_APPSECRETE_KEY + ":}")
    @Getter
    @Setter
    private String appsecret;

    @Value("${" + AuthzPropertyKeys.JISHUAPI_IDCARD_VERIFY_URL_KEY + ":}")
    @Getter
    @Setter
    private String idcardverifyUrl;

    @Value("${" + AuthzPropertyKeys.JISHUAPI_PLATETYPE_URL_KEY + ":}")
    @Getter
    @Setter
    private String platetypeUrl;

    @Value("${" + AuthzPropertyKeys.JISHUAPI_PLATEVERIFY_URL_KEY + ":}")
    @Getter
    @Setter
    private String plateverifyUrl;

    @Value("${" + AuthzPropertyKeys.JUSHUAPI_DRIVER_LICENSE_URL_KEY + ":}")
    @Getter
    @Setter
    private String driverlicenseUrl;
}
