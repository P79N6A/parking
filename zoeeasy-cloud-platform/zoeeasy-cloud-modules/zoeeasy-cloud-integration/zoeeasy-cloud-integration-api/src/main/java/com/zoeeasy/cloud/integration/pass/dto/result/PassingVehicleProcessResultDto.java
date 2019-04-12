package com.zoeeasy.cloud.integration.pass.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 过车记录处理结果
 *
 * @author walkman
 * @since 2018/10/15 0015
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleProcessResultDto", description = "添加过车记录请求参数")
public class PassingVehicleProcessResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录ID
     */
    private Long passingId;

    /**
     * 过车记录流水号
     */
    private String passingNo;
}
