package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 审核通过停车场请求结果
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ManagementParkingPagedResultDto", description = "审核通过停车场请求结果")
public class ManagementParkingPagedResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 停车场编号
     */
    @ApiModelProperty("停车场编号")
    private String code;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    private Integer lotTotal;

    /**
     * 剩余车位数
     */
    @ApiModelProperty("剩余车位数")
    private Integer lotAvailable;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 所属商户
     */
    @ApiModelProperty("所属商户")
    private String tenantName;

    /**
     * 上下架
     */
    @ApiModelProperty("上下架")
    private Integer runStatus;

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    private Date creationTime;

    /**
     * 是否可编辑
     */
    @ApiModelProperty("是否可编辑")
    private Boolean edit;

}
