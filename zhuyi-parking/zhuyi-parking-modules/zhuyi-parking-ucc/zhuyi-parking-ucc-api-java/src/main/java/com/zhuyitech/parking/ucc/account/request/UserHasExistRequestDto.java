package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户是否已存在请求参数
 *
 * @author walkman
 */
@ApiModel(description = "用户是否已存在请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasExistRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Mobile(message = "手机号不能为空")
    private String phoneNumber;

}