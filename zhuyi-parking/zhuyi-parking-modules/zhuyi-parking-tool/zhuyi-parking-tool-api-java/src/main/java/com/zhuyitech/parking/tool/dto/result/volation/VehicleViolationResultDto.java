package com.zhuyitech.parking.tool.dto.result.volation;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 车辆违章查询结果
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VehicleViolationResultDto", description = "车辆违章查询结果")
public class VehicleViolationResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 未处理违章总罚款
     */
    @ApiModelProperty(value = "未处理违章总罚款")
    private BigDecimal totalFine;

    /**
     * 未处理违章总扣分
     */
    @ApiModelProperty(value = "未处理违章总扣分")
    private Integer totalPoints;

    /**
     * 未处理违章条数
     */
    @ApiModelProperty(value = "未处理违章条数")
    private Integer untreatedCount;

    /**
     * 违章总条数
     */
    @ApiModelProperty(value = "违章总条数")
    private Integer totalCount;

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
