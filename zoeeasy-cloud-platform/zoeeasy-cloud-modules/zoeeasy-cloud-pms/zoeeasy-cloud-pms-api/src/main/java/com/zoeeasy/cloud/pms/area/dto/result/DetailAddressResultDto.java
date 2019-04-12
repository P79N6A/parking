package com.zoeeasy.cloud.pms.area.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地址详情视图
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DetailAddressResultDto", description = "地址详情视图")
public class DetailAddressResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 地址详情
     */
    @ApiModelProperty("detailAddress")
    private String detailAddress;

}
