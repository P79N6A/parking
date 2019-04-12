package com.zoeeasy.cloud.charge.strategy;

import com.zoeeasy.cloud.charge.chg.CalculateAmountStrategy;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.utils.AmountUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * @author AkeemSuper
 * @description 日夜分时计次收费策略
 * @date 2018/3/6 0006
 */
@Service("gistToDateTimes")
public class GistToDateTimes implements CalculateAmountStrategy {

    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String MINUTE_FORMAT = "HH:mm";
    private static final String START_TIME = "00:00:00";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern(MINUTE_FORMAT);
    @Autowired
    private GistToDate gistToDate;
    /**
     * 当天停车开始时间
     */
    private DateTime natureStartTime;
    /**
     * 当天停车结束时间
     */
    private DateTime natureEndTime;

    /**
     * 停车金额
     */
    private Integer totalAmount = 0;
    /**
     * 第一天金额
     */
    private Integer firstDayAmount;
    /**
     * 第二天金额
     */
    private Integer twiceDayAmount;
    /**
     * 中间一天金额
     */
    private Integer tagAmount;
    /**
     * 最后一天金额
     */
    private Integer lastDayAmount;
    /**
     * 中间天的总金额
     */
    private Integer tagTotalAmount;

    /**
     * 停车天数
     */
    private int days;

