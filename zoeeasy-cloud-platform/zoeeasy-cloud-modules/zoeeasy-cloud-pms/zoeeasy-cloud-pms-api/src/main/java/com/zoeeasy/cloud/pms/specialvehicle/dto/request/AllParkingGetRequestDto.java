package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 是否全部停车场获取请求参数
 *
 * @date: 2018/10/20.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AllParkingGetRequestDto", description = "是否全部停车场获取请求参数")
public class AllParkingGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
