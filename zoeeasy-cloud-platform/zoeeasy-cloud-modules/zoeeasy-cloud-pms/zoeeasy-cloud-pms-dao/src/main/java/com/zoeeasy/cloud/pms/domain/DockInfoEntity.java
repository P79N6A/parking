package com.zoeeasy.cloud.pms.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 场库停车场对接信息
 *
 * @author walkman
 */
@TableName("pms_dock_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class DockInfoEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户Id
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 对接终端/平台编号
     */
    @TableField("platformCode")
    private String platformCode;

    /**
     * 对接终端/平台名称
     */
    @TableField("platformName")
    private String platformName;

    /**
     * 对接平台类型
     */
    @TableField("platformType")
    private Integer platformType;

    /**
     * 对接终端/平台编号
     */
    @TableField("terminateCode")
    private String terminateCode;

    /**
     * 对接终端/平台名称
     */
    @TableField("terminateName")
    private String terminateName;

    /**
     * 对接终端/平台版本号
     */
    @TableField("terminateVersion")
    private String terminateVersion;

    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 访问凭证
     */
    @TableField("accesskey")
    private String accesskey;

    /**
     * 安全秘钥
     */
    @TableField("accessSecrete")
    private String accessSecrete;

    /**
     * token值
     */
    @TableField("token")
    private String token;

    /**
     * token有效开始时间
     */
    @TableField("startTime")
    private Date startTime;

    /**
     * token有效截止时间
     */
    @TableField("endTime")
    private Date endTime;

    /**
     * 最后心跳时间
     */
    @TableField("lastHeartBeatTime")
    private Date lastHeartBeatTime;

    /**
     * 请求接入的网域类型，1-公网，2-专网
     */
    @TableField("netZoneType")
    private Integer netZoneType;

    /**
     * 对接终端/平台的IP
     */
    @TableField("platformIp")
    private String platformIp;

    /**
     * 对接终端/平台的端口
     */
    @TableField("platformPort")
    private Integer platformPort;

    /**
     * 终端接入的通讯协议版本号
     */
    @TableField("protocolVersion")
    private String protocolVersion;

    /**
     * 对接终端/平台注册的账单下发请求URL
     */
    @TableField("placeOrderUrl")
    private String placeOrderUrl;

    /**
     * 对接终端/平台注册的账单支付通知URL
     */
    @TableField("notifyOrderUrl")
    private String notifyOrderUrl;

    /**
     * 对接终端/平台注册的远程开闸请求接口URL
     */
    @TableField("openStrobeUrl")
    private String openStrobeUrl;

    /**
     * 心跳时间间隔：单位秒
     */
    @TableField("heartBeatInterval")
    private Integer heartBeatInterval;

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
}
