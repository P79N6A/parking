package com.zhuyitech.parking.pms.dto.request.car;


import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加车型请求参数
 *
 * @author walkman
 */
@ApiModel(value = "CarBrandAddRequestDto", description = "添加车型请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandAddRequestDto extends SessionDto {

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
     * 图标
     */
    @ApiModelProperty("logo")
    private String logo;

    /**
     * 深度 1品牌 2子公司 3车型 4具体车型
     */
    @ApiModelProperty("depth")
    private Integer depth;

    /**
     * 价格字符
     */
    @ApiModelProperty("价格字符")
    private String price;

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
