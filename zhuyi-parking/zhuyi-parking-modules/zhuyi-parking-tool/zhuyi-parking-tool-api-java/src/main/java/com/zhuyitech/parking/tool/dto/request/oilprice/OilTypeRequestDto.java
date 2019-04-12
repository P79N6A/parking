package com.zhuyitech.parking.tool.dto.request.oilprice;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/8/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilTypeRequestDto", description = "油价类型请求参数")
public class OilTypeRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}
