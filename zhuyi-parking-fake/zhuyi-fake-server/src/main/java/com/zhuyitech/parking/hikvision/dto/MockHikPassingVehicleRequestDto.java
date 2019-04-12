package com.zhuyitech.parking.hikvision.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 海康过车数据模拟请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MockPassingVehicleAddRequestDto", description = "海康过车数据模拟请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class MockHikPassingVehicleRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码", required = true)
    @NotBlank(message = "车牌号码不能为空")
    private String plateNo;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", allowableValues = "0,1,2,6", required = true)
    private Integer plateColor;

    /**
     * 通过时间 格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(value = "通过时间 格式：yyyy-MM-dd HH:mm:ss", required = true)
    @NotBlank(message = "通过时间不能为空")
    private String passTime;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号", required = true)
    @NotBlank(message = "停车场编号不能为空")
    private String parkCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkName;

    /**
     * 出入口编号
     */
    @ApiModelProperty(value = "出入口编号")
    private String gateCode;

    /**
     * 出入口名称
     */
    @ApiModelProperty(value = "出入口名称")
    private String gateName;

    /**
     * 车道编号
     */
    @ApiModelProperty(value = "车道编号")
    private String laneNo;

    /**
     * 车道名称
     */
    @ApiModelProperty(value = "车道名称")
    private String laneName;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型", allowableValues = "0,1,2")
    private Integer carType;

    /**
     * 过车方向
     */
    @ApiModelProperty(value = "过车方向(1 入车 2 出车)", required = true, allowableValues = "1,2")
    private Integer direct;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String berthCode;

}
