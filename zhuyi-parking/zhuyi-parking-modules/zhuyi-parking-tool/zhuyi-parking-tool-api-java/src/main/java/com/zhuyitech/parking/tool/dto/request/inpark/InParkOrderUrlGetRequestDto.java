package com.zhuyitech.parking.tool.dto.request.inpark;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Inpark订单列表URL请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InParkOrderUrlGetRequestDto", description = "Inpark订单列表URL请求参数")
public class InParkOrderUrlGetRequestDto extends SessionDto {
    private static final long serialVersionUID = 1L;
}
