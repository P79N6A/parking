package com.zoeeasy.cloud.charge.park.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 收费规则关联停车信息视图
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeRuleViewResultDto", description = "收费规则关联停车信息视图")
public class ParkingChargeRuleViewResultDto extends EntityDto<Long> {

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
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 收费规则ID
     */
    @ApiModelProperty("收费规则ID")
    private Long ruleId;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    private String name;

}
