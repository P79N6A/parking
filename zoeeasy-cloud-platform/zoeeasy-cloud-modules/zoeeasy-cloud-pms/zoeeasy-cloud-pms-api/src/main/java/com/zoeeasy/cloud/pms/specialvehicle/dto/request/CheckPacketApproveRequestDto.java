package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.ApproveStatusEnumValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.RejectReasonEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CheckPacketApproveRequestDto", description = "取消包期请求参数")
public class CheckPacketApproveRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 审核意见
     */
    @ApiModelProperty("审核意见")
    @FluentValidate({ApproveStatusEnumValidator.class})
    private Integer approveStatus;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    @FluentValidate({RejectReasonEnumValidator.class})
    private Integer rejectReason;

    /**
     * 其他原因
     */
    @ApiModelProperty("其他原因")
    private String reason;

}
