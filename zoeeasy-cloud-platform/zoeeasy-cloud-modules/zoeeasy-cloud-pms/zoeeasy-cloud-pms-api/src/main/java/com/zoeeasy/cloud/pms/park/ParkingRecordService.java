package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingRecordPagedResultRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingRecordViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordAddRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordQueryByIntoRecordNoRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordUpdateIntegrationRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.SaveParkingRecordResultDto;

/**
 * 平台停车记录服务
 *
 * @author walkman
 */
public interface ParkingRecordService {

    /**
     * 分页查询停车记录
     */
    PagedResultDto<ParkingRecordViewResultDto> getParkingRecordPageList(ParkingRecordPagedResultRequestDto requestDto);

    /**
     * 更新停车记录
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingRecord(ParkingRecordUpdateIntegrationRequestDto requestDto);

    /**
     * 保存停车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<SaveParkingRecordResultDto> saveParkingRecord(ParkingRecordAddRequestDto requestDto);

    /**
     * 根据入车流水号修改泊位
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingRecordByIntoRecordNo(ParkingRecordQueryByIntoRecordNoRequestDto requestDto);
}
