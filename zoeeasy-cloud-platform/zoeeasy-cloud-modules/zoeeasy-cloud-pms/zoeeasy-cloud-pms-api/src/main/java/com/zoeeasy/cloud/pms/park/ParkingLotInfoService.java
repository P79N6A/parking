package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.MagneticDetectorByParkingLotQueryPageResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotPagedResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotInfoGetByCodeRequestDto;

/**
 * 车位管理服务
 *
 * @author walkman
 * @date 2017-12-11
 */
public interface ParkingLotInfoService {

    /**
     * 新增车位
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto addParkingLot(ParkingLotAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除车位
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto deleteParkingLot(ParkingLotDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 更新车位
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateParkingLot(ParkingLotUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取车位
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingLotResultDto> getParkingLot(ParkingLotGetRequestDto requestDto);

    /**
     * 分页获取车位列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<ParkingLotPagedResultDto> getParkingLotPagedList(ParkingLotQueryPagedResultRequestDto requestDto);

    /**
     * 获取车位列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<ParkingLotResultDto> getParkingLotList(ParkingLotListGetRequestDto requestDto);

    /**
     * 根据泊位编号获取泊位列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingLotResultDto> getParkingLotByCodeList(ParkingLotByCodeListGetRequestDto requestDto);

    /**
     * 分页查询泊位配置列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticDetectorByParkingLotQueryPageResultDto> getMagneticDetectorByParkingLotPagedList(MagneticDetectorByParkingLotQueryPageRequestDto requestDto);

    /**
     * 根据泊位code获取泊位
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingLotResultDto> getParkingLotByCode(ParkingLotInfoGetByCodeRequestDto requestDto);

    /**
     * 根据泊位code获取泊位(无租户)
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingLotResultDto> getParkingLotByLotCode(ParkingLotInfoGetByCodeRequestDto requestDto);
}
