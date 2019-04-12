package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝注册
 *
 * @author zwq
 */
@ApiModel(value = "UserAlipayRegisterRequestDto", description = "支付宝注册")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAlipayRegisterRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * alipayUserId
     */
    @ApiModelProperty(value = "alipayUserId", required = true, notes = "alipayUserId")
    private String alipayUserId;

    /**
     * avatar
     */
    @ApiModelProperty(value = "avatar", required = true, notes = "avatar")
    private String avatar;

    /**
     * province
     */
    @ApiModelProperty(value = "province", required = true, notes = "province")
    private String province;

    /**
     * city
     */
    @ApiModelProperty(value = "city", required = true, notes = "city")
    private String city;

    /**
     * nickName
     */
    @ApiModelProperty(value = "nickName", required = true, notes = "nickName")
    private String nickName;

    /**
     * 是否是学生
     */
    @ApiModelProperty(value = "isStudentCertified", notes = "isStudentCertified")
    private String isStudentCertified;

    /**
     * 用户类型（1/2）1代表公司账户2代表个人账户
     */
    @ApiModelProperty(value = "userType", notes = "userType")
    private String userType;

    /**
     * 用户状态（Q/T/B/W）（Q代表快速注册用户 T代表已认证用户 B代表被冻结账户 W代表已注册，未激活的账户）
     */
    @ApiModelProperty(value = "userStatus", notes = "userStatus")
    private String userStatus;

    /**
     * 是否通过实名认证。T是通过 F是没有实名认证。
     */
    @ApiModelProperty(value = "isCertified", notes = "isCertified")
    private String isCertified;

    /**
     * 性别（F：女性；M：男性）。
     */
    @ApiModelProperty(value = "gender", notes = "gender")
    private String gender;

    /**
     * phoneNumber
     */
    @ApiModelProperty(value = "phoneNumber", required = true, notes = "phoneNumber")
    private String phoneNumber;

    /**
     * 短信回执ID
     */
    @ApiModelProperty(value = "短信回执ID")
    private String smsRequestId;

    /**
     * verifyCode
     */
    @ApiModelProperty(value = "验证码")
    private String verifyCode;

}
