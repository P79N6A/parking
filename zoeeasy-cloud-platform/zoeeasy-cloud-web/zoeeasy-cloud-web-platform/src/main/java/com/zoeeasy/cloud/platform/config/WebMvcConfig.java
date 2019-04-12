package com.zoeeasy.cloud.platform.config;

import com.scapegoat.infrastructure.core.client.OpenClientProvider;
import com.scapegoat.infrastructure.web.interceptor.ApiSignedHandlerInterceptor;
import com.zoeeasy.cloud.platform.provider.DefaultOpenClientProvider;
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
//        registry.addInterceptor(apiSignedInterceptor())
//                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

//    @Bean
//    ApiSignedHandlerInterceptor apiSignedInterceptor() {
//        ApiSignedHandlerInterceptor signedInterceptor = new ApiSignedHandlerInterceptor();
//        signedInterceptor.setOpenClientProvider(openClientProvider());
//        return signedInterceptor;
//    }

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

