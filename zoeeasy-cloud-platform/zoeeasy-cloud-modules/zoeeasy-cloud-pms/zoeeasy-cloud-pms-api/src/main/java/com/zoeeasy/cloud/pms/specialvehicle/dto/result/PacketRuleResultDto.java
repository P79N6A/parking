package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import com.scapegoat.infrastructure.jackson.convert.serializer.ToBigDecimalYuanSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 包期规则视图模型
 *
 * @date: 2018/10/15.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketRuleResultDto", description = "包期规则视图模型")
public class PacketRuleResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 包期规则名称
     */
    @ApiModelProperty("packetName")
    private String packetName;

    /**
     * 包期类型，1：包月，2：包年
     */
    @ApiModelProperty(value = "包期类型")
    private Integer packetType;

    /**
     * 包期金额
     */
    @ApiModelProperty(value = "包期金额")
    @JsonSerialize(using = ToBigDecimalYuanSerializer.class)
    private Integer price;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

}
