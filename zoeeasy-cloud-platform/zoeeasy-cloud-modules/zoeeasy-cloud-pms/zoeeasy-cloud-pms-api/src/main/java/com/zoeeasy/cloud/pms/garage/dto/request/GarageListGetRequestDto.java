package com.zoeeasy.cloud.pms.garage.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车库列表请求参数
 * Created by song on 2018/9/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarageListGetRequestDto", description = "车库列表请求参数")
public class GarageListGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

    /**
     * 车库名称
     */
    @ApiModelProperty("name")
    private String name;

}
