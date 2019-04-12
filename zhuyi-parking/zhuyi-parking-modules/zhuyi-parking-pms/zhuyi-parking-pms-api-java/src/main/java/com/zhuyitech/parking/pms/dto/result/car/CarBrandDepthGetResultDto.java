package com.zhuyitech.parking.pms.dto.result.car;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车型视图模型
 *
 * @author zwq
 */
@ApiModel(value = "CarBrandDepthGetResultDto", description = "车型视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandDepthGetResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

}
