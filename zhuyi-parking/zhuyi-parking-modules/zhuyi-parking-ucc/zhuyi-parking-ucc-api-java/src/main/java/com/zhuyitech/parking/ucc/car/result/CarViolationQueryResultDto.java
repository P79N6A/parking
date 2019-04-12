package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationItemResultDto;
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
 * @date 2018/4/14
 */
@ApiModel(value = "CarViolationQueryResultDto", description = "车辆违章查询结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class CarViolationQueryResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    @SensitiveInfo(SensitiveType.PLATE_NUMBER)
    private String plateNumber;

    /**
     * 认证状态
     */
    @ApiModelProperty(value = "认证状态(1 认证中 2 已认证 3认证不通过)")
    private Integer status;

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
