package com.zoeeasy.cloud.pds.magneticdetector.dto.result.park;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据停车场泊位查询地磁参数返回
 *
 * @Date: 2018/10/17
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorByParkingAndParkingLotResultDto", description = "根据停车场泊位查询地磁参数返回")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorByParkingAndParkingLotResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁编号
     */
    @ApiModelProperty(value = "地磁编号")
    private String code;
}
