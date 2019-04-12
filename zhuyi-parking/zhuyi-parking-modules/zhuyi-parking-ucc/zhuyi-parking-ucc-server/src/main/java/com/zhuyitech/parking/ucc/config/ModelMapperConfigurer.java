package com.zhuyitech.parking.ucc.config;

import com.scapegoat.boot.modelmapper.ConfigurationConfigurer;
import com.scapegoat.boot.modelmapper.TypeMapConfigurer;
import com.zhuyitech.parking.ucc.domain.*;
import com.zhuyitech.parking.ucc.dto.result.*;
import com.zhuyitech.parking.ucc.car.result.UserCarSummaryViewResultDto;
import com.zhuyitech.parking.ucc.car.result.UserCarViewResultDto;
import com.zhuyitech.parking.ucc.car.result.UserPlateNumberResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.CustomerUserResultDto;
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
    public TypeMapConfigurer<UserCarInfo, UserPlateNumberResultDto> userPlateNumberMapping() {

        return new TypeMapConfigurer<UserCarInfo, UserPlateNumberResultDto>() {

            @Override
            public void configure(TypeMap<UserCarInfo, UserPlateNumberResultDto> typeMap) {

                typeMap.addMapping(UserCarInfo::getPlateColor, UserPlateNumberResultDto::setPlateColor);
                typeMap.addMapping(UserCarInfo::getCarLevel, UserPlateNumberResultDto::setCarStyle);
                typeMap.addMapping(UserCarInfo::getFullPlateNumber, UserPlateNumberResultDto::setPlateNumber);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<UserCarInfo, UserCarSummaryViewResultDto> userCarSummaryMapping() {

        return new TypeMapConfigurer<UserCarInfo, UserCarSummaryViewResultDto>() {

            @Override
            public void configure(TypeMap<UserCarInfo, UserCarSummaryViewResultDto> typeMap) {

                typeMap.addMapping(UserCarInfo::getPlateColor, UserCarSummaryViewResultDto::setPlateColor);
                typeMap.addMapping(UserCarInfo::getCarLevel, UserCarSummaryViewResultDto::setCarStyle);
                typeMap.addMapping(UserCarInfo::getFullPlateNumber, UserCarSummaryViewResultDto::setPlateNumber);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<UserCarInfo, UserCarViewResultDto> userCarViewResultMapping() {

        return new TypeMapConfigurer<UserCarInfo, UserCarViewResultDto>() {

            @Override
            public void configure(TypeMap<UserCarInfo, UserCarViewResultDto> typeMap) {

                typeMap.addMapping(UserCarInfo::getRemark, UserCarViewResultDto::setRejectReason);
            }
        };
    }


    @Bean
    public TypeMapConfigurer<User, CustomerUserResultDto> userCustomerMapping() {
        return new TypeMapConfigurer<User, CustomerUserResultDto>() {

            @Override
            public void configure(TypeMap<User, CustomerUserResultDto> typeMap) {

                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getRealName(), CustomerUserResultDto::setRealName));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getGender(), CustomerUserResultDto::setGender));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getBirthday(), CustomerUserResultDto::setBirthday));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getCertificateStatus(), CustomerUserResultDto::setCertificated));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getCertificatedDate(), CustomerUserResultDto::setCertificatedDate));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxUuid(), CustomerUserResultDto::setWxUuid));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxOpenId(), CustomerUserResultDto::setWxOpenId));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxUnionId(), CustomerUserResultDto::setWxUnionId));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxNickname(), CustomerUserResultDto::setWxNickname));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxSex(), CustomerUserResultDto::setWxSex));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxCounty(), CustomerUserResultDto::setWxCounty));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxProvince(), CustomerUserResultDto::setWxProvince));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxCity(), CustomerUserResultDto::setWxCity));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxPrivilege(), CustomerUserResultDto::setWxPrivilege));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getWxAuthorized(), CustomerUserResultDto::setWxAuthorized));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getQqNumber(), CustomerUserResultDto::setQqNumber));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getQqAuthorized(), CustomerUserResultDto::setQqAuthorized));
                typeMap.addMappings(mapper -> mapper.map(src -> src.getUserInfo().getLevel(), CustomerUserResultDto::setLevel));

            }
        };
    }

    @Bean
    public TypeMapConfigurer<UserPayOrder, UserPayOrderResultDto> userPayOrderMapping() {
        return new TypeMapConfigurer<UserPayOrder, UserPayOrderResultDto>() {
            @Override
            public void configure(TypeMap<UserPayOrder, UserPayOrderResultDto> typeMap) {

                typeMap.addMapping(UserPayOrder::getBizOrderType, UserPayOrderResultDto::setBizOrderType);
                typeMap.addMapping(UserPayOrder::getPayWay, UserPayOrderResultDto::setPayWay);
            }
        };
    }

    @Bean
    public TypeMapConfigurer<UserAuthApply, UserAuthApplyResultDto> userAuthApplyMapping() {
        return new TypeMapConfigurer<UserAuthApply, UserAuthApplyResultDto>() {
            @Override
            public void configure(TypeMap<UserAuthApply, UserAuthApplyResultDto> typeMap) {

                typeMap.addMapping(UserAuthApply::getFacePhotoArray, UserAuthApplyResultDto::setFacePhotos);
            }
        };
    }

}
