package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.convert.serializer.ToBigDecimalYuanSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketReceiptResultDto", description = "收费项目明细视图模型")
public class ParkingPacketRuleResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 包期规则名称
     */
    @ApiModelProperty("包期规则名称")
    private String packetName;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer count;

    /**
     * 金额(元)
     */
    @ApiModelProperty("金额")
    @JsonSerialize(using = ToBigDecimalYuanSerializer.class)
    private Integer price;

    /**
     * 有效时间
     */
    @ApiModelProperty("有效时间")
    private String validTime;

}
