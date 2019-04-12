package com.zoeeasy.cloud.gather.hikvision.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PassingImageFetchResultDto extends BaseDto {

    private List<String> images;
}
