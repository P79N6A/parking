package com.zoeeasy.cloud.sys.auditlog.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.sys.visit.cts.VisitLogConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加操作日志请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AuditLogAddRequestDto", description = "添加操作日志请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditLogAddRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "tenantId")
    private Long tenantId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "userId")
    @NotBlank(message = VisitLogConstant.USER_ID_NOT_EMPTY)
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "account")
    @NotBlank(message = VisitLogConstant.ACCOUNT_NOT_EMPTY)
    private String account;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @ApiModelProperty(value = "type")
    private Integer type;

    /**
     * 操作方式
     */
    @ApiModelProperty(value = "logType")
    private Integer logType;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户
     */
    @ApiModelProperty(value = "operatorType")
    private Integer operatorType;

    /**
     * 日志标题
     */
    @ApiModelProperty(value = "title")
    @NotEmpty(message = "日志标题不能为空")
    private String title;

    /**
     * 操作IP地址
     */
    @ApiModelProperty(value = "remoteAddress")
    private String remoteAddress;

    /**
     * 用户代理
     */
    @ApiModelProperty(value = "userAgent")
    private String userAgent;

    /**
     * 请求URI
     */
    @ApiModelProperty(value = "requestUrl")
    private String requestUrl;

    /**
     * 方法名称
     */
    @ApiModelProperty(value = "method")
    private String method;

    /**
     * 日志内容
     */
    @ApiModelProperty(value = "content")
    private String content;

    /**
     * 操作状态（0正常 1异常）
     */
    @ApiModelProperty(value = "status")
    private Integer status;

    /**
     * 操作提交的数据
     */
    @ApiModelProperty(value = "params")
    private String params;

    /**
     * 异常信息
     */
    @ApiModelProperty(value = "exception")
    private String exception;

}
