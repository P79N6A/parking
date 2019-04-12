package com.zoeeasy.cloud.pms.config;

import com.scapegoat.boot.modelmapper.ConfigurationConfigurer;
import com.scapegoat.boot.modelmapper.TypeMapConfigurer;
import com.zoeeasy.cloud.pms.domain.PassingVehicleRecordEntity;
import com.zoeeasy.cloud.pms.passing.dto.result.PassingVehicleRecordViewResultDto;
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

    /**
     * mapperConfiguration
     *
     * @return
     */
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
    public TypeMapConfigurer<PassingVehicleRecordEntity, PassingVehicleRecordViewResultDto> passingVehicleRecordMapping() {

        return new TypeMapConfigurer<PassingVehicleRecordEntity, PassingVehicleRecordViewResultDto>() {

            @Override
            public void configure(TypeMap<PassingVehicleRecordEntity, PassingVehicleRecordViewResultDto> typeMap) {

                typeMap.addMapping(PassingVehicleRecordEntity::getPassingType, PassingVehicleRecordViewResultDto::setPassCarType);
            }
        };
    }

}
