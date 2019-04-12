package com.zhuyitech.parking.platform.config;

import ch.qos.logback.access.servlet.TeeFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WEB配置类
 *
 * @author walkman
 */
@Configuration
@EnableAutoConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Remote ip filter remote ip filter.
     *
     * @return the remote ip filter
     */
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    /**
     * Tee filter tee filter.
     *
     * @return the tee filter
     */
    @Bean
    @ConditionalOnProperty(prefix = "server.tomcat.accesslog", name = "debug", havingValue = "true")
    public TeeFilter teeFilter() {
        //复制请求响应流，用于打印调试日志
        return new TeeFilter();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 系统对外暴露的 URL 不会识别和匹配 .* 后缀
        // 系统不区分 URL 的最后一个字符是否是斜杠 /
        configurer.setUseSuffixPatternMatch(false)
                .setUseTrailingSlashMatch(true);
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        // 等价于<mvc:default-servlet-handler />, 对静态资源文件的访问, 将无法 mapping 到 Controller 的 path 交给 default servlet handler 处理
        configurer.enable();
    }

    /**
     * Validator local validator factory bean.
     *
     * @return the local validator factory bean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Gets method validation post processor.
     *
     * @return the method validation post processor
     */
    @Bean
    public MethodValidationPostProcessor getMethodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }
}
