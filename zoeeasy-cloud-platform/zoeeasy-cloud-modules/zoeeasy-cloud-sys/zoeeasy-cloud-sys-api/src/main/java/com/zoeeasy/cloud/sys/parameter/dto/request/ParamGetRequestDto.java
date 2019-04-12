package com.zoeeasy.cloud.sys.parameter.dto.request;

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
public class ParamGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * tenantId
     */
    @ApiModelProperty(value = "tenantId")
    private Long tenantId;

    /**
     * 参数类型
     */
    @ApiModelProperty(value = "参数类型")
    private Integer type;

    /**
     * 参数秘钥
     */
    @ApiModelProperty(value = "参数秘钥")
    private String paramKey;
}
