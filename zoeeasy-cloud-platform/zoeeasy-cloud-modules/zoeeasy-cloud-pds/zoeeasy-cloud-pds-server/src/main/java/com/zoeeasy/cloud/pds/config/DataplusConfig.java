package com.zoeeasy.cloud.pds.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhj
 */
@Configuration
@MapperScan("com.zoeeasy.cloud.pds.mapper")
public class DataplusConfig {
}
