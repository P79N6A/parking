package com.zhuyitech.parking.ucc.domain;


import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;

import java.util.Date;

/**
 * @author walkman
 */
@TableName("up_user")
public class User extends FullAuditedEntity<Long> implements IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * uuid
     */
    @TableField(value = "uuid")
    private String uuid;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 头像
     */
    @TableField(value = "portrait")
    private String portrait;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 联系电话
     */
    @TableField(value = "phoneNumber")
    private String phoneNumber;

    /**
     * 手机号是否确认
     */
    @TableField(value = "phoneNumberConfirmed")
    private Boolean phoneNumberConfirmed;

    /**
     * 邮箱
     */
    @TableField(value = "emailAddress")
    private String emailAddress;

    /**
     * 邮箱是否确认
     */
    @TableField(value = "emailAddressConfirmed")
    private Boolean emailAddressConfirmed;

    /**
     * 失败登录次数
     */
    @TableField(value = "accessAttemptCount")
    private Integer accessAttemptCount;

    /**
     * 用户状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 是否默认用户
     */
    @TableField(value = "defaultUser")
    private Boolean defaultUser;

    /**
     * 是否管理员
     */
    @TableField(value = "isAdministrator")
    private Boolean isAdministrator;

    /**
     * 用户类型
     */
    @TableField(value = "userType")
    private Integer userType;

    /**
     * 最后登录时间
     */
    @TableField(value = "lastLoginTime")
    protected Date lastLoginTime;

    /**
     * 交易密码盐
     */
    @TableField(value = "tradeSalt")
    private String tradeSalt;

    /**
     * 交易密码
     */
    @TableField(value = "tradePassword")
    private String tradePassword;

    /**
     * 交易失败次数
     */
    @TableField(value = "tradeAttemptCount")
    private Integer tradeAttemptCount;

    /**
     * 别名(用于推送)
     */
    @TableField(value = "alias")
    private String alias;

    /**
     * 用户信息
     */
    @TableField(exist = false)
    private UserInfo userInfo;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getEmailAddressConfirmed() {
        return emailAddressConfirmed;
    }

    public void setEmailAddressConfirmed(Boolean emailAddressConfirmed) {
        this.emailAddressConfirmed = emailAddressConfirmed;
    }

    public Integer getAccessAttemptCount() {
        return accessAttemptCount;
    }

    public void setAccessAttemptCount(Integer accessAttemptCount) {
        this.accessAttemptCount = accessAttemptCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(Boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public Boolean getAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getTradeSalt() {
        return tradeSalt;
    }

    public void setTradeSalt(String tradeSalt) {
        this.tradeSalt = tradeSalt;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public Integer getTradeAttemptCount() {
        return tradeAttemptCount;
    }

    public void setTradeAttemptCount(Integer tradeAttemptCount) {
        this.tradeAttemptCount = tradeAttemptCount;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
