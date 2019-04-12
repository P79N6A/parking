package com.zhuyitech.parking.ucc.dto.request.recharge;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户充值记录请求参数
 *
 * @Date: 2018/3/2
 * @author: yuzhicheng
 */
@ApiModel(value = "UserRechargeRecordGetRequestDto", description = "获取用户充值记录请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRechargeRecordGetRequestDto extends SessionEntityDto<Long> {

    public static final long serialVersionUID = 1L;

    /**
     * 订单UUID
     */
    @ApiModelProperty("orderUuid")
    private String orderUuid;

    /**
     * 充值订单号
     */
    @ApiModelProperty("orderNo")
    private String orderNo;

}
