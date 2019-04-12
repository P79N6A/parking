package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除停车场请求参数
 * Created by song on 2018/9/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingDeleteRequestDto", description = "删除停车场请求参数")
public class ParkingDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
