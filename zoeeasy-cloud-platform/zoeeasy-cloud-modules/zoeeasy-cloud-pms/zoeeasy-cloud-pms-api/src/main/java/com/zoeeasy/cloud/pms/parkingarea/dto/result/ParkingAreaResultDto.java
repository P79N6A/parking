package com.zoeeasy.cloud.pms.parkingarea.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 泊位区域视图模型
 * Created by song on 2018/9/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAreaResultDto", description = "泊位区域视图模型")
public class ParkingAreaResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;

    /**
     * 车库名称
     */
    @ApiModelProperty("车库名称")
    private String garageName;

    /**
     * 泊车区域编号
     */
    @ApiModelProperty("泊车区域编号")
    private String code;

    /**
     * 泊位区域名称
     */
    @ApiModelProperty("泊位区域名称")
    private String name;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    private Integer lotTotal;

    /**
     * 固定车位总数
     */
    @ApiModelProperty("固定车位总数")
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
     * 楼层id
     */
    @ApiModelProperty("楼层id")
    private Long floorId;

    /**
     * 楼层名称
     */
    @ApiModelProperty("楼层名称")
    private String floorName;

}
