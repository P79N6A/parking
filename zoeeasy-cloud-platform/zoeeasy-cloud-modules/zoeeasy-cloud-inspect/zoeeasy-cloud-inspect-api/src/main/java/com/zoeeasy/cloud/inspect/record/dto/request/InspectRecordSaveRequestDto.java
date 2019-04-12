package com.zoeeasy.cloud.inspect.record.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InspectRecordSaveRequestDto", description = "保存巡检记录请求参数")
public class InspectRecordSaveRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = true)
    @NotNull(message = "租户id不能为空")
    private Long tenantId;

    /**
     * 停车记录ID
     */
    @ApiModelProperty(value = "停车记录ID")
    @NotNull(message = "停车记录ID不能为空")
    private Long recordId;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty(value = "停车记录流水号")
    @NotBlank(message = "停车记录流水号不能为空")
    private String recordNo;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = "停车场ID不能为空")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 停车卡号
     */
    @ApiModelProperty(value = "停车卡号")
    private String cardNumber;

    /**
     * 停车码
     */
    @ApiModelProperty(value = "停车码")
    private String codeNumber;

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
     * 停车类型
     */
    @ApiModelProperty(value = "停车类型")
    private Integer parkingType;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date endTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 巡检员收费员ID
     */
    @ApiModelProperty(value = "巡检员收费员ID")
    @NotNull(message = "巡检员收费员ID不能为空")
    private Long inspectUserId;

    /**
     * 巡查时间
     */
    @ApiModelProperty(value = "巡查时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date inspectTime;

    /**
     * 异常原因，1 -剩余车位数不正确，2 -车牌号码不匹配 3 车牌颜色不匹配 4 泊位不匹配 5 车型不匹配 6 入场时间不匹配
     */
    @ApiModelProperty(value = "异常原因，1 -剩余车位数不正确，2 -车牌号码不匹配 3 车牌颜色不匹配 4 泊位不匹配 5 车型不匹配 6 入场时间不匹配")
    private String inspectReason;

    /**
     * 巡查结果，1 已处理 2 未处理
     */
    @ApiModelProperty(value = "巡查结果，1 已处理 2 未处理")
    private Integer inspectResult;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    private String description;
}
