package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 过车记录对象
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleRecordBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录唯一ID
     */
    private String unid;

    /**
     * 车牌号码
     */
    private String plateNo;

    /**
     * 车牌颜色
     */
    private Integer plateColor;

    /**
     * 通过时间 格式：yyyy-MM-dd HH:mm:ss
     */
    private String passTime;

    /**
     * 停车场编号
     */
    private String parkCode;

    /**
     * 停车场名称
     */
    private String parkName;

    /**
     * 出入口编号
     */
    private String gateCode;

    /**
     * 出入口名称
     */
    private String gateName;

    /**
     * 车道编号
     */
    private String laneNo;

    /**
     * 车道名称
     */
    private String laneName;

    /**
     * 车辆类型
     */
    private Integer carType;

    /**
     * 过车方向
     */
    private Integer direct;

    /**
     * 泊位编号,实际为 berthNumber
     */
    private String berthCode;

}
