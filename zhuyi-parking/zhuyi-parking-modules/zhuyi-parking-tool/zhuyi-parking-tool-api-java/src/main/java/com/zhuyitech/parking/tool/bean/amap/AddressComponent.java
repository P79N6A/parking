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
public class AddressComponent implements Serializable {

    private static final long serialVersionUID = 1L;


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
    private String adcode;

}
