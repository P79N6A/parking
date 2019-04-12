package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/10/24 0024
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "parkingVehicleRecordGetByIntoPassNoRequestDto", description = "根据入车记录号获取在停车辆请求参数")
public class ParkingVehicleRecordGetByIntoPassNoRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 入车记录流水号
     */
    @ApiModelProperty(value = "入车记录流水号", required = true)
    @NotBlank(message = "入车记录流水号不能为空")
    private String intoRecordNo;

    /**
     * parkingId
     */
    @ApiModelProperty(value = "parkingId", required = true)
    @NotBlank(message = "parkingId不能为空")
    private Long parkingId;

}
