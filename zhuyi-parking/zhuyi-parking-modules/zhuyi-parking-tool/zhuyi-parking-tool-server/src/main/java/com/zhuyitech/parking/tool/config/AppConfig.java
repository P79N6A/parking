package com.zhuyitech.parking.tool.config;

import com.scapegoat.infrastructure.shiro.service.DefaultSaltGenerator;
import com.scapegoat.infrastructure.shiro.service.SaltGenerator;
import com.scapegoat.infrastructure.shiro.service.ShiroCryptoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnMissingBean
    protected SaltGenerator saltGenerator() {
        return new DefaultSaltGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    protected ShiroCryptoService shiroCryptoService() {
        return new ShiroCryptoService();
    }
}
