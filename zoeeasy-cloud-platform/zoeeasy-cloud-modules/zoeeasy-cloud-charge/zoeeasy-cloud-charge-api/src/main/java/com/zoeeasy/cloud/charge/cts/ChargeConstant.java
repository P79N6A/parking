package com.zoeeasy.cloud.charge.cts;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
public final class ChargeConstant {

    private ChargeConstant() {
    }

    //validator
    public static final int SMALL_INT_MAX = 32767;
    public static final int INT_MAX = 999900;
    public static final int MIN = 2;
    public static final int MAX = 32;
    public static final int DESCRIBE_LENGTH = 512;
    public static final String LENGTH_RANGE = "请输入{min}-{max}个字符!不包含：V:*?\"<|'%>&";
    public static final String NAME_REGEX = "^((?!V|:|\\*|\\?|'|<|\\||‘|%|>|&|\").)*$";
    public static final String CODE_REGEX = "^[0-9|a-z|A-Z]{2,255}$";
    public static final String RULE_CODE_REGEX_ERROR_MESSAGE = "规则编号有误";
    public static final String DESCRIBE_REGEX_ERROR = "规则描述输入有误,请输入0-512个字符!不包含：V:*?\"<|'%>&";
    public static final String FREE_TIME_VALUE_OUT_OF_ROUND = "免费时长超出范围,请输入有效数字 数字范围在0~32700之间";
    public static final String RULE_NAME_REGEX_ERROR_MESSAGE = "规则名称输入有误,请输入1-32个字符!不包含：V:*?\"<|'%>&";
    public static final String PER_PRICE_VALUE_OUT_OF_ROUND = "按次收费金额超出范围,请输入小于等于327的数字";
    public static final String PRICE_VALUE_OUT_OF_ROUND = "时段收费单价超出范围,请输入小于等于9999的数字";
    public static final String DAY_TOP_AMOUNT_VALUE_OUT_OF_ROUND = "24小时封顶金额超出范围,请输入有效数字 数字范围在0~9999之间";
    public static final String TOP_AMOUNT_VALUE_OUT_OF_ROUND = "封顶金额超出范围,请输入有效数字 数字范围在0~9999之间";
    public static final String UNIT_TIME_VALUE_OUT_OF_ROUND = "最小计时单位超出范围,请输入小于等于32700的数字";
    public static final String PART_TIME_VALUE_OUT_OF_ROUND = "时段超出范围,请输入小于等于32700的数字";

    //holiday
    public static final String HOLIDAY_TYPE_NOT_STANDARD = "节假日类型不符合规范";
    public static final String HOLIDAY_TYPE_NOT_EMPTY = "节假日类型不能为空";
    public static final String HOLIDAY_NAME_NOT_STANDARD = "节假日名称不符合规范";
    public static final String HOLIDAY_NAME_NOT_EMPTY = "节假日名称不能为空";
    public static final String HOLIDAY_DATE_NOT_STANDARD = "节假日日期不符合规范";
    public static final String HOLIDAY_DATE_NOT_EMPTY = "节假日日期不能为空";
    public static final String HOLIDAY_DATE_LIMIT = "节假日只能配置当前或未来日期";
    public static final String HOLIDAY_CALENDAR_DELETE_DATE_NOT_STANDARD = "删除节假日日期不符合规范";
    public static final String HOLIDAY_CALENDAR_DELETE_DATE_NOT_EMPTY = "删除节假日日期不能为空";

    //charge
    public static final String CHARGE_RULE_NOT_EMPTY = "收费规则不能为空";
    public static final String CHARGE_RULE_ID_EMPTY = "收费规则ID不能为空";
    public static final String CHARGE_CODE_NOT_EMPTY = "收费规则编号不能为空";
    public static final String CHARGE_CODE_NOT_STANDARD = "收费规则编号不规范";
    public static final String CHARGE_NAME_NOT_EMPTY = "收费规则名称不能为空";
    public static final String CHARGE_NAME_NOT_STANDARD = "收费规则名称不规范";
    public static final String CHARGE_RULE_TYPE_NOT_EMPTY = "收费规则类型不能为空";
    public static final String CHARGE_RULE_TYPE_NOT_STANDARD = "收费规则类型不符合规范";
    public static final String CHARGE_CAR_TYPE_NOT_EMPTY = "车辆类型不能为空";
    public static final String CHARGE_CAR_TYPE_NOT_STANDARD = "车辆类型不规范";
    public static final String CHARGE_PARKING_LEVEL_NOT_EMPTY = "停车场等级不能为空";
    public static final String CHARGE_PARKING_LEVEL_NOT_STANDARD = "停车场等级不规范";
    public static final String CHARGE_HOLIDAY_TYPE_NOT_EMPTY = "假期类型不能为空";
    public static final String CHARGE_DELETE_ID_NOT_EMPTY = "删除收费规则id不能为空";
    public static final String PLATE_NUMBER_TYPE_NOT_EMPTY = "车牌类型不能为空";
    public static final String PASS_TIME_ERROR = "停车时间有误";
    public static final String PASS_TIME_NOT_EMPTY = "停车时间不能为空";
    public static final String PLATE_NUMBER_TYPE_NOT_STANDARD = "车牌类型不规范";
    public static final String PARKING_ID_NOT_NULL = "停车场id不能为空";
    public static final String PLATE_COLOR_NOT_STANDARD = "车牌颜色不符合规范";
    public static final String TRY_CAR_TYPE_NOT_STANDARD = "试算车辆类型不符合规范";
    public static final String TRY_CAR_TYPE_NOT_MATCH = "试算车辆类型不符合规范";
    public static final String TRY_PARKING_TIME_NOT_STANDARD = "试算停车时间不符合规范";
    public static final String TRY_PARKING_TIME_NOT_EMPTY = "试算停车时间不符合规范";
    public static final String PARKING_CHARGE_RULE_TIME_NOT_STANDARD = "上下线时间不符合规范";
    public static final String PLATE_NUMBER_NOT_EMPTY = "车牌号不能为空";
    public static final String PLATE_NUMBER_NOT_STANDARD = "车牌号车牌号不符合规范";
    public static final String DAY_TOP_AMOUNT_EMPTY = "启用24小时封顶金额时金额不能为空";
    public static final String TOP_AMOUNT_EMPTY = "启用封顶金额时金额不能为空";

    public static final String PARKING_CHARGE_RULE_TRY_LIST_EMPTY = "试算规则列表不能为空";
}
