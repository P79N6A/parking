package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @Description: 获取停车记录请求参数
 * @Date: 2018/2/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordGetRequestDto", description = "获取停车记录请求参数")
public class ParkingRecordGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
