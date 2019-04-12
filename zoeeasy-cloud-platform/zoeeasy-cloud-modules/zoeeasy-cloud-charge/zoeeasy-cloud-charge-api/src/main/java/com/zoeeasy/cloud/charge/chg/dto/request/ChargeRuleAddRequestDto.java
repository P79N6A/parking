package com.zoeeasy.cloud.charge.chg.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.scapegoat.infrastructure.jackson.convert.deserializer.ToIntegerFenDeserializer;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 添加停车场收费规则请求参数
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleAddRequestDto", description = "添加停车场收费规则请求参数")
public class ChargeRuleAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 收费规则编号
     */
    @ApiModelProperty(value = "收费规则编号", required = true)
    @NotBlank(message = ChargeConstant.CHARGE_CODE_NOT_EMPTY)
    @Length(min = ChargeConstant.MIN, max = ChargeConstant.MAX, message = ChargeConstant.LENGTH_RANGE)
    @Pattern(regexp = ChargeConstant.CODE_REGEX, message = ChargeConstant.RULE_CODE_REGEX_ERROR_MESSAGE)
    private String code;

    /**
     * 收费规则名称
     */
    @ApiModelProperty(value = "收费规则名称", required = true)
    @NotBlank(message = ChargeConstant.CHARGE_NAME_NOT_EMPTY)
    @Length(max = ChargeConstant.MAX, message = ChargeConstant.LENGTH_RANGE)
    @Pattern(regexp = ChargeConstant.NAME_REGEX, message = ChargeConstant.RULE_NAME_REGEX_ERROR_MESSAGE)
    private String name;

    /**
     * 规则类型 1 不收费,2 按时计费 3,按次计费 4,日夜分时分次计费
     */
    @ApiModelProperty(value = "规则类型", notes = "1 不收费,2 按时计费 3,按次计费 4,日夜分时分次计费", required = true)
    @NotNull(message = ChargeConstant.CHARGE_RULE_TYPE_NOT_EMPTY)
    private Integer ruleType;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型", required = true)
    @NotNull(message = ChargeConstant.CHARGE_CAR_TYPE_NOT_EMPTY)
    private Integer carType;

    /**
     * 车牌类型
     */
    @ApiModelProperty("车牌类型")
    private String plateNumberType;

    /**
     * 停车点等级 0,不区分 ,1 一级,2 二级, 3 三级
     */
    @ApiModelProperty(value = "停车点等级", required = true)
    @NotNull(message = ChargeConstant.CHARGE_PARKING_LEVEL_NOT_EMPTY)
    private Integer parkingLevel;

    /**
     * 假期类型：1工作日，2双休 3 小长假 4长假
     */
    @ApiModelProperty(value = "假期类型", required = true)
    @NotNull(message = ChargeConstant.CHARGE_HOLIDAY_TYPE_NOT_EMPTY)
    private Integer holidayType;

    /**
     * 计时开始时间
     */
    @ApiModelProperty("计时开始时间 HH:mm")
    @Length(max = ChargeConstant.MAX, message = ChargeConstant.LENGTH_RANGE)
    private String timesStartTime;

    /**
     * 计时结束时间
     */
    @ApiModelProperty("计时结束时间 HH:mm")
    @Length(max = ChargeConstant.MAX, message = ChargeConstant.LENGTH_RANGE)
    private String timesEndTime;

    /**
     * 计次开始时间
     */
    @ApiModelProperty("计次开始时间 HH:mm")
    @Length(max = ChargeConstant.MAX, message = ChargeConstant.LENGTH_RANGE)
    private String perStartTime;

    /**
     * 计次结束时间
     */
    @ApiModelProperty("计次结束时间 HH:mm")
    @Length(max = ChargeConstant.MAX, message = ChargeConstant.LENGTH_RANGE)
    private String perEndTime;

    /**
     * 按次收费金额
     */
    @ApiModelProperty("按次收费金额")
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    @Range(max = ChargeConstant.SMALL_INT_MAX, message = ChargeConstant.PER_PRICE_VALUE_OUT_OF_ROUND)
    private Integer perPrice;

    /**
     * 是否启用封顶金额：0,不启用1,启用
     */
    @ApiModelProperty("是否启用封顶金额")
    private Boolean topStatus;

    /**
     * 免费时长
     */
    @ApiModelProperty("免费时长")
    @Range(max = ChargeConstant.SMALL_INT_MAX, message = ChargeConstant.FREE_TIME_VALUE_OUT_OF_ROUND)
    private Integer freeTime;

    /**
     * 超出免费时长时免费时长是否收费 0,不收费 1,收费
     */
    @ApiModelProperty("超出免费时长时免费时长是否收费")
    private Boolean overFreeTime;

    /**
     * 24小时内存在按时段收费则全按时段收费 0,不存在 1,存在
     */
    @ApiModelProperty("24小时内存在按时段收费则全按时段收费")
    private Boolean existPartTime;

    /**
     * 24小时内是否启用封顶金额 0,不启用1,启用
     */
    @ApiModelProperty("24小时内是否启用封顶金额")
    private Boolean inTimeTop;

    /**
     * 封顶金额
     */
    @ApiModelProperty("封顶金额")
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    @Range(max = ChargeConstant.INT_MAX, message = ChargeConstant.TOP_AMOUNT_VALUE_OUT_OF_ROUND)
    private Integer topAmount;

    /**
     * 24小时封顶金额
     */
    @ApiModelProperty("24小时封顶金额")
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    @Range(max = ChargeConstant.INT_MAX, message = ChargeConstant.DAY_TOP_AMOUNT_VALUE_OUT_OF_ROUND)
    private Integer dayTopAmount;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @Length(max = ChargeConstant.DESCRIBE_LENGTH, message = ChargeConstant.LENGTH_RANGE)
    @Pattern(regexp = ChargeConstant.NAME_REGEX, message = ChargeConstant.DESCRIBE_REGEX_ERROR)
    private String description;

    /**
     * 最小计时单位
     */
    @ApiModelProperty("最小计时单位")
    @Range(max = ChargeConstant.SMALL_INT_MAX, message = ChargeConstant.UNIT_TIME_VALUE_OUT_OF_ROUND)
    private Integer unitTime;

    /**
     * 计时收费时段
     */
    @ApiModelProperty("计时收费时段")
    private List<ChargeRuleTimeAddRequestDto> partTimes;

}
