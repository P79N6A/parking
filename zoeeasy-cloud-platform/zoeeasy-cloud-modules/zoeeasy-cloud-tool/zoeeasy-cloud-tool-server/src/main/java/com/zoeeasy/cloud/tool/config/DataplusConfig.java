package com.zoeeasy.cloud.tool.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper 配置
 *
 * @author walkman
 */
@Configuration
@MapperScan("com.zoeeasy.cloud.tool.mapper")
public class DataplusConfig {

}
