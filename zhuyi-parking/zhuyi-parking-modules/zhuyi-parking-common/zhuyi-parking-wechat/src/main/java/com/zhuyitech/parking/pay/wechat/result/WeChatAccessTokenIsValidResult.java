package com.zhuyitech.parking.pay.wechat.result;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 *     accesstoken是否有效
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-14:14
 */
@ApiModel(value = "WeChatAccessTokenIsValidResult", description = "accesstoken是否有效")
public class WeChatAccessTokenIsValidResult extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否有效
     */
    @ApiModelProperty("是否有效")
    private Boolean isValid;

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
