package com.zhuyitech.parking.tool.dto.result.oilprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 油价详情
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OilPriceInfo {

    /**
     * 城市
     */
    private String city;

    /**
     * b90
     */
    private String b90;

    /**
     * b93
     */
    private String b93;

    /**
     * b97
     */
    private String b97;

    /**
     * b0
     */
    private String b0;

}
