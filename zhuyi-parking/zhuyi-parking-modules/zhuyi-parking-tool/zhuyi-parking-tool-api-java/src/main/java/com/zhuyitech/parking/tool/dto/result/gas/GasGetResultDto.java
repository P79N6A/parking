package com.zhuyitech.parking.tool.dto.result.gas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 获取充电桩返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GasGetResultDto", description = "获取充电桩返回结果")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GasGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 返回状态
     */
    private String message;

    /**
     * 返回状态
     */
    private Integer status;

    /**
     * 返回状态
     */
    private List<GasResult> results;

}
