package com.zoeeasy.cloud.charge.strategy;

import com.zoeeasy.cloud.charge.chg.CalculateAmountStrategy;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleTimeViewResultDto;
import com.zoeeasy.cloud.charge.utils.AmountUtils;
import com.zoeeasy.cloud.core.cst.Const;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : AkeemSuper
 * @Description: 计时收费策略
 * @Date: 2018/3/6 0006
 */
@Service("gistToDate")
public class GistToDate implements CalculateAmountStrategy {

    private static final int ONE_MINUTE_MILLIS = 60000;

    private static final int ONE_DAY_MINUTE = 1440;

    private int mark;

    private int flag = 0;

    /**
     * 停车金额
     */
    private Integer totalAmount;

    /**
     * 一天的停车开始时间
     */
    private DateTime natureStartTime;
    /**
     * 一天的停车结束时间
     */
    private DateTime natureEndTime;

    /**
     * 每天停车时长
     */
    private Duration perDuration;

    private int perParkingDuration;

    /**
     * 有n个收费时段，前n-1个时段收费总和
     */
    private Integer stageTotal = 0;

    /**
     * 该规则用户停车天数
     */
    private int days;

    /**
     * 根据最小计时单位计算时长的个数
     *
     * @param unitTime 规则的最小计时单位
     * @param duration 时长
     */
    public static double figureUnitTimeSize(int unitTime, int duration) {
        double unitTimeSize = 0;
        if (Const.HALF_HOUR == unitTime) {
            unitTimeSize = Math.ceil((double) duration / Const.HALF_HOUR) / (double) 2;
        } else if (Const.HOUR == unitTime) {
            unitTimeSize = Math.ceil((double) duration / Const.HOUR);
        }
        return unitTimeSize;
    }

