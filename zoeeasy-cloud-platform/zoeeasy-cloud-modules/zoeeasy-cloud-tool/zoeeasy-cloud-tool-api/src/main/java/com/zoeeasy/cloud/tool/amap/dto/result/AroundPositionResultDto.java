package com.zoeeasy.cloud.tool.amap.dto.result;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "AroundPositionGetRequest", description = "逆地理获取adcode请求参数")
@EqualsAndHashCode(callSuper = false)
public class AroundPositionResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Double minLongitude;

    private Double maxLongitude;

    private Double minLatitude;

    private Double maxLatitude;
}
