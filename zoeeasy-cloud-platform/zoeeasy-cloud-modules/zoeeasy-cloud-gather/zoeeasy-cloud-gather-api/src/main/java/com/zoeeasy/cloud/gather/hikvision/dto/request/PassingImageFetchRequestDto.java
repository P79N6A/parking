package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 海康过车图像请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "hikVehicleRecordUpDateRequestDto", description = "海康平台停车账单请求参数")
public class PassingImageFetchRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hikPassingUuid;
    
    private String parkCode;
}
