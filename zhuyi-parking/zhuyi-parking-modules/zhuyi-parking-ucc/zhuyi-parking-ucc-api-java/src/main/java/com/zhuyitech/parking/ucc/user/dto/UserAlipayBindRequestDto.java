package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 绑定支付宝
 *
 * @author zwq
 */
@ApiModel(description = "绑定支付宝")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAlipayBindRequestDto extends SessionDto {

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
    @ApiModelProperty(value = "isStudentCertified", required = true, notes = "isStudentCertified")
    private String isStudentCertified;

    /**
     * 用户类型（1/2）1代表公司账户2代表个人账户
     */
    @ApiModelProperty(value = "userType", required = true, notes = "userType")
    private String userType;

    /**
     * 用户状态（Q/T/B/W）（Q代表快速注册用户 T代表已认证用户 B代表被冻结账户 W代表已注册，未激活的账户）
     */
    @ApiModelProperty(value = "userStatus", required = true, notes = "userStatus")
    private String userStatus;

    /**
     * 是否通过实名认证。T是通过 F是没有实名认证。
     */
    @ApiModelProperty(value = "isCertified", required = true, notes = "isCertified")
    private String isCertified;

    /**
     * 性别（F：女性；M：男性）。
     */
    @ApiModelProperty(value = "gender", required = true, notes = "gender")
    private String gender;

}
