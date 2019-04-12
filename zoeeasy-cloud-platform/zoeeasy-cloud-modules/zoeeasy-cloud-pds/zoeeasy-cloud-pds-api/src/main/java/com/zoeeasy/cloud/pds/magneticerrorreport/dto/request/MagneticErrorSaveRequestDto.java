package com.zoeeasy.cloud.pds.magneticerrorreport.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pds.magneticerrorreport.cts.MangeticErrorReportConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Data
@ApiModel(value = "MagneticErrorSaveRequestDto", description = "保存地磁误报记录请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticErrorSaveRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "tenantId", required = true)
    @NotNull(message = MangeticErrorReportConstant.TENANT_ID_NOT_NULL)
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "parkingId")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 检测器ID
     */
    @ApiModelProperty(value = "detectorId", required = true)
    @NotNull(message = MangeticErrorReportConstant.DETECOTR_ID_NOT_NULL)
    private Long detectorId;

    /**
     * 地磁管理器类型(厂商) 数据字典
     */
    @ApiModelProperty(value = "provider")
    private Integer provider;

    /**
     * (厂商)设备序列号
     */
    @ApiModelProperty(value = "serialNumber")
    private String serialNumber;

    /**
     * 手持终端编号
     */
    @ApiModelProperty(value = "terminalId")
    private String terminalId;

    /**
     * 地磁报告时间
     */
    @ApiModelProperty(value = "reportTime")
    private Date reportTime;

    /**
     * 地磁报告类型(1-车辆到达, 2-车辆离开)
     */
    @ApiModelProperty(value = "reportType")
    private Integer reportType;

    /**
     * 收费员或巡检员ID
     */
    @ApiModelProperty(value = "inspectUserId")
    private Long inspectUserId;

    /**
     * 收费员或巡检员姓名
     */
    @ApiModelProperty(value = "inspectUserName")
    private String inspectUserName;

    /**
     * 误报处理时间
     */
    @ApiModelProperty(value = "processTime")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date processTime;

    /**
     * 误报原因，0-地磁误报进车（进车时），1-地磁误报出车（出车时），2-连续上报
     */
    @ApiModelProperty(value = "processReason")
    private Integer processReason;
}
