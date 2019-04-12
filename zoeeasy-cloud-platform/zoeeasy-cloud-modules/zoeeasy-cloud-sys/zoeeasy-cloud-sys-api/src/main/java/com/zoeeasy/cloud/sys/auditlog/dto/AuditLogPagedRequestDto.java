package com.zoeeasy.cloud.sys.auditlog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 分页查询操作日志请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AuditLogQueryPagedResultRequestDto", description = "分页查询操作日志请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditLogPagedRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 请求URI
     */
  /*  @ApiModelProperty(value = "请求URI")
    private String requestUri;
*/

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String account;

    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    private Integer type;

    /**
     * 日志类型
     */
 /*   @ApiModelProperty(value = "日志类型")
    private Integer logType;*/

    /**
     * 方法名称
     */
   /* @ApiModelProperty(value = "方法名称")
    private String method;*/

    /**
     * 创建时间开始
     */
    @ApiModelProperty("创建时间-开始")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date creationTimeStart;

    /**
     * 创建时间结束
     */
    @ApiModelProperty("创建时间-结束")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date creationTimeEnd;

}