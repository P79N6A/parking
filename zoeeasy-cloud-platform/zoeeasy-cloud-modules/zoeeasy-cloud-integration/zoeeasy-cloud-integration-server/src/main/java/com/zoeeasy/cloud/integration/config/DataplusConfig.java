package com.zoeeasy.cloud.integration.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper 配置
 *
 * @author walkman
 */
@Configuration
@MapperScan("com.zoeeasy.cloud.integration.mapper")
public class DataplusConfig {

}
