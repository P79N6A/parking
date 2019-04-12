package com.zhuyitech.parking.integration.appointment.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户预约订单下单请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserAppointOrderPlaceRequestDto", description = "预约订单下单请求参数")
public class UserAppointOrderPlaceRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场ID不能为空")
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 预约预计开始时间
     */
    @ApiModelProperty(value = "预约预计开始时间", required = true)
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    @NotNull(message = "预计开始时间不能为空")
    private Date scheduleTime;

    /**
     * 预约金额
     */
    @ApiModelProperty(value = "预约金额", required = true)
    @NotNull(message = "预约金额不能为空")
    private BigDecimal appointAmount;

}
