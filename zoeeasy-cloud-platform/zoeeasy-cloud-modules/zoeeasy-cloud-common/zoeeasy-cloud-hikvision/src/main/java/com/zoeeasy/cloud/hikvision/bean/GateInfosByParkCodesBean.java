package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 停车场出入口对象
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GateInfosByParkCodesBean implements Serializable {
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
     * 出入口名称
     */
    private String gateName;
    /**
     * 包含车道数
     */
    private Integer laneNum;
    /**
     * 终端编号
     */
    private String terminalCode;
    /**
     * 终端名称
     */
    private String terminalName;

    public String getParkCode() {
        return parkCode;
    }

}
