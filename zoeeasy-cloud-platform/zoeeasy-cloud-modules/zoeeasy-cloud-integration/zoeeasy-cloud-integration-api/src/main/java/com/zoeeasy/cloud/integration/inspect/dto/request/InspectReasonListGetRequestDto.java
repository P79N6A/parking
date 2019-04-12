package com.zoeeasy.cloud.integration.inspect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 巡检异常原因请求参数表
 *
 * @author zwq
 */
@ApiModel(value = "InspectReasonListGetRequestDto", description = "巡检异常原因请求参数表")
@Data
@EqualsAndHashCode(callSuper = true)
public class InspectReasonListGetRequestDto extends SignedSessionRequestDto {

}
