package com.zhuyitech.parking.config;

import com.scapegoat.infrastructure.core.client.OpenClientProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * openClientProvider Bean
     *
     * @return
     */
    @Bean
    OpenClientProvider openClientProvider() {
        return new DefaultOpenClientProvider();
    }
}
