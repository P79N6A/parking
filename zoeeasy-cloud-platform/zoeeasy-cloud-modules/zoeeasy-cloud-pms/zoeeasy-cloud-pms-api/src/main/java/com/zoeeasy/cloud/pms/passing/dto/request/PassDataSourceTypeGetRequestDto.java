package com.zoeeasy.cloud.pms.passing.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/16 0016
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassTypeGetRequestDto", description = "过车类型枚举请求参数")
public class PassDataSourceTypeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
}
