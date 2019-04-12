package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 通知海康平台停车账单支付成功请求参数
 *
 * @author walkman
 * @date 2018-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingPaymentNotifyRequestDto", description = "海康平台停车账单请求参数")
public class ParkingPaymentNotifyRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单编号
     */
    @ApiModelProperty(value = "账单编号", required = true)
    @NotBlank(message = "账单编号不能为空")
    private String billNo;

    /**
     * 支付时间 单位：毫秒
     */
    @ApiModelProperty(value = "支付时间", required = true)
    @NotNull(message = "支付时间不能为空")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date payTime;

    /**
     * 支付金额 单位： 分
     */
    @ApiModelProperty(value = "支付金额", required = true)
    @NotNull(message = "支付金额不能为空")
    private Integer payAmount;

    /**
     * 支付类型 参照支付类型枚举
     */
    @ApiModelProperty(value = "支付类型", required = true)
    @NotBlank
    private String payType;

}
