package com.zoeeasy.cloud.open.config;

import com.scapegoat.infrastructure.core.client.OpenClientProvider;
import com.scapegoat.infrastructure.web.interceptor.ApiSignedHandlerInterceptor;
import com.zoeeasy.cloud.open.interceptor.MyApiSignedHandlerInterceptor;
import com.zoeeasy.cloud.open.provider.DefaultOpenClientProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author walkman
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiSignedInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

   /* @Bean
    ApiSignedHandlerInterceptor apiSignedInterceptor() {
        ApiSignedHandlerInterceptor signedInterceptor = new ApiSignedHandlerInterceptor();
        signedInterceptor.setOpenClientProvider(openClientProvider());
        return signedInterceptor;
    }*/

    @Bean
    MyApiSignedHandlerInterceptor apiSignedInterceptor() {
        MyApiSignedHandlerInterceptor signedInterceptor = new MyApiSignedHandlerInterceptor();
        signedInterceptor.setOpenClientProvider(openClientProvider());
        return signedInterceptor;
    }

    /**
     * openClientProvider Bean
     *
     * @return
     */
    @Bean
    OpenClientProvider openClientProvider() {
        return DefaultOpenClientProvider.getInstance();
    }
}

