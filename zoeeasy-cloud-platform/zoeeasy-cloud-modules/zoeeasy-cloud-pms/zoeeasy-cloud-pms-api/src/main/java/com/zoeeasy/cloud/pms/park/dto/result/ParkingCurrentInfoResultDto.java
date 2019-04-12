package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询停车场当前状态返回模型
 *
 * @Date: 2019/1/4
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingCurrentInfoResultDto", description = "查询停车场当前状态返回模型")
public class ParkingCurrentInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 运营商ID
     */
    @ApiModelProperty(value = "运营商ID")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 运营状态
     * 0.正常营业  1.暂停营业
     */
    @ApiModelProperty(value = "运营状态  0.正常营业  1.暂停营业")
    private Integer operationState;

    /**
     * 可用车位数
     */
    @ApiModelProperty(value = "可用车位数")
    private Integer lotAvailable;

    /**
     * 可预订剩余车位数
     */
    @ApiModelProperty(value = "可预订剩余车位数")
    private Integer lotAppointmentAvailable;

    /**
     * 剩余可包期车的数量
     */
    @ApiModelProperty(value = "剩余可包期车的数量")
    private Integer lotBagAvailable;

}
