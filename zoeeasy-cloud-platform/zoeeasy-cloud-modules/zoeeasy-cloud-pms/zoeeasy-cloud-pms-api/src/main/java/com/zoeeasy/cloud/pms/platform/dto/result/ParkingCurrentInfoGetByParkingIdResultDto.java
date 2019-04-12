package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zwq
 * @Description: 获取停车场当前信息结果
 * @Date: 2018/09/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingCurrentInfoGetByParkingIdResultDto", description = "获取停车场当前信息结果")
public class ParkingCurrentInfoGetByParkingIdResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 运营商ID
     */
    @ApiModelProperty("运营商ID")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 运营状态
     * 0.正常营业  1.暂停营业
     */
    @ApiModelProperty("运营状态")
    private Integer operationState;

    /**
     * 可用车位数
     */
    @ApiModelProperty("可用车位数")
    private Integer lotAvailable;

    /**
     * 可预订剩余车位数
     */
    @ApiModelProperty("可预订剩余车位数")
    private Integer lotAppointmentAvailable;

    /**
     * 剩余可包期车的数量
     */
    @ApiModelProperty("剩余可包期车的数量")
    private Integer lotBagAvailable;
}
