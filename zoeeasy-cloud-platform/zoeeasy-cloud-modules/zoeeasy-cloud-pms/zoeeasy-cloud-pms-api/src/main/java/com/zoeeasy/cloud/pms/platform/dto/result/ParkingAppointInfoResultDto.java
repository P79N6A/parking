package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场预约信息视图模型
 *
 * @author zwq
 * @date 2019-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointInfoResultDto", description = "停车场预约信息视图模型")
public class ParkingAppointInfoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 是否支持预约
     */
    @ApiModelProperty(value = "是否支持预约")
    private Integer supportAppointment;

    /**
     * 是否支持将停车预订订金作为停车费的一部分 0 否， 1 是
     */
    @ApiModelProperty(value = "是否支持将停车预订订金作为停车费的一部分 0 否， 1 是")
    private Boolean supportDeposit;

    /**
     * 支持预约车位总数
     */
    @ApiModelProperty(value = "支持预约车位总数")
    private Integer lotAppointmentTotal;

    /**
     * 预约规则的简短描述
     */
    @ApiModelProperty(value = "预约规则的简短描述")
    private String appointmentRule;

    /**
     * 是否关联预约规则，0-否，1-是
     */
    @ApiModelProperty(value = "是否关联预约规则，0-否，1-是'")
    private Boolean relatedRule;

    /**
     * 是否当前有效的收费信息
     */
    @ApiModelProperty(value = "是否当前有效的收费信息")
    private Boolean active;
}

