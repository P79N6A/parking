package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 根据限行尾号和归属地获取车辆信息
 *
 * @author yuzhicheng
 */
@ApiModel(value = "UserCarInfoGetByTailNumberRequestDto", description = "获取用户车型请求参数")
public class UserCarInfoGetByTailNumberRequestDto extends PagedResultRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌前缀
     */
    @ApiModelProperty("车牌前缀")
    private String cityPrefix;

    /**
     * 限行尾号
     */
    @ApiModelProperty("限行尾号")
    private String tailNumber;

    /**
     * 尾号是字母的处理方式
     */
    @ApiModelProperty("尾号是字母的处理方式")
    private Integer limitPattern;

    public String getCityPrefix() {
        return cityPrefix;
    }

    public void setCityPrefix(String cityPrefix) {
        this.cityPrefix = cityPrefix;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public Integer getLimitPattern() {
        return limitPattern;
    }

    public void setLimitPattern(Integer limitPattern) {
        this.limitPattern = limitPattern;
    }
}
