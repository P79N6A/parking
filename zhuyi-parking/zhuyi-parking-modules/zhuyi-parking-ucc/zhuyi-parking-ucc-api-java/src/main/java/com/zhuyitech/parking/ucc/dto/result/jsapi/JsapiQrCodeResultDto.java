package com.zhuyitech.parking.ucc.dto.result.jsapi;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求生成二维码接口响应参数
 *
 * @author zwq
 */
@ApiModel(value = "JsapiQrCodeResultDto", description = "请求生成二维码接口响应参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class JsapiQrCodeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * ticket
     */
    @ApiModelProperty("ticket")
    private String ticket;

    /**
     * 有效时间(秒)
     */
    @ApiModelProperty("有效时间(秒)")
    private Integer expireSeconds;

    /**
     * 二维码图片解析后的地址
     */
    @ApiModelProperty("二维码图片解析后的地址")
    private Integer url;

    /**
     * errcode
     */
    private String errCode;

    /**
     * errmsg
     */
    private String errMsg;

}
