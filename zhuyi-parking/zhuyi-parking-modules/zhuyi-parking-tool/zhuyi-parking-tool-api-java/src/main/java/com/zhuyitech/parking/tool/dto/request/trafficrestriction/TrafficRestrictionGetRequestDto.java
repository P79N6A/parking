package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据用户所在城市获取限行的请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionGetRequestDto", description = "根据用户所在城市获取限行的请求参数")
public class TrafficRestrictionGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", required = true)
    private Double latitude;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", required = true)
    private Double longitude;

    /**
     * adCode
     */
    @ApiModelProperty(value = "adCode")
    private String adCode;

}
