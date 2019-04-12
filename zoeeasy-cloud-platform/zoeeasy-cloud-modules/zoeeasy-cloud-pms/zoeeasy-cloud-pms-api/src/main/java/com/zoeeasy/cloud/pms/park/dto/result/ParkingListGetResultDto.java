package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场列表视图模型
 * Created by song on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingResultDto", description = "停车场列表视图模型")
public class ParkingListGetResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 停车场编号
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 区域ID
     */
    @ApiModelProperty("区域id")
    private Long areaId;

    /**
     * 停车场简称
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * 停车场全称
     */
    @ApiModelProperty("fullName")
    private String fullName;

}
