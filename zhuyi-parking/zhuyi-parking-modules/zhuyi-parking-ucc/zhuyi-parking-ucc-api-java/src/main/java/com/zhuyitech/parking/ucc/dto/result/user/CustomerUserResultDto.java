package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 会员用户信息视图模型
 *
 * @author walkman
 */
@ApiModel(value = "CustomerUserResultDto", description = "会员用户信息视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerUserResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @ApiModelProperty("uuid")
    private String uuid;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String portrait;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phoneNumber;

    /**
     * 手机号是否确认
     */
    @ApiModelProperty("手机号是否确认")
    private Boolean phoneNumberConfirmed;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String emailAddress;

    /**
     * 邮箱是否确认
     */
    @ApiModelProperty("邮箱是否确认")
    private Boolean emailAddressConfirmed;

    /**
     * 失败登录次数
     */
    @ApiModelProperty("失败登录次数")
    private Integer accessAttemptCount;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer status;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer userType;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    protected Date lastLoginTime;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String realName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "birthday")
    private Date birthday;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @SensitiveInfo(SensitiveType.ID_CARD)
    private String cardNo;

    /**
     * 是否实名认证
     */
    @ApiModelProperty(value = "是否实名认证")
    private Boolean certificated;

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

}