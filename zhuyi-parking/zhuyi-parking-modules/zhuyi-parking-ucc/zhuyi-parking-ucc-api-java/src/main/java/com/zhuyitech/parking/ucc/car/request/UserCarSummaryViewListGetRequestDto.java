package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;


/**
 * 当前用户车辆列表请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserCarSummaryViewListGetRequestDto", description = "当前用户车辆列表请求参数")
public class UserCarSummaryViewListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}
