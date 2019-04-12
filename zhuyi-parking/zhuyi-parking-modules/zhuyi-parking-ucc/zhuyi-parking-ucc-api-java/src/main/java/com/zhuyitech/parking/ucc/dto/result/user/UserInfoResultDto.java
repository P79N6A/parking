package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/7/11 0011
 */
@ApiModel(value = "UserInfoResultDto", description = "用户信息返回结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * userId
     */
    @ApiModelProperty(value = "userId")
    private Long userId;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String cardNo;

    /**
     * 是否实名认证
     */
    @ApiModelProperty(value = "是否实名认证")
    private Integer certificated;

    /**
     * 实名认证时间
     */
    @ApiModelProperty(value = "实名认证时间")
    private Date certificatedDate;

    /**
     * 微信Uuid
     */
    @ApiModelProperty(value = "微信Uuid")
    private String wxUuid;

    /**
     * 微信OpenId
     */
    @ApiModelProperty(value = "微信OpenId")
    private String wxOpenId;

    /**
     * 用户统一标识
     */
    @ApiModelProperty(value = "用户统一标识")
    private String wxUnionId;

    /**
     * 普通用户昵称
     */
    @ApiModelProperty(value = "普通用户昵称")
    private String wxNickname;

    /**
     * 普通用户性别，1为男性，2为女性
     */
    @ApiModelProperty(value = "普通用户性别")
    private Integer wxSex;

    /**
     * 国家，如中国为CN
     */
    @ApiModelProperty(value = "国家")
    private String wxCounty;

    /**
     * 普通用户个人资料填写的城市
     */
    @ApiModelProperty(value = "普通用户个人资料填写的城市")
    private String wxProvince;

    /**
     * 普通用户个人资料填写的城市
     */
    @ApiModelProperty(value = "普通用户个人资料填写的城市")
    private String wxCity;

    /**
     * 用户特权信息
     */
    @ApiModelProperty(value = "用户特权信息")
    private String wxPrivilege;

    /**
     * 是否微信认证
     */
    @ApiModelProperty(value = "是否微信认证")
    private Boolean wxAuthorized;

    /**
     * 支付宝用户的唯一userId
     */
    @ApiModelProperty(value = "支付宝用户的唯一userId")
    private String aliUserId;

    /**
     * 支付宝用户昵称
     */
    @ApiModelProperty(value = "支付宝用户昵称")
    private String aliNickname;

    /**
     * 用户头像地址
     */
    @ApiModelProperty(value = "用户头像地址")
    private String aliAvatar;

    /**
     * 省份名称
     */
    @ApiModelProperty(value = "省份名称")
    private String aliProvince;

    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    private String aliCity;

    /**
     * 是否是学生
     */
    @ApiModelProperty(value = "是否是学生")
    private String aliIsStudentCertified;

    /**
     * 用户类型1代表公司账户2代表个人账户
     */
    @ApiModelProperty(value = "用户类型1代表公司账户2代表个人账户")
    private String aliUserType;

    /**
     * 用户状态Q代表快速注册用户 T代表已认证用户 B代表被冻结账户 W代表已注册，未激活的账户
     */
    @ApiModelProperty(value = "用户状态Q代表快速注册用户")
    private String aliUserStatus;

    /**
     * 是否通过实名认证。T是通过 F是没有实名认证。
     */
    @ApiModelProperty(value = "是否通过实名认证")
    private String aliIsCertified;

    /**
     * 性别（F：女性；M：男性）
     */
    @ApiModelProperty(value = "性别")
    private String aliGender;

    /**
     * 是否支付宝认证
     */
    @ApiModelProperty(value = "是否支付宝认证")
    private Boolean aliAuthorized;

    /**
     * QQ号码
     */
    @ApiModelProperty(value = "QQ号码")
    private String qqNumber;

    /**
     * 是否QQ认证
     */
    @ApiModelProperty(value = "是否QQ认证")
    private Boolean qqAuthorized;

    /**
     * 用户等级
     */
    @ApiModelProperty(value = "用户等级")
    private Integer level;

    /**
     * 邀请用户ID
     */
    @ApiModelProperty(value = "邀请用户ID")
    private Long invitedUserId;

}
