package com.zoeeasy.cloud.sys.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方接口消息请求表(sys_message_log)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-20 17:39:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_message_log")
public class MessageLogEntity implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ip
     */
    @TableField("ip")
    private String ip;

    /**
     * url
     */
    @TableField("url")
    private String url;

    /**
     * 消息类型
     */
    @TableField("messageType")
    private String messageType;

    /**
     * 请求参数
     */
    @TableField("parameter")
    private String parameter;

    /**
     * 请求时间
     */
    @TableField("requestTime")
    private Date requestTime;

    /**
     * 响应内容
     */
    @TableField("result")
    private String result;

    /**
     * 响应时间
     */
    @TableField("responseTime")
    private Date responseTime;

    /**
     * 请求状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建者
     */
    @TableField("creatorUserId")
    private Long creatorUserId;

    /**
     * 创建时间
     */
    @TableField("creationTime")
    private Date creationTime;

}