package com.zoeeasy.cloud.charge.platform;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingCurrentChargeInfoRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleForUserParkingRecordRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingCurrentChargeInfoResultDto;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/2 0002
 */
public interface PlatformChargeService {

    /**
     * 用户停车记录模块查询收费规则
     *
     * @param requestDto
     * @return
     */
    List<ChargeRuleInfoViewResultDto> getChargeRuleForUserParkingRecord(ParkingChargeRuleForUserParkingRecordRequestDto requestDto);

    /**
     * 带租户id获取停车场收费信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingCurrentChargeInfoResultDto> getParkingCurrentChargeInfo(ParkingCurrentChargeInfoRequestDto requestDto);

}
