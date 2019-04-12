package com.zoeeasy.cloud.pms.specialvehicle.cst;

/**
 * Created by song on 2018/9/11.
 */
public final class SpecialVehicleConstant {

    public static final int PACKET_NAME_MIN_LENGTH = 1;
    public static final int PACKET_NAME_MAX_LENGTH = 32;
    public static final int INT_MAX = 999990;
    public static final String PACKET_RULE_PACKET_NAME_NOT_NULL = "包期规则名称为必填项";
    public static final String PACKET_RULE_PACKET_NAME_ILLEGAL = "包期规则名称输入有误，请输入1~32个字符！不包含：V:*?\"<|'%>&";
    public static final String PACKET_RULE_PACKET_NAME_EXIST = "包期规则名称已经存在";
    public static final String PACKET_VEHICLE_ALL_PARKING_NOT_NULL = "是否是全部停车场为必填项";
    public static final String PACKET_VEHICLE_ALL_PARKING_ERROR = "是否全部停车场输入有误";
    public static final String PACKET_RULE_PACKET_NAME_NOT_TO_DELETE = "规则正在使用不能删除";
    public static final String PACKET_RULE_PACKET_NAME_NOT_TO_EDIT = "规则正在使用不能修改";
    public static final String PACKET_RULE_PACKET_NAME_NOT_EXIST = "包期规则不存在";
    public static final String PACKET_RULE_PACKET_TYPE_NOT_MATE = "包期规则与包期类型不匹配";
    public static final String PACKET_VEHICLE_EXIST = "此车已添加过此类包期车";
    public static final String PACKET_RULE_PRICE_NOT_NULL = "包期金额为必填项";
    public static final String PACKET_RULE_PRICE_ERROR = "包期金额超出范围，请输入有效金额，数字范围在0~9999.9之间";
    public static final String PACKET_VEHICLE_PLATE_COLOR_ERROR = "车牌颜色输入有误";
    public static final String PACKET_VEHICLE_PACKET_TYPE_NOT_NULL = "包期类型为必填项";
    public static final String PACKET_VEHICLE_PACKET_TYPE_ERROR = "包期类型输入有误";
    public static final String PACKET_VEHICLE_AREA_CODE_NOT_NULL = "区域code为必填项";
    public static final String PACKET_VEHICLE_BEGIN_DATE_NOT_NULL = "开始时间为必填项";
    public static final String PACKET_VEHICLE_END_DATE_NOT_NULL = "结束时间为必填项";
    public static final String BEGIN_TIME_NOT_GT_END_TIME = "开始时间不能大于结束时间";
    public static final String PACKET_VEHICLE_PLATE_NUMBER_NOT_NULL = "车牌号码为必填项";
    public static final int PACKET_VEHICLE_PLATE_NUMBER_MIN_LENGTH = 2;
    public static final int PACKET_VEHICLE_PLATE_NUMBER_MAX_LENGTH = 10;
    public static final String PACKET_VEHICLE_PLATE_NUMBER_LENGTH_RANGE = "车牌号码{min}-{max}个字符";
    public static final String PACKET_VEHICLE_CAR_TYPE_NOT_NULL = "车辆类型为必填项";
    public static final String PACKET_VEHICLE_PLATE_COLOR_NOT_NULL = "车牌颜色为必填项";
    public static final String PACKET_VEHICLE_PLATE_TYPE_NOT_NULL = "车牌类型为必填项";
    public static final String PACKET_VEHICLE_SPECIAL_SYMBOLS = "^((?!V|:|\\*|\\?|'|<|\\||‘|%|>|&|\").)*$";
    public static final String PACKET_VEHICLE_SPECIAL_SYMBOLS_ILLEGAL = "车主姓名输入有误，请输入1~14个字符！不包含：V:*?\"<|'%>&";
    public static final String PACKET_VEHICLE_OWNER_NAME_NOT_NULL = "车主姓名为必填项";
    public static final int PACKET_VEHICLE_OWNER_NAME_MIN_LENGTH = 1;
    public static final int PACKET_VEHICLE_OWNER_NAME_MAX_LENGTH = 14;
    public static final String PACKET_VEHICLE_OWNER_PHONE_NOT_NULL = "车主手机为必填项";
    public static final int PACKET_VEHICLE_OWNER_PHONE_MIN_LENGTH = 1;
    public static final int PACKET_VEHICLE_OWNER_PHONE_MAX_LENGTH = 32;
    public static final String PACKET_VEHICLE_OWNER_PHONE_LENGTH_RANGE = "车主手机{min}-{max}个字符";
    public static final String PACKET_VEHICLE_OWNER_CARD_NO_NOT_NULL = "车主身份证号为必填项";
    public static final String SPECIAL_VEHICLE_OWNER_PHONE_LENGTH_RANGE = "车主电话最多{max}个字符";
    public static final int PACKET_VEHICLE_OWNER_CARD_NO_MIN_LENGTH = 1;
    public static final int PACKET_VEHICLE_OWNER_CARD_NO_MAX_LENGTH = 64;
    public static final String PACKET_VEHICLE_OWNER_CARD_NO_LENGTH_RANGE = "车主身份证号{min}-{max}个字符";
    public static final int PACKET_VEHICLE_OWNER_ADDRESS_MAX_LENGTH = 25;
    public static final String PACKET_VEHICLE_OWNER_ADDRESS_LENGTH_RANGE = "车主地址输入有误,请输入0~25个字符！不包含：V:*?\"<|'%>&";
    public static final int PACKET_VEHICLE_OWNER_EMAIL_MAX_LENGTH = 64;
    public static final String PACKET_VEHICLE_OWNER_EMAIL_LENGTH_RANGE = "车主邮件最多{max}个字符";
    public static final String APPROVE_STATUS_ENUM_ERROR = "审核意见输入有误";
    public static final String REJECT_REASON_ENUM_ERROR = "驳回原因输入有误";
    public static final String PARKING_ID_NOT_EXIST = "停车场不存在";
    public static final String BLACKLIST_NOT_EXIST = "黑名单不存在";
    public static final String SPECIAL_VEHICLE_CAR_COLOR_ERROR = "车辆颜色输入有误";
    public static final String SPECIAL_VEHICLE_CAR_TYPE_ERROR = "车辆类型输入有误";
    public static final String SPECIAL_VEHICLE_PLATE_TYPE_ERROR = "车牌类型输入有误";
    public static final String SPECIAL_VEHICLE_VISIT_TYPE_ERROR = "访客车类型输入有误";
    public static final String VISIT_VEHICLE_NOT_EXIST = "访客车不存在";
    public static final String EFFECTED_STATUS_ERROR = "生效状态输入有误";
    public static final String SPECIAL_VEHICLE_FIXED_TYPE_ERROR = "固定车类型输入有误";
    public static final String PARKING_LOT_BINDING = "泊位已经绑定固定车";
    public static final String FIXED_VEHICLE_NOT_EXIST = "固定车不存在";
    public static final String PARKING_LOT_NOT_EXIST = "泊位不存在";
    public static final String SPECIAL_TYPE_EXIST = "此车已经是特殊车";
    public static final String WHITELIST_NOT_EXIST = "白名单不存在";
    public static final String SPECIAL_VEHICLE_VISIT_TYPE_NOT_NULL = "访客车类型为必填项";
    public static final String SPECIAL_VEHICLE_FIXED_TYPE_NOT_NULL = "固定车类型为必填项";
    public static final String SPECIAL_VEHICLE_PARKING_ID_NOT_NULL = "停车场为必填项";
    public static final int SPECIAL_VEHICLE_REMARK_MAX_LENGTH = 32;
    public static final String PACKET_VEHICLE_REMARK_ILLEGAL = "备注输入有误,请输入0~32个字符！不包含：V:*?\"<|'%>&";
    public static final String PACKET_RULE_NOT_EMPTY = "包期规则不能为空";
    public static final String CARD_NO = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
    public static final String CARD_NO_ILLEGAL = "身份证格式不正确";


    private SpecialVehicleConstant() {
    }

}
