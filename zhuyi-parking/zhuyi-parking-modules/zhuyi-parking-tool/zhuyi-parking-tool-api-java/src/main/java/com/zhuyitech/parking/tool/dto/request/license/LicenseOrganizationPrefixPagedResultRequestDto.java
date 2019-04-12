package com.zhuyitech.parking.tool.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LicenseOrganizationPrefixPagedResultRequestDto", description = "车牌前缀列表分页请求参数表")
public class LicenseOrganizationPrefixPagedResultRequestDto extends SessionPagedRequestDto {

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
