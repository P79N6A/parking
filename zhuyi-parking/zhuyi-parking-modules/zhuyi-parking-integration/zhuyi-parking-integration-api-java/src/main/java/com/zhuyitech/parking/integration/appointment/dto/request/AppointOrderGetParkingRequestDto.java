package com.zhuyitech.parking.integration.appointment.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取预约订单详情请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderGetParkingRequestDto", description = "获取预约订单详情请求参数")
public class AppointOrderGetParkingRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预定订单号
     */
    @ApiModelProperty(value = "预定订单号", required = true)
    @NotBlank(message = "预定订单号不能为空")
    private String orderNo;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;
}
