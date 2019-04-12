package com.zhuyitech.parking.tool.dto.result.gas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * 加油站结果集
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GasResult", description = "加油站结果集")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GasResult extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 地址
     */
    private String address;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 返回状态
     */
    private Location location;

    public static class Location {

        @Getter
        @Setter
        private double lng;

        @Getter
        @Setter
        private double lat;
    }

}
