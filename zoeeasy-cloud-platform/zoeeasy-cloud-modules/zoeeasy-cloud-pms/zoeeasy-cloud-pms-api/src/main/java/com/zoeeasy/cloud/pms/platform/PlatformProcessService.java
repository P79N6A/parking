package com.zoeeasy.cloud.pms.platform;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingRecordHistorySaveRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.result.*;

/**
 * 平台停车处理服务
 *
 * @author AkeemSuper
 * @date 2018/10/31 0031
 */
public interface PlatformProcessService {

    /**
     * 找到车辆的最后一次在场记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingVehicleRecordResultDto> findLastParkingRecord(ParkingVehicleRecordFindLastRecordRequestDto requestDto);

    /**
     * 保存在场车辆记录
     *
     * @param requestDto
     * @return
     */
    ResultDto saveParkingVehicleRecord(ParkingVehicleRecordSaveRequestDto requestDto);

    /**
     * 删除在场车辆记录
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkVehicleRecord(ParkingVehicleRecordDeleteRequestDto requestDto);

    /**
     * 根据入车记录获取在场车辆
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
     * 保存历史停车记录
     *
     * @param requestDto
     * @return
     */
    ResultDto saveParkingRecordHistory(ParkingRecordHistorySaveRequestDto requestDto);

    /**
     * 获取停车记录图片
     */
    ListResultDto<ParkingImageViewResultDto> getParkingRecordImageList(ParkingRecordImageGetRequestDto requestDto);

    /**
     * 保存停车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<SaveParkingRecordResultDto> saveParkingRecord(ParkingRecordAddRequestDto requestDto);

    /**
     * 通过过车记录流水号 停车场id 泊位ID获取停车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingRecordResultDto> getByIntoRecordNo(ParkingRecordGetByIntoRecordNoRequestDto requestDto);

    /**
     * 更新停车记录
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingRecord(ParkingRecordUpdateIntegrationRequestDto requestDto);

    /**
     * 获取过车时间前后的停车记录
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingRecordResultDto> getByPassTime(ParkingRecordGetByPassTimeRequestDto requestDto);

    /**
     * 通过停车记录流水号获取停车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingRecordResultDto> getByRecordNo(ParkingRecordGetByRecordNoRequestDto requestDto);

    /**
     * 根据过车记录流水号获取过车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleRecordResultDto> getPassVehicleRecord(PassingVehicleRecordGetRequestDto requestDto);

    /**
     * 添加过车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PassingVehicleRecordCreatedResultDto> savePassingVehicleRecord(PassingVehicleRecordCreateRequestDto requestDto);

    /**
     * 根据过车记录流水号更新过车记录
     *
     * @param requestDto
     */
    ResultDto updatePassVehicleRecord(PassingRecordUpdateRequestDto requestDto);

    /**
     * 保存停车过车记录图片
     *
     * @param requestDto
     * @return
     */
    ResultDto saveParkingRecordImage(ParkingRecordImageSaveRequestDto requestDto);

    /**
     * 根据泊位删除在停车辆
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkVehicleByLot(ParkVehicleDeleteByLotRequestDto requestDto);

    /**
     * 根据三方记录号获取过车记录
     */
    ObjectResultDto<PassingVehicleRecordResultDto> getPassRecordByThirdNo(PassRecordGetByThirdNoRequestDto requestDto);

    /**
     * 根据三方记录号和获停车场编号取过车记录
     */
    ObjectResultDto<PassingVehicleRecordResultDto> getPassRecordByThirdNoAndCloudParkingCode(PassRecordGetByThirdNoRequestDto requestDto);

    /**
     * 根据停车记录号查询停车记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingRecordResultDto> getParkingRecordByRecordNo(ParkingRecordQueryByRecordNoRequestDto requestDto);
}
