package com.zoeeasy.cloud.pds.magneticdetector.dto.result.park;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 厂商视图模型
 *
 * @author lhj
 */
@Data
@ApiModel(value = "ProviderResultDto", description = "MagneticDetectorResultDto")
@EqualsAndHashCode(callSuper=false)
public class ProviderResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁管理器类型(厂商) 数据字典
     */
    @ApiModelProperty(value ="地磁管理器类型(厂商) 数据字典")
    private Integer provider;

    /**
     * 厂商名称
     */
    @ApiModelProperty(value ="厂商名称")
    private String providerName;
}
