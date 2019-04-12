package com.zoeeasy.cloud.inspect.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询停车场请求参数
 *
 * @Date: 2018/11/16
 * @author: lhj
 */
@ApiModel(value = "ParkingByUserRequestDto", description = "查询停车场请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingByUserRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

}
