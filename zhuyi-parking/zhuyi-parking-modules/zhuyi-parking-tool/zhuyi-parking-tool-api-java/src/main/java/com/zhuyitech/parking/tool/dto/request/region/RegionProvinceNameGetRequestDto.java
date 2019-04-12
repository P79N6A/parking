package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 通过adcode获取省name
 *
 * @author zwq
 */
@ApiModel(value = "RegionProvinceNameGetRequestDto", description = "通过adcode获取省name")
public class RegionProvinceNameGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编码
     */
    @ApiModelProperty(value = "区域编码")
    private String adcode;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }
}
