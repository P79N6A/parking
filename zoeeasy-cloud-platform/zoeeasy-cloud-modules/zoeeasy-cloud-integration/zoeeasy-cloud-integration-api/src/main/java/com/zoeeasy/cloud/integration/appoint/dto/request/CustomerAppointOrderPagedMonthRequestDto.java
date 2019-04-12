package com.zoeeasy.cloud.integration.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 分页获取车主用户预约订单列表请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CustomerAppointOrderPagedMonthRequestDto", description = "分页获取用户预约订单列表请求参数")
public class CustomerAppointOrderPagedMonthRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * customerUserId
     */
    @ApiModelProperty(value = "customerUserId", required = true)
    @NotNull(message = IntegrationConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;

    /**
     * 查询月份
     */
    @ApiModelProperty(value = "查询月份")
    @DateTimeFormat(pattern = Const.FORMAT_DATE_MONTH)
    private Date dateTime;
}
