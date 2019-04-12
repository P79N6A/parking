package com.zhuyitech.parking.pms.dto.result.car;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车辆logo视图
 *
 * @Date: 2018/1/16
 * @author: yuzhicheng
 */
@ApiModel(value = "CarLogoResultDto", description = "车辆logo视图")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarLogoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * logo
     */
    @ApiModelProperty("logo")
    private String logo;

}
