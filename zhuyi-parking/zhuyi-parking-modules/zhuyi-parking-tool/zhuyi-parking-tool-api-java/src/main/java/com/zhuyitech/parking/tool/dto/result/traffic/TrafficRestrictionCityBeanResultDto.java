package com.zhuyitech.parking.tool.dto.result.traffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zhuyitech.parking.tool.bean.traffic.TrafficRestrictionCityBean;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 限行城市返回结果类
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "TrafficRestrictionResultDto", description = "限行城市返回结果类")
public class TrafficRestrictionCityBeanResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 返回说明
     */
    private String reason;

    /**
     * 返回结果集
     */
    @ApiModelProperty(value = "返回结果集")
    private List<TrafficRestrictionCityBean> result;

}
