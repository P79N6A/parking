package com.zoeeasy.cloud.pms.area.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场区域树请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaParkingTreeRequestDto", description = "停车场区域树请求参数")
public class AreaParkingTreeRequestDto  extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;
}
