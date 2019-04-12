package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据泊位编号获取泊位列表请求参数
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotByCodeListGetRequestDto", description = "根据泊位编号获取泊位列表请求参数")
public class ParkingLotByCodeListGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String code;
}
