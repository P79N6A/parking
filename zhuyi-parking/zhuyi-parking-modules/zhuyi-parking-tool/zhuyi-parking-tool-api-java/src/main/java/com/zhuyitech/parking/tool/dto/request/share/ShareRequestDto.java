package com.zhuyitech.parking.tool.dto.request.share;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分享请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/24 0024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShareRequestDto", description = "分享请求参数")
public class ShareRequestDto extends BaseDto {
    private static final long serialVersionUID = 1L;

}
