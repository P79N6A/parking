package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zwq
 * @Description: 巡检更新停车记录结果
 * @Date: 2018/09/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordUpdateResultDto", description = "巡检更新停车记录结果")
public class ParkingRecordUpdateResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty("停车记录流水号")
    private Boolean succeed;
}
