package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketApproveGetResultDto", description = "包期车信息视图模型")
public class PacketApproveGetResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车主姓名
     */
    @ApiModelProperty("车主姓名")
    private String ownerName;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private String plateColor;

    /**
     * 车主手机
     */
    @ApiModelProperty("车主手机")
    private String ownerPhone;

    /**
     * 包期类型
     */
    @ApiModelProperty("包期类型")
    private String packetType;

    /**
     * 有效期限
     */
    @ApiModelProperty("有效期限")
    private String effectivityTime;

    /**
     * 审核意见
     */
    @ApiModelProperty("审核意见")
    private String approveStatus;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    private String rejectReason;

    /**
     * 其他原因
     */
    @ApiModelProperty("其他原因")
    private String reason;

}
