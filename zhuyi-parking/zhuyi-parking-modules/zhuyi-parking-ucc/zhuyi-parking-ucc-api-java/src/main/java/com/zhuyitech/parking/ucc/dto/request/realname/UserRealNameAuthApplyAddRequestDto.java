package com.zhuyitech.parking.ucc.dto.request.realname;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.IdentityNo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 增加用户实名认证请求参数
 *
 * @author walkman
 * @date 2018-01-01
 */
@ApiModel(value = "UserRealNameAuthApplyAddRequestDto", description = "增加用户实名认证请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRealNameAuthApplyAddRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(required = true, value = "用户真实姓名")
    private String realName;

    /**
     * 用户身份证号
     */
    @ApiModelProperty(required = true, value = "用户身份证号")
    @IdentityNo
    private String cardNo;

    /**
     * 用户身份证正面照
     */
    @NotBlank(message = "用户身份证正面照不能为空")
    @ApiModelProperty(required = true, value = "用户身份证正面照")
    private String cardFront;

    /**
     * 用户身份证反面照
     */
    @ApiModelProperty(value = "用户身份证反面照")
    private String cardContrary;

}