    /**
     * 日夜分时计次计算
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return BigDecimal
     */
    @Override
    public Integer calculateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        tagTotalAmount = 0;
        lastDayAmount = 0;
        tagAmount = 0;
        twiceDayAmount = 0;
        firstDayAmount = 0;
        totalAmount = 0;
        if (chargeRuleValidStartTime.equals(chargeRuleValidEndTime)) {
            return 0;
        }
        //按时收费段开始时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        //停车天数，指停车开始到结束所占用的天数
        days = AmountUtils.differentDays(chargeRuleValidStartTime, chargeRuleValidEndTime);
        //未启用24小时封顶金额
        if (!chargeRule.getInTimeTop()) {
            //白天计时，晚上计次(0:00在计次收费段)
            if (start.isBefore(end)) {
                if (days == 1) {
                    //只有一天
                    totalAmount = totalOneDayNoDayTopAmountDayTimeToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                } else {
                    //大于一天
                    totalAmount = totalManyDaysNoDayTopAmountDayTimeToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                }
            } else {
                //白天按次收费，晚上按时收费(0:00在计时收费段)
                if (days == 1) {
                    //只有一天
                    totalAmount = totalOneDayNoDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                } else if (days == 2) {
                    //只有两天
                    totalAmount = totalTwoDaysNoDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                } else {
                    //两天以上
                    totalAmount = totalManyDaysNoDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                }
            }
        } else {
            //启用封顶金额
            //白天计时，晚上计次收费(0:00在计次收费段)
            if (start.isBefore(end)) {
                //当用户停车时间只在一天之内
                if (days == 1) {
                    //只有一天
                    totalAmount = totalOneDayHaveDayTopAmountDayTimeToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                } else {
                    //大于一天
                    totalAmount = totalManyDaysHaveDayTopAmountDayTimeToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                }
            } else {
                //(0:00在计时收费段)
                //白天按次收费，晚上按时收费
                if (days == 1) {
                    //只有一天
                    totalAmount = totalOneDayHaveDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                } else if (days == 2) {
                    //只有两天
                    totalAmount = totalTwoDaysHaveDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                } else {
                    //大于两天
                    totalAmount = totalManyDaysHaveDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
                }
            }
        }
        totalAmount = AmountUtils.compareTopAmount(chargeRule, totalAmount);
        return totalAmount;
    }

    /**
     * 将完整时间戳转换成HH
     *
     * @param dateTime 被转换的时间
     * @return DateTime
     */
    private DateTime dateChange(DateTime dateTime) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        String str = format.format(dateTime.toDate());
        DateTimeFormatter dtf1 = DateTimeFormat.forPattern(TIME_FORMAT);
        return DateTime.parse(str, dtf1);
    }

    /**
     * 用户停车时间只在一天之内，且该规则为白天按时收费，晚上计次收费，未启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalOneDayNoDayTopAmountDayTimeToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        natureStartTime = dateChange(chargeRuleValidStartTime);
        natureEndTime = dateChange(chargeRuleValidEndTime);
        if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
            natureEndTime = natureEndTime.plusDays(1).withTimeAtStartOfDay();
        }
        //当用户停车开始、结束时间都在按时收费段的开始时间之前
        if (natureStartTime.isBefore(start) && (natureEndTime.isBefore(start) || natureEndTime.isEqual(start))) {
            firstDayAmount = chargeRule.getPerPrice();
        }
        //当用户的停车开始时间在按时收费的开始时间之前，用户停车结束时间在按时收费开始时间之后
        if ((natureStartTime.isBefore(start) || natureStartTime.isEqual(start)) && natureEndTime.isAfter(start)) {
            //当用户的停车结束时间在按时收费段之间
            if (natureEndTime.isBefore(end) || natureEndTime.isEqual(end)) {
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            } else {
                //当用户的停车结束时间在按时收费段之外
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, end, chargeRule) + chargeRule.getPerPrice();
            }
        }
        //当用户停车时间段在按时收费时间段之间
        if ((natureStartTime.isAfter(start) || natureStartTime.isEqual(start)) && (natureEndTime.isBefore(end) || natureEndTime.isEqual(end))) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
        }
        //当用户的停车开始时间在按时收费开始时间之后，用户停车结束时间在按时收费结束时间之后
        if ((natureStartTime.isAfter(start) || natureStartTime.isEqual(start)) && natureEndTime.isAfter(end)) {
            //当用户停车开始时间在按时收费段之间
            if (natureStartTime.isBefore(end)) {
                firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice();
            } else {
                //当用户停车时间段在按次收费时间段
                firstDayAmount = chargeRule.getPerPrice();
            }
        }
        totalAmount = firstDayAmount;
        return totalAmount;
    }

    /**
     * 用户停车时间在多天（大于或等于两天），且该规则为白天按时收费，晚上计次收费，未启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalManyDaysNoDayTopAmountDayTimeToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        //停车天数
        days = AmountUtils.differentDays(chargeRuleValidStartTime, chargeRuleValidEndTime);
        //当用户停车时间跨多天
        for (int i = 1; i <= days; i++) {
            //用户停车时间的第一天
            if (i == 1) {
                natureStartTime = dateChange(chargeRuleValidStartTime);
                //当用户在按时收费开始时间之前停车
                if (natureStartTime.isBefore(start)) {
                    firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, end, chargeRule) + chargeRule.getPerPrice();
                } else if (natureStartTime.isAfter(end) || natureStartTime.isEqual(end)) {
                    //当用户在按时收费结束时间之后停车
                    firstDayAmount = chargeRule.getPerPrice();
                } else {
                    //当用户在按时收费开始时间之间停车
                    firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice();
                }
            } else if (i == days) {
                //用户停车时间的最后一天
                natureEndTime = dateChange(chargeRuleValidEndTime);
                if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
                    natureEndTime = natureEndTime.plusDays(1).withTimeAtStartOfDay();
                }
                //用户停车结束时间在按时收费开始时间之前
                if (natureEndTime.isBefore(start) || natureEndTime.isEqual(start)) {
                    lastDayAmount = 0;
                } else if (natureEndTime.isAfter(end)) {
                    //用户停车结束时间在按时收费开始时间之后
                    lastDayAmount = gistToDate.calculateAmount(start, end, chargeRule) + chargeRule.getPerPrice();
                } else {
                    //用户停车结束时间在按时收费开始时间之间
                    lastDayAmount = gistToDate.calculateAmount(start, natureEndTime, chargeRule);
                }
            } else {
                //第一天和最后一天的时间之外的停车时间
                tagAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, end, chargeRule);
                tagTotalAmount = tagTotalAmount + tagAmount;
            }
        }
        totalAmount = firstDayAmount + tagTotalAmount + lastDayAmount;
        return totalAmount;
    }

    /**
     * 用户停车时间只在一天之内，且该规则为白天按次收费，晚上计时收费，未启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalOneDayNoDayTopAmountNightToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        natureStartTime = dateChange(chargeRuleValidStartTime);
        natureEndTime = dateChange(chargeRuleValidEndTime);
        if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
            natureEndTime = natureEndTime.plusDays(1).withTimeAtStartOfDay();
        }
        //当用户停车时间在当天的第一个按时收费段
        if (natureEndTime.isBefore(end) || natureEndTime.isEqual(end)) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
        }
        //当用户入车时间在按时收费段的结束时间之前，出车时间在此之后
        if ((natureStartTime.isBefore(end) || natureStartTime.isEqual(end)) && natureEndTime.isAfter(end)) {
            //当用户出车时间在按时收费段的开始时间之前
            if (natureEndTime.isBefore(start) || natureEndTime.isEqual(start)) {
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(natureStartTime, end, chargeRule);
            } else {
                //当用户出车时间在按时收费段的开始时间之后
                firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice()
                        + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            }
        }
        //用户入车时间在按次收费段
        if ((natureStartTime.isAfter(end) || natureStartTime.isEqual(end)) && natureStartTime.isBefore(start)) {
            //当用户出车时间在按次收费段
            if ((natureEndTime.isBefore(start) || natureEndTime.isEqual(start))) {
                firstDayAmount = chargeRule.getPerPrice();
            } else {
                //当用户出车在按时收费段
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            }
        }
        //用户入车时间在当天第二个按时收费段
        if (natureStartTime.isAfter(start) || natureStartTime.isEqual(start)) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
        }
        totalAmount = firstDayAmount;
        return totalAmount;
    }

    /**
     * 用户停车时间只在两天之内，且该规则为白天按次收费，晚上计时收费，未启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalTwoDaysNoDayTopAmountNightToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        //计算第一天金额
        natureStartTime = dateChange(chargeRuleValidStartTime);
        natureEndTime = dateChange(chargeRuleValidStartTime).plusDays(1).withTimeAtStartOfDay();
//        不计算最后一段时长
        //用户入车时间在当天第一个计时段
        if (natureStartTime.isBefore(end)) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice();
        } else {
            //用户入车时间在当天的计次收费段
            if (natureStartTime.isBefore(start)) {
                firstDayAmount = chargeRule.getPerPrice();
            } else {
                //用户的入车时间在当天的第二个计时收费段
                firstDayAmount = 0;
            }
        }
        DateTime twiceEndTime = dateChange(chargeRuleValidEndTime);
        if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
            twiceEndTime = twiceEndTime.plusDays(1).withTimeAtStartOfDay();
        }
        //第二天 计算和前一天交接的计时段
        //入车时间在计时开始之前
        if (natureStartTime.isBefore(start) || natureStartTime.isEqual(start)) {
            if (twiceEndTime.isBefore(end) || twiceEndTime.isEqual(end)) {
                //出车时间在计时结束之后
                twiceDayAmount = gistToDate.calculateAmount(start, dateChange(chargeRuleValidEndTime).plusDays(1), chargeRule);
            } else if (twiceEndTime.isAfter(end) && (twiceEndTime.isBefore(start) || twiceEndTime.isEqual(start))) {
                //出车时间在计时结束之后再第二段之前
                twiceDayAmount = gistToDate.calculateAmount(start, end.plusDays(1), chargeRule) + chargeRule.getPerPrice();
            } else {
                //出车时间在第二段计时开始之后
                twiceDayAmount = gistToDate.calculateAmount(start, end.plusDays(1), chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, twiceEndTime, chargeRule);
            }
        } else {
            //入车时间在计时开始之后
            if (twiceEndTime.isBefore(end) || twiceEndTime.isEqual(end)) {
                //出车时间在结束之前
                twiceDayAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidStartTime), dateChange(chargeRuleValidEndTime).plusDays(1), chargeRule);
            } else if (twiceEndTime.isAfter(end) && (twiceEndTime.isBefore(start) || twiceEndTime.isEqual(start))) {
                //出车时间在计时结束之后并在第二次开始之前
                twiceDayAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidStartTime), end.plusDays(1), chargeRule) + chargeRule.getPerPrice();
            } else {
                // 出车时间在第二段计时之后
                twiceDayAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidStartTime), end.plusDays(1), chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, twiceEndTime, chargeRule);
            }
        }
        return totalAmount = firstDayAmount + twiceDayAmount;
    }

    /**
     * 用户停车时间在多天之内（大于两天），且该规则为白天按次收费，晚上计时收费，未启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalManyDaysNoDayTopAmountNightToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        natureStartTime = dateChange(chargeRuleValidStartTime);
        natureEndTime = dateChange(chargeRuleValidStartTime).plusDays(1).withTimeAtStartOfDay();
        //出车时间点
        //计算第一天 未计算第一天的第二段计时
        if (natureStartTime.isBefore(end)) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice();
        } else {
            //用户入车时间在当天的计次收费段
            if (natureStartTime.isBefore(start)) {
                firstDayAmount = chargeRule.getPerPrice();
            } else {
                //用户的入车时间在当天的第二个计时收费段
                firstDayAmount = 0;
            }
        }
        //最后一天的出车时间
        DateTime lastEndTime = dateChange(chargeRuleValidEndTime);
        if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
            lastEndTime = lastEndTime.plusDays(1).withTimeAtStartOfDay();
        }
        //出车时间在计时结束之前
        if (lastEndTime.isBefore(end) || lastEndTime.isEqual(end)) {
            lastDayAmount = gistToDate.calculateAmount(start, dateChange(chargeRuleValidEndTime).plusDays(1), chargeRule);
        } else if (lastEndTime.isAfter(end) && (lastEndTime.isBefore(start) || lastEndTime.isEqual(start))) {
            //出车时间在前一段计时结束之后后一段计时结束之前
            lastDayAmount = gistToDate.calculateAmount(start, end.plusDays(1), chargeRule) + chargeRule.getPerPrice();
        } else {
            //出车时间在最后一段计时
            lastDayAmount = gistToDate.calculateAmount(start, end.plusDays(1), chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, lastEndTime, chargeRule);
        }
        for (int j = 2; j < days; j++) {
            if (j == 2) {
                //第二天要考虑第一天的入车时间
                if (natureStartTime.isBefore(start) || natureStartTime.isEqual(start)) {
                    tagAmount = gistToDate.calculateAmount(start, end.plusDays(1), chargeRule) + chargeRule.getPerPrice();
                } else {
                    tagAmount = gistToDate.calculateAmount(natureStartTime, end.plusDays(1), chargeRule) + chargeRule.getPerPrice();
                }
            } else {
                tagAmount = gistToDate.calculateAmount(start, end.plusDays(1), chargeRule) + chargeRule.getPerPrice();
            }
            tagTotalAmount += tagAmount;
        }
        return totalAmount = firstDayAmount + tagTotalAmount + lastDayAmount;
    }

    /**
     * 用户停车时间在一天之内，且该规则为白天按时收费，晚上计次收费,并启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalOneDayHaveDayTopAmountDayTimeToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        natureStartTime = dateChange(chargeRuleValidStartTime);
        natureEndTime = dateChange(chargeRuleValidEndTime);
        if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
            natureEndTime = natureEndTime.plusDays(1).withTimeAtStartOfDay();
        }
        //当用户停车开始、结束时间都在按时收费段的开始时间之前
        if (natureStartTime.isBefore(start) && (natureEndTime.isBefore(start) || natureEndTime.isEqual(start))) {
            firstDayAmount = chargeRule.getPerPrice();
        }
        //当用户的停车开始时间在按时收费的开始时间之前，用户停车结束时间在按时收费开始时间之后
        if (natureStartTime.isBefore(start) && natureEndTime.isAfter(start)) {
            //当用户的停车结束时间在按时收费段之间
            if (natureEndTime.isBefore(end) || natureEndTime.equals(end)) {
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            } else {
                //当用户的停车结束时间在按时收费段之外
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, end, chargeRule) + chargeRule.getPerPrice();
            }
        }
        //当用户停车时间段在按时收费时间段之间
        if ((natureStartTime.isAfter(start) || natureStartTime.isEqual(start)) && (natureEndTime.isBefore(end) || natureEndTime.isEqual(end))) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
        }
        //当用户的停车开始时间在按时收费开始时间之后，用户停车结束时间在按时收费结束时间之后
        if ((natureStartTime.isAfter(start) || natureStartTime.isEqual(start)) && natureEndTime.isAfter(end)) {
            //当用户停车开始时间在按时收费段之间
            if (natureStartTime.isBefore(end)) {
                firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice();
            } else {
                //当用户停车时间段在按次收费时间段
                firstDayAmount = chargeRule.getPerPrice();
            }
        }
        totalAmount = AmountUtils.compareDayTopAmount(chargeRule, firstDayAmount);
        return totalAmount;
    }

    /**
     * 用户停车时间在多天（大于或等于两天），且该规则为白天按时收费，晚上计次收费,并启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalManyDaysHaveDayTopAmountDayTimeToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        //当用户停车时间跨多天
        for (int i = 1; i <= days; i++) {
            //用户停车时间的第一天
            if (i == 1) {
                natureStartTime = dateChange(chargeRuleValidStartTime);
                //当用户在按时收费开始时间之前停车
                if (natureStartTime.isBefore(start)) {
                    firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, end, chargeRule) + chargeRule.getPerPrice();
                } else if (natureStartTime.isAfter(end) || natureStartTime.isEqual(end)) {
                    //当用户在按时收费开始时间之后停车
                    firstDayAmount = chargeRule.getPerPrice();
                } else {
                    //当用户在按时收费开始时间之间停车
                    firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice();
                }
                firstDayAmount = AmountUtils.compareDayTopAmount(chargeRule, firstDayAmount);
            }
            //用户停车时间的最后一天
            else if (i == days) {
                natureEndTime = dateChange(chargeRuleValidEndTime);
                if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
                    natureEndTime = natureEndTime.plusDays(1).withTimeAtStartOfDay();
                }
                //用户停车结束时间在按时收费开始时间之前
                if (natureEndTime.isBefore(start) || natureEndTime.isEqual(start)) {
                    lastDayAmount = 0;
                } else if (natureEndTime.isAfter(end)) {
                    //用户停车结束时间在按时收费开始时间之后
                    lastDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, end, chargeRule);
                } else {
                    //用户停车结束时间在按时收费开始时间之间
                    lastDayAmount = gistToDate.calculateAmount(start, natureEndTime, chargeRule);
                }
                lastDayAmount = AmountUtils.compareDayTopAmount(chargeRule, lastDayAmount);
            } else {
                //第一天和最后一天的时间之外的停车时间
                tagAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, end, chargeRule);
                tagAmount = AmountUtils.compareDayTopAmount(chargeRule, tagAmount);
                tagTotalAmount += tagAmount;
            }
        }
        totalAmount = firstDayAmount + tagTotalAmount + lastDayAmount;
        return totalAmount;
    }

    /**
     * 用户停车时间在一天之内，且该规则为白天按次收费，晚上计时收费,并启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalOneDayHaveDayTopAmountNightToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        natureStartTime = dateChange(chargeRuleValidStartTime);
        natureEndTime = dateChange(chargeRuleValidEndTime);
        if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
            natureEndTime = natureEndTime.plusDays(1).withTimeAtStartOfDay();
        }
        //当用户停车时间在当天的第一个按时收费段
        if (natureEndTime.isBefore(end) || natureEndTime.isEqual(end)) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
        }
        //当用户的入车时间在当天的第一个按时收费段之内，出车时间在此之外
        if (natureStartTime.isBefore(end) && natureEndTime.isAfter(end)) {
            //当用户的出车时间在当天的按次收费段
            if (natureEndTime.isBefore(start) || natureEndTime.isEqual(start)) {
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(natureStartTime, end, chargeRule);
            } else {
                //当用户的出车时间在当天的第二个按时收费段
                firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            }
        }
        //当用户的入车时间在当天的按次收费段
        if ((natureStartTime.isAfter(end) || natureStartTime.isEqual(end)) && natureStartTime.isBefore(start)) {
            //当用户的出车时间在当天的按次收费段
            if (natureEndTime.isBefore(start) || natureEndTime.isEqual(start)) {
                firstDayAmount = chargeRule.getPerPrice();
            } else {
                //当用户的出车时间在当天的第二个按时收费段
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            }
        }
        //当用户的停车时间在当天的第二个按时收费段
        if (natureStartTime.isAfter(start) || natureStartTime.isEqual(start)) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
        }
        totalAmount = AmountUtils.compareDayTopAmount(chargeRule, firstDayAmount);
        return totalAmount;
    }

    /**
     * 用户停车时间在两天之内，且该规则为白天按次收费，晚上计时收费,并启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalTwoDaysHaveDayTopAmountNightToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        //预计算第一天金额
        natureStartTime = dateChange(chargeRuleValidStartTime);
        natureEndTime = dateChange(chargeRuleValidStartTime).plusDays(1).withTimeAtStartOfDay();
        //当用户的入车时间在当天的第一个按时收费段
        if (natureStartTime.isBefore(end)) {
            firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
        }
        if (natureStartTime.isAfter(end) || natureStartTime.isEqual(end)) {
            //当用户的入车时间在当天的按次收费段
            if (natureStartTime.isBefore(start)) {
                firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            } else {
                //当用户的入车时间在当天的第二个按时收费段
                firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
            }
        }
        //预计算第二天金额
        DateTime twiceEndTime = dateChange(chargeRuleValidEndTime);
        if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
            twiceEndTime = twiceEndTime.plusDays(1).withTimeAtStartOfDay();
        }
        if (twiceEndTime.isBefore(end) || twiceEndTime.isEqual(end)) {
            //出车时间在第一个计时段
            twiceDayAmount = gistToDate.calculateAmount(chargeRuleValidEndTime.withTimeAtStartOfDay(), chargeRuleValidEndTime, chargeRule);
        } else {
            if (twiceEndTime.isBefore(start) || twiceEndTime.isEqual(start)) {
                //出车时间在计次段
                twiceDayAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidEndTime.withTimeAtStartOfDay()), end, chargeRule) + chargeRule.getPerPrice();
            } else {
                //出车在第二个计时段
                twiceDayAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidEndTime.withTimeAtStartOfDay()), end, chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, twiceEndTime, chargeRule);
            }
        }
        //若第一天金额大于日封顶金额
        if (firstDayAmount.compareTo(chargeRule.getDayTopAmount()) == 0 || firstDayAmount.compareTo(chargeRule.getDayTopAmount()) > 0) {
            firstDayAmount = AmountUtils.compareDayTopAmount(chargeRule, firstDayAmount);
            twiceDayAmount = AmountUtils.compareDayTopAmount(chargeRule, twiceDayAmount);
            return totalAmount = firstDayAmount + twiceDayAmount;
        } else {
            //若第一天金额小于日封顶金额
            //第二天金额大于日封顶金额
            if (twiceDayAmount.compareTo(chargeRule.getDayTopAmount()) == 0 || twiceDayAmount.compareTo(chargeRule.getDayTopAmount()) > 0) {
                twiceDayAmount = AmountUtils.compareDayTopAmount(chargeRule, twiceDayAmount);
                return totalAmount = firstDayAmount + twiceDayAmount;
            } else {
                //第一天小于封顶金额,第二天小于封顶金额
                return totalTwoDaysNoDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
            }
        }
    }

    /**
     * 用户停车时间在多天（大于两天），且该规则为白天按次收费，晚上计时收费,并启用24小时封顶金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return Integer
     */
    private Integer totalManyDaysHaveDayTopAmountNightToDateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        //按时收费段开始、结束时间格式转换成HH：mm格式
        DateTime start = DateTime.parse(chargeRule.getTimesStartTime(), DATE_FORMAT);
        DateTime end = DateTime.parse(chargeRule.getTimesEndTime(), DATE_FORMAT);
        //判断停满一天是否超出24封顶金额
        Integer standAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidEndTime.withTimeAtStartOfDay()), end, chargeRule) + chargeRule.getPerPrice()
                + gistToDate.calculateAmount(start, dateChange(chargeRuleValidEndTime.withTimeAtStartOfDay()).plusDays(1), chargeRule);
        if (!(standAmount.compareTo(chargeRule.getDayTopAmount()) > 0 || standAmount.compareTo(chargeRule.getDayTopAmount()) == 0)) {
            //未超出封顶按照未启用封顶金额计算
            return totalManyDaysNoDayTopAmountNightToDateAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
        } else {
            //预计算第一天金额
            natureStartTime = dateChange(chargeRuleValidStartTime);
            natureEndTime = dateChange(chargeRuleValidStartTime).plusDays(1).withTimeAtStartOfDay();
            //当用户的入车时间在当天的第一个按时收费段
            if (natureStartTime.isBefore(end)) {
                firstDayAmount = gistToDate.calculateAmount(natureStartTime, end, chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
            }
            if (natureStartTime.isAfter(end) || natureStartTime.isEqual(end)) {
                //当用户的入车时间在当天的按次收费段
                if (natureStartTime.isBefore(start)) {
                    firstDayAmount = chargeRule.getPerPrice() + gistToDate.calculateAmount(start, natureEndTime, chargeRule);
                } else {
                    //当用户的入车时间在当天的第二个按时收费段
                    firstDayAmount = gistToDate.calculateAmount(natureStartTime, natureEndTime, chargeRule);
                }
            }
            //预算最后一天金额
            DateTime lastEndTime = dateChange(chargeRuleValidEndTime);
            if (chargeRuleValidEndTime.toString(TIME_FORMAT).equals(START_TIME)) {
                lastEndTime = lastEndTime.plusDays(1).withTimeAtStartOfDay();
            }
            if (lastEndTime.isBefore(end) || lastEndTime.isEqual(end)) {
                //出车时间在第一个计时段
                lastDayAmount = gistToDate.calculateAmount(chargeRuleValidEndTime.withTimeAtStartOfDay(), chargeRuleValidEndTime, chargeRule);
            } else {
                if (lastEndTime.isBefore(start) || lastEndTime.isEqual(start)) {
                    //出车时间在计次段
                    lastDayAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidEndTime.withTimeAtStartOfDay()), end, chargeRule) + chargeRule.getPerPrice();
                } else {
                    //出车在第二个计时段
                    lastDayAmount = gistToDate.calculateAmount(dateChange(chargeRuleValidEndTime.withTimeAtStartOfDay()), end, chargeRule) + chargeRule.getPerPrice() + gistToDate.calculateAmount(start, lastEndTime, chargeRule);
                }
            }
            //中间天金额的计算
            for (int j = 2; j < days; j++) {
                tagAmount = chargeRule.getDayTopAmount();
                tagTotalAmount += tagAmount;
            }
            firstDayAmount = AmountUtils.compareDayTopAmount(chargeRule, firstDayAmount);
            lastDayAmount = AmountUtils.compareDayTopAmount(chargeRule, lastDayAmount);
            totalAmount = firstDayAmount + tagTotalAmount + lastDayAmount;
            return totalAmount;
        }
    }
}
