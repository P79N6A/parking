package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.cts.ParkingHikConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author AkeemSuper
 * @date 2018/9/25 0025
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "hikVehicleRecordUpDateRequestDto", description = "海康平台停车账单请求参数")
public class HikVehicleRecordUpProcessStatusDateRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录唯一id
     */
    @ApiModelProperty("过车记录唯一id")
    @NotBlank(message = ParkingHikConstant.PASS_UUID_NOT_EMPTY)
    private String passingUuid;

    /**
     * 处理状态
     */
    @ApiModelProperty("过车记录唯一id")
    private Integer processStatus;

    /**
     * 原因
     */
    @ApiModelProperty("过车记录唯一id")
    private String processRemark;
}
