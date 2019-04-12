package com.zoeeasy.cloud.pds.magneticmanager.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 批量更新设备的管理器Id请求参数
 *
 * @Date: 2018/8/29
 * @author: wh
 */
@Data
@ApiModel(value = "MagneticManagerIdBatchUpdateRequestDto", description = "批量更新设备的管理器Id请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticManagerIdBatchUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 关联地磁管理器id
     */
    @ApiModelProperty("managerId")
    @NotNull(message = MagneticManagerConstant.RELEVANCE_MAGNETIC_MANAGER_ID_NOT_NULL)
    private Long managerId;

    /**
     * 设备检测器codes
     */
    @ApiModelProperty(value = "设备检测器Ids")
    private List<String> codes;
}

