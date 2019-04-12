package com.zhuyitech.parking.pms.config;

import com.scapegoat.boot.modelmapper.ConfigurationConfigurer;
import com.scapegoat.boot.modelmapper.TypeMapConfigurer;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.zhuyitech.parking.pms.domain.*;
import com.zhuyitech.parking.pms.dto.result.park.ParkingImageViewResultDto;

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
    public TypeMapConfigurer<ParkingImage, ParkingImageViewResultDto> parkingVehicleRecordMapping() {

        return new TypeMapConfigurer<ParkingImage, ParkingImageViewResultDto>() {

            @Override
            public void configure(TypeMap<ParkingImage, ParkingImageViewResultDto> typeMap) {

                typeMap.addMapping(ParkingImage::getImageUrl, ParkingImageViewResultDto::setUrl);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<CarBrand, ComboboxItemDto> carBrandMapping() {

        return new TypeMapConfigurer<CarBrand, ComboboxItemDto>() {

            @Override
            public void configure(TypeMap<CarBrand, ComboboxItemDto> typeMap) {

                typeMap.addMapping(CarBrand::getId, ComboboxItemDto::setValue);
                typeMap.addMapping(CarBrand::getName, ComboboxItemDto::setDisplayText);
            }
        };
    }
}
