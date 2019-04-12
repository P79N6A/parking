package com.zoeeasy.cloud.pms.gate.cst;

public final class ParkingGateConstant {

    public static final String PARKING_ID_NOT_NULL = "停车场ID不能为空";
    public static final String PARKING_GATE_CODE_NOT_NULL = "出入口编号不能为空";
    public static final String PARKING_GATE_NAME_NOT_NULL = "出入口名称不能为空";
    public static final int PARKING_GATE_CODE_MIN_LENGTH = 2;
    public static final int PARKING_GATE_CODE_MAX_LENGTH = 32;
    public static final int GATE_CODE_MIN_LENGTH = 2;
    public static final int GATE_CODE_MAX_LENGTH = 20;
    public static final String GATE_CODE_LENGTH_RANGE = "客户端出入口编号应{min}-{max}个字符之间";
    public static final String PARKING_GATE_CODE_LENGTH_RANGE = "出入口编号应{min}-{max}个字符之间";
    public static final int PARKING_GATE_NAME_MAX_LENGTH = 32;
    public static final String PARKING_GATE_NAME_LENGTH_RANGE = "出入口名称应在{max}个字符之内";
    public static final String PARKING_GATE_DIRECTION_NOT_NULL = "出入口类型不能为空";
    public static final int PARKING_GATE_REMARKS_MAX_LENGTH = 2000;
    public static final String PARKING_GATE_REMARKS_LENGTH_RANGE = "备注应在{max}个字符之内";
    public static final String PARKING_NOT_EXIT = "停车场不存在";
    public static final String PARKING_GARAGE_NOT_EXIT = "车库不存在";
    public static final String PARKING_GATE_NOT_EXIT = "出入口不存在";
    public static final String PARKING_GATE_CODE_EXIT = "出入口编号已存在";
    public static final String PARKING_GATE_NAME_EXIT = "出入口名称已存在";
    public static final String PARKING_GATE_TYPE_NOT_EXIT = "出入口类型不存在";
    public static final String PARKING_GATE_CODE_PARTTERN = "[a-zA-Z0-9]{2,32}";
    public static final String GATE_CODE_PARTTERN = "[a-zA-Z0-9]{2,20}";
    public static final String PARKING_GATE_CODE_ILLEGAL = "出入口编号不符合规范";
    public static final String PARKING_GATE_NAME_PARTTERN = "[\\u4e00-\\u9fa5a-zA-Z0-9]{1,32}";
    public static final String PARKING_GATE_NAME_ILLEGAL = "出入口名称不符合规范";
    public static final String PARKING_GATE_HAVE_SUBSET = "出入口存在子集,不能删除";
    public static final String PARKING_GATE_ID_CAN_NOT_NULL = "出入口ID不能为null";
    private ParkingGateConstant() {

    }
}
