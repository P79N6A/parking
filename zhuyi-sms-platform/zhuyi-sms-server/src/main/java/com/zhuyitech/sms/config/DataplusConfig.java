package com.zhuyitech.sms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * DataMapper 配置
 *
 * @author walkman
 */
@Configuration
@MapperScan("com.zhuyitech.sms.mapper")
public class DataplusConfig {

}
