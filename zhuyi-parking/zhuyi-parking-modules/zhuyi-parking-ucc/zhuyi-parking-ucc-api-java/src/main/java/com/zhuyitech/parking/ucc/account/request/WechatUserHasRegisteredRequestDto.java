package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 微信用户是否注册
 *
 * @author walkman
 */
@ApiModel(value = "WechatUserHasRegisteredRequestDto", description = "微信用户是否注册")
public class WechatUserHasRegisteredRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * openId
     */
    /*@ApiModelProperty(required = true, value = "openId", allowEmptyValue = false)
    @NotBlank(message = "openId不能为空")
    private String openId;*/

    /**
     * unionid
     */
    @ApiModelProperty(required = true, value = "unionid", allowEmptyValue = false)
    @NotBlank(message = "unionid不能为空")
    private String unionId;

   /* public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }*/

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
