package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.JuHePropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * juhe配置属性
 *
 * @author zwq
 */
@Configuration
public class JuHeConfig {

    @Value("${" + JuHePropertyKeys.JUHE_VEHICLELIMIT_URL + ":}")
    @Getter
    @Setter
    private String url;

    @Value("${" + JuHePropertyKeys.JUHE_VEHICLELIMIT_CITY_URL + ":}")
    @Getter
    @Setter
    private String cityUrl;

    @Value("${" + JuHePropertyKeys.JUHE_VEHICLELIMIT_APPKEY + ":}")
    @Getter
    @Setter
    private String appkey;

    @Value("${" + JuHePropertyKeys.JUHE_OILPRICE_APPKEY + ":}")
    @Getter
    @Setter
    private String oilAppkey;

    @Value("${" + JuHePropertyKeys.JUHE_OILPRICE_URL + ":}")
    @Getter
    @Setter
    private String oilUrl;
}
