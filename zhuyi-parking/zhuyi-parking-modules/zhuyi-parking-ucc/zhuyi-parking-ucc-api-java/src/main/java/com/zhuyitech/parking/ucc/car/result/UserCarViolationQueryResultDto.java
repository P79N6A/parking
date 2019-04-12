package com.zhuyitech.parking.ucc.car.result;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zhuyitech.parking.common.constant.Const;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationItemResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户车辆违章记录查询结果
 *
 * @author walkman
 * @Date: 2018/4/16
 */
@ApiModel(value = "UserCarViolationQueryResultDto", description = "用户车辆违章记录查询结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarViolationQueryResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 完整车牌号
     */
    @ApiModelProperty(value = "车牌号")
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
     * 违章记录更新时间
     */
    @ApiModelProperty(value = "违章记录更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date violationUpdateTime;

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
