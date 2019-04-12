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
public class OilPriceDetailRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 省份code
     */
    @ApiModelProperty(value = "省份code", required = true)
    private String code;

}
