package com.zoeeasy.cloud.integration.appoint.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingImageViewResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.List;

/**
 * 可预约停车场详情
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointmentParkingDetailViewResultDto", description = "可预约停车场详情")
public class AppointmentParkingDetailViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String name;

    /**
     * LOGO
     */
    @ApiModelProperty(value = "LOGO")
    private String logo;

    /**
     * 开始营业时间，格式HH:mm:ss
     */
    @ApiModelProperty(value = "开始营业时间")
    private String openStartTime;

    /**
     * 结束营业时间，格式HH:mm:ss
     */
    @ApiModelProperty(value = "结束营业时间")
    private String openEndTime;

    /**
     * 收费规则
     */
    @ApiModelProperty(value = "收费规则")
    private String chargeRule;

    /**
     * 收费描述信息
     */
    @ApiModelProperty(value = "收费描述信息")
    private String chargeDescription;

    /**
     * 预约规则
     */
    @ApiModelProperty(value = "预约规则")
    private String appointmentRule;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 车位总数
     */
    @ApiModelProperty(value = "车位总数")
    private Integer lotTotal;

    /**
     * 可用车位数
     */
    @ApiModelProperty(value = "可用车位数")
    private Integer lotAvailable;

    /**
     * 总共可预约车位
     */
    @ApiModelProperty(value = "支持预约车位总数")
    private Integer lotAppointmentTotal;

    /**
     * 剩余可预约车位
     */
    @ApiModelProperty(value = "剩余可预约车位")
    private Integer lotAppointmentAvailable;

    /**
     * 距离
     */
    @ApiModelProperty(value = "距离")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "##.##")
    @JsonSerialize(using = ToStringSerializer.class)
    private Double distance;

    /**
     * 距离单位(小于1km，单位m;d大于1km，单位km)
     */
    @ApiModelProperty(value = "距离单位")
    private String distanceUnit;

    /**
     * 是否区分大小型车费
     */
    @ApiModelProperty(value = "是否区分大小型车费")
    private Boolean discriminateLargeSmall;

    /**
     * 大型车收费规则
     */
    @ApiModelProperty(value = "大型车收费规则")
    private String largeVehicleRule;

    /**
     * 小型车收费规则
     */
    @ApiModelProperty(value = "小型车收费规则")
    private String smallVehicleRule;

    /**
     * 停车图像URL列表
     */
    @ApiModelProperty(value = "停车图像URL列表")
    private List<ParkingImageViewResultDto> images;

    /**
     * 24小时封顶金额
     */
    @ApiModelProperty(value = "24小时封顶金额")
    private BigDecimal dayTopAmount;

}
