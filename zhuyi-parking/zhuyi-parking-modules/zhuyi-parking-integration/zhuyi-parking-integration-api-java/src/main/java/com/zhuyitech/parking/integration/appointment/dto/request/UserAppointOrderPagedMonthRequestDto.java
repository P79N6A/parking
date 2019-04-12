package com.zhuyitech.parking.integration.appointment.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 分页获取车主用户预约订单列表请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserAppointOrderPagedMonthRequestDto", description = "分页获取用户预约订单列表请求参数")
public class UserAppointOrderPagedMonthRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 查询月份
     */
    @ApiModelProperty(value = "查询月份")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date dateTime;
}