    /**
     * 按时收费计算
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return BigDecimal
     */
    @Override
    public Integer calculateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        Long mills = new Duration(chargeRuleValidStartTime, chargeRuleValidEndTime).getMillis() / 60000;
        Integer parkingTime = mills.intValue();
        if (parkingTime.compareTo(0) == 0) {
            return 0;
        }
        //计算停车时长内的天数
        days = AmountUtils.differentDays(chargeRuleValidStartTime, chargeRuleValidEndTime);
        //未启用24小时封顶金额
        if (!chargeRule.getInTimeTop()) {
            totalAmount = getFirstTagAmount(chargeRule, parkingTime);
        } else {
            //启用24小时封顶金额
            //只有一天
            if (days == 1) {
                totalAmount = totalOneDayUseDayTopAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
            }
            //只有两天
            else if (days == 2) {
                totalAmount = totalTwoDaysUseDayTopAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
            }
            //三天及以上
            else {
                totalAmount = totalManyDaysUseDayTopAmount(chargeRuleValidStartTime, chargeRuleValidEndTime, chargeRule);
            }
        }
        totalAmount = AmountUtils.compareTopAmount(chargeRule, totalAmount);
        return totalAmount;
    }

    /**
     * 当用户停车时段在分时段之内处理方案
     *
     * @param chargeRule      规则
     * @param parkingDuration 停车时长
     * @return BigDecimal
     */
    private Integer getInnerPartTimeAmount(ChargeRuleInfoViewResultDto chargeRule, int parkingDuration) {
        //用户停车时段在分段内的最后一段时长
        int partLastTime;
        List<ChargeRuleTimeViewResultDto> partTimes = chargeRule.getPartTimes();
        Integer totalAmount = 0;
        Integer stageTotal = 0;
        int perTime;
        double perTimeSize;
        Integer perAmount;
        for (int j = 0; j < partTimes.size(); j++) {
            if (partTimes.get(j).getTimePart() <= parkingDuration) {
                if (j > 0) {
                    perTime = partTimes.get(j).getTimePart() - partTimes.get(j - 1).getTimePart();
                    perTimeSize = (double) perTime / Const.HOUR;
                    Double perValue = partTimes.get(j).getPrice() * perTimeSize;
                    perAmount = perValue.intValue();
                } else {
                    perTimeSize = (double) partTimes.get(j).getTimePart() / Const.HOUR;
                    Double perValue = partTimes.get(j).getPrice() * perTimeSize;
                    perAmount = perValue.intValue();
                }
                stageTotal += perAmount;
                totalAmount = stageTotal;
            }
            if (partTimes.get(j).getTimePart() > parkingDuration) {
                if (j > 0) {
                    partLastTime = parkingDuration - partTimes.get(j - 1).getTimePart();
                    perTimeSize = figureUnitTimeSize(chargeRule.getUnitTime(), partLastTime);
                    Double paetLastValue = partTimes.get(j).getPrice() * perTimeSize;
                    totalAmount = stageTotal + paetLastValue.intValue();
                    mark = j;
                    break;
                } else {
                    partLastTime = parkingDuration;
                    perTimeSize = figureUnitTimeSize(chargeRule.getUnitTime(), partLastTime);
                    Double partLastValue = partTimes.get(j).getPrice() * perTimeSize;
                    totalAmount = stageTotal + partLastValue.intValue();
                    mark = j;
                    break;
                }
            }
        }
        return totalAmount;
    }

    /**
     * 计算第一段停车金额
     *
     * @param chargeRule      规则
     * @param parkingDuration 停车时长
     * @return Integer
     */
    private Integer getFirstTagAmount(ChargeRuleInfoViewResultDto chargeRule, int parkingDuration) {
        mark = 0;
        List<ChargeRuleTimeViewResultDto> partTimes = chargeRule.getPartTimes();
        int lastTime = partTimes.get(partTimes.size() - 1).getTimePart();
        double perTimeSize;
        double tagTime;
        Integer lastAmount;
        int perTime;
        Integer perAmount;
        Integer stageTotal = 0;
        Integer totalAmount;
        if (parkingDuration <= chargeRule.getFreeTime()) {
            return 0;
        }
        if (!chargeRule.getOverFreeTime()) {
            //免费时长不收费
            if (parkingDuration > chargeRule.getFreeTime()) {
                parkingDuration = (parkingDuration - chargeRule.getFreeTime());
            } else {
                return 0;
            }
        }
        if (parkingDuration > lastTime) {
            tagTime = figureUnitTimeSize(chargeRule.getUnitTime(), parkingDuration - lastTime);
            Double lastValue = partTimes.get(partTimes.size() - 1).getPrice() * tagTime;
            lastAmount = lastValue.intValue();
            for (int i = 0; i < partTimes.size(); i++) {
                if (i > 0) {
                    perTime = (partTimes.get(i).getTimePart() - partTimes.get(i - 1).getTimePart());
                } else {
                    perTime = partTimes.get(i).getTimePart();
                }
                if (perTime == 0) {
                    perAmount = 0;
                } else {
                    perTimeSize = (double) perTime / Const.HOUR;
                    Double perValue = partTimes.get(i).getPrice() * perTimeSize;
                    perAmount = perValue.intValue();
                }
                stageTotal += perAmount;
            }
            totalAmount = lastAmount + stageTotal;
        } else {
            // 当用户停车时段在分时段之内处理方案
            totalAmount = getInnerPartTimeAmount(chargeRule, parkingDuration);
        }
        return totalAmount;
    }

    /**
     * 启用日封顶金额，用户停车只在一天的计算方法
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param chargeRule 规则
     * @return BigDecimal
     */
    private Integer totalOneDayUseDayTopAmount(DateTime startTime, DateTime endTime, ChargeRuleInfoViewResultDto chargeRule) {
        natureStartTime = startTime;
        natureEndTime = endTime;
        perDuration = new Duration(natureStartTime, natureEndTime);
        perParkingDuration = (int) (perDuration.getMillis() / ONE_MINUTE_MILLIS);
        totalAmount = getFirstTagAmount(chargeRule, perParkingDuration);
        totalAmount = AmountUtils.compareDayTopAmount(chargeRule, totalAmount);
        return totalAmount;
    }

    /**
     * 启用日封顶金额，用户停车只在两天的计算方法
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param chargeRule 规则
     * @return BigDecimal
     */
    private Integer totalTwoDaysUseDayTopAmount(DateTime startTime, DateTime endTime, ChargeRuleInfoViewResultDto chargeRule) {
        //计算第一天金额
        Duration firstDayDuration = new Duration(startTime, startTime.plusDays(1).withTimeAtStartOfDay());
        int firstDayParkingDuration = (int) (firstDayDuration.getMillis() / ONE_MINUTE_MILLIS);
        Integer firstDayAmount;
        Integer twiceDayAmount;
        firstDayAmount = getFirstTagAmount(chargeRule, firstDayParkingDuration);
        flag = mark;
        //计算第二天金额
        Duration twiceDayDuration = new Duration(startTime.withTimeAtStartOfDay().plusDays(1), endTime);
        int twiceDayParkingDuration = (int) (twiceDayDuration.getMillis() / ONE_MINUTE_MILLIS);
        twiceDayAmount = getFirstTagAmount(chargeRule, twiceDayParkingDuration);
        //若第一天金额大于日封顶金额
        if (firstDayAmount.compareTo(chargeRule.getDayTopAmount()) == 0 || firstDayAmount.compareTo(chargeRule.getDayTopAmount()) > 0) {
            firstDayAmount = AmountUtils.compareDayTopAmount(chargeRule, firstDayAmount);
            twiceDayAmount = AmountUtils.compareDayTopAmount(chargeRule, twiceDayAmount);
        } else {
            //若第一天金额小于日封顶金额
            //第二天金额大于日封顶金额
            if (twiceDayAmount.compareTo(chargeRule.getDayTopAmount()) == 0 || twiceDayAmount.compareTo(chargeRule.getDayTopAmount()) > 0) {
                twiceDayAmount = chargeRule.getDayTopAmount();
            } else {
                twiceDayAmount = getFirstTagAmount(chargeRule, firstDayParkingDuration + twiceDayParkingDuration);
                return twiceDayAmount;
            }
        }
        totalAmount = firstDayAmount + twiceDayAmount;
        return totalAmount;
    }

    /**
     * 启用日封顶金额，用户停车三天及以上天的计算方法
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param chargeRule 规则
     * @return BigDecimal
     */
    private Integer totalManyDaysUseDayTopAmount(DateTime startTime, DateTime endTime, ChargeRuleInfoViewResultDto chargeRule) {
        //最后一段收费金额
        Integer lastAmount;
        //用户停车第二天的第一个收费时段时间
        int frontTime;
        //收费时段个数
        double tagTime;
        //计费时间段
        List<ChargeRuleTimeViewResultDto> partTimes = chargeRule.getPartTimes();
        //倒数第一个收费时段时长
        int lastTime = partTimes.get(partTimes.size() - 1).getTimePart();
        //该规则用户停车天数
        days = AmountUtils.differentDays(startTime, endTime);
        Integer firstDayAmount;
        Integer otherOneDayAmount;
        Integer otherDayAmount = 0;
        Integer lastDayAmount = 0;
        Integer twiceDayAmount;
        int firstDayParkingDuration;
        Integer frontAmount;
        //第一天
        natureStartTime = startTime;
        natureEndTime = startTime.plusDays(1).withTimeAtStartOfDay();
        perDuration = new Duration(natureStartTime, natureEndTime);
        firstDayParkingDuration = (int) (perDuration.getMillis() / ONE_MINUTE_MILLIS);
        firstDayAmount = getFirstTagAmount(chargeRule, firstDayParkingDuration);
        flag = mark;
        //计算第二天金额
        int twiceDayParkingDuration = ONE_DAY_MINUTE;
        twiceDayAmount = getFirstTagAmount(chargeRule, twiceDayParkingDuration);
        for (int i = 2; i <= days; i++) {
            if (i == 2) {
                if (firstDayAmount.compareTo(chargeRule.getDayTopAmount()) == 0 || firstDayAmount.compareTo(chargeRule.getDayTopAmount()) > 0) {
                    firstDayAmount = AmountUtils.compareDayTopAmount(chargeRule, firstDayAmount);
                    twiceDayAmount = AmountUtils.compareDayTopAmount(chargeRule, twiceDayAmount);
                } else {
                    if (firstDayParkingDuration < lastTime) {
                        tagTime = figureUnitTimeSize(chargeRule.getUnitTime(), twiceDayParkingDuration - (lastTime - firstDayParkingDuration));
                        Double lastValue = partTimes.get(partTimes.size() - 1).getPrice() * tagTime;
                        lastAmount = lastValue.intValue();
                        frontTime = partTimes.get(flag).getTimePart() - firstDayParkingDuration;
                        tagTime = figureUnitTimeSize(chargeRule.getUnitTime(), frontTime);
                        Double frontValue = partTimes.get(flag).getPrice() * tagTime;
                        frontAmount = frontValue.intValue();
                        //  计算分段收费金额stageTotal
                        stageTotal = getStageTotal(partTimes, stageTotal, mark + 1);
                        stageTotal += frontAmount;
                        twiceDayAmount = lastAmount + stageTotal;
                        twiceDayAmount = AmountUtils.compareDayTopAmount(chargeRule, twiceDayAmount);
                    } else {
                        twiceDayAmount = partTimes.get(partTimes.size() - 1).getPrice() * 24;
                        twiceDayAmount = AmountUtils.compareDayTopAmount(chargeRule, twiceDayAmount);
                    }
                }
            } else if (i == days) {
                //最后一天
                natureStartTime = startTime.withTimeAtStartOfDay().plusDays(days - 1);
                natureEndTime = endTime;
                perDuration = new Duration(natureStartTime, natureEndTime);
                perParkingDuration = (int) (perDuration.getMillis() / ONE_MINUTE_MILLIS);
                lastDayAmount = getFirstTagAmount(chargeRule, perParkingDuration);
                lastDayAmount = AmountUtils.compareDayTopAmount(chargeRule, lastDayAmount);
            } else {
                perParkingDuration = ONE_DAY_MINUTE;
                otherOneDayAmount = AmountUtils.compareDayTopAmount(chargeRule, getFirstTagAmount(chargeRule, perParkingDuration));
                otherDayAmount += otherOneDayAmount;
            }
        }
        totalAmount = firstDayAmount + twiceDayAmount + otherDayAmount + lastDayAmount;
        return totalAmount;

    }

    /**
     * 计算分段收费金额stageTotal
     *
     * @param partTimes  时段列表
     * @param stageTotal stageTotal
     * @return BigDecimal
     */
    private Integer getStageTotal(List<ChargeRuleTimeViewResultDto> partTimes, Integer stageTotal, int start) {
        //每个时段收费金额
        Integer perAmount;
        // 每个时段时长
        int perTime;
        //每个时段有几个收费单位
        double perTimeSize;

        for (int i = start; i < partTimes.size(); i++) {
            perTime = partTimes.get(i).getTimePart() - partTimes.get(i - 1).getTimePart();
            perTimeSize = (double) perTime / Const.HOUR;
            Double perValue = partTimes.get(i).getPrice() * perTimeSize;
            perAmount = perValue.intValue();
            stageTotal += perAmount;
        }
        return stageTotal;
    }
}
