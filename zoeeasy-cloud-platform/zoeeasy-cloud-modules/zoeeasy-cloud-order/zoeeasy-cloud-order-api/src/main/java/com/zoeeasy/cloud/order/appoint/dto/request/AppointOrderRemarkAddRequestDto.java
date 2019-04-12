package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加备注
 *
 * @author zwq
 * @date 2018-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderRemarkAddRequestDto", description = "添加备注")
public class AppointOrderRemarkAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    @NotBlank(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
