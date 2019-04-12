package com.zhuyitech.parking.ucc.dto.result;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实名认证状态结果
 *
 * @author walkman
 * @date 2018-01-10
 */
@ApiModel(value = "UserAuthStatusResultDto", description = "用户实名认证结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAuthStatusResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 认证状态 1 认证中  2 已认证  3 认证不通过
     */
    @ApiModelProperty(value = "认证状态,0 未实名 1 认证中  2 已认证  3 认证不通过")
    private Integer authStatus;

}
