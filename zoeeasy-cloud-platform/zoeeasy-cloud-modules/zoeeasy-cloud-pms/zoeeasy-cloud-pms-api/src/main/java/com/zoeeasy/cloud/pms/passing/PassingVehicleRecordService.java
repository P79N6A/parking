package com.zoeeasy.cloud.pms.passing;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.passing.dto.request.*;
import com.zoeeasy.cloud.pms.passing.dto.result.PassingVehicleRecordViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingRecordUpdateRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingVehicleRecordCreateRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingVehicleRecordGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PassingVehicleRecordCreatedResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PassingVehicleRecordResultDto;

/**
 * @author AkeemSuper
 * @Description: 平台过车记录服务
 * @Date: 2018/2/28 0028
 */
public interface PassingVehicleRecordService {

    /**
     * 分页查询过车记录
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<PassingVehicleRecordViewResultDto> getPassVehicleRecordListPage(PassingVehicleRecordQueryPageRequestDto requestDto);

    /**
     * 后台根据passNo获取过车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleRecordViewResultDto> getPassRecordByPassNo(PassingVehicleRecordGetByPassNoRequestDto requestDto);

    /**
     * 添加过车记录备注
     *
     * @param requestDto
     * @return
     */
    ResultDto addPassVehicleRecordRemark(PassVehicleRecordAddRemarkRequestDto requestDto);

    /**
     * 人工校对过车记录
     *
     * @param requestDto
     * @return
     */
    ResultDto proofreadPassVehicleRecord(PassVehicleRecordProofreadRequestDto requestDto);

    /**
     * 带租户根据过车记录号获取过车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleRecordResultDto> getByPassNo(PassingVehicleRecordGetRequestDto requestDto);

    /**
     * 带租户根据过车记录流水号更新过车记录
     *
     * @param requestDto
     */
    ResultDto updatePassVehicleRecord(PassingRecordUpdateRequestDto requestDto);

    /**
     * 带租户添加过车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleRecordCreatedResultDto> savePassRecord(PassingVehicleRecordCreateRequestDto requestDto);

    /**
     * 获取异常过车记录
     */
    ListResultDto<PassingVehicleRecordResultDto> getExceptionPassRecord(PassVehicleGetExceptionRequestDto requestDto);

    /**
     * 修改过车记录泊位
     *
     * @param requestDto
     * @return
     */
    ResultDto updatePassVehicleRecordByCode(PassVehicleRecordByPassingRequestDto requestDto);

    /**
     * 无租户根据过车记录号获取过车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleRecordResultDto> getPassVehicleByPassNo(PassingVehicleRecordGetRequestDto requestDto);
}
