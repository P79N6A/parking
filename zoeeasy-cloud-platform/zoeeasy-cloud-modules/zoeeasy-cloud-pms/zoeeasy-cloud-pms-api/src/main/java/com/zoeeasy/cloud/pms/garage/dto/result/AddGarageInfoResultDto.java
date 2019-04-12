package com.zoeeasy.cloud.pms.garage.dto.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车库列表视图模型
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddGarageInfoResultDto", description = "车库列表视图模型")
public class AddGarageInfoResultDto extends CloudResultDto {

    private static final long serialVersionUID = 1L;

}
