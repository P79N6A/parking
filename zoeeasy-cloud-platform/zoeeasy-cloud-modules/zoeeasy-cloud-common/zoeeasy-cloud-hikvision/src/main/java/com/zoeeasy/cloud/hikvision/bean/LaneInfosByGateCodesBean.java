package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 车道对象
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LaneInfosByGateCodesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 停车场编号
     */
    private String parkCode;
    /**
     * 出入口编号
     */
    private String gateCode;
    /**
     * 车道编号
     */
    private String laneNo;
    /**
     * 车道名称
     */
    private String laneName;
    /**
     * 车道类型
     */
    private Integer laneType;

}
