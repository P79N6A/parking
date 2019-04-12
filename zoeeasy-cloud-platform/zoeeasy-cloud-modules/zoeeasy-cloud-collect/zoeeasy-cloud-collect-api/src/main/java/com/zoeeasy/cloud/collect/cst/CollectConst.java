package com.zoeeasy.cloud.collect.cst;

/**
 * @author wf
 */
public class CollectConst {
    /**
     * 默认服务器地址
     */
    public static final String DEFAULT_SERVER = "0.0.0.0";

    /**
     * 默认监听端口
     */
    public static final int DEFAULT_PORT = 10578;

    /**
     * 心跳超时时间
     */
    public static final int TIMEOUT = 5000;

    /**
     * 编码方式
     */
    public static final String CHARSET = "utf-8";

    /**
     * 报文头长度
     */
    public static final Integer MESSAGE_HEAD_LENGTH = 4;

    /**
     * 处理类判断字段
     */
    public static final String BIZ_SERVICE = "service";

    /**
     * 心跳回复在线
     */
    public static final String HEARTBEAT_MESSAGE = "在线";

    /**
     * 注册接口认证成功
     */
    public static final String CHECKKEY_MESSAGE_SUCCESS = "认证成功";

    /**
     * 注册接口认证识别
     */
    public static final String CHECKKEY_MESSAGE_FAIL = "认证失败";

    /**
     * 注册接口失败：ParkKey异常
     */
    public static final String CHECKKEY_MESSAGE_INVALIDKEY = "认证失败，ParkKey异常";

    /**
     * 注册接口认证失败：无此车场
     */
    public static final String CHECKKEY_MESSAGE_NOPARK = "认证失败，无此车场";

    /**
     * Apollo配置文件名
     */
    public static final String COLLECT_PROPERTIES = "collect";

    /**
     * Apollo配置文件：停车场Key
     */
    public static final String COLLECT_PARKKEY = "collect.parkkey";

    /**
     * Apollo配置文件：TCP服务端端口号
     */
    public static final String COLLECT_SERVER_PORT = "collect.server.port";

    /**
     * TIO服务端启动类Bean名字
     */
    public static final String COLLECT_SERVER_STARTER_BEAN = "collectServerBean";

}
