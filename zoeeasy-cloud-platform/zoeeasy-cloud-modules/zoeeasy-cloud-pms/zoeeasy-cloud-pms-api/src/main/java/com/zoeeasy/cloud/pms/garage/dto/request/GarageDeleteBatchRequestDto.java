package com.zoeeasy.cloud.pms.garage.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 批量删除车库请求参数
 * Created by song on 2018/9/25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarageDeleteBatchRequestDto", description = "批量删除车库请求参数")
public class GarageDeleteBatchRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * ids
     */
    @ApiModelProperty("ids")
    private List<Long> ids;

}
