package com.zhuyitech.parking.ucc.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;

import java.util.Date;

/**
 * 用户信息
 *
 * @author walkman
 */
@TableName("up_user_info")
public class UserInfo extends FullAuditedEntity<Long> implements IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * userId
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 真实姓名
     */
    @TableField(value = "realName")
    private String realName;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 身份证号
     */
    @TableField(value = "cardNo")
    private String cardNo;

    /**
     * 是否实名认证
     */
    @TableField(value = "certificateStatus")
    private Integer certificateStatus;

    /**
     * 实名认证时间
     */
    @TableField(value = "certificatedDate")
    private Date certificatedDate;

    /**
     * 微信Uuid
     */
    @TableField(value = "wxUuid")
    private String wxUuid;

    /**
     * 微信OpenId
     */
    @TableField(value = "wxOpenId")
    private String wxOpenId;

    /**
     * 用户统一标识
     */
    @TableField(value = "wxUnionId")
    private String wxUnionId;

    /**
     * 普通用户昵称
     */
    @TableField(value = "wxNickname")
    private String wxNickname;

    /**
     * 普通用户性别，1为男性，2为女性
     */
    @TableField(value = "wxSex")
    private Integer wxSex;

    /**
     * 国家，如中国为CN
     */
    @TableField(value = "wxCounty")
    private String wxCounty;

    /**
     * 普通用户个人资料填写的城市
     */
    @TableField(value = "wxProvince")
    private String wxProvince;

    /**
     * 普通用户个人资料填写的城市
     */
    @TableField(value = "wxCity")
    private String wxCity;

    /**
     * 用户特权信息
     */
    @TableField(value = "wxPrivilege")
    private String wxPrivilege;

    /**
     * 是否微信认证
     */
    @TableField(value = "wxAuthorized")
    private Boolean wxAuthorized;

    /**
     * 支付宝用户的唯一userId
     */
    @TableField(value = "aliUserId")
    private String aliUserId;

    /**
     * 支付宝用户昵称
     */
    @TableField(value = "aliNickname")
    private String aliNickname;

    /**
     * 用户头像地址
     */
    @TableField(value = "aliAvatar")
    private String aliAvatar;

    /**
     * 省份名称
     */
    @TableField(value = "aliProvince")
    private String aliProvince;

    /**
     * 市名称
     */
    @TableField(value = "aliCity")
    private String aliCity;

    /**
     * 是否是学生
     */
    @TableField(value = "aliIsStudentCertified")
    private String aliIsStudentCertified;

    /**
     * 用户类型1代表公司账户2代表个人账户
     */
    @TableField(value = "aliUserType")
    private String aliUserType;

    /**
     * 用户状态Q代表快速注册用户 T代表已认证用户 B代表被冻结账户 W代表已注册，未激活的账户
     */
    @TableField(value = "aliUserStatus")
    private String aliUserStatus;

    /**
     * 是否通过实名认证。T是通过 F是没有实名认证。
     */
    @TableField(value = "aliIsCertified")
    private String aliIsCertified;

    /**
     * 性别（F：女性；M：男性）
     */
    @TableField(value = "aliGender")
    private String aliGender;

    /**
     * 是否支付宝认证
     */
    @TableField(value = "aliAuthorized")
    private Boolean aliAuthorized;

    /**
     * QQ号码
     */
    @TableField(value = "qqNumber")
    private String qqNumber;

    /**
     * 是否QQ认证
     */
    @TableField(value = "qqAuthorized")
    private Boolean qqAuthorized;

    /**
     * 用户等级
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 邀请用户ID
     */
    @TableField(value = "invitedUserId")
    private Long invitedUserId;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    protected Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    protected Long lastModifierUserId;

    /**
     * 更新日期
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    protected Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    protected Long deleterUserId;

    /**
     * 删除日期
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    protected Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    protected Integer deleted;

    /**
     * 版本号
     */
    @Version
    private Long version;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getWxUnionId() {
        return wxUnionId;
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public Integer getWxSex() {
        return wxSex;
    }

    public void setWxSex(Integer wxSex) {
        this.wxSex = wxSex;
    }

    public String getWxCounty() {
        return wxCounty;
    }

    public void setWxCounty(String wxCounty) {
        this.wxCounty = wxCounty;
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince;
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity;
    }

    public String getWxPrivilege() {
        return wxPrivilege;
    }

    public void setWxPrivilege(String wxPrivilege) {
        this.wxPrivilege = wxPrivilege;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(Integer certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public Date getCertificatedDate() {
        return certificatedDate;
    }

    public void setCertificatedDate(Date certificatedDate) {
        this.certificatedDate = certificatedDate;
    }

    public String getWxUuid() {
        return wxUuid;
    }

    public void setWxUuid(String wxUuid) {
        this.wxUuid = wxUuid;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public Boolean getWxAuthorized() {
        return wxAuthorized;
    }

    public void setWxAuthorized(Boolean wxAuthorized) {
        this.wxAuthorized = wxAuthorized;
    }

    public String getAliNickname() {
        return aliNickname;
    }

    public void setAliNickname(String aliNickname) {
        this.aliNickname = aliNickname;
    }

    public String getAliUserId() {
        return aliUserId;
    }

    public void setAliUserId(String aliUserId) {
        this.aliUserId = aliUserId;
    }

    public String getAliAvatar() {
        return aliAvatar;
    }

    public void setAliAvatar(String aliAvatar) {
        this.aliAvatar = aliAvatar;
    }

    public String getAliProvince() {
        return aliProvince;
    }

    public void setAliProvince(String aliProvince) {
        this.aliProvince = aliProvince;
    }

    public String getAliCity() {
        return aliCity;
    }

    public void setAliCity(String aliCity) {
        this.aliCity = aliCity;
    }

    public String getAliIsStudentCertified() {
        return aliIsStudentCertified;
    }

    public void setAliIsStudentCertified(String aliIsStudentCertified) {
        this.aliIsStudentCertified = aliIsStudentCertified;
    }

    public String getAliUserType() {
        return aliUserType;
    }

    public void setAliUserType(String aliUserType) {
        this.aliUserType = aliUserType;
    }

    public String getAliUserStatus() {
        return aliUserStatus;
    }

    public void setAliUserStatus(String aliUserStatus) {
        this.aliUserStatus = aliUserStatus;
    }

    public String getAliIsCertified() {
        return aliIsCertified;
    }

    public void setAliIsCertified(String aliIsCertified) {
        this.aliIsCertified = aliIsCertified;
    }

    public String getAliGender() {
        return aliGender;
    }

    public void setAliGender(String aliGender) {
        this.aliGender = aliGender;
    }

    public Boolean getAliAuthorized() {
        return aliAuthorized;
    }

    public void setAliAuthorized(Boolean aliAuthorized) {
        this.aliAuthorized = aliAuthorized;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public Boolean getQqAuthorized() {
        return qqAuthorized;
    }

    public void setQqAuthorized(Boolean qqAuthorized) {
        this.qqAuthorized = qqAuthorized;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getInvitedUserId() {
        return invitedUserId;
    }

    public void setInvitedUserId(Long invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    @Override
    public Long getCreatorUserId() {
        return creatorUserId;
    }

    @Override
    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public Long getLastModifierUserId() {
        return lastModifierUserId;
    }

    @Override
    public void setLastModifierUserId(Long lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }

    @Override
    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    @Override
    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    @Override
    public Long getDeleterUserId() {
        return deleterUserId;
    }

    @Override
    public void setDeleterUserId(Long deleterUserId) {
        this.deleterUserId = deleterUserId;
    }

    @Override
    public Date getDeletionTime() {
        return deletionTime;
    }

    @Override
    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    @Override
    public Integer getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }
}
