package com.zoeeasy.cloud.pms.platform;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.park.dto.request.AppointmentParkingCountRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingChargeInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingCurrentGetByIdRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingDetailInfoPageRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetByCodeRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingInfoByParkingLotCodeQueryPageRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingInfoUpdateRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotByParkingIdListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AppointParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.AppointmentParkingCountResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingCurrentInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingDetailInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoByParkingLotCodeQueryPageResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotByParkingIdResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingAppointInfoResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingChargeInfoResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingCurrentInfoGetByParkingIdResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingGetLotAvailableRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingImageViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingInfoAroundResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingLocationResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingLotStatusResultDto;

/**
 * 平台停车场服务
 *
 * @author walkman
 */
public interface PlatformParkingInfoService {

    //-----------------parkingInfo-----------------

    /**
     * 通过海康停车场Id获取停车场信息
     *
     * @param requestDto ParkingInfoGetByHikParkIdRequestDto
     * @return ObjectResultDto<ParkingInfoResultDto>
     */
    ObjectResultDto<ParkingInfoResultDto> getParkInfoByHikParkId(ParkingInfoGetByHikParkIdRequestDto requestDto);

    /**
     * 通过客户端编号获取停车场
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingInfoResultDto> getParkInfoByCode(ParkingInfoGetByCodeRequestDto requestDto);

    /**
     * 处理过车数据
     *
     * @param requestDto ParkingInfoGetRequestDto
     * @return ObjectResultDto<ParkingInfoResultDto>
     */
    ObjectResultDto<ParkingInfoResultDto> getParkInfoById(ParkingInfoGetRequestDto requestDto);

    /**
     * 获取停车场地址
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<String> getParkingAddressByParkId(ParkingGetRequestDto requestDto);

    /**
     * 获取停车场
     *
     * @param requestDto ParkingGetRequestDto
     * @return ObjectResultDto<ParkingResultDto>
     */
    ObjectResultDto<ParkingResultDto> getParkingApp(ParkingGetRequestDto requestDto);

    /**
     * 可预约停车场数量
     *
     * @param requestDto AppointmentParkingCountRequestDto
     * @return ObjectResultDto<AppointmentParkingCountResultDto>
     */
    ObjectResultDto<AppointmentParkingCountResultDto> getAppointmentParkingCount(AppointmentParkingCountRequestDto requestDto);

    /**
     * 通过位置查询
     *
     * @param requestDto AppointmentParkingListGetRequestDto
     * @return ListResultDto<AppointParkingInfoResultDto>
     */
    ListResultDto<AppointParkingInfoResultDto> selectAppointmentParkingList(AppointmentParkingListGetRequestDto requestDto);

    /**
     * 获取停车场图像
     *
     * @param requestDto ParkingImageGetRequestDto
     * @return ListResultDto<ParkingImageViewResultDto>
     */
    ListResultDto<ParkingImageViewResultDto> getParkingImageList(ParkingImageGetRequestDto requestDto);

    /**
     * 获取停车场地址
     *
     * @param requestDto ParkingLocationGetRequestDto
     * @return ObjectResultDto<ParkingLocationResultDto>
     */
    ObjectResultDto<ParkingLocationResultDto> getParkingAddress(ParkingLocationGetRequestDto requestDto);

    /**
     * 获取附近的停车场
     *
     * @param requestDto ParkingInfoAroundGetRequestDto
     * @return ListResultDto<ParkingInfoAroundResultDto>
     */
    ListResultDto<ParkingInfoAroundResultDto> getAroundParkingInfoList(ParkingInfoAroundGetRequestDto requestDto);

    /**
     * 通过停车场id获取停车场收费信息
     *
     * @param requestDto ParkingChargeInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingChargeInfoResultDto>
     */
    ObjectResultDto<ParkingChargeInfoResultDto> getParkChargeInfoByParkingId(ParkingChargeInfoGetByParkingIdRequestDto requestDto);

    /**
     * 通过客户端编号获取停车场
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingInfoResultDto> getParkInfoByLocalCode(ParkingInfoGetByLocalCodeRequestDto requestDto);

    /**
     * 根据停车场Id查询在停车辆
     *
     * @param resultDto
     * @return
     */
    ObjectResultDto<ParkingCurrentInfoResultDto> selectCurrentInfoByParkingId(ParkingCurrentGetByIdRequestDto resultDto);

    /**
     * 通过停车场收费信息id获取停车场收费信息
     *
     * @param requestDto ParkingChargeInfoGetByIdRequestDto
     * @return ObjectResultDto<ParkingChargeInfoResultDto>
     */
    ObjectResultDto<ParkingChargeInfoResultDto> getParkChargeInfoById(ParkingChargeInfoGetByIdRequestDto requestDto);

