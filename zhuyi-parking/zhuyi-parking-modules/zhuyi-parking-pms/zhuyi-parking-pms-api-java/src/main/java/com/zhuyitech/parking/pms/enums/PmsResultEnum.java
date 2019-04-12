package com.zhuyitech.parking.pms.enums;

import lombok.Getter;

/**
 * PMS结果枚举
 *
 * @author walkman
 */
public enum PmsResultEnum {

    //车品牌
    CAR_BRAND_NAME_EXISTS(20200, "车型名称已经存在"),
    CAR_BRAND_NAME_EMPTY(20201, "车型名称为空"),
    CAR_BRAND_NOT_FOUND(20202, "车型名称不存在"),
    CAR_BRAND_PARENT_NOT_FOUND(20202, "车型名称不存在"),
    CAR_BRAND_CHILDREN_EXISTS(20203, "存在子车型，不允许删除"),
    DEPTH_EMPTY(20204, "深度为空"),

    //停车场
    PARKING_CODE_EMPTY(20301, "停车场编码为空"),
    PARKING_CODE_EXISTS(20302, "停车场编码已经存在"),
    PARKING_NAME_EMPTY(20303, "停车场名称为空"),
    PARKING_NAME_EXISTS(20304, "停车场名称已经存在"),
    PARKING_NOT_FOUND(20305, "停车场不存在"),
    PARKING_HIK_EXISTS(20306, "海康平台停车场编号已存在"),
    PARKING_ALI_EXISTS(20307, "支付宝平台停车场编号已存在"),
    PARKING_ID_EMPTY(20308, "停车场Id为空"),
    PARKING_ADDRESS_GET_ERROR(20309, "停车场地址获取失败"),

    PARKING_LOT_CODE_EMPTY(20330, "车位编码为空"),
    PARKING_LOT_CODE_EXISTS(20331, "车位编码已经存在"),
    PARKING_LOT_NAME_EMPTY(20332, "车位名称为空"),
    PARKING_LOT_NAME_EXISTS(20333, "车位名称已经存在"),
    PARKING_LOT_NOT_FOUND(20334, "车位不存在"),
    PARKING_LOT_HIK_EXISTS(20335, "海康平台车位编号已存在"),
    PARKING_LOT_ALI_EXISTS(20336, "支付宝平台车位编号已存在"),

    //车牌前缀
    LICENSE_PREFIX_NAME_EMPTY(20401, "名称为空"),
    LICENSE_PREFIX_PARENTNUM_EXISTS(20402, "车牌前缀已经存在"),
    LICENSE_PREFIX_CHILDREN_EXISTS(20403, "存在子车牌前缀，不允许删除"),
    LICENSE_PREFIX_NOT_FOUND(20404, "车牌前缀不存在"),
    LICENSE_PREFIX_TYPE_EMPTY(20405, "类型为空"),
    LICENSE_PREFIX_TYPE_ERR(20406, "类型错误"),
    LICENSE_PREFIX_TYPE_ERROR(20407, "前缀不能有父ID"),
    LICENSE_INVALID(20408, "无效车牌"),

    //收费规则
    CHARGE_RULE_CODE_EMPTY(20501, "规则编号为空"),
    CHARGE_RULE_RUlE_NAME_EMPTY(20502, "规则名称为空"),
    CHARGE_RULE_CAR_TYPE_EMPTY(20503, "车辆类型为空"),
    CHARGE_RULE_PARKING_LEVEL_EMPTY(20504, "停车场等级为空"),
    CHARGE_RULE_HOLIDAY_TYPE_EMPTY(20505, "停车场等级为空"),
    CHARGE_RULE_RULE_TYPE_EMPTY(20506, "收费规则类型为空"),
    CHARGE_RULE_CODE_EXIST(20507, "规则编号已存在"),
    CHARGE_RULE_NOT_EXIST(20508, "收费规则不存在"),
    CHARGE_RULE_ID_EMPTY(20509, "收费规则ID为空"),
    CHARGE_RULE_TYPE_NOT_MODIFY(20510, "收费规则类型不能修改"),
    PLATE_NUMBER_TYPE_EMPTY(20511, "车牌类型为空"),
    PARKING_CHARGE_RULE_NOT_FOUND(20512, "停车规则为空"),
    CHARGE_RULE_PART_TIMES_EMPTY(20513, "收费时段为空"),
    CHARGE_RULE_PART_TIMES_TIME_ERROR(20514, "时间段无效"),
    CHARGE_RULE_PART_TIMES_PRICE_ERROR(20515, "时间段价格无效"),
    CHARGE_RULE_PART_TIMES_REPEAT(20516, "收费时间段重复"),
    CHARGE_RULE_TIMES_COUNT_ERROR(20517, "计时时间范围无效"),
    CHARGE_RULE_TIMES_RANGE_ERROR(20518, "计次时间范围无效"),

    //停车记录
    PARKING_RECORD_NOT_FOUND(206100, "停车记录不存在"),
    PASSING_RECORD_ENTRY_NOT_FOUND(206101, "入车记录不存在"),
    PASSING_RECORD_EXIT_NOT_FOUND(206102, "出车记录不存在"),
    PASSING_RECORD_NOT_FOUND(206103, "过车记录不存在"),
    PARKING_PLATE_NUMBER_EMPTY(206104, "车牌号为空"),
    PARKING_ORDER_NO_EMPTY(206105, "订单号为空"),
    PARKING_ORDER_NOT_FOUND(206106, "停车订单不存在"),
    PARKING_ORDER_ENTRY_NOT_FOUND(206107, "入车记录不存在"),
    PARKING_ORDER_EXIT_NOT_FOUND(206108, "出车记录不存在"),
    PARKING_ORDER_PAY_STATUS_INVALID(206109, "停车订单支付状态无效"),
    PARKING_ORDER_PAYSUSSESS_CANT_UPDATE(206110, "停车订单已支付不可修改"),

    //预约停车
    GET_APPOINTPARKING_PAGELIST(207100, "分页获取可预约停车场列表失败"),
    GET_APPOINTPARKING_COUNT(207101, "获取可预约停车场数量失败"),
    GET_APPOINTPARKING_DEATAILS(207102, "获取可预约停车场详情失败"),
    RULE_CODE_EMPTY(207103, "预约规则编号为空"),
    RULE_NAME_EMPTY(207104, "预约规则名称为空"),
    APPOINTRULE_ADD_ERR(207105, "预约规则添加失败"),
    APPOINTRULE_NOT_FOUND(207106, "预约规则不存在"),
    APPOINTRULE_GET_ERR(207107, "预约规则获取失败"),
    APPOINT_RULECODE_EXISTS(207108, "预约规则code已存在"),
    APPOINT_RULEID_EMPTY(207109, "预约规则ID为空"),
    APPOINTRULE_UPDATE_ERR(207110, "预约规则更新失败"),
    APPOINTRULE_DELETE_ERR(207111, "预约规则删除失败"),
    APPOINT_RULELIST_GET_ERR(207112, "获取预约规则列表失败"),
    APPOINT_RULELIST_PAGEGET_ERR(207113, "分页获取预约规则列表失败"),
    PARKING_APPOINT_RULE_DELETE_ERR(207114, "预约规则关联停车场删除失败"),
    APPOINT_RULE_EMPTY(207115, "预约规则为空"),
    PARKING_APPOINT_RULE_EXISTS(207116, "停车场该时段预约规则已存在"),
    PARKING_APPOINT_RULE_OPERERR(207117, "停车场预约规则维护失败"),
    APPOINT_RULE_QUERY_ERR(207118, "停车场该时间段对应预约规则查询失败"),
    APPOINT_RULE_NOT_EXISTS(207119, "停车场该时间段对应预约规则不存在"),
    APPOINT_TIME_EMPTY(207120, "预约时间为空"),
    APPOINT_RULE_ONLINE_INVALID(207121, "预约规则生效起始日期无效"),
    PARKING_APPOINT_RULE_EMPTY(207122, "停车场预约规则不存在"),
    INPUT_TOOLONG(207123, "输入过长"),
    STARTTIME_EMPTY(207124, "开始时间为空"),
    ENDTIME_EMPTY(207125, "结束时间为空"),
    TIME_ERR(207126, "时间输入有误"),
    FLEXTIME_EMPTY(207127, "弹性入场时间为空"),
    FEE_EMPTY(207128, "预约服务费用为空"),
    DESCRIPTION_EMPTY(207129, "预约规则描述为空"),
    CHARGETYPE_EMPTY(207130, "收费类型为空"),
    CHARGEPRICE_EMPTY(207131, "收费金额为空"),
    PAYLIMIT_EMPTY(207132, "未支付自动取消时长为空"),
    REFUNDLIMIT_EMPTY(207133, "已支付可退款时长为空"),
    CANREFUND_EMPTY(207134, "是否退款为空"),
    OVERTIMELIMIT_EMPTY(207135, "超时未入场自动取消时长为空"),
    CHARGETYPE_ERR(207136, "收费类型有误"),
    APPOINT_RULE_IN_USED(207137, "收费规则已关联"),
    FEE_CALCULATE_ERR(207138, "费用计算失败"),
    ORDER_PAYED(207139, "此账单已支付"),

    //预约
    PLAN_APPOINT_BEGINTIME_EMPTY(11112, "预计预约开始时间为空"),
    APPOINT_PLACE_ORDER_ERR(11113, "预约下单失败"),
    APPOINT_AMOUNT_EMPTY(11114, "预约金额为空"),
    APPOINT_ORDER_NO_EMPTY_ERROR(11116, "订单号为空"),
    APPOINT_ORDER_NOT_FOUND(11117, "预约订单不存在"),
    AMOUNT_DIFFERENT(11115, "预约金额不一致"),
    GET_USER_APPOINT_ERR(11118, "获取预约订单失败"),
    PAYING_TRY_LATER(11119, "支付处理中,请稍后再试"),
    APPOINT_RULE_NOT_FOUND(11120, "预约规则不存在"),
    PARKING_APPOINT_NOT_SUPPORT_ERROR(11121, "该停车场暂时不支持预约"),
    SCHEDULE_TIME_ERROR(1111230, "入场时间不能小于当前时间"),
    SCHEDULE_TIME_EMPTY(1111230, "入场时间不能大于当前时间"),
    GET_USER_APPOINT_LIST_ERR(11122, "获取预约订单列表失败"),
    CANCEL_APPOINT_ORDER_ERR(111223, "取消预约订单失败"),
    APPOINT_ORDER_PAYSTATUS_ERR(111224, "预约订单支付状态有误"),
    OVERTIME_CANCEL_FALSE(111225, "预约订单不允许超时取消"),
    OVER_CANCEL_LIMITTIME(111226, "超过取消时限不允许手动取消"),
    APPOINT_STATUS_ERR(111227, "本次预约已取消"),
    CLOSE_PAYTIMEOUT_ORDER_ERR(111228, "关闭超时未支付订单失败"),
    CLOSE_ENTERTIMEOUT_ORDER_ERR(111229, "关闭过期预约订单失败"),
    ORDER_ID_EMPTY(111230, "预约订单ID为空"),
    REMARK_ERR(111231, "备注失败"),
    GET_APPIONTSTATUS_LIST_ERR(111232, "获取预约状态列表失败"),
    GET_PAYWAY_LIST_ERR(111233, "获取支付方式列表失败"),
    GET_ENTRYSTATUS_LIST_ERR(111234, "获取入场状态列表失败"),
    GET_PAYSTATUS_LIST_ERR(111235, "获取支付状态列表失败"),
    USER_ID_EMPTY(111236, "用户ID为空"),
    JUDGE_ERR(111237, "判断失败"),
    USER_APPOINTORDER_EXIST(111238, "您已预约车位，不能重复预约"),
    PLATENUMBER_EMPTY(111239, "车牌号为空"),
    APPOINT_CHECK_ORDER_ERR(111240, "预约订单确认失败"),

    //平台车辆
    VEHICLE_RECORD_REQUEST_EMPTY(208001, "请求参数为空"),
    VEHICLE_RECORD_PLATE_NUMBER_EMPTY(208002, "车牌号为空"),
    //节假日
    HOLIDAY_SCHEDULE_NULL(209000, "假期类型不存在"),

    //图像
    IMAGE_OPERATE_ERR(210000, "图像处理失败");;

    /**
     * 值
     */
    @Getter
    private Integer value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    PmsResultEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PmsResultEnum parse(Integer value) {
        if (value != null) {
            PmsResultEnum[] array = values();
            for (PmsResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
