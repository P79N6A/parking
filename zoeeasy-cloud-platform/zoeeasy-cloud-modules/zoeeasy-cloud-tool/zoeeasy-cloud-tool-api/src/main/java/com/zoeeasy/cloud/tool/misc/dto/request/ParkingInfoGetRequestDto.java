package com.zoeeasy.cloud.tool.misc.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 停车场信息请求参数
 *
 * @author walkman
 */
@ApiModel(value = "PlateNumberCheckRequestDto", description = "停车场信息请求参数")
public class ParkingInfoGetRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 必选	关键字，或关键字的首字母、拼音
     */
    @ApiModelProperty(value = "关键字，或关键字的首字母、拼音", required = true)
    private String keyword;

    /**
     * 必选	检索区域名称，可输入城市名或省份名或全国
     */
    @ApiModelProperty(value = "关键字，或关键字的首字母、拼音", required = true)
    private String region;

    /**
     * 可选	分页页码，1为第一页。默认值为1
     */
    @ApiModelProperty(value = "分页页码", hidden = true)
    private Integer pageNum;

    /**
     * 可选	每页记录数，最大值为50。默认值为10，超过50则按照50处理
     */
    @ApiModelProperty(value = "每页记录数", hidden = true)
    private Integer pageSize;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
