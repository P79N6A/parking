package com.zhuyitech.parking.tool.config;

import com.scapegoat.boot.modelmapper.ConfigurationConfigurer;
import com.scapegoat.boot.modelmapper.TypeMapConfigurer;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.zhuyitech.parking.tool.bean.violation.VehicleViolationDataResultBean;
import com.zhuyitech.parking.tool.bean.violation.VehicleViolationItemResultBean;
import com.zhuyitech.parking.tool.domain.OilPrice;
import com.zhuyitech.parking.tool.domain.Region;
import com.zhuyitech.parking.tool.domain.VehicleViolation;
import com.zhuyitech.parking.tool.dto.result.oilprice.OilPriceCurrentResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionViewResultDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationItemResultDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationResultDto;
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
    public TypeMapConfigurer<Region, RegionResultDto> regionMapping() {

        return new TypeMapConfigurer<Region, RegionResultDto>() {

            @Override
            public void configure(TypeMap<Region, RegionResultDto> typeMap) {

            }
        };
    }

    @Bean
    public TypeMapConfigurer<Region, RegionViewResultDto> regionViewMapping() {

        return new TypeMapConfigurer<Region, RegionViewResultDto>() {

            @Override
            public void configure(TypeMap<Region, RegionViewResultDto> typeMap) {
                typeMap.addMapping(Region::getAdCode, RegionViewResultDto::setCode);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<Region, ComboboxItemDto> regionComboboxItemMapping() {

        return new TypeMapConfigurer<Region, ComboboxItemDto>() {

            @Override
            public void configure(TypeMap<Region, ComboboxItemDto> typeMap) {

                typeMap.addMapping(Region::getCode, ComboboxItemDto::setValue);
                typeMap.addMapping(Region::getName, ComboboxItemDto::setDisplayText);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<VehicleViolationDataResultBean, VehicleViolationResultDto> vehicleViolationDataResultBeanMapping() {

        return new TypeMapConfigurer<VehicleViolationDataResultBean, VehicleViolationResultDto>() {

            @Override
            public void configure(TypeMap<VehicleViolationDataResultBean, VehicleViolationResultDto> typeMap) {

                typeMap.addMapping(VehicleViolationDataResultBean::getAmount, VehicleViolationResultDto::setTotalCount);
                typeMap.addMapping(VehicleViolationDataResultBean::getTotalFine, VehicleViolationResultDto::setTotalFine);
                typeMap.addMapping(VehicleViolationDataResultBean::getTotalPoints, VehicleViolationResultDto::setTotalPoints);
                typeMap.addMapping(VehicleViolationDataResultBean::getUntreated, VehicleViolationResultDto::setUntreatedCount);
            }
        };
    }

    /**
     * @return
     */
    @Bean
    public TypeMapConfigurer<VehicleViolationItemResultBean, VehicleViolation> vehicleViolationBeanMapping() {

        return new TypeMapConfigurer<VehicleViolationItemResultBean, VehicleViolation>() {

            @Override
            public void configure(TypeMap<VehicleViolationItemResultBean, VehicleViolation> typeMap) {

                typeMap.addMapping(VehicleViolationItemResultBean::getCode, VehicleViolation::setCode);
                typeMap.addMapping(VehicleViolationItemResultBean::getTime, VehicleViolation::setViolationTime);
                typeMap.addMapping(VehicleViolationItemResultBean::getAddress, VehicleViolation::setAddress);
                typeMap.addMapping(VehicleViolationItemResultBean::getReason, VehicleViolation::setReason);
                typeMap.addMapping(VehicleViolationItemResultBean::getFine, VehicleViolation::setFine);
                typeMap.addMapping(VehicleViolationItemResultBean::getPoint, VehicleViolation::setPoint);
                typeMap.addMapping(VehicleViolationItemResultBean::getViolationCity, VehicleViolation::setViolationCity);
                typeMap.addMapping(VehicleViolationItemResultBean::getProvince, VehicleViolation::setProvince);
                typeMap.addMapping(VehicleViolationItemResultBean::getCity, VehicleViolation::setCity);
                typeMap.addMapping(VehicleViolationItemResultBean::getCanSelect, VehicleViolation::setCanSelect);
                typeMap.addMapping(VehicleViolationItemResultBean::getProcessStatus, VehicleViolation::setProcessStatus);
                typeMap.addMapping(VehicleViolationItemResultBean::getViolationNum, VehicleViolation::setViolationNum);
                typeMap.addMapping(VehicleViolationItemResultBean::getOrganName, VehicleViolation::setOrganName);
                typeMap.addMapping(VehicleViolationItemResultBean::getPaymentStatus, VehicleViolation::setPaymentStatus);
            }
        };
    }

    /**
     * @return
     */
    @Bean
    public TypeMapConfigurer<VehicleViolationItemResultBean, VehicleViolationItemResultDto> vehicleViolationItemResultMapping() {

        return new TypeMapConfigurer<VehicleViolationItemResultBean, VehicleViolationItemResultDto>() {

            @Override
            public void configure(TypeMap<VehicleViolationItemResultBean, VehicleViolationItemResultDto> typeMap) {

                typeMap.addMapping(VehicleViolationItemResultBean::getCode, VehicleViolationItemResultDto::setCode);
                typeMap.addMapping(VehicleViolationItemResultBean::getTime, VehicleViolationItemResultDto::setViolationTime);
                typeMap.addMapping(VehicleViolationItemResultBean::getAddress, VehicleViolationItemResultDto::setAddress);
                typeMap.addMapping(VehicleViolationItemResultBean::getReason, VehicleViolationItemResultDto::setReason);
                typeMap.addMapping(VehicleViolationItemResultBean::getFine, VehicleViolationItemResultDto::setFine);
                typeMap.addMapping(VehicleViolationItemResultBean::getPoint, VehicleViolationItemResultDto::setPoint);
                typeMap.addMapping(VehicleViolationItemResultBean::getViolationCity, VehicleViolationItemResultDto::setViolationCity);
                typeMap.addMapping(VehicleViolationItemResultBean::getProvince, VehicleViolationItemResultDto::setProvince);
                typeMap.addMapping(VehicleViolationItemResultBean::getCity, VehicleViolationItemResultDto::setCity);
                typeMap.addMapping(VehicleViolationItemResultBean::getCanSelect, VehicleViolationItemResultDto::setCanSelect);
                typeMap.addMapping(VehicleViolationItemResultBean::getProcessStatus, VehicleViolationItemResultDto::setProcessStatus);
                typeMap.addMapping(VehicleViolationItemResultBean::getViolationNum, VehicleViolationItemResultDto::setViolationNum);
                typeMap.addMapping(VehicleViolationItemResultBean::getOrganName, VehicleViolationItemResultDto::setOrganName);
                typeMap.addMapping(VehicleViolationItemResultBean::getPaymentStatus, VehicleViolationItemResultDto::setPaymentStatus);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<OilPrice, OilPriceCurrentResultDto> oilPriceMapping() {

        return new TypeMapConfigurer<OilPrice, OilPriceCurrentResultDto>() {

            @Override
            public void configure(TypeMap<OilPrice, OilPriceCurrentResultDto> typeMap) {
                typeMap.addMapping(OilPrice::getB90, OilPriceCurrentResultDto::setB89);
                typeMap.addMapping(OilPrice::getB93, OilPriceCurrentResultDto::setB92);
                typeMap.addMapping(OilPrice::getB97, OilPriceCurrentResultDto::setB95);
            }
        };
    }

}
