package com.zoeeasy.cloud.ucc.menu.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 所有静态菜单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AllStaticMenuListGetRequestDto", description = "所有静态菜单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AllStaticMenuListGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;
}
