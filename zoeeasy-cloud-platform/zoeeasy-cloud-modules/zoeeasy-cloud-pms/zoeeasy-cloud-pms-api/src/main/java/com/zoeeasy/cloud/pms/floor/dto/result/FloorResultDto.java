package com.zoeeasy.cloud.pms.floor.dto.result;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 楼层详情返回结果
 *
 * Created by song on 2019/3/23 10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FloorResultDto", description = "楼层详情返回结果")
public class FloorResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 车库id
     */
    @ApiModelProperty(value = "车库ID")
    private Long garageId;

    /**
     * 楼层编号
     */
    @ApiModelProperty(value = "楼层编号")
    private String floorCode;

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    private String floorName;

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
     * 蓝牙导航图
     */
    @ApiModelProperty("蓝牙导航图")
    private String bleNavigationPic;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
