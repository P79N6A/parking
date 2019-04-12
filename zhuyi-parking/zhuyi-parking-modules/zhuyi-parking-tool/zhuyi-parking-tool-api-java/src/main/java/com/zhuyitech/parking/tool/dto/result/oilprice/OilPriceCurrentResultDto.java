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
@ApiModel(value = "OilPriceCurrentResultDto", description = "当前油价列表查询")
public class OilPriceCurrentResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 省份
     */
    @ApiModelProperty(value = "province")
    private String province;

    /**
     * code
     */
    @ApiModelProperty(value = "code")
    private String code;

    /**
     * 89号汽油油价(元)
     */
    @ApiModelProperty(value = "89号汽油油价(元)")
    private String b89;

    /**
     * 92号汽油油价(元)
     */
    @ApiModelProperty(value = "92号汽油油价(元)")
    private String b92;

    /**
     * 95号汽油油价(元)
     */
    @ApiModelProperty(value = "95号汽油油价(元)")
    private String b95;

    /**
     * 0号汽油油价(元)
     */
    @ApiModelProperty(value = "0号汽油油价(元)")
    private String b0;

}
