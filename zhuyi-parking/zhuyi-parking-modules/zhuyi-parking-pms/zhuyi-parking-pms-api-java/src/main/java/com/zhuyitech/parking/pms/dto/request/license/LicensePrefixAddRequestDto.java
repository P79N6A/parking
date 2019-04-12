package com.zhuyitech.parking.pms.dto.request.license;


import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加车牌前缀请求参数
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixAddRequestDto", description = "添加车牌前缀请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixAddRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点
     */
    @ApiModelProperty(value = "parentId", required = true)
    private Long parentId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "name", required = true)
    private String name;

    /**
     * 类型 1前缀 2首字母
     */
    @ApiModelProperty(value = "type", required = true, notes = " 类型 1前缀 2首字母")
    private Integer type;

}
