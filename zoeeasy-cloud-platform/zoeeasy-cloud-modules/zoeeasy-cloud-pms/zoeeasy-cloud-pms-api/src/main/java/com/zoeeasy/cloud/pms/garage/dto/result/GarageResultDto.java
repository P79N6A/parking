package com.zoeeasy.cloud.pms.garage.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车库视图模型
 * Created by song on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarageResultDto", description = "车库视图模型")
public class GarageResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty("parkingName")
    private String parkingName;

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
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 蓝牙id
     */
    @ApiModelProperty("蓝牙id")
    private String bleUuid;

}
