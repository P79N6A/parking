package com.zhuyitech.parking.tool.dto.result.license;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车牌前缀视图模型
 *
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LicenseOrganizationPrefixResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 父ID
     */
    @ApiModelProperty("父ID")
    private Long parentId;

}
