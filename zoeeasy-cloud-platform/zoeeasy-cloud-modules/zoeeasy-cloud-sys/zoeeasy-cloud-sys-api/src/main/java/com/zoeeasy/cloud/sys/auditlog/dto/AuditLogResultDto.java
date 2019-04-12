package com.zoeeasy.cloud.sys.auditlog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 系统操作日志视图模型
 *
 * @author walkman
 */
@ApiModel(value = "AuditLogResultDto", description = "系统操作日志视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditLogResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "userId")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
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

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date creationTime;
}