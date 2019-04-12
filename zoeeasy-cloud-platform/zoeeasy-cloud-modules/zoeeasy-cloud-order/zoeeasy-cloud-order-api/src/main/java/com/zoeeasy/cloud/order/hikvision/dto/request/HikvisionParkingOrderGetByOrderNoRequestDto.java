package com.zoeeasy.cloud.order.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/9/30 0030
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HikvisionParkingOrderGetByOrderNoRequestDto", description = "海康平台订单返回结果")
public class HikvisionParkingOrderGetByOrderNoRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 平台订单号
     */
    @ApiModelProperty(value = "平台订单号", required = true)
    @NotBlank(message = OrderConstant.RECORD_NO_NOT_EMPTY)
    @Length(min = OrderConstant.MIN, max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String orderNo;

    /**
     * 租户Id
     */
    @ApiModelProperty(value = "租户Id", required = true)
    @NotBlank(message = OrderConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;
}
