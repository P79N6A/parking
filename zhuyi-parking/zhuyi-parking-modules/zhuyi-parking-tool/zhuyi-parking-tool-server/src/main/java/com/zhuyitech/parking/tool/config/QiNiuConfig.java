package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.QiNiuPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * QINIU配置属性
 *
 * @author zwq
 */
@Configuration
public class QiNiuConfig {

    @Value("${" + QiNiuPropertyKeys.QINIU_ACCESSKAY + ":}")
    @Getter
    @Setter
    private String accesskey;

    @Value("${" + QiNiuPropertyKeys.QINIU_SECRETKAY + ":}")
    @Getter
    @Setter
    private String secretekey;

    @Value("${" + QiNiuPropertyKeys.QINIU_BUCKET + ":}")
    @Getter
    @Setter
    private String bucket;

    @Value("${" + QiNiuPropertyKeys.QINIU_URL + ":}")
    @Getter
    @Setter
    private String url;
}
