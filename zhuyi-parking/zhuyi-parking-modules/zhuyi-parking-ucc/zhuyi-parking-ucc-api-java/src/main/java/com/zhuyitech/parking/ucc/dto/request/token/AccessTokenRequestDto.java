package com.zhuyitech.parking.ucc.dto.request.token;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 访问令牌请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AccessTokenRequestDto", description = "访问令牌请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccessTokenRequestDto extends BaseDto {

    private static final long serialVersionUID = 7336709167855003667L;

    /**
     * clientId
     */
    @ApiModelProperty(value = "clientId")
    @NotEmpty(message = "clientId is required")
    @Length(min = 5, max = 255, message = "clientId长度(5-255)")
    private String clientId;

    /**
     * clientSecret
     */
    @ApiModelProperty(value = "clientSecret")
    @NotEmpty(message = "clientSecret is required")
    @Length(min = 8, max = 255, message = "clientId长度(8-255)")
    private String clientSecret;

    @ApiModelProperty(value = "grantType")
    @NotEmpty(message = "grantType is required")
    private String grantType;

    /**
     *
     */
    @ApiModelProperty(value = "grantType")
    private String principal;

    /**
     *
     */
    @ApiModelProperty(value = "credentials")
    private String credentials;

    /**
     *
     */
    @ApiModelProperty(value = "scope")
    private String scope;

}
