package com.zoeeasy.cloud.charge.park.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description: 用户停车记录模块查询收费规则请求参数
 * @autor: AkeemSuper
 * @date: 2018/3/7 0007
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeRuleForUserParkingRecordRequestDto", description = "用户停车记录模块查询收费规则请求参数")
public class ParkingChargeRuleForUserParkingRecordRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场ID")
    private Long parkingId;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间", required = true)
    @NotNull(message = "停车开始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间", required = true)
    @NotNull(message = "停车结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;

    /**
     * 车牌类型
     */
    @ApiModelProperty(value = "车牌类型")
    private Integer carStyle;

    /**
     * 停车场等级
     */
    @ApiModelProperty(value = "停车场等级", required = true)
    @NotNull(message = "停车场等级")
    private Integer parkingLevel;

    /**
     * 租户Id
     */
    @ApiModelProperty(value = "租户ID", required = true)
    @NotNull(message = "租户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;

}
