package com.zhuyitech.parking.tool.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改车管局信息请求参数
 *
 * @Date: 2018/4/15
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LicenseOrganizationUpdateRequestDto", description = "修改车管局信息请求参数")
public class LicenseOrganizationUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 城市
     */
    @ApiModelProperty(value = "city")
    private String city;

    /**
     * 发动机号：-1是指输入全部字段，0是指不需要输入，6是指输入后六位，以此类推
     */
    @ApiModelProperty(value = "engineNoLength")
    private Integer engineNoLength;

    /**
     * 车架号：-1是指输入全部字段，0是指不需要输入，6是指输入后六位，以此类推
     */
    @ApiModelProperty(value = "vinLength")
    private Integer vinLength;

    /**
     * 车牌前缀
     */
    @ApiModelProperty(value = "cityPrefix")
    private String cityPrefix;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "carTypes")
    private String carTypes;

}
