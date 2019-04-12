package com.zhuyitech.parking.tool.dto.result.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 行政区域地址视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RegionAddressResultDto", description = "区域视图模型")
public class RegionAddressResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址")
    private String address;

}