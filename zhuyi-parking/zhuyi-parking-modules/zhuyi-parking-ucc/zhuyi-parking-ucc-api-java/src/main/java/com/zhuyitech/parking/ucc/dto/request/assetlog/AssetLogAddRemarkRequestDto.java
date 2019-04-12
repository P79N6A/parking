package com.zhuyitech.parking.ucc.dto.request.assetlog;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 添加备注
 *
 * @author zwq
 * @date 2018-06-26
 */
@ApiModel(value = "AssetLogAddRemarkRequestDto", description = "添加备注")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogAddRemarkRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
