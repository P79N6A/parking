package com.zoeeasy.cloud.tool.amap.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 逆地理编码结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@ApiModel(value = "AllRegeoCodeGetResultDto", description = "获取省市区县Adcode")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class AllRegeoCodeGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String address;

}
