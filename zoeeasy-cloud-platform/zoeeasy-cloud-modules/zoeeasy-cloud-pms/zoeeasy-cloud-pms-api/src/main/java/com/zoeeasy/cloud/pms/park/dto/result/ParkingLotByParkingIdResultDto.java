package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据停车场Id获取泊位列表模型
 *
 * @Date: 2018/11/16
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotByParkingIdResultDto", description = "根据停车场Id获取泊位列表模型")
public class ParkingLotByParkingIdResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty("number")
    private String number;
}
