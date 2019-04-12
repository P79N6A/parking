package com.zhuyitech.parking.pms.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车牌前缀列表分页请求参数表
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixQueryPagedResultRequestDto", description = "车牌前缀列表分页请求参数表")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixQueryPagedResultRequestDto extends SessionPagedRequestDto {

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
