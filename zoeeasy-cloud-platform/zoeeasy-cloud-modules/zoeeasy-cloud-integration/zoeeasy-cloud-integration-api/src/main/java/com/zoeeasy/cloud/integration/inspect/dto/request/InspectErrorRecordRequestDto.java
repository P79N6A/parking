package com.zoeeasy.cloud.integration.inspect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/10/22 0022
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InspectErrorRecordRequestDto", description = "误报巡检记录请求参数")
public class InspectErrorRecordRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位id
     */
    @ApiModelProperty(value = "泊位id", required = true)
    @NotBlank(message = InspectConstant.PARKING_LOT_CODE_NOT_EMPTY)
    private String parkingLotCode;

    /**
     * 误报原因
     */
    @ApiModelProperty(value = "误报原因")
    @NotNull(message = InspectConstant.INSPECT_REASON_NOT_EMPTY)
    private Integer processReason;

}
