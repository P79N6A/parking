package com.zoeeasy.cloud.charge.appointrule.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车场预约规则视图模型
 *
 * @author walkman
 * @date 2018/04/03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleViewResultDto", description = "停车场预约规则视图模型")
public class ParkingAppointRuleViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date onlineTime;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date offlineTime;

    /**
     * 上线状态
     */
    @ApiModelProperty("上线状态")
    private String status;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 停车场编码
     */
    @ApiModelProperty("停车场编码")
    private String parkingCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 预约规则ID
     */
    @ApiModelProperty("收费规则ID")
    private Long ruleId;

    /**
     * 预约规则编号
     */
    @ApiModelProperty("预约规则编号")
    private String ruleCode;

    /**
     * 预约规则名称
     */
    @ApiModelProperty("预约规则名称")
    private String ruleName;
}
