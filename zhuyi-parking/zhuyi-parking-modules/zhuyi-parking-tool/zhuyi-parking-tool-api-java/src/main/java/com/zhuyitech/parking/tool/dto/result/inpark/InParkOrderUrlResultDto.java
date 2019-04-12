package com.zhuyitech.parking.tool.dto.result.inpark;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Inpark订单列表URL返回结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InParkOrderUrlResultDto", description = "Inpark订单列表URL返回结果")
public class InParkOrderUrlResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private String url;
}