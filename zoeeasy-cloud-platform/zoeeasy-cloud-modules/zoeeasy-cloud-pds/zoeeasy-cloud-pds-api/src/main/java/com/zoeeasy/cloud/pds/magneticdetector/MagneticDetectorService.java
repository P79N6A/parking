package com.zoeeasy.cloud.pds.magneticdetector;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.inspect.MagneticDetectorGetByInspectRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.*;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.inspect.MagneticDetectorGetByInspectResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.*;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerIdBatchUpdateRequestDto;

/**
 * 设备服务
 *
 * @author lhj
 */
public interface MagneticDetectorService {

    /**
     * 新增设备
     *
     * @param requestDto
     * @return
     */
    ResultDto addMagneticDetector(MagneticDetectorAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 更新设备
     *
     * @param requestDto
     * @return
     */
    ResultDto updateMagneticDetector(MagneticDetectorUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除设备
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteMagneticDetector(MagneticDetectorDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取设备
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<MagneticDetectorByCodeResultDto> getMagneticDetector(MagneticDetectorGetRequestDto requestDto);

    /**
     * 根据ID获取设备
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<MagneticDetectorResultDto> getMagneticDetectorById(MagneticDetectorGetRequestByIdDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取地磁检测器列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<MagneticDetectorByCodeResultDto> getMagneticDetectorList(MagneticDetectorListResultRequestDto requestDto);

    /**
     * 根据停车场泊位查询地磁
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<MagneticDetectorByParkingAndParkingLotResultDto> getMagneticDetectorByParkingAndParkingLot(MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto requestDto);

    /**
     * 分页查询设备列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticDetectorByCodeResultDto> getMagneticDetectorPagedList(MagneticDetectorQueryPagedResultRequestDto requestDto);

    /**
     * 批量更新设备管理器Id
     *
     * @param requestDto
     * @return
     */
    ResultDto batchUpdateMagneticManagerId(MagneticManagerIdBatchUpdateRequestDto requestDto);

    /**
     * 更新设备泊位Id
     *
     * @param requestDto
     * @return
     */
    ResultDto updateDetectorParkingLotId(DetectorParkingLotIdBatchUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页查询未关联管理器设备列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticDetectorRelevanceResultDto> getNotRelevanceMagneticDetectorPagedList(MagneticDetectorNotRelevanceQueryPageRequestDto requestDto);

    /**
     * 分页查询已关联管理器设备列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticDetectorRelevanceResultDto> getRelevanceMagneticDetectorPagedList(MagneticDetectorRelevanceQueryPageRequestDto requestDto);


    /**
     * 分页查询地磁检测器状态
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticDetectorStatusQueryPageResultDto> getMagneticDetectorStatusPagedList(MagneticDetectorStatusQueryPageRequestDto requestDto);

    /**
     * 分页查询未关联泊位设备
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> getNotRelevanceParkingLotMagneticDetectorPagedList(MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto requestDto);

    /**
     * 分页查询已关联泊位设备
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> getRelevanceParkingLotMagneticDetectorPagedList(MagneticDetectorRelevanceParkingLotQueryPageRequestDto requestDto);

    /**
     * 巡检获取地磁检测器
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<MagneticDetectorGetByInspectResultDto> getMagneticDetectorByInspect(MagneticDetectorGetByInspectRequestDto requestDto);

    /**
     * 根据泊位id获取设备
     *
     * @param requestDto
     * @return
     */
    ListResultDto<MagneticDetectorByCodeResultDto> getMagneticDetectorListByParkingLotId(MagneticDetectorParkingLotGetRequestDto requestDto);
}
