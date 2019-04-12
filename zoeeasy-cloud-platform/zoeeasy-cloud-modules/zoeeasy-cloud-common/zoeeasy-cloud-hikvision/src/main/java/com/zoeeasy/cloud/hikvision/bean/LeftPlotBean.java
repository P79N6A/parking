package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 剩余车位对象
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeftPlotBean implements Serializable {
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

}
