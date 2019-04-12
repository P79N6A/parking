package com.zoeeasy.cloud.integration.message.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/30 0030
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticPassMessageRequestDto", description = "地磁消息发送请求参数")
public class MagneticPassMessageRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场id不能为空")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID", required = true)
    @NotNull(message = "泊位IDid不能为空")
    private Long parkingLotId;

    /**
     * 过车类型  0 未知 1.入车 2.出车
     */
    @ApiModelProperty(value = "过车类型", required = true)
    @NotNull(message = "过车类型不能为空")
    private Integer passingType;

    /**
     * 过车时间
     */
    @ApiModelProperty(value = "过车时间", required = true)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date passTime;
}
