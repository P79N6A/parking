package com.zhuyitech.parking.data.dto.result.park;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 停车图像结果模型
 *
 * @author walkman
 */
@ApiModel(value = "ParkingImageViewResultDto", description = "停车图像结果模型")
public class ParkingImageViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
