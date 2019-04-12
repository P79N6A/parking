package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用户资产请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserAssetGetRequestDto", description = "用户资产请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAssetGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    private Long userId;

}