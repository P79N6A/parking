package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据泊位编号查询停车信息请求参数
 *
 * @Date: 2018/11/15
 * @author: lhj
 */
@Data
@ApiModel(value = "ParkingInfoByParkingLotCodeQueryPageRequestDto", description = "根据泊位编号查询停车信息请求参数")
@EqualsAndHashCode(callSuper = false)
public class ParkingInfoByParkingLotCodeQueryPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位number
     */
    @ApiModelProperty(value = "泊位number")
    private String number;
}
