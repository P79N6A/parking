package com.zhuyitech.parking.tool.dto.result.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zhuyitech.parking.common.constant.Const;
import com.zhuyitech.parking.tool.bean.traffic.TrafficRestrictionInfoBean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 限行返回结果视图
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficRestrictionInfoViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    @ApiModelProperty("日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private String date;

    /**
     * 星期
     */
    @ApiModelProperty("星期")
    private String week;

    /**
     * 城市拼音
     */
    @ApiModelProperty("城市拼音")
    private String city;

    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String cityname;

    /**
     * 罚款描述
     */
    @ApiModelProperty("罚款描述")
    private String fine;

    /**
     * 特殊描述
     */
    @ApiModelProperty("特殊描述")
    private String remarks;

    /**
     * 是否限行 1:是
     */
    @ApiModelProperty("是否限行 1:是 0否")
    private Integer isxianxing;

    /**
     * 节假日
     */
    @ApiModelProperty("节假日")
    private String holiday;

    /**
     * 限行尾号
     */
    @ApiModelProperty("限行尾号")
    private Integer[] xxweihao;

    /**
     * 限行信息
     */
    @ApiModelProperty("限行信息")
    private List<TrafficRestrictionInfoBean> des;

}
