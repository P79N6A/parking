package com.zoeeasy.cloud.ucc.config;

import com.scapegoat.infrastructure.shiro.service.DefaultSaltGenerator;
import com.scapegoat.infrastructure.shiro.service.SaltGenerator;
import com.scapegoat.infrastructure.shiro.service.ShiroCryptoService;
import com.zoeeasy.cloud.core.DefaultRoleManagerConfig;
import com.zoeeasy.cloud.core.IRoleManagementConfig;
import com.zoeeasy.cloud.ucc.navigation.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper 配置
 *
 * @author walkman
 */
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

    @Bean
    protected IRoleManagementConfig roleManagementConfig() {
        return new DefaultRoleManagerConfig();
    }

    /**
     * NavigationManager
     *
     * @return
     */
    @Bean(initMethod = "initialize")
    NavigationManager navigationManager() {
        NavigationManager navigationManager = new NavigationManager();
        return navigationManager;
    }

    @Bean
    NavigationProvider databaseNavigationProvider() {
        return new DatabaseNavigationProvider();
    }

    /**
     * @return
     */
    @Bean
    UserNavigationManager userNavigationManager() {
        return new UserNavigationManager(navigationManager());
    }
}
