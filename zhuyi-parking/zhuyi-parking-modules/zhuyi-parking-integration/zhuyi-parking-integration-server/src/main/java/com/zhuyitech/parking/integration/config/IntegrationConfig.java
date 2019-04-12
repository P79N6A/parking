package com.zhuyitech.parking.integration.config;

import com.zhuyitech.parking.integration.constant.IntegrationKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 常量类
 *
 * @author AkeemSuper
 * @date 2018/7/13 0013
 */
@Configuration
public class IntegrationConfig {

    /**
     * 是否人工审核
     */
    @Value("${" + IntegrationKeys.MANUAL_AUDIT + ":}")
    @Getter
    @Setter
    private Boolean manualAudit;
}
