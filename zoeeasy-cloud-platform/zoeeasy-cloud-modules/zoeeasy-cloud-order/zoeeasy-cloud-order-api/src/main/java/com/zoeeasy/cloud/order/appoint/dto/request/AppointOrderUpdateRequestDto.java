package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 修改预约账单请求参数
 *
 * @Date: 2018/7/25
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderUpdateRequestDto")
public class AppointOrderUpdateRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @ApiModelProperty("订单流水号")
    @NotEmpty(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

    /**
     * 预约状态
     */
    @ApiModelProperty("预约状态")
    private Integer appointStatus;

    /**
     * 取消时间
     */
    @ApiModelProperty("取消时间")
    private Date cancelTime;

    /**
     * 取消原因
     */
    @ApiModelProperty("取消原因")
    private String cancelReason;

    /**
     * 支付状态
     */
    @ApiModelProperty("支付状态")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty("支付时间")
    private Date payTime;

    /**
     * 支付方式(根据PayWayEnum)
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 支付类型(根据PayTypeEnum)
     */
    @ApiModelProperty(value = "支付类型")
    private Integer payType;

    /**
     * 预约实付金额
     */
    @ApiModelProperty("预约实付金额(分)")
    private Integer actualPayAmount;
}
