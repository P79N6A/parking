package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by song on 2018/10/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketVehiclePagedResultDto", description = "包期车列表视图模型")
public class PacketVehiclePagedResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 是否全部停车场
     */
    @ApiModelProperty("是否全部停车场")
    private String allParking;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private String plateColor;

    /**
     * 驾驶人姓名
     */
    @ApiModelProperty("驾驶人姓名")
    private String ownerName;

    /**
     * 驾驶人电话
     */
    @ApiModelProperty("驾驶人电话")
    private String ownerPhone;

    /**
     * 包期类型
     */
    @ApiModelProperty("包期类型")
    private String packetType;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    /**
     * 到期时间
     */
    @ApiModelProperty("到期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 是否充值
     */
    @ApiModelProperty("是否充值")
    private Integer topUpStatus;

}
