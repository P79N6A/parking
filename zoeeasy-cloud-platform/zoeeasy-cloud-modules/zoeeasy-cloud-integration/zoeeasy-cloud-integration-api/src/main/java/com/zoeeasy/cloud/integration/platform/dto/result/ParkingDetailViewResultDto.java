package com.zoeeasy.cloud.integration.platform.dto.result;

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
 * 停车场详情视图模型
 *
 * @author walkman
 */
@ApiModel(value = "ParkingDetailViewResultDto", description = "停车场详情视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingDetailViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String name;

    /**
     * 是否接入平台
     */
    @ApiModelProperty(value = "是否接入平台", notes = "是否接入平台")
    private Boolean platformSupport;

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
     * 停车场类型
     */
    @ApiModelProperty(value = "停车场类型(1为地面，2为地下，3为路边)")
    private String type;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "0 为现金支付 1为支付宝在线缴费，2为支付宝代扣缴费，3当面付")
    private String payType;

    /**
     * 是否收费
     */
    @ApiModelProperty(value = "是否收费")
    private Boolean chargeFee;

    /**
     * 是否限免
     */
    @ApiModelProperty(value = "是否限免(True 是 False 否 )")
    private Boolean nowFree;

    /**
     * 收费规则
     */
    @ApiModelProperty(value = "收费规则")
    private String chargeRule;

    /**
     * 收费信息
     */
    @ApiModelProperty(value = "收费信息")
    private String chargeDescription;

    /**
     * 免费停车时长
     */
    @ApiModelProperty(value = "免费停车时长")
    private Integer freeTimeLength;

    /**
     * 24小时封顶金额
     */
    @ApiModelProperty(value = "24小时封顶金额")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "##.##")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal dayTopAmount;

    /**
     * 是否可预约(false不可预约 true可预约)
     */
    @ApiModelProperty(value = "是否可预约", notes = "是否可预约(false不可预约 true可预约)")
    private Boolean supportAppointment;

    /**
     * 预约规则
     */
    @ApiModelProperty(value = "预约规则")
    private String appointmentRule;

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
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

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
    @ApiModelProperty(value = "总共可预约车位")
    private Integer lotAppointmentTotal;

    /**
     * 剩余可预约车位
     */
    @ApiModelProperty(value = "剩余可预约车位")
    private Integer lotAppointmentAvailable;

    /**
     * 距离
     */
    @ApiModelProperty(value = "距离(单位：米)")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "##.##")
    @JsonSerialize(using = ToStringSerializer.class)
    private Double distance;

    /**
     * 距离单位(小于1km，单位m;d大于1km，单位km)
     */
    @ApiModelProperty(value = "距离单位")
    private String distanceUnit;

    /**
     * 空闲等级(1:绿色 2: 黄色 3: 红色)
     */
    @ApiModelProperty(value = "空闲等级(1:绿色 2: 黄色 3: 红色)")
    private Integer freeLevel;

    /**
     * 停车图像URL列表
     */
    @ApiModelProperty(value = "停车图像URL列表")
    private List<ParkingImageViewResultDto> imageUrls;

}
