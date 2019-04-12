package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: 校验支付密码
 * @Autor: AkeemSuper
 * @Date: 2018/2/11 0011
 */
@ApiModel(value = "UserCheckTradePasswordRequestDto", description = "校验支付密码")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTradePasswordCheckRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付密码
     */
    @ApiModelProperty(required = true, value = "支付密码", allowEmptyValue = false)
    @NotBlank(message = "支付密码不能为空")
    @Length(min = 6, max = 6, message = "请输入6位支付密码")
    private String tradePassword;

}
