package com.zhuyitech.sms.config;

import com.scapegoat.boot.modelmapper.ConfigurationConfigurer;
import com.scapegoat.boot.modelmapper.TypeMapConfigurer;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.zhuyitech.sms.domain.SmsClient;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper 配置
 *
 * @author walkman
 */
@Configuration
public class ModelMapperConfigurer {

    @Bean
    public ConfigurationConfigurer mapperConfiguration() {

        return new ConfigurationConfigurer() {
            @Override
            public void configure(org.modelmapper.config.Configuration configuration) {

                configuration.setSkipNullEnabled(true);
                configuration.setMatchingStrategy(MatchingStrategies.STRICT);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<SmsClient, ComboboxItemDto> smsClientComboboxItemMapping() {

        return new TypeMapConfigurer<SmsClient, ComboboxItemDto>() {

            @Override
            public void configure(TypeMap<SmsClient, ComboboxItemDto> typeMap) {

                typeMap.addMapping(SmsClient::getClientId, ComboboxItemDto::setValue);
                typeMap.addMapping(SmsClient::getClientName, ComboboxItemDto::setDisplayText);
            }
        };
    }
}
