package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SmsSendResultDto", description = "短信发送结果")
public class SmsSendResultDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求ID
     */
    @ApiModelProperty(value = "请求ID")
    private String requestId;

    /**
     * 发送回执ID,可根据该ID查询具体的发送状态
     */
    @ApiModelProperty(value = "发送回执ID")
    private String bizId;

    /**
     * 发送返回校验key
     */
    @ApiModelProperty(value = "发送返回校验key")
    private String checkIdentify;

}
