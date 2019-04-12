package com.zhuyitech.parking.ucc.dto.request.visitlog;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 分页查询登录日志请求参数
 *
 * @author walkman
 */
@ApiModel(value = "VisitLogQueryPagedResultRequestDto", description = "分页查询登录日志请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class VisitLogQueryPagedResultRequestDto extends PagedResultRequestDto {

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