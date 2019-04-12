package com.zoeeasy.cloud.fina.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper 配置
 *
 * @author walkman
 */
@Configuration
@MapperScan("com.zoeeasy.cloud.fina.mapper")
public class DataplusConfig {

}
