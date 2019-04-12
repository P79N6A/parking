package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车位列表视图
 * Created by song on 2018/9/25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotPagedResultDto", description = "车位列表视图")
public class ParkingLotPagedResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位号
     */
    @ApiModelProperty("泊位号")
    private String number;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String code;

    /**
     * 停车场编号
     */
    @ApiModelProperty("停车场编号")
    private String parkingCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 泊位状态(0-空闲, 1-占用，2-未知)
     */
    @ApiModelProperty("泊位状态(0-空闲, 1-占用，2-未知)")
    private Integer status;

    /**
     * 停车场id
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

}
