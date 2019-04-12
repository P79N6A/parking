package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 获取省列表
 *
 * @author walkman
 */
@ApiModel(value = "ProvinceListGetRequestDto", description = "删除地区请求参数")
public class ProvinceListGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 省编码
     */
    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}