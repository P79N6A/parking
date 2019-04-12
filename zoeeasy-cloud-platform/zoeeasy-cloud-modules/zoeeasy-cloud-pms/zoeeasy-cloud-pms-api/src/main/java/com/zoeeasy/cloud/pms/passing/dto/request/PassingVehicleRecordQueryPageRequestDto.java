package com.zoeeasy.cloud.pms.passing.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author AkeemSuper
 * @Description: 分页查询平台过车记录请求参数
 * @Date: 2018/2/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleRecordQueryPageRequestDto", description = "分页查询平台过车记录请求参数")
public class PassingVehicleRecordQueryPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位编号")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String parkingLotNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;

    /**
     * 校对状态
     */
    @ApiModelProperty(value = "校对状态")
    private Integer proofStatus;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private Integer dataSource;

    /**
     * 过车类型
     */
    @ApiModelProperty(value = "过车类型")
    private Integer passCarType;

    /**
     * 过车开始时间
     */
    @ApiModelProperty("过车开始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTime;

    /**
     * 过车结束时间
     */
    @ApiModelProperty("过车结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;

}
