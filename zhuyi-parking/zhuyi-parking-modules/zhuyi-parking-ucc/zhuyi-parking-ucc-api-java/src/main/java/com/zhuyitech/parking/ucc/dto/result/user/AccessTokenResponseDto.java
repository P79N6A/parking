package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 访问令牌响应参数
 *
 * @author walkman
 */
@ApiModel(value = "AccessTokenResponseDto", description = "访问令牌响应参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccessTokenResponseDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("访问令牌")
    private String accessToken;

    @ApiModelProperty("refreshToken")
    private String refreshToken;

    @ApiModelProperty("tokenType")
    private String tokenType;

    @ApiModelProperty("有效时间(秒)")
    private Integer expiresIn;

}
