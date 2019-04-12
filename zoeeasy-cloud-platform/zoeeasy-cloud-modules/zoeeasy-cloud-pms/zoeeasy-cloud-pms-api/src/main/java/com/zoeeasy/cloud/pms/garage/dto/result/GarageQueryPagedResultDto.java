package com.zoeeasy.cloud.pms.garage.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车库列表视图模型
 * Created by song on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingListResultDto", description = "车库列表视图模型")
public class GarageQueryPagedResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车库编号
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 车库名称
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    private Integer lotCount;

    /**
     * 固定车位数
     */
    @ApiModelProperty("固定车位数")
    private Integer lotFixed;

    /**
     * 剩余车位数
     */
    @ApiModelProperty("剩余车位数")
    private Integer lotAvailable;

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

}
