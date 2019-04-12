package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付宝用户是否注册
 *
 * @author walkman
 */
@ApiModel(value = "AlipayUserHasRegisteredRequestDto", description = "支付宝用户是否注册")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayUserHasRegisteredRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * alipayUserId
     */
    @ApiModelProperty(required = true, value = "alipayUserId", allowEmptyValue = false)
    @NotBlank(message = "alipayUserId不能为空")
    private String alipayUserId;

}
