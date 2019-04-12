package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 根据省全称获取省份简称
 *
 * @author zwq
 */
@ApiModel(value = "ProvinceGetByAdcodeRequestDto", description = "根据省全称获取省份简称")
public class ProvinceGetByAdcodeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编码
     */
    @ApiModelProperty(value = "区域编码", required = true)
    private String adcode;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }
}