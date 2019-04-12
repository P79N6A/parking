package com.zoeeasy.cloud.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * charge结果枚举
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ChargeResultEnum {

    /**
     * 假期类型不存在
     */
    HOLIDAY_SCHEDULE_EMPTY(120200001, "假期类型不存在"),
    HOLIDAY_DATE_FOMART_FAIL(120200002, "假期日期格式不正确"),
    HOLIDAY_DATE_NOT_WEEKDAY(120200003, "正常双休日不能配置在正常工作日中,请重新选择！"),
    HOLIDAY_CALENDAR_EMPTY(120200004, "删除节假日配置不存在"),
    HOLIDAY_CALENDAR_EXIST(120200005, "节假日配置已存在"),
    HOLIDAY_CALENDAR_DELETE_DATE_EMPTY(120200006, "删除节假日日期不能为空"),
    HOLIDAY_CALENDAR_DELETE_DATE_ERROR(1202000067, "删除节假日配置日期不能为过去时间"),

    //收费规则
    CHARGE_RULE_CODE_EMPTY(120200101, "规则编号为空"),
    CHARGE_RULE_NAME_EMPTY(120200102, "规则名称为空"),
    CHARGE_RULE_CAR_TYPE_EMPTY(120200103, "车辆类型为空"),
    CHARGE_RULE_PARKING_LEVEL_EMPTY(120200104, "停车场等级为空"),
    CHARGE_RULE_HOLIDAY_TYPE_EMPTY(120200105, "假期类型不能为空"),
    CHARGE_RULE_RULE_TYPE_EMPTY(120200106, "收费规则类型为空"),
    CHARGE_RULE_CODE_EXIST(120200107, "规则编号已存在"),
    CHARGE_RULE_NOT_EXIST(120200108, "收费规则不存在"),
    CHARGE_RULE_ID_EMPTY(120200109, "收费规则ID为空"),
    CHARGE_RULE_TYPE_NOT_MODIFY(120200110, "收费规则类型不能修改"),
    PLATE_NUMBER_TYPE_EMPTY(120200111, "车牌类型为空"),
    PARKING_CHARGE_RULE_NOT_FOUND(120200112, "停车收费规则为空"),
    CHARGE_RULE_PART_TIMES_EMPTY(120200113, "收费时段为空"),
    CHARGE_RULE_PART_TIMES_TIME_ERROR(120200114, "时间段无效"),
    CHARGE_RULE_PART_TIMES_PRICE_ERROR(120200115, "时间段价格无效"),
    CHARGE_RULE_PART_TIMES_REPEAT(120200116, "收费时间段重复"),
    CHARGE_RULE_TIMES_COUNT_ERROR(120200117, "计时时间范围无效"),
    CHARGE_RULE_TIMES_RANGE_ERROR(120200118, "计次时间范围无效"),
    CHARGE_RULE_CODE_NOT_UPDATE(120200119, "收费规则编号不允许修改"),
    CHARGE_RULE_CORRELATION_LIMIT_UPDATE(120200120, "被关联收费规则限制修改"),
    CHARGE_RULE_UNIT_TIME_NOT_STANDARD(120200121, "最小计时单位不符合规范"),
    PARKING_CHARGE_RULE_EMPTY(120200122, "该计算时间段内停车场无有效关联收费规则"),

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
    ;

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

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ChargeResultEnum parse(Integer value) {
        if (value != null) {
            ChargeResultEnum[] array = values();
            for (ChargeResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
