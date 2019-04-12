package com.zhuyitech.parking.tool.dto.result.oilprice;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 查询当前油价
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OilPriceRightNowResultDto", description = "查询当前油价")
public class OilPriceRightNowResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 省份
     */
    @ApiModelProperty(value = "province")
    private String province;

    /**
     * 90号汽油油价(元)
     */
    @ApiModelProperty(value = "90号汽油油价(元)")
    private String b90;

    /**
     * 93号汽油油价(元)
     */
    @ApiModelProperty(value = "93号汽油油价(元)")
    private String b93;

    /**
     * 97号汽油油价(元)
     */
    @ApiModelProperty(value = "97号汽油油价(元)")
    private String b97;

    /**
     * 0号汽油油价(元)
     */
    @ApiModelProperty(value = "0号汽油油价(元)")
    private String b0;

}
