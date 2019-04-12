package com.zhuyitech.parking.ucc.dto.request.assetlog;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户收支明细详情
 *
 * @author walkman
 * @date 2018-04-01
 */
@ApiModel(value = "AssetLogDetailRequestDto", description = "用户收支明细详情")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogDetailRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

}
