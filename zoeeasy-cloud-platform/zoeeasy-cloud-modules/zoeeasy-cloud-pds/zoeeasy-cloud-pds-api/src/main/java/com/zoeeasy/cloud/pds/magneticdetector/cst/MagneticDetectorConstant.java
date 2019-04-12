package com.zoeeasy.cloud.pds.magneticdetector.cst;

/**
 * 地磁检测器相关常量定义
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
public final class MagneticDetectorConstant {

    private MagneticDetectorConstant() {
    }
    public static final int  MAGNETIC_DETECTOR_PORT_MIN_LENGTH=0;

    public static final int  MAGNETIC_DETECTOR_PORT_MAM_LENGTH=65535;

    public static final String MAGNETIC_DETECTOR_PORT_LENGTH="端口号取值范围0~65535";

    public static final String SPECIAL_CHARACTER_PATTERN = "^((?!V|:|\\*|\\?|'|<|\\||‘|%|>|&|\").)*$";

    public static final String PARKING_INFO_PARKING_ID_NOT_NULL = "停车场ID为必填项";

    public static final String PARKING_LOT_ID_NOT_NULL = "泊位D为必填项";

    public static final String PARKING_NONENTITY = "停车场不存在";

    public static final String PARKING_AND_PARKING_LOT_MISMATCHING = "停车场与泊位不匹配";

    public static final String MAGNETIC_DETECTOR_SERIAL_NUMBER_NONENTITY = "设备序列号已存在";

    public static final String MAGNETIC_DETECTOR_SERIAL_NUMBER_NOT_NULL = "厂商设备序列号为必填项";

    public static final int MAGNETIC_DETECTOR_VERSION_NUMBER_MAX_LENGTH = 32;

    public static final String MAGNETIC_DETECTOR_VERSION_NUMBER_LENGTH_RANGE = "版本号输入有误，请输入0~32个字符！";

    public static final int MAGNETIC_DETECTOR_SERIAL_NUMBER_MIN_LENGTH = 1;

    public static final int MAGNETIC_DETECTOR_SERIAL_NUMBER_MAX_LENGTH = 32;

    public static final String MAGNETIC_DETECTOR_SERIAL_NUMBER_LENGTH_RANGE = "设备序列号输入有误，请输入1~32个字符！";

    public static final int MAGNETIC_DETECTOR_INSTALL_POSITION_MAX_LENGTH = 256;

    public static final String MAGNETIC_DETECTOR_INSTALL_POSITION_LENGTH_RANGE = "安装位置输入有误，请输入0~256个字符！";

    public static final int MAGNETIC_DETECTOR_CODE_MIN_LENGTH = 1;

    public static final int MAGNETIC_DETECTOR_CODE_MAX_LENGTH = 64;

    public static final String MAGNETIC_DETECTOR_CODE_LENGTH_RANGE = "地磁编号1`64个字符";

    public static final String MAGNETIC_DETECTOR_CODE_REGULAR = "^[0-9|a-z|A-Z]{2,64}$";

    public static final String MAGNETIC_DETECTOR_CODE_MESSAGE = "地磁编号不符合规则";

    public static final String MAGNETIC_DETECTOR_HEARTBEAT_INTEVAL_NOT_NULL = "心跳间隔为必填项";

    public static final String MAGNETIC_DETECTOR_INSTALLATION_TIME_NOT_NULL = "安装时间为必填项";

    public static final String MAGNETIC_DETECTOR_PROVIDER_NOT_NULL = "厂商为必填项";

    public static final String PROVIDER_NONENTITY = "厂商不存在";

    public static final String MAGNETIC_DETECTOR_CODE_NOT_NULL = "地磁检测器编号为必填项";

    public static final String MAGNETIC_DETECTOR_CODE_EXIST = "地磁检测器编号已存在";

    public static final String DETECTOR_ID_NOT_NULL = "检测器ID为必填项";

    public static final String MAGNETIC_DETECTOR_RELEVANCE_PARKING_LOT_ID_NOT_NULL = "泊位ID为必填项";

    public static final String MAGNETIC_DETECTOR_STATIC = "地磁检测器状态为必填项";

    public static final String MAGNETIC_DETECTOR_MANAGER_ID_NOT_NULL = "管理器ID为必填项";

    public static final String MAGNETIC_DETECTOR_YET_RELEVANCE_PARKING_LOT = "地磁已关联泊位";

    public static final String PARKING_LOT_NONENTITY = "泊位不存在";

    public static final String MAGNETIC_DETECTOR_NONENTITY = "设备不存在";

    public static final String PARKING_MISMATCHING = "停车场不匹配";

}
