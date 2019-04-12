package com.zoeeasy.cloud.pms.park.config;

import com.zoeeasy.cloud.pms.park.cst.ParkingPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * pms属性配置
 *
 * @author wangfeng
 */
@Configuration
public class ParkingConfig {

    @Value("${" + ParkingPropertyKeys.WECHAT_QRCODE_URL_TEMPLET + ":}")
    @Getter
    @Setter
    private String templet;

}
