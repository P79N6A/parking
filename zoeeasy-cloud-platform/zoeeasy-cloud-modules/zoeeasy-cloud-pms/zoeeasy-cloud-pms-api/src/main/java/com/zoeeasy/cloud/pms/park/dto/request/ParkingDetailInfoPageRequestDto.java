package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 分页获取停车场详情信息
 *
 * @Date: 2018/11/15
 * @author: lhj
 */
@Data
@ApiModel(value = "ParkingDetailInfoPageRequestDto", description = "根据泊位编号查询停车信息请求参数")
@EqualsAndHashCode(callSuper = false)
public class ParkingDetailInfoPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

}