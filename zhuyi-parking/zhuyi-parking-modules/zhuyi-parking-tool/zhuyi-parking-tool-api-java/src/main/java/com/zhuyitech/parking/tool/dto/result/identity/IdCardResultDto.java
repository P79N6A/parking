package com.zhuyitech.parking.tool.dto.result.identity;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/7/23 0023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IdCardResultDto", description = "身份证二要素校验二结果返回")
public class IdCardResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 认证状态 0 成功
     */
    @ApiModelProperty(value = "认证状态 0 成功 ")
    private Integer errorCode;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String reason;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String realName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private String birth;

    /**
     * 地区代码
     */
    @ApiModelProperty(value = "地区代码")
    private String address;

}
