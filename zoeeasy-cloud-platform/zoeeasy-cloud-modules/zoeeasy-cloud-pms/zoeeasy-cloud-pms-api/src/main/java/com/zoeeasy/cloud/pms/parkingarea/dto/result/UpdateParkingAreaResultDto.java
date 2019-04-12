package com.zoeeasy.cloud.pms.parkingarea.dto.result;

import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2018/12/1
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UpdateParkingAreaResultDto", description = "修改停车区域视图模型")
public class UpdateParkingAreaResultDto extends CloudResultDto {

    private static final long serialVersionUID = 1L;
}
