package com.zoeeasy.cloud.pms.parkingarea.dto.result;

import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加停车区域视图模型
 *
 * @Date: 2018/12/1
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddParkingAreaResultDto", description = "添加停车区域视图模型")
public class AddParkingAreaResultDto extends CloudResultDto {

    private static final long serialVersionUID = 1L;

}
