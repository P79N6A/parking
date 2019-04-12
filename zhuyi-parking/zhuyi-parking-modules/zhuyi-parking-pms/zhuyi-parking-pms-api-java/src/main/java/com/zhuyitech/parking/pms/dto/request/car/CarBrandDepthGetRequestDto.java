package com.zhuyitech.parking.pms.dto.request.car;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 车型列表请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "CarBrandDepthGetRequestDto", description = "车型列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandDepthGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;


    /**
     * 深度 1品牌 2子公司 3车型 4具体车型
     */
    @ApiModelProperty(value = "depth", required = true)
    @NotNull
    private Integer depth;

}