    /**
     * 根据parkingId获取停车场预约信息
     *
     * @param requestDto ParkingAppointInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingAppointInfoResultDto>
     */
    ObjectResultDto<ParkingAppointInfoResultDto> getParkingAppointmentInfoByParkingId(ParkingAppointInfoGetByParkingIdRequestDto requestDto);

    /**
     * 根据parkingId获取停车场预约信息 (无租户)
     *
     * @param requestDto ParkingAppointInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingAppointInfoResultDto>
     */
    ObjectResultDto<ParkingAppointInfoResultDto> getAppointmentInfoByParkingId(ParkingAppointInfoGetByParkingIdRequestDto requestDto);

    /**
     * 获取停车场当前余位数量
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingCurrentInfoGetByParkingIdResultDto> getParkingCurrentInfoByParkingId(ParkingCurrentInfoGetByParkingIdRequestDto requestDto);

    /**
     * 根据泊位编号获取泊位信息
     *
     * @param requestDto ParkingLotInfoGetByCodeRequestDto
     * @return ObjectResultDto<ParkingLotResultDto>
     */
    ObjectResultDto<ParkingLotResultDto> getParkingLotByCode(ParkingLotInfoGetByCodeRequestDto requestDto);

    /**
     * 处理过车记录时获车位信息
     *
     * @param requestDto ParkingLotInfoGetForPassVehicleRequestDto
     * @return ObjectResultDto<ParkingLotResultDto>
     */
    ObjectResultDto<ParkingLotResultDto> getParkingLotForPassVehicle(ParkingLotInfoGetForPassVehicleRequestDto requestDto);

    /**
     * 客户端获取泊位信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingLotResultDto> getParkingLotByLocal(ParkingLotInfoGetByLocalRequestDto requestDto);

    /**
     * 处理过车记录----根据停车场id和泊位id获取泊位信息
     *
     * @param requestDto ParkingLotInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingLotResultDto>
     */
    ObjectResultDto<ParkingLotResultDto> getParkingLotByParkingId(ParkingLotInfoGetByParkingIdRequestDto requestDto);

    /**
     * 通过停车场id和泊位Id获取泊位状态
     *
     * @param requestDto ParkingLotStatusGetRequestDto
     * @return ObjectResultDto<ParkingLotStatusResultDto>
     */
    ObjectResultDto<ParkingLotStatusResultDto> findByParkingIdAndParkingLotId(ParkingLotStatusGetRequestDto requestDto);

    /**
     * 变更停车场可用车位数及变更车位状态空闲
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto increaseParkingLotAvailable(ParkingLotIncreaseRequestDto requestDto);

    /**
     * 变更停车场可用车位数及变更车位状态占用
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto decreaseParkingLotAvailable(ParkingLotDecreaseRequestDto requestDto);

    /**
     * 根据是否预约更新可预约车位
     */
    ResultDto updateLotAppointAvailable(ParkingLotAppointUpdateRequestDto requestDto);

    /**
     * 分页查询根据泊位编号查询停车信息
     *
     * @param request
     * @return
     */
    PagedResultDto<ParkingInfoByParkingLotCodeQueryPageResultDto> getParkingInfoByParkingLotCode(ParkingInfoByParkingLotCodeQueryPageRequestDto request);

    /**
     * 根据停车场Id获取泊位列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingLotByParkingIdResultDto> getParkingLotByByParkingIdList(ParkingLotByParkingIdListGetRequestDto requestDto);

    /**
     * 获取停车场(无租户)
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingResultDto> getParkingInfoById(ParkingGetRequestDto requestDto);

    /**
     * 获取停车场(无租户)
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingResultDto> getParkingInfoByCode(ParkingGetByCodeRequestDto requestDto);

    /**
     * 获取停车场可用车位数
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingResultDto> getParkingLotAvailable(ParkingByParkingIdGetRequestDto requestDto);

    /**
     * 修改可用车位数
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingLotAvailable(ParkingGetLotAvailableRequestDto requestDto);

    /**
     * 根据ID获取停车场预约信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointInfoResultDto> getAppointmentInfoById(ParkingAppointInfoGetByIdRequestDto requestDto);

    /**
     * 获取停车场地址
     *
     * @param requestDto ParkingLocationGetRequestDto
     * @return ObjectResultDto<ParkingLocationResultDto>
     */
    ObjectResultDto<ParkingLocationResultDto> getParkingAddressForCloud(ParkingLocationGetRequestDto requestDto);

    /**
     * 查询停车场信息结果是否存在
     *
     * @param request ParkingInfoExistDto
     * @return 是否存在
     */
    ResultDto parkingInfoExist(ParkingInfoExistDto request);

    /**
     * 分页获取停车场详情信息
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingDetailInfoResultDto> getParkingDetailInfoList(ParkingDetailInfoPageRequestDto requestDto);

    /**
     * 更新停车场基本信息
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingInfo(ParkingInfoUpdateRequestDto requestDto);

}
