package com.zhuyitech.parking.ucc.dto.request.visitlog;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 菜单列表请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "OperationLogListGetRequestDto", description = "菜单列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = true)
public class VisitLogListGetRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端IP
     */
    @ApiModelProperty(value = "客户端IP")
    private String clientIp;

    /**
     * 会话ID
     */
    @ApiModelProperty(value = "会话ID")
    private String sessionId;

    /**
     * 登录用户名
     */
    @ApiModelProperty(value = "登录用户名")
    private String username;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String accessToken;

    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String refreshToken;

}