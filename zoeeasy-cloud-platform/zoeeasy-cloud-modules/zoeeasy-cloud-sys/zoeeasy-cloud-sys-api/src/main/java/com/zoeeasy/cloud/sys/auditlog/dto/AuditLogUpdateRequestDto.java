package com.zoeeasy.cloud.sys.auditlog.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 更新操作日志请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AuditLogUpdateRequestDto", description = "更新操作日志请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditLogUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户代理
     */
    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 请求URI
     */
    @ApiModelProperty(value = "请求URI")
    private String requestUri;

    /**
     * 日志标题
     */
    @ApiModelProperty(value = "日志标题")
    private String title;

    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    private String type;

    /**
     * 方法名称
     */
    @ApiModelProperty(value = "方法名称")
    private String method;

    /**
     * 异常信息
     */
    @ApiModelProperty(value = "异常信息")
    private String exception;

    /**
     * 日志内容
     */
    @ApiModelProperty(value = "日志内容")
    private String content;

    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    private String logType;

    /**
     * 操作提交的数据
     */
    @ApiModelProperty(value = "操作提交的数据")
    private String params;

    /**
     * 操作IP地址
     */
    @ApiModelProperty(value = "操作IP地址")
    private String remoteAddress;

}