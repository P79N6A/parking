package com.zhuyitech.parking.pms.dto.request.car;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车型列表请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "CarBrandListGetRequestDto", description = "车型列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandListGetRequestDto extends SessionEntityDto<Long> {

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
     * 父ID
     */
    @ApiModelProperty("父ID")
    private Long parentId;

    /**
     * 深度 1品牌 2子公司 3车型 4具体车型
     */
    @ApiModelProperty("depth")
    private Integer depth;

    /**
     * 年款
     */
    @ApiModelProperty("年款")
    private String yearType;

    /**
     * 车辆等级
     */
    @ApiModelProperty("车辆等级")
    private String sizeType;

    /**
     * 销售状态
     */
    @ApiModelProperty("销售状态")
    private String saleState;

    /**
     * 生产状态
     */
    @ApiModelProperty("生产状态")
    private String productionState;

}
