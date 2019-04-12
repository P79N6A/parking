package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MyPlateNumberRequestVo
 * @Description 我的车牌号
 * @Author qhxu
 * @Date 2019/3/22 19:40
 * @Version1.0
 **/
@Data
@ApiModel(value = "PlateNumberMyRequestVo", description = "我的车牌号")
public class PlateNumberMyRequestVo extends SessionDto {

    /**
     * 客户端类型(0:app,1:微信小程序，2:支付宝小程序，3：公众号)
     */
    @ApiModelProperty(value = "终端类型")
    private String clientType;
}
