package com.zhuyitech.parking.tool.bean.amap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 逆地理获取adcode返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class Regecode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String formatted_address;

    /**
     * 地址元素列表
     */
    private AddressComponent addressComponent;

}
