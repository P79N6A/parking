package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据第三方账单编号查询账单请求参数
 *
 * @Date: 2018/6/26
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderQueryByThirdBillNoRequestDto")
public class ParkingOrderQueryByThirdBillNoRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String thirdBillNo;

}
