package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zwq
 * @date 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotStatusUpdateByParkingLotIdRequestDto", description = "更新泊位状态请求参数")
public class ParkingLotStatusUpdateByParkingLotIdRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * parkingId
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

    /**
     * parkingLotId
     */
    @ApiModelProperty("parkingLotId")
    private Long parkingLotId;

    /**
     * 泊位状态
     */
    @ApiModelProperty("泊位状态")
    private Integer status;

    /**
     * 占用时间
     */
    @ApiModelProperty("占用时间")
    private Date occupyTime;
}
