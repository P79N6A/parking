package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleRecordSaveResultDto", description = "保存过车记录结果")
public class PassingVehicleRecordCreatedResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
