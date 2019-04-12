package com.zhuyitech.parking.ucc.dto.request.realname;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;
import com.scapegoat.infrastructure.validate.annotaion.validation.IdentityNo;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 更新用户实名认证请求参数
 *
 * @author walkman
 * @date 2018-01-10
 */
@ApiModel(value = "CurrentUserAuthApplyUpdateRequestDto", description = "更新用户实名认证请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentUserAuthApplyUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    @NotBlank(message = "用户真实姓名不能为空")
    private String realName;

    /**
     * 用户身份证号
     */
    @ApiModelProperty(value = "用户身份证号")
    @NotBlank(message = "用户身份证号不能为空")
    @IdentityNo
    private String cardNo;

    /**
     * 用户身份证正面照
     */
    @ApiModelProperty(value = "用户身份证正面照")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String cardFront;

    /**
     * 用户身份证反面照
     */
    @ApiModelProperty(value = "用户身份证反面照")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String cardContrary;

}
