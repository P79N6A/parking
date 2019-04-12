package com.zoeeasy.cloud.inspect.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询停车场模型
 *
 * @Date: 2018/11/16
 * @author: lhj
 */
@ApiModel(value = "ParkingByUserResultDto", description = "查询停车场模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingByUserResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

}
