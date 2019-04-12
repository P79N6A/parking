package com.zoeeasy.cloud.pms.park.dto.result;

import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddParkingInfoResultDto", description = "停车场管理系统添加泊位视图模型")
public class AddParkingLotResultDto extends CloudResultDto {

    private static final long serialVersionUID = 1L;
}
