package com.zhuyitech.parking.tool.dto.result.volation;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 车辆违章查询结果
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VehicleViolationCategoryResultDto", description = "车辆违章查询结果")
public class VehicleViolationCategoryResultDto extends BaseDto {

    /**
     * 未处理违章记录列表
     */
    @ApiModelProperty(value = "未处理违章记录列表")
    private List<VehicleViolationItemResultDto> untreatedViolations;

    /**
     * 已处理违章记录列表
     */
    @ApiModelProperty(value = "已处理违章记录列表")
    private List<VehicleViolationItemResultDto> treatedViolations;

}
