package com.zoeeasy.cloud.order.appoint.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 预约停车订单视图模型
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderHadResultDto", description = "预约停车订单视图模型")
public class AppointOrderHadResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否存在已预约订单
     */
    @ApiModelProperty(value = "是否存在已预约订单")
    private Boolean had;
}

