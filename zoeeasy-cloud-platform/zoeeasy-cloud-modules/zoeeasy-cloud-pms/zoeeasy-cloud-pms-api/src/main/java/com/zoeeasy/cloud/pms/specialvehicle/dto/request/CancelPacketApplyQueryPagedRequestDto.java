package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.ApproveStatusEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CancelPacketApplyQueryPagedRequestDto", description = "取消包期申请列表分页请求参数")
public class CancelPacketApplyQueryPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    @FluentValidate({ApproveStatusEnumValidator.class})
    private Integer approveStatus;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

}
