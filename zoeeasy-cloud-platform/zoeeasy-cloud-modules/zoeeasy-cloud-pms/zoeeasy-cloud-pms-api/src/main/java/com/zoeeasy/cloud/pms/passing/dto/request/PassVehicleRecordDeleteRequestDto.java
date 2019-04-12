package com.zoeeasy.cloud.pms.passing.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除过车记录请求参数
 *
 * @author AkeemSuper
 * @Date: 2018/7/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Api(value = "PassVehicleRecordDeleteRequestDto")
public class PassVehicleRecordDeleteRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty(value = "平台过车流水号")
    @NotBlank(message = "平台过车流水号不能为空")
    private String passingNo;

}
