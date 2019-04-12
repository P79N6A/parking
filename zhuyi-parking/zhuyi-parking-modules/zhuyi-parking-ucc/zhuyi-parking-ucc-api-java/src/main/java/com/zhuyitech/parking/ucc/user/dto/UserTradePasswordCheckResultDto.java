package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 支付密码校验结果
 *
 * @author walkman
 */
@ApiModel(value = "UserTradePasswordCheckResultDto", description = "支付密码校验结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTradePasswordCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Boolean passed;

}