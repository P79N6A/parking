package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信小程序二维码视图模型
 *
 * @Date: 2019/3/19
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeChatSmallAppQrcodeResultDto", description = "微信小程序二维码视图模型")
public class WeChatSmallAppQrcodeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * token
     */
    private String token;
}
