package com.zoeeasy.cloud.gather.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 海康过车记录接口调用记录
 *
 * @author walkman
 */
@TableName("gat_hikvision_vehicle_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class HikvisionVehicleLogEntity extends CreationAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ip
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * url
     */
    @TableField(value = "url")
    private String url;

    /**
     * messageType
     */
    @TableField(value = "messageType")
    private Integer messageType;

    /**
     * 请求时间
     */
    @TableField(value = "requestTime")
    private Date requestTime;

    /**
     * 请求参数
     */
    @TableField(value = "param")
    private String parameter;

    /**
     * 响应时间
     */
    @TableField(value = "responseTime")
    private Date responseTime;

    /**
     * 响应结果
     */
    @TableField(value = "result")
    private String result;

    /**
     * 处理状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 日志类型
     */
    @TableField(value = "logType")
    private Integer logType;

    /**
     * 查询开始的过车时间
     */
    @TableField(value = "startTime")
    private Date startTime;

    /**
     * 查询结束的过车时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

}
