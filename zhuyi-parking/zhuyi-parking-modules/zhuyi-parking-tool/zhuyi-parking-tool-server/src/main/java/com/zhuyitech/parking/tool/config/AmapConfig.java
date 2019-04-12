package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.AmapPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * amap配置属性
 *
 * @author zwq
 */
@Configuration
public class AmapConfig {

    @Value("${" + AmapPropertyKeys.AMAP_PLATFORM_KEY + ":}")
    @Getter
    @Setter
    private String key;

    @Value("${" + AmapPropertyKeys.AMAP_URL_PREFIX + ":}")
    @Getter
    @Setter
    private String prefix;

    @Value("${" + AmapPropertyKeys.AMAP_REGION_PAGESIZE + ":}")
    @Getter
    @Setter
    private Integer pageSize;
}
