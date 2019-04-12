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
@ApiModel(value = "IdCardCertResultDto", description = "身份证二要素校验结果返回")
public class IdCardCertResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 认证状态 true 成功 false 失败
     */
    @ApiModelProperty(value = "认证状态 true 成功 false 失败")
    private Boolean status;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String msg;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 身份证所在地
     */
    @ApiModelProperty(value = "身份证所在地")
    private String area;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 区县
     */
    @ApiModelProperty(value = "区县")
    private String prefecture;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private String birthday;

    /**
     * 地区代码
     */
    @ApiModelProperty(value = "地区代码")
    private String addCode;

    /**
     * 身份证校验码
     */
    @ApiModelProperty(value = "身份证校验码")
    private String lastCode;

}
