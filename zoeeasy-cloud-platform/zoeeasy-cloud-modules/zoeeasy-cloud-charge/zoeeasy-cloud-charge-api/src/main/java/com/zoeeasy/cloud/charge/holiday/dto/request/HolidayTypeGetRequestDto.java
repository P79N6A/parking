package com.zoeeasy.cloud.charge.holiday.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/9/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HolidayTypeGetRequestDto", description = "节假日类型请求参数")
public class HolidayTypeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
}
