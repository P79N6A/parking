package com.zoeeasy.cloud.pms.garage.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车库列表视图模型
 * Created by song on 2018/9/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarageListGetResultDto", description = "车库列表视图模型")
public class GarageListGetResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车库编号
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 车库名称
     */
    @ApiModelProperty("name")
    private String name;

}
