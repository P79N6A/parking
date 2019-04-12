package com.zoeeasy.cloud.tool.amap.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 逆地理编码返回结果
 *
 * @author walkman
 * @date 2018-04-12
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class RegeoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回结果状态值,返回值为 0 或 1，0 表示请求失败；1 表示请求成功
     */
    private String status;

    /**
     * 返回状态说明
     */
    private String info;

    /**
     * 返回状态
     */
    private String infocode;

    /**
     * 逆地理编码列表
     */
    private Regecode regeocode;

}
