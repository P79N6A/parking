package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ApplyParkingPagedResultDto", description = "审核停车场请求结果")
public class ApplyParkingPagedResultDto extends BaseDto {

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
     * 所属商户
     */
    @ApiModelProperty("所属商户")
    private String tenantName;

    /**
     * 停车场地址
     */
    @ApiModelProperty("停车场地址")
    private String detailAddress;

    /**
     * 收费规则条数
     */
    @ApiModelProperty("收费规则条数")
    private Integer chargeRuleTotal;

    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    private Date applyTime;

    /**
     * 审核时间
     */
    @ApiModelProperty("审核时间")
    private Date auditTime;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer auditStatus;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    private Integer auditNotPassReason;

    /**
     * 审核人员
     */
    @ApiModelProperty("审核人员")
    private String auditUserName;

    /**
     * 上下线状态
     */
    @ApiModelProperty("上下线状态: 1未上架 2上架审核中 3上架处理中 4已上架 5下架审核中 6下架处理中 7已下架 8上架申请驳回 9下架申请驳回")
    private Integer runStatus;

}
