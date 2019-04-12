package com.zhuyitech.parking.data.service.api;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.data.dto.result.PassingVehicleRecordResultDto;

/**
 * @Description: 平台过车记录服务
 * @Autor: AkeemSuper
 * @Date: 2018/2/28 0028
 */
public interface PassingVehicleRecordService {

    /**
     * 迁移数据-分页查询过车记录
     *
     * @param requestDto PagedResultRequestDto
     * @return PassingVehicleRecordResultDto
     */
    PagedResultDto<PassingVehicleRecordResultDto> getCloudPassVehicleRecordListPage(PagedResultRequestDto requestDto);

}
