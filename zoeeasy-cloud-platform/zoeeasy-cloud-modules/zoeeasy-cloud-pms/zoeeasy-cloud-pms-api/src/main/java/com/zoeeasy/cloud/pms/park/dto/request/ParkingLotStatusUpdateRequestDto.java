package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "parkingLotStatusUpdateRequestDto", description = "更新泊位状态请求参数")
public class ParkingLotStatusUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @NotNull(message = ParkingLotConstant.PARKING_LOT_ID_NOT_NULL)
    private Long id;

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
