package com.zoeeasy.cloud.pds.magneticdetector.dto.result.inspect;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticDetectorGetByInspectResultDto", description = "巡检获取地磁检测器")
public class MagneticDetectorGetByInspectResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 地磁管理器类型(厂商) 数据字典
     */
    private Integer provider;

    /**
     * (厂商)设备序列号
     */
    private String serialNumber;
}
