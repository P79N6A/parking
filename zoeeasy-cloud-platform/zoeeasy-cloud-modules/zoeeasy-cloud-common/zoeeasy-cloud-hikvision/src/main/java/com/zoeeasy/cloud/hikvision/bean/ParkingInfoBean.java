package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 停车场基本信息对象
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 停车场编号
     */
    private String parkCode;
    /**
     * 停车场名称
     */
    private String parkName;
    /**
     * 总车位数
     */
    private Integer totalPlot;
    /**
     * 剩余车位数
     */
    private Integer leftPlot;
    /**
     * 固定车总车位数
     */
    private Integer totalFixedPlot;
    /**
     * 固定车剩余车位数
     */
    private Integer leftFixedPlot;
    /**
     * 停车场描述
     */
    private String description;
    /**
     * 停车场经度
     */
    private String parkLongitude;
    /**
     * 停车场纬度
     */
    private String parkLatitude;
    /**
     * 停车点类型
     */
    private Integer parkType;
    /**
     * 停车点等级
     */
    private Integer parkLevel;

}
