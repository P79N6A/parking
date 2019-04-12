package com.zoeeasy.cloud.charge.chg.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/15 0015
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "chargeRuleInfoViewResultDto", description = "收费规则信息返回结果")
public class ChargeRuleInfoViewResultDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收费规则ID
     */
    @ApiModelProperty("收费规则ID")
    private Long ruleId;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    private Date onlineTime;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    private Date offlineTime;

    /**
     * 收费规则编号
     */
    @ApiModelProperty("收费规则编号")
    private String code;

    /**
     * 收费规则名称
     */
    @ApiModelProperty("收费规则名称")
    private String name;

    /**
     * 规则类型 1 不收费,2 按时计费 3,按次计费 4,日夜分时分次计费
     */
    @ApiModelProperty("规则类型")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer ruleType;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer carType;

    /**
     * 车牌类型
     */
    @ApiModelProperty("车牌类型")
    private Integer plateNumberType;

    /**
     * 停车点等级
     */
    @ApiModelProperty("停车点等级")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer parkingLevel;

    /**
     * 假期类型：1工作日，2双休 3 小长假 4长假
     */
    @ApiModelProperty("假期类型")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer holidayType;

    /**
     * 计时开始时间
     */
    @ApiModelProperty("计时开始时间")
    private String timesStartTime;

    /**
     * 计时结束时间
     */
    @ApiModelProperty("计时结束时间")
    private String timesEndTime;

    /**
     * 计次开始时间
     */
    @ApiModelProperty("计次开始时间")
    private String perStartTime;

    /**
     * 计次结束时间
     */
    @ApiModelProperty("计次结束时间")
    private String perEndTime;

    /**
     * 按次收费金额
     */
    @ApiModelProperty("按次收费金额")
    private Integer perPrice;

    /**
     * 免费时长
     */
    @ApiModelProperty("免费时长")
    private Integer freeTime;

    /**
     * 是否启用封顶金额：0,不启用1,启用
     */
    @ApiModelProperty("是否启用封顶金额")
    private Boolean topStatus;

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
    private Integer topAmount;

    /**
     * 24小时封顶金额
     */
    @ApiModelProperty("24小时封顶金额")
    private Integer dayTopAmount;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 最小计时单位
     */
    @ApiModelProperty("最小计时单位")
    private Integer unitTime;

    /**
     * 时间段列表
     */
    @ApiModelProperty("时间段列表")
    private List<ChargeRuleTimeViewResultDto> partTimes;
}
