package com.zhuyitech.parking.ucc.dto.request.realname;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用户实名申请审核请求参数
 *
 * @author walkman
 * @date 2018-01-01
 */
@ApiModel(value = "UserAuthApproveRequestDto", description = "增加用户实名申请审核请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAuthApproveRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 审核意见 1: 同意 2 拒绝
     */
    @ApiModelProperty(required = true, notes = "审核意见 1: 同意 2 拒绝")
    @NotNull(message = "审核意见不能为空")
    private Integer approveOpinion;

    /**
     * 说明,审核拒绝必填
     */
    @ApiModelProperty(notes = "说明,审核拒绝必填")
    private String remarks;

    /**
     * 真实姓名
     */
    @ApiModelProperty(notes = "真实姓名")
    private String realName;

    /**
     * 身份证号
     */
    @ApiModelProperty(notes = "身份证号")
    private String cardNo;

}