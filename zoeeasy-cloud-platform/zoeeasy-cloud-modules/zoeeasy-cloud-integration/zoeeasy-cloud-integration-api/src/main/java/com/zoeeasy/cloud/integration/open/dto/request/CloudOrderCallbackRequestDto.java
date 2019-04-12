package com.zoeeasy.cloud.integration.open.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CloudOrderCallbackRequestDto", description = "账单回调接口请求参数")
public class CloudOrderCallbackRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    private Long parkingId;

    /**
     * 停车场管理系统订单号
     */
    @ApiModelProperty(value = "停车场管理系统订单号", required = true)
    @NotNull(message = "停车场管理系统订单号不能为空")
    private String billCode;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额", required = true)
    @NotNull(message = "实付金额不能为空")
    private Integer payAmount;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式", required = true)
    @NotNull(message = "支付方式不能为空")
    private Integer payWay;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date payTime;

}
