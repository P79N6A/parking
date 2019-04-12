package com.zhuyitech.parking.tool.dto.request.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "OilPricePutInRequestDto", description = "获取城市油价请求参数")
public class OilPricePutInRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

}
