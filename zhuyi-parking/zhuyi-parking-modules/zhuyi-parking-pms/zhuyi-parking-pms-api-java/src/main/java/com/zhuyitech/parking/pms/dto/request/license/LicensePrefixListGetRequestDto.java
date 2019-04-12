package com.zhuyitech.parking.pms.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车牌前缀列表请求参数表
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixListGetRequestDto", description = "车牌前缀列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixListGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", notes = " 类型 1前缀 2首字母")
    private Integer type;

    /**
     * 父ID
     */
    @ApiModelProperty("父ID")
    private Long parentId;

}
