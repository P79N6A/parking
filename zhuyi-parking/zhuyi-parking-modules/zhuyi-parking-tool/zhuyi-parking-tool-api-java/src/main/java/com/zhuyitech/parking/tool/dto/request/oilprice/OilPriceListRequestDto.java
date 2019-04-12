package com.zhuyitech.parking.tool.dto.request.oilprice;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/8/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceListRequestDto", description = "油价列表查询请求参数 ")
public class OilPriceListRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

}
