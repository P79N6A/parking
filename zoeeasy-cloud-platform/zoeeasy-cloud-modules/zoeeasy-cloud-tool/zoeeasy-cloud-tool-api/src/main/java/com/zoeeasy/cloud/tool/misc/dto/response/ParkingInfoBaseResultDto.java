package com.zoeeasy.cloud.tool.misc.dto.response;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 停车场数据返回结果
 *
 * @author walkman
 * @date 2018-04-12
 */
@Data
@ApiModel(value = "ParkingInfoBaseResultDto", description = "停车场数据返回结果")
public class ParkingInfoBaseResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "telephone")
    private String telephone;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

}
