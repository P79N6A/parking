package com.zoeeasy.cloud.pms.lane.cst;

public final class ParkingLaneConstant {

    public static final String PARKING_ID_NOT_NULL = "停车场ID不能为空";
    public static final String PARKING_LANE_CODE_NOT_NULL = "车道编号不能为空";
    public static final String PARKING_LANE_NAME_NOT_NULL = "车道名称不能为空";
    public static final int PARKING_LANE_CODE_MIN_LENGTH = 2;
    public static final int PARKING_LANE_CODE_MAX_LENGTH = 32;
    public static final String PARKING_LANE_CODE_LENGTH_RANGE = "车道编号应{min}-{max}个字符之间";
    public static final int LANE_CODE_MIN_LENGTH = 2;
    public static final int LANE_CODE_MAX_LENGTH = 20;
    public static final String LANE_CODE_LENGTH_RANGE = "客户端车道编号应{min}-{max}个字符之间";
    public static final int PARKING_LANE_NAME_MIN_LENGTH = 2;
    public static final int PARKING_LANE_NAME_MAX_LENGTH = 32;
    public static final String PARKING_LANE_NAME_LENGTH_RANGE = "车道名称应在{max}个字符之内";
    public static final String PARKING_LANE_DIRECTION_NOT_NULL = "车道类型不能为空";
    public static final int PARKING_LANE_REMARKS_MAX_LENGTH = 2000;
    public static final String PARKING_LANE_REMARKS_LENGTH_RANGE = "备注应在{max}个字符之内";
    public static final String PARKING_NOT_EXIT = "停车场不存在";
    public static final String PARKING_GARAGE_NOT_EXIT = "车库不存在";
    public static final String PARKING_GATE_NOT_EXIT = "出入口不存在";
    public static final String PARKING_LANE_NOT_EXIST_PARKING_GARAGE = "车道不属于此车库";
    public static final String PARKING_LANE_NOT_EXIST_PARKING_GATE = "车道不属于此出入口";
    public static final String PARKING_GARAGE_NOT_EXIST_PARKING_GATE = "车库不存在此出入口";
    public static final String PARKING_LANE_NOT_EXIT = "车道不存在";
    public static final String PARKING_LANE_CODE_EXIT = "车道编码已存在";
    public static final String PARKING_LANE_NAME_EXIT = "车道名称已存在";
    public static final String PARKING_LANE_TYPE_NOT_EXIT = "车道类型不存在";
    public static final String PARKING_LANE_CODE_PARTTERN = "[a-zA-Z0-9]{2,32}";
    public static final String LANE_CODE_PARTTERN = "[a-zA-Z0-9]{2,20}";
    public static final String PARKING_LANE_CODE_ILLEGAL = "车道编号不符合规范";
    public static final String PARKING_LANE_NAME_PARTTERN = "[\\u4e00-\\u9fa5a-zA-Z0-9]{1,32}";
    public static final String PARKING_LANE_NAME_ILLEGAL = "车道名称不符合规范";
    public static final String PARKING_GATE_NOT_NULL = "出入口为空";
    public static final String PARKING_GARAGE_NOT_NULL = "车库为空";
    public static final int PARKING_LANE_COUNT_CHANGE = 1;
    public static final String PARKING_LANE_ID_CAN_NOT_NULL = "车道ID不能为空";

    private ParkingLaneConstant() {
    }

}
