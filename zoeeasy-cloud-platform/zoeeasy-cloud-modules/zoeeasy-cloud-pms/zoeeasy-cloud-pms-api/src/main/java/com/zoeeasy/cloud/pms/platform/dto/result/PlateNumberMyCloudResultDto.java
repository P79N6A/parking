package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PlateNumberMyCloudResultDto
 * @Description 我的车牌号
 * @Author qhxu
 * @Date 2019/3/22 19:40
 * @Version1.0
 **/
@Data
@ApiModel(value = "PlateNumberMyCloudResultDto", description = "我的车牌号")
public class PlateNumberMyCloudResultDto extends SignedSessionRequestDto {

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String fullPlateNumber;

    /**
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotCode;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 车位名称
     */
    @ApiModelProperty(value = "车位名称")
    private String parkingLotName;

    /**
     * 车场名称
     */
    @ApiModelProperty(value = "车场名称")
    private String parkingName;

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**
     * 停车位置描述
     */
    @ApiModelProperty(value = "停车位置描述")
    private String parkingLotDescription;

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid")
    private String uuid;
}
