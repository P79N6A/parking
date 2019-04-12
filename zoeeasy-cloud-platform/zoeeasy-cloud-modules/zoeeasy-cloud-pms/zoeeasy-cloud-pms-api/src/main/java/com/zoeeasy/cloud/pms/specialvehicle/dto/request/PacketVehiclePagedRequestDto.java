package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.AllParkingEnumValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketTypeEnumValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PlateColorEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketVehiclePagedRequestDto", description = "分页获取包期车请求参数")
public class PacketVehiclePagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    @FluentValidate({PlateColorEnumValidator.class})
    private Integer plateColor;

    /**
     * 是否全部停车场
     */
    @ApiModelProperty("是否全部停车场")
    @FluentValidate({AllParkingEnumValidator.class})
    private Integer allParking;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 包期类型
     */
    @ApiModelProperty("包期类型")
    @FluentValidate({PacketTypeEnumValidator.class})
    private Integer packetType;

}
