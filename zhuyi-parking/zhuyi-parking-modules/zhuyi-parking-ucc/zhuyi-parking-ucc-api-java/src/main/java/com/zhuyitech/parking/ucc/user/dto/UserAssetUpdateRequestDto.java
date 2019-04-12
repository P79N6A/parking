package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 用户资产更新请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserAssetUpdateRequestDto", description = "用户资产更新请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAssetUpdateRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "余额", required = true)
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;

}