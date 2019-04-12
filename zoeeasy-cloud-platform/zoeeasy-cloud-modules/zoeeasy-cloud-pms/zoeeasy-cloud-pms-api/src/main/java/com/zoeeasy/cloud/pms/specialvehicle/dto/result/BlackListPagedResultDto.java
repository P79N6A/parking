package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BlackListPagedResultDto", description = "黑名单分页列表视图模型")
public class BlackListPagedResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

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
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 车主姓名
     */
    @ApiModelProperty("车主姓名")
    private String ownerName;

    /**
     * 车主电话
     */
    @ApiModelProperty("车主电话")
    private String ownerPhone;

    /**
     * 黑名单期限
     */
    @ApiModelProperty("validTime")
    private String validTime;

    /**
     * 生效状态
     */
    @ApiModelProperty("生效状态")
    private String status;

    /**
     * 备注
     */
    private String remark;

}
