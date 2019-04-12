package com.zoeeasy.cloud.open.config;

import com.scapegoat.infrastructure.web.filter.FundamentalServletRequestReplacedFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class AppConfig {


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

}
