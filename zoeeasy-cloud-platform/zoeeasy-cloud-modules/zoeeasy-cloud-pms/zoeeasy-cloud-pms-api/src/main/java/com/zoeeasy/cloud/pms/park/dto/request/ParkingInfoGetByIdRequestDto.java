package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据ID获取停车场
 *
 * @Date: 2018/10/12
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingInfoGetByIdRequestDto", description = "根据ID获取停车场")
public class ParkingInfoGetByIdRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;
}
