package com.zoeeasy.cloud.pms.garage.dto.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改车库列表视图模型
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UpdateGarageInfoResultDto", description = "修改车库列表视图模型")
public class UpdateGarageInfoResultDto extends CloudResultDto {

    private static final long serialVersionUID = 1L;
}
