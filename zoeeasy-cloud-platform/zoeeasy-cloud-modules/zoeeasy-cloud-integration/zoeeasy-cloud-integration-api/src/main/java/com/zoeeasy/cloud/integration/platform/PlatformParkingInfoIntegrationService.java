package com.zoeeasy.cloud.integration.platform;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointmentParkingDetailRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointmentAroundParkingGetRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointmentParkingDetailViewResultDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointmentParkingResultDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAndNotifyRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAroundGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingDetailGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingExtendInfoGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.result.*;
import com.zoeeasy.cloud.pms.park.dto.request.AppointmentParkingCountRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AppointmentParkingCountResultDto;


/**
 * 平台停车场信息集成服务
 *
 * @author walkman
 */
public interface PlatformParkingInfoIntegrationService {

    /**
     * 获取附近的停车场
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAroundViewResultDto> getAroundParking(ParkingAroundGetRequestDto requestDto);

    /**
     * 获取附近的停车场基本信息列表
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAroundBaseViewResultDto> getAroundParkingBaseViewList(ParkingAroundGetRequestDto requestDto);

    /**
     * 获取附近的停车场的扩展信息
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAroundItemExtendViewResultDto> getAroundParkingExtendView(ParkingExtendInfoGetRequestDto requestDto);

    /**
     * 获取停车场详情
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingDetailViewResultDto> getParkingDetail(ParkingDetailGetRequestDto requestDto);

    /**
     * 可预约停车场数量
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointmentParkingCountResultDto> getAppointmentParkingCount(AppointmentParkingCountRequestDto requestDto);

    /**
     * 分页获取可预约停车场列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<AppointmentParkingResultDto> getAppointmentParkingList(AppointmentAroundParkingGetRequestDto requestDto);

    /**
     * 可预约停车场详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointmentParkingDetailViewResultDto> getAppointmentParkingDetail(AppointmentParkingDetailRequestDto requestDto);

    /**
     * 获取可用车位和消息条数
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingAndNotifyResultDto> getParkingNotify(ParkingAndNotifyRequestDto requestDto) throws ValidationException, BusinessException;

}
