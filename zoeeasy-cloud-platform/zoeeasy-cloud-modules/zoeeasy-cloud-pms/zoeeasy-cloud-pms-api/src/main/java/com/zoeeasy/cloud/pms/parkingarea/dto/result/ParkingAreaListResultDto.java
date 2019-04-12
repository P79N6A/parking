package com.zoeeasy.cloud.pms.parkingarea.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 泊位区域列表视图模型
 * Created by song on 2018/9/29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAreaListResultDto", description = "泊位区域列表视图模型")
public class ParkingAreaListResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

}
