package com.zhuyitech.parking.pms.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改车牌前缀请求参数
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixUpdateRequestDto", description = "修改车牌前缀请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixUpdateRequestDto extends SessionEntityDto<Long> {

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
