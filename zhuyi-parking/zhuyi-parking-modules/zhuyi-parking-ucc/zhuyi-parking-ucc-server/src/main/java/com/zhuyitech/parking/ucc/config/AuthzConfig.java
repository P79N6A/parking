package com.zhuyitech.parking.ucc.config;

import com.zhuyitech.parking.ucc.constant.AuthzPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * amap配置属性
 *
 * @author zwq
 */
@Configuration
public class AuthzConfig {

    @Value("${" + AuthzPropertyKeys.AUTHENTICATION_SERVER_URL_KEY + ":}")
    @Getter
    @Setter
    private String url;
}
