package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by song on 2018/10/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "EndDateRequestDto", description = "结束时间获取请求参数")
public class EndDateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_BEGIN_DATE_NOT_NULL)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date beginDate;

    /**
     * 包期类型
     */
    @ApiModelProperty("包期类型")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PACKET_TYPE_NOT_NULL)
    private Integer packetType;

}
