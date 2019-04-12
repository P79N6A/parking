package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordGetByPassTimeRequestDto", description = "获取过车时间前后的过车记录的请求参数")
public class ParkingRecordGetByPassTimeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场ID不能为空")
    private Long parkingId;

    /**
     * 过车时间
     */
    @ApiModelProperty(value = "过车时间", required = true)
    @NotNull(message = "过车时间不能为空")
    private Date passTime;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    private String plateNumber;

}
