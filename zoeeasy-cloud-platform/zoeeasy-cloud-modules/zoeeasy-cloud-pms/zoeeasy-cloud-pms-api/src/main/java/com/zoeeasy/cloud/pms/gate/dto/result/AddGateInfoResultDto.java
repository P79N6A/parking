package com.zoeeasy.cloud.pms.gate.dto.result;

import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场出入口视图模型
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddGateInfoResultDto", description = "停车场出入口视图模型")
public class AddGateInfoResultDto extends CloudResultDto {

    private static final long serialVersionUID = 1L;

}
