package com.zoeeasy.cloud.pds.magneticmanager.cst;

/**
 * 地磁管理器相关常量定义
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
public final class MagneticManagerConstant {

    private MagneticManagerConstant() {
    }

    public static final String LATITUDE_PATTERN = "(-|\\+)?(90\\.0{0,6}|(\\d|[1-8]\\d)\\.\\d{0,6})";

    public static final String LATITUDE_ILLEGAL = "纬度输入有误，请输入1~10个字符！请输入-90~90的数字，允许使用小数点和负数";

    public static final String LONGITUDE_PATTERN = "^(-|\\+)?(180\\.0{0,6}|(\\d{1,2}|1([0-7]\\d))\\.\\d{0,6})$";

    public static final String LONGITUDE_ILLEGAL = "经度输入有误，请输入1~11个字符！请输入-180~180的数字，允许使用小数点和负数";

    public static final String PARKING_INFO_PARKING_ID_NOT_NULL = "停车场ID为必填项";

    public static final String PARKING_NONENTITY = "停车场不存在";

    public static final String MAGNETIC_MANAGER_SERIAL_NUMBER_NOT_NULL = "设备序列号为必填项";

    public static final String MAGNETIC_MANAGER_ID_NOT_NULL = "设备管理器ID为必填项";

    public static final String UNBIND_MAGNETIC_DETECTOR_MANAGER = "请先解绑地磁管理器";

    public static final String MAGNETIC_MANAGER_SERIAL_NUMBER_YET_EXIST = "厂商设备序列号已经存在";

    public static final int MAGNETIC_MANAGER_SERIAL_NUMBER_MIN_LENGTH = 1;

    public static final int MAGNETIC_MANAGER_SERIAL_NUMBER_MAX_LENGTH = 32;

    public static final String MAGNETIC_MANAGER_SERIAL_NUMBER_LENGTH_RANGE = "设备序列号输入有误，请输入1~32个字符！";

    public static final int MAGNETIC_MANAGER_INSTALL_POSITION_MAX_LENGTH = 256;

    public static final String MAGNETIC_MANAGER_INSTALL_POSITION_LENGTH_RANGE = "安装位置输入有误，请输入0~256个字符！不包含：V:*?\"<|'%>&";

    public static final int MAGNETIC_MANAGER_SIM_NO_MAX_LENGTH = 32;

    public static final String MAGNETIC_MANAGER_SIM_NO_LENGTH_RANGE = "SIM卡号输入有误，请输入0~32个字符！不包含：V:*?\"<|'%>&";

    public static final String MAGNETIC_MANAGER_CODE_REGULAR = "^[0-9|a-z|A-Z]{2,64}$";

    public static final String MAGNETIC_MANAGER_CODE_MESSAGE = "管理器编号不符合规则";

    public static final String MAGNETIC_MANAGER_PORT_NOT_NULL = "端口号为必填项";

    public static final String MAGNETIC_MANAGER_IP_ADDRESS_NOT_NULL = "IP地址为必填项";

    public static final String MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_NOT_NULL = "心跳间隔为必填项";

    public static final int MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MIN_LENGTH = 1;

    public static final int MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MAX_LENGTH = 9999;

    public static final String MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_LENGTH = "心跳间隔输入有误，请输入1~9999的整数";

    public static final String MAGNETIC_MANAGER_INSTALLATION_TIME_NOT_NULL = "安装时间为必填项";

    public static final String MAGNETIC_MANAGER_PROVIDER_NOT_NULL = "厂商为必填项";

    public static final String MAGNETIC_MANAGER_CODE_NOT_NULL = "地磁管理器编号为必填项";

    public static final String MAGNETIC_MANAGER_CODE_EXIST = "地磁管理器编号已存在";

    public static final String RELEVANCE_MAGNETIC_MANAGER_ID_NOT_NULL = "关联地磁管理器ID为必填项";

    public static final String MAGNETIC_MANAGER_NONENTITY = "地磁管理器不存在";
}
