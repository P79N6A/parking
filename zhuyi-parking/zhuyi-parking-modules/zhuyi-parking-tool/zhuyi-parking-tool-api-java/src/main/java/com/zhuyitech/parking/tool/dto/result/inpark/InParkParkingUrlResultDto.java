package com.zhuyitech.parking.tool.dto.result.inpark;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Inpark停车场列表URL返回结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InParkParkingUrlResultDto", description = "Inpark停车场列表URL返回结果")
public class InParkParkingUrlResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private String url;
}