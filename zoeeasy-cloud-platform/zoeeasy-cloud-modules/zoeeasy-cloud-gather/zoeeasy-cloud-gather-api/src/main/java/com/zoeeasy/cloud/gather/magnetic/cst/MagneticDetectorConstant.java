package com.zoeeasy.cloud.gather.magnetic.cst;

/**
 * 地磁检测器相关常量定义
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
public final class MagneticDetectorConstant {

    private MagneticDetectorConstant() {
    }

    public static final String MAGNETIC_DETECTOR_ID_NOT_NULL = "检测器ID不能为空";

    public static final String MAGNETIC_MAGNETIC_PROVIDER_NOT_NULL = "地磁管理器类型(厂商)不能为空";

    public static final String MAGNETIC_DETECTOR_TENANT_ID_NOT_NULL = "租户ID不能为空";

    public static final String MAGNETIC_DETECTOR_PARKING_ID_NOT_NULL = "停车场ID不能为空";

    public static final String MAGNETIC_DETECTOR_PARKING_LOT_ID_NOT_NULL = "泊位ID不能为空";

    public static final String MAGNETIC_DETECTOR_DETECTOR_ID_NOT_NULL = "检测器ID不能为空";

    public static final String MAGNETIC_DETECTOR_SERIAL_NUMBER_NOT_NULL = "设备序列号不能为空";

    public static final int MAGNETIC_DETECTOR_SERIAL_NUMBER_MIN_LENGTH = 1;

    public static final int MAGNETIC_DETECTOR_SERIAL_NUMBER_MAX_LENGTH = 50;

    public static final String MAGNETIC_DETECTOR_SERIAL_NUMBER_LENGTH_RANGE = "厂商设备序列号{min}-{max}个字符";

    public static final int MAGNETIC_DETECTOR_COMMAND_CODE_MIN_LENGTH = 1;

    public static final int MAGNETIC_DETECTOR_COMMAND_CODE_MAX_LENGTH = 20;

    public static final String MAGNETIC_DETECTOR_COMMAND_CODE_LENGTH_RANGE = "指令编码为{min}-{max}个字符";

    public static final String MAGNETIC_DETECTOR_BEAT_TIME_NOT_NULL = "心跳时间不能为空";

    public static final String MAGNETIC_DETECTOR_OCCUPY_STATUS_NOT_NULL = "占用状态不能为空";

    public static final int MAGNETIC_DETECTOR_BATTERY_MIN_LENGTH = 0;

    public static final int MAGNETIC_DETECTOR_BATTERY_MAX_LENGTH = 100;

    public static final String MAGNETIC_DETECTOR_BATTERY_LENGTH_RANGE = "电量值区间为0-100";

    public static final String MAGNETIC_DETECTOR_PROVIDER_NOT_NULL = "厂商不能为空";

    public static final String MAGNETIC_DETECTOR_CHANGE_TIME_NOT_NULL = "变更时间不能为空";

    public static final String MAGNETIC_DETECTOR_CHANGE_TYPE_NOT_NULL = "泊位业务变更原因不能为空";
}
