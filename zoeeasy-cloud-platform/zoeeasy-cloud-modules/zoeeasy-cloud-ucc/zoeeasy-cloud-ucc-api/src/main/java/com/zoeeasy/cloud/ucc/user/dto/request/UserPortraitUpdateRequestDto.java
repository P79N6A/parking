package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author walkman
 * @since 2018/11/3
 */
@ApiModel(value = "UserPortraitUpdateRequestDto", description = "用户修改个人头像的请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPortraitUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户头像URL")
    @NotEmpty(message = UccConstant.USER_PORTRAIT_URL_NOT_EMPTY)
    private String portraitUrl;

}


