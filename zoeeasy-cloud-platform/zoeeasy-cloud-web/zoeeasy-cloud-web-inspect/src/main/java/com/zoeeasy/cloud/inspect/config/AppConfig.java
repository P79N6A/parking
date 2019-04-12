package com.zoeeasy.cloud.inspect.config;

import com.scapegoat.infrastructure.shiro.filter.SecurityContextRequestFilter;
import com.scapegoat.infrastructure.shiro.web.SecurityContextRequestProcessor;
import com.scapegoat.infrastructure.web.filter.FundamentalServletRequestReplacedFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class AppConfig {

    @Bean("securityContextRequestProcessor")
    public SecurityContextRequestProcessor securityContextRequestProcessor() {
        return new SecurityContextRequestProcessor();
    }

    /**
     * @return
     */
    @Bean
    public FilterRegistrationBean servletRequestReplacedFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FundamentalServletRequestReplacedFilter());
        registration.addUrlPatterns("/*");
        registration.setName("servletRequestReplacedFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }

    /**
     * SecurityContextRequestFilter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean securityContextRequestFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        SecurityContextRequestFilter filter = new SecurityContextRequestFilter();
        filter.setRequestProcessor(securityContextRequestProcessor());
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("securityContextRequestFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }
}
