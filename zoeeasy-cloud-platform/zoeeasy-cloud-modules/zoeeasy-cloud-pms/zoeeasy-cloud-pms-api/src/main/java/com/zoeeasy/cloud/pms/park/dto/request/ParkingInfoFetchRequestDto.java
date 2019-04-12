package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.RequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全国停车场数据获取
 */
@Data
@ApiModel(value = "ParkingInfoFetchRequestDto", description = "全国停车场数据获取")
public class ParkingInfoFetchRequestDto extends RequestDto {

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

    @ApiModelProperty(value = "0为拉取所有，否则拉取count*50固定数目即停止", required = true)
    private Integer count;

}
