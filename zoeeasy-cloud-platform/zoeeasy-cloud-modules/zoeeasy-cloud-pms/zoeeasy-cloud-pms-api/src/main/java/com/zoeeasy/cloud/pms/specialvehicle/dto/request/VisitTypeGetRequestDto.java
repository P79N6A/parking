package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 访客车类型获取请求参数
 *
 * @date: 2018/10/18.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitTypeGetRequestDto", description = "访客车类型获取请求参数")
public class VisitTypeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
}
