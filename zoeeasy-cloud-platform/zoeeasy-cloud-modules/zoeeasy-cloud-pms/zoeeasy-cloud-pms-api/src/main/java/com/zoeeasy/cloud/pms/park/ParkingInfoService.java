package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.*;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingImageGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingImageViewResultDto;


/**
 * 停车场管理服务
 * Created by song on 2018/9/18.
 */
public interface ParkingInfoService {

    /**
     * 添加停车场
     *
     * @param requestDto
     * @return
     */
    ResultDto addParking(ParkingAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 添加无租户停车场-临时迁移数据使用
     *
     * @param requestDto NonTenantParkingAddRequestDto
     * @return ResultDto
     */
    ResultDto addNonTenantParking(NonTenantParkingAddRequestDto requestDto);

    /**
     * 删除停车场
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParking(ParkingDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改停车场
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParking(ParkingUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取停车场
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingResultDto> getParking(ParkingGetRequestDto requestDto);

    /**
     * 获取停车场
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingResultDto> getParkingInfo(ParkingGetRequestDto requestDto);

    /**
     * 根据停车场code获取停车场信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingResultDto> getParkingInfoByCode(ParkingByCodeGetRequestDto requestDto);

    /**
     * 分页获取停车场列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingListResultDto> getParkingPagedList(ParkingQueryPagedRequestDto requestDto);

    /**
     * 获取停车场列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingListGetResultDto> getParkingList(ParkingListGetRequestDto requestDto);

    /**
     * 获取停车场图像
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingImageViewResultDto> getParkingImageList(ParkingImageGetRequestDto requestDto);

    /**
     * 根据id获取停车场区域
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingByIdResultDto> getParkingById(ParkingInfoGetByIdRequestDto requestDto);

    /**
     * 申请停车场上下线
     *
     * @return
     */
    ResultDto applyRunStatus(ApplyRunStatusRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 撤销上下架申请
     *
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    ResultDto repealApply(RepealApplyRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改可用车位
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    ResultDto updateLotAvailable(LotAvailableUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 生成停车场二维码
     *
     * @param requestDto ParkQRCodeRequestDto
     * @return ObjectResultDto
     */
    ObjectResultDto<ParkQRCodeResultDto> getQRCodeUrl(ParkQRCodeRequestDto requestDto);

    /**
     * 获取小程序二维码token
     *
     * @return
     */
    ObjectResultDto<WeChatSmallAppQrcodeResultDto> getToken();

}
