package com.zhuyitech.parking.tool.dto.result.oilprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 获取油价返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceResultDto", description = "获取油价返回结果")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OilPriceResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 返回状态
     */
    private String resultcode;

    /**
     * 返回状态
     */
    private String reason;

    /**
     * 返回状态
     */
    private String error_code;

    /**
     * 返回状态
     */
    private List<OilPriceInfo> result;
    
}
