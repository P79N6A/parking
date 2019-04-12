package com.zoeeasy.cloud.inspect.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author walkman
 * @date 2017-12-01
 */
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        CorsFilter corsFilter = new CorsFilter(source);
        registration.setFilter(corsFilter);
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(-100);
        return registration;
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

}
