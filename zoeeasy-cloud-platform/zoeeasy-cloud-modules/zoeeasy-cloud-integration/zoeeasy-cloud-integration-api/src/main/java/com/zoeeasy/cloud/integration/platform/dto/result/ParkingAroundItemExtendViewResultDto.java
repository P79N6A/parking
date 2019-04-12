package com.zoeeasy.cloud.integration.platform.dto.result;


import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 停车场基本扩展信息视图模型
 *
 * @author walkman
 */
@ApiModel(value = "ParkingViewResultDto", description = "停车场基本信息视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingAroundItemExtendViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

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
     * 免费停车时长
     */
    @ApiModelProperty(value = "免费停车时长")
    private Integer freeTimeLength;

    /**
     * 24小时封顶金额
     */
    @ApiModelProperty(value = "24小时封顶金额")
    private String dayTopAmount;

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
     * 总共可预约车位
     */
    @ApiModelProperty(value = "总共可预约车位")
    private Integer lotAppointmentTotal;

}
