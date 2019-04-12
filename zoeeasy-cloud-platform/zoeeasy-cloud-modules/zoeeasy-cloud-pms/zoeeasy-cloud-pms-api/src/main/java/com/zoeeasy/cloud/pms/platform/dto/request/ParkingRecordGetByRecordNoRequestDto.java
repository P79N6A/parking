package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/10/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordGetByRecordNoRequestDto", description = "根据停车记录流水号获取停车记录")
public class ParkingRecordGetByRecordNoRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty(value = "停车记录流水号", required = true)
    @NotBlank(message = "停车记录流水号不能为空")
    private String recordNo;
}
