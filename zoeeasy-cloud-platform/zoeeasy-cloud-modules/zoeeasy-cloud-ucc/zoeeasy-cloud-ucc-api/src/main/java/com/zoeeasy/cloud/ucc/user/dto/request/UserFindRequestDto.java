package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserFindRequestDto", description = "查找用户请求参数")
public class UserFindRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = UccConstant.ACCOUNT_NAME_NOT_EMPTY)
    private String account;

}
