package com.zoeeasy.cloud.pms.park.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.validator.LotTypeEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场列表请求参数
 * Created by song on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingListGetRequestDto", description = "停车场列表请求参数")
public class ParkingListGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 区域code
     */
    @ApiModelProperty("areaCode")
    private String areaCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * 停车场类型
     */
    @ApiModelProperty("lotType")
    @FluentValidate({LotTypeEnumValidator.class})
    private String lotType;

    /**
     * 停车场编号
     */
    @ApiModelProperty("code")
    private String code;

}
