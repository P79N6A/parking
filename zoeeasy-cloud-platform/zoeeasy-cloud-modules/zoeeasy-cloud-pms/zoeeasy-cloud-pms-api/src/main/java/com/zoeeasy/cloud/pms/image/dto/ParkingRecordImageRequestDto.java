package com.zoeeasy.cloud.pms.image.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加停车图片请求参数
 *
 * @Date: 2019/3/27
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordImageRequestDto", description = "添加停车图片请求参数")
public class ParkingRecordImageRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户
     */
    @ApiModelProperty("tenantId")
    private Long tenantId;

    /**
     * 过车记录ID
     */
    @ApiModelProperty("bizId")
    private Long bizId;

    /**
     * 停车场Code
     */
    @ApiModelProperty("parkingCode")
    private String parkingCode;

    /**
     * 全景图像
     */
    @ApiModelProperty("fullImage")
    private String fullImage;

    /**
     * 车牌图像
     */
    @ApiModelProperty("plateImage")
    private String plateImage;

    /**
     * 过车/停车记录流水号
     */
    @ApiModelProperty("过车/停车记录流水号")
    private String bizNo;
}
