package com.zhuyitech.parking.tool.dto.request.messagelog;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/4/10 0010
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MessageLogRequestAddDto", description = "添加第三方接口调用日志请求参数")
public class MessageLogRequestAddDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip", required = true)
    private String ip;

    /**
     * url
     */
    @ApiModelProperty(value = "url", required = true)
    private String url;

    /**
     * 消息类型
     */
    @ApiModelProperty(value = "messageType", required = true)
    private String messageType;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "parameter", required = true)
    private String parameter;

    /**
     * 请求时间
     */
    @ApiModelProperty(value = "requestTime", required = true)
    private Date requestTime;

    /**
     * 响应结果
     */
    @ApiModelProperty(value = "result", required = true)
    private String result;

    /**
     * 响应时间
     */
    @ApiModelProperty(value = "responseTime", required = true)
    private Date responseTime;

    /**
     * 处理状态
     */
    @ApiModelProperty(value = "status", required = true)
    private Integer status;

}
