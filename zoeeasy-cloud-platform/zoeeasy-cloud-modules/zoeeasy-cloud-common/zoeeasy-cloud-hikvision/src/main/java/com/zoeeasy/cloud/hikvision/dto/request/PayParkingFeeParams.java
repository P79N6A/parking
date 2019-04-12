package com.zoeeasy.cloud.hikvision.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 停车账单支付成功后通知云平台的请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayParkingFeeParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单编号
     */
    @ApiModelProperty(value = "账单编号", required = true)
    @NotBlank
    private String billNo;

    /**
     * 支付时间 单位：毫秒
     */
    @ApiModelProperty(value = "支付时间", required = true)
    @NotNull
    private Long payTime;

    /**
     * 支付金额 单位： 分
     */
    @ApiModelProperty(value = "支付金额", required = true)
    @NotNull
    private Integer payAmount;

    /**
     * 支付类型 参照支付类型枚举
     */
    @ApiModelProperty(value = "支付类型", required = true)
    @NotBlank
    private String payType;

}
