package com.zoeeasy.cloud.pms.parkingarea.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 泊位区域列表请求参数
 * Created by song on 2018/9/29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAreaListRequestDto", description = "泊位区域列表请求参数")
public class ParkingAreaListRequestDto extends SignedRequestDto {

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;

    /**
     * 泊车区域名称
     */
    @ApiModelProperty("泊车区域名称")
    private String name;

}
