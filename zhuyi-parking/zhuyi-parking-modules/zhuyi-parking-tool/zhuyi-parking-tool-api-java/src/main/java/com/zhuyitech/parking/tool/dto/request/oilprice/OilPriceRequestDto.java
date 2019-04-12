package com.zhuyitech.parking.tool.dto.request.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取城市油价请求参数
 *
 * @author zwq
 * @date 2018/4/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceRequestDto", description = "获取城市油价请求参数")
public class OilPriceRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * key
     */
    @ApiModelProperty(value = "key", hidden = true)
    private String key;

    /**
     * dtype
     */
    @ApiModelProperty(value = "dtype", hidden = true)
    private String dtype;

}
