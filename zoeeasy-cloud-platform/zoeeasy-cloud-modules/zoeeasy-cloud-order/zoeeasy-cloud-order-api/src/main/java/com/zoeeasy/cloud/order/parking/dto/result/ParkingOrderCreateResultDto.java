package com.zoeeasy.cloud.order.parking.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/19 0019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderCreateResultDto", description = "保存订单返回结果")
public class ParkingOrderCreateResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
