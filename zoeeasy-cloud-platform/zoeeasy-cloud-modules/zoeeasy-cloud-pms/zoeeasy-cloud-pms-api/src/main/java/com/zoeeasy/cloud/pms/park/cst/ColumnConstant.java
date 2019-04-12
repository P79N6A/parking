package com.zoeeasy.cloud.pms.park.cst;

public class ColumnConstant {

    private ColumnConstant(){}

    public static final String PARKING_ID = "parkingId";
    public static final String ID = "id";
    public static final String LOTTYPE = "lotType like concat('%', concat({0}, '%'))";
    public static final String RULE_ID = "ruleId";
    public static final String EFFECTED_STATUS = "effectedStatus";
    public static final String PLATE_NUMBER = "plateNumber";
    public static final String PARKING_LOT_ID = "parkingLotId";
    public static final String STATUS = "status";
    public static final String CODE = "code";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String BIZ_TYPE = "bizType";
    public static final String BIZ_NO = "bizNo";
    public static final String BIZ_ID = "bizId";

}
