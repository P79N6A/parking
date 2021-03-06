package com.zhuyitech.parking.ucc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapper 配置
 *
 * @author walkman
 */
@Configuration
@MapperScan("com.zhuyitech.parking.ucc.mapper")
public class DataplusConfig {

}
