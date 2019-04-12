package com.zhuyitech.parking.ucc.dto.request.jsapi;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 请求生成二维码接口请求参数
 *
 * @author zwq
 */
@ApiModel(value = "JsapiQrCodeRequestDto", description = "请求生成二维码接口请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class JsapiQrCodeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

}
