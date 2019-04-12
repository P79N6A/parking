package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据停车场Id获取泊位列表请求参数
 *
 * @Date: 2018/11/16
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotByParkingIdListGetRequestDto", description = "根据停车场Id获取泊位列表请求参数")
public class ParkingLotByParkingIdListGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
