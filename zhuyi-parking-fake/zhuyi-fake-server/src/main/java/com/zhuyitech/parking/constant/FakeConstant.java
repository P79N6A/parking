package com.zhuyitech.parking.constant;

import com.scapegoat.infrastructure.config.FundamentalConfigProvider;

/**
 * Rabbit配置常量
 *
 * @author walkman
 */
public final class FakeConstant {

    public static final String RABBITMQ_HOST = FundamentalConfigProvider.getString(FakePropertieskey.RABBITMQ_HOST_KEY,
            "127.0.0.1");

    public static final Integer RABBITMQ_PORT = FundamentalConfigProvider.getInt(FakePropertieskey.RABBITMQ_PORT_KEY, 5672);

    public static final String RABBITMQ_USERNAME = FundamentalConfigProvider.getString(FakePropertieskey.RABBITMQ_USERNAME_KEY, "guest");

    public static final String RABBITMQ_PASSWORD = FundamentalConfigProvider.getString(FakePropertieskey.RABBITMQ_PASSWORD_KEY, "guest");

    public static final String RABBITMQ_VHOST = FundamentalConfigProvider.getString(FakePropertieskey.RABBITMQ_VHOST_KEY, "/");

    public static final String INMOTION_CMD = "sendParkStatu";

}
