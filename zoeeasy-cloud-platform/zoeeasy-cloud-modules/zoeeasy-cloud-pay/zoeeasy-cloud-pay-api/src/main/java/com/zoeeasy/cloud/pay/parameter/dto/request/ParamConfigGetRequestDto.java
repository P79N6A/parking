package com.zoeeasy.cloud.pay.parameter.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据租户ID和参数类型获取参数列表
 *
 * @author zwq
 */
@ApiModel(value = "ParamGetRequestDto", description = "根据租户ID和参数类型获取参数列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamConfigGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 参数类型
     */
    @ApiModelProperty(value = "参数类型")
    private Integer type;

}
