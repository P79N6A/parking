package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.BaiduPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * baidu配置属性
 *
 * @author zwq
 */
@Configuration
public class BaiduConfig {

    @Value("${" + BaiduPropertyKeys.BAIDU_ASR_APP_ID_KEY + ":}")
    @Getter
    @Setter
    private String appId;

    @Value("${" + BaiduPropertyKeys.BAIDU_ASR_API_KEY_KEY + ":}")
    @Getter
    @Setter
    private String apikey;

    @Value("${" + BaiduPropertyKeys.BAIDU_ASR_SECRET_KEY_KEY + ":}")
    @Getter
    @Setter
    private String secretekey;
}
