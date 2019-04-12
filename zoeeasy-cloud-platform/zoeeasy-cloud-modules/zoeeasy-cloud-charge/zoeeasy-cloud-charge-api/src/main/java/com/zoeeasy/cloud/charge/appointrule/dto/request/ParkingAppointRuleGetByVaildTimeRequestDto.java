package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zwq
 * @Description: 根据上线下线时间及停车场Id获取
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleGetByVaildTimeRequestDto", description = "根据上线下线时间及停车场Id获取")
public class ParkingAppointRuleGetByVaildTimeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date onlineTime;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date offlineTime;
}
