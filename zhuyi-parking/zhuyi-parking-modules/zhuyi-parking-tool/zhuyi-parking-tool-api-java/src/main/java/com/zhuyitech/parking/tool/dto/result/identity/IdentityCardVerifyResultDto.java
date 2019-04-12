package com.zhuyitech.parking.tool.dto.result.identity;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 身份证校验结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IdentityCardVerifyResultDto", description = "身份证校验结果")
public class IdentityCardVerifyResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 验证状态
     */
    @ApiModelProperty(value = "认证状态", notes = "0 认证一致 1不一致")
    private Integer verifyStatus;

    /**
     * 验证消息
     */
    @ApiModelProperty(value = "用户认证信息")
    private String verifyMsg;

}
