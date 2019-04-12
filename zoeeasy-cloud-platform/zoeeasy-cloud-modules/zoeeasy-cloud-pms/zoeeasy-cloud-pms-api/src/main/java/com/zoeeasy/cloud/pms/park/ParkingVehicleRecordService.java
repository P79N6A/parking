package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.park.dto.request.MyPlateNumberParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingVehicleRecordGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingVehicleRecordQueryPageRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.result.*;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingVehicleRecordLotRequestDto;

/**
 * 停车场在停车辆服务
 *
 * @Date: 2018/3/1
 * @author: yuzhicheng
 */
public interface ParkingVehicleRecordService {

    /**
     * 分页获取停车场在停车辆
     *
     * @param requestDto 分页获取停车场在停车辆请求参数
     * @return PagedResultDto
     */
    PagedResultDto<ParkingVehicleRecordViewResultDto> getParkingVehicleRecordListPage(ParkingVehicleRecordQueryPageRequestDto requestDto);

    /**
     * 获取停车场在停车辆
     *
     * @param requestDto 获取停车场在停车辆请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<ParkingVehicleRecordViewResultDto> getParkingVehicleRecord(ParkingVehicleRecordGetRequestDto requestDto);

    /**
     * 带租户根据入车记录获取在场车辆
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingVehicleRecordResultDto> getByIntoRecordNo(ParkingVehicleRecordGetByIntoPassNoRequestDto requestDto);

    /**
     * 更新在停车辆
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingVehicle(ParkingVehicleRecordUpdateRequestDto requestDto);

    /**
     * 删除在场车辆记录
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkVehicleRecord(ParkingVehicleRecordDeleteRequestDto requestDto);

    /**
     * 保存在场车辆记录
     *
     * @param requestDto
     * @return
     */
    ResultDto saveParkingVehicleRecord(ParkingVehicleRecordSaveRequestDto requestDto);

    /**
     * 根据车牌/车位查找
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<PlateNumberMyCloudResultDto> getParkingGuidance(ParkingGuidanceParamDto requestDto);

    /**
     * 获取我的车牌停车场在停车辆请求参数
     *
     * @param requestDto 获取我的车牌停车场在停车辆请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<ParkingVehicleRecordViewResultDto> getMyPlateNumberParkingVehicleRecord(MyPlateNumberParkingGetRequestDto requestDto);

    /**
     * 根据车牌获取泊位Id
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingVehicleRecordLotResultDto> getCodeId(ParkingVehicleRecordLotRequestDto requestDto);

    /**
     * 修改在停车辆停车场
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingVehicleRecord(ParkingRecordQueryByIntoRecordNoRequestDto requestDto);

    /**
     *  获取楼层
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingFloorResultDto> getParkingFloor(ParkingFloorRequestDto requestDto);
}
