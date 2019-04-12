package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.EffectedStatusEnumValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PlateColorEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BlackListQueryPagedRequestDto", description = "分页黑名单请求参数")
public class BlackListQueryPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    @FluentValidate({PlateColorEnumValidator.class})
    private Integer plateColor;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 生效状态
     */
    @ApiModelProperty("生效状态")
    @FluentValidate({EffectedStatusEnumValidator.class})
    private Integer status;

}
