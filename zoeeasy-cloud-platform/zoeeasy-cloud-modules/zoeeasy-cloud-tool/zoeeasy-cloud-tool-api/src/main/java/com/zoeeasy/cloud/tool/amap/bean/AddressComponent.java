package com.zoeeasy.cloud.tool.amap.bean;

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
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class AddressComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 行政区编码
     */
    private String adcode;

    /**
     * province : 浙江省
     * city : 杭州市
     * citycode : 0571
     * district : 西湖区
     * adcode : 330106
     * township : 转塘街道
     * towncode : 330106010000
     */
    private String province;
    private String district;

}
