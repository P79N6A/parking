package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by song on 2018/10/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketApproveResultDto", description = "取消包期申请列表视图模型")
public class PacketApproveResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer approveStatus;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    private String rejectReason;

}
