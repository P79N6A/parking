package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "ApplyParkingPagedRequestDto", description = "分页查询审核停车场请求参数")
@EqualsAndHashCode(callSuper = false)
public class ApplyParkingPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号
     */
    @ApiModelProperty("停车场编号")
    private String code;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    private String tenantName;

    /**
     * 上线状态
     */
    @ApiModelProperty("上线状态")
    private Integer runStatus;

    /**
     * 审核人员
     */
    @ApiModelProperty("审核人员")
    private Long auditUserId;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;

}
