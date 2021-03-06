package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝获取userInfo
 *
 * @author zwq
 * @date 2018-01-29
 */
@ApiModel(value = "AlipayGetUserInfoResultDto", description = " 支付宝获取userInfo")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayGetUserInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付宝用户的唯一userId
     */
    private String alipayUserId;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 省份名称
     */
    private String province;

    /**
     * 市名称
     */
    private String city;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 是否是学生
     */
    private String isStudentCertified;
    /**
     * 用户类型（1/2）1代表公司账户2代表个人账户
     */
    private String userType;

    /**
     * 用户状态（Q/T/B/W）（Q代表快速注册用户 T代表已认证用户 B代表被冻结账户 W代表已注册，未激活的账户）
     */
    private String userStatus;

    /**
     * 是否通过实名认证。T是通过 F是没有实名认证。
     */
    private String isCertified;

    /**
     * 性别（F：女性；M：男性）。
     */
    private String gender;

}
