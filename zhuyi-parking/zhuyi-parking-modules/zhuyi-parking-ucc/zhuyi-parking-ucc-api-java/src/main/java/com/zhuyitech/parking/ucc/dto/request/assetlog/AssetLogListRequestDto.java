package com.zhuyitech.parking.ucc.dto.request.assetlog;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户收支明细服务列表
 *
 * @author walkman
 * @date 2018-04-01
 */
@ApiModel(value = "AssetLogListRequestDto", description = "用户收支明细列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogListRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

}
