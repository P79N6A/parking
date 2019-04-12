package com.zhuyitech.parking.pms.dto.result.car;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌信息视图模型
 *
 * @author walkman
 */
@ApiModel(value = "CarBrandViewResultDto", description = "品牌信息视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 全称
     */
    @ApiModelProperty("全称")
    private String fullName;

    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    private String initial;

    /**
     * 上级ID
     */
    @ApiModelProperty("上级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * LOGO
     */
    @ApiModelProperty("LOGO")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String logo;

}
