package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GetUnitTimeRequestDto", description = "获取最小计时单位请求参数")
public class GetUnitTimeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
}
