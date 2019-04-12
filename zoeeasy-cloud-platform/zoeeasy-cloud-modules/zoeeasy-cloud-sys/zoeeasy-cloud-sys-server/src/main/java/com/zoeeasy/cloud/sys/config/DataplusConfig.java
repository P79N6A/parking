package com.zoeeasy.cloud.sys.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper 配置
 *
 * @author walkman
 */
@Configuration
@MapperScan("com.zoeeasy.cloud.sys.mapper")
public class DataplusConfig {

}
