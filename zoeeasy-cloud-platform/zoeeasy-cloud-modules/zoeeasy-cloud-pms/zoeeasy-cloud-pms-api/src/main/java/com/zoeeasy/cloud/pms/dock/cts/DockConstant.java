package com.zoeeasy.cloud.pms.dock.cts;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
public class DockConstant {

    private DockConstant() {
    }

    public static final int SMALL_INT_MAX = 32767;
    public static final int INT_MAX = 2147483647;
    public static final String TENANT_ID_EMPTY = "租户id为空";
    public static final String TOKEN_START_TIME_NOT_STANDARD = "token有效开始时间不规范";
    public static final String TOKEN_END_TIME_NOT_STANDARD = "token有效结束时间不规范";
    public static final String CLIENT_EXIST = "客户端系统已经注册";
    public static final String TERMINATE_CODE_EMPTY = "对接终端编号不能为空";
    public static final String TERMINATE_NAME_EMPTY = "对接终端名称不能为空";
    public static final String TERMINATE_VERSION_EMPTY = "对接终端版本号不能为空";
    public static final String PLATFORM_IP_EMPTY = "对接终端/平台的IP不能为空";
    public static final String PLATFORM_PORT_EMPTY = "对接终端/ 对接终端/平台的端口不能为空";
    public static final String PLACE_ORDER_URL_EMPTY = "对接终端/平台注册的账单下发请求URL不能为空";
    public static final String NOTIFY_ORDER_URL_EMPTY = "对接终端/平台注册的账单支付通知URL不能为空";
    public static final String OPEN_STROBE_URL_EMPTY = "对接终端/平台注册的远程开闸请求接口URL不能为空";
    public static final String HEART_BEAT_INTERVAL_EMPTY = "心跳时间间隔：单位秒不能为空";
    public static final String NET_ZONE_TYPE_NOT_STANDARD = "请求接入的网域类型";
    public static final String PORT_VALUE_OUT_RANGE = "端口号输入错误,请输入0~2147483647范围内的数字";
    public static final String HEART_VALUE_OUT_RANGE = "心跳间隔输入错误,请输入0~23767范围内的数字";

}
