package com.zhuyitech.parking.tool.dto.result.carpoint;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车辆扣分查询返回结果
 *
 * @Date: 2018/4/12
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CarPointPenaltyDataResultDto", description = "车辆扣分查询返回结果")
public class LicensePointPenaltyDataResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 驾驶证号
     */
    @ApiModelProperty(value = "驾驶证号")
    private String licenseNumber;

    /**
     * 档案编号
     */
    @ApiModelProperty(value = "档案编号")
    private String licenseId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String realName;

    /**
     * 准驾车型
     */
    @ApiModelProperty(value = "准驾车型")
    private String type;

    /**
     * 领证日期
     */
    @ApiModelProperty(value = "领证日期")
    private String startDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private String endDate;

    /**
     * 证件状态
     */
    @ApiModelProperty(value = "证件状态")
    private String licenseStatus;

    /**
     * 扣分
     */
    @ApiModelProperty(value = "扣分")
    private Integer score;

}
