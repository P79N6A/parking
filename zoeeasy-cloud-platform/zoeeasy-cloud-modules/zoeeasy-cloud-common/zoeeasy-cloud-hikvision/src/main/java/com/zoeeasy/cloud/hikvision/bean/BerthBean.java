package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description: 泊位信息
 * @author: AkeemSuper
 * @date: 2018/2/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BerthBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号
     */
    private String parkCode;
    /**
     * 泊位编号
     */
    private String berthCode;
    /**
     * 泊位状态
     */
    private Integer berthStatus;

}
