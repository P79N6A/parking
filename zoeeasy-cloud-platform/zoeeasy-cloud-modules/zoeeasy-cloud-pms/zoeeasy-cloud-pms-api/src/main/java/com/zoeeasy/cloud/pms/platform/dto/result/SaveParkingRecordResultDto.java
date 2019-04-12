package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/9 0009
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("SaveParkingRecordResultDto")
public class SaveParkingRecordResultDto extends EntityDto<Long> {
}
