package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/19 0019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "hikvisionVehicleRecordGetRequestDto")
public class HikvisionVehicleRecordGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
    /**
     * 过车记录唯一ID
     */
    @ApiModelProperty(value = "过车记录唯一ID")
    private String unid;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    private String parkCode;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    private String plateNo;

    /**
     * 过车方向
     */
    @ApiModelProperty("过车方向")
    private Integer direct;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    private Date passTime;
}
