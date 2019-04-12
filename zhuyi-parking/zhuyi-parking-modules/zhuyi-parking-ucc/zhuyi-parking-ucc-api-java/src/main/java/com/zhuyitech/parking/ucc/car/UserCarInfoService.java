package com.zhuyitech.parking.ucc.car;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.car.request.*;
import com.zhuyitech.parking.ucc.car.result.*;
import com.zhuyitech.parking.ucc.dto.request.mock.CarAuthImageQueryPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.request.mock.CarAuthImageUpdateRequestDto;
import com.zhuyitech.parking.ucc.dto.request.mock.UserCarImageQueryPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.request.mock.UserCarImageUpdateRequestDto;
import com.zhuyitech.parking.ucc.dto.request.violation.UserCarViolationQueryRequestDto;
import com.zhuyitech.parking.ucc.dto.result.mock.CarAuthImageResultDto;
import com.zhuyitech.parking.ucc.dto.result.mock.UserCarImageResultDto;

/**
 * 用户车辆信息服务
 *
 * @author yuzhicheng
 */
public interface UserCarInfoService {

    /**
     * 获取当前用户车辆信息
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<UserCarViewResultDto> getUserCar(CurrentUserCarGetRequestDto requestDto);

    /**
     * 通过车牌号获取用户车辆信息
     */
    ObjectResultDto<UserCarViewResultDto> getUserCar(UserCarInfoGetByPlateNumberRequestDto requestDto);

    /**
     * 增加车辆认证信息
     */
    ResultDto addUserCar(UserCarInfoAddRequestDto requestDto);

    /**
     * 修改车辆认证信息
     */
    ResultDto updateUserCar(UserCarInfoUpdateRequestDto requestDto);

    /**
     * 根据ID获取车辆信息
     */
    ObjectResultDto<UserCarInfoResultDto> getUserPlateNumberList(UserCarInfoGetRequestDto requestDto);

    /**
     * 获取当前用户车辆列表
     */
    ListResultDto<UserCarViewResultDto> getUserCarList(UserPlateNumberListGetRequestDto requestDto);

    /**
     * 获取当前用户所有的车辆信息
     *
     * @param requestDto requestDto
     */
    ListResultDto<UserPlateNumberResultDto> getUserPlateNumberList(UserPlateNumberListGetRequestDto requestDto);

    /**
     * 获取用户名下所有的车牌
     *
     * @param requestDto requestDto
     * @return UserCarPlateNumberResultDto
     */
    ListResultDto<UserCarPlateNumberResultDto> userPlateNumberList(UserPlateNumberListGetRequestDto requestDto);

    /**
     * 获取用户名下所有的车牌
     *
     * @param userId requestDto
     * @return UserCarPlateNumberResultDto
     */
    ListResultDto<UserCarPlateNumberMyResultDto> getPlateNumbers(String userId);

    /**
     * 获取当前用户所有的车辆信息
     *
     * @param requestDto requestDto
     */
    ListResultDto<UserCarSummaryViewResultDto> getUserCarSummaryList(UserCarSummaryViewListGetRequestDto requestDto);

    /**
     * 查询用户车辆违章记录
     *
     * @param requestDto requestDto
     */
    ObjectResultDto<UserCarViolationQueryResultDto> queryCarViolations(UserCarViolationQueryRequestDto requestDto);

    /**
     * 查询用户是否有绑定的车辆
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    ObjectResultDto<UserHasCarResultDto> userHasCar(UserHasCarRequestDto requestDto);

    /**
     * 查询用户车辆个数
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    ObjectResultDto<UserCarCountResultDto> userCarCount(UserCarCountRequestDto requestDto);

    /**
     * 查询用户绑定的车辆是否超过绑定限制
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    ObjectResultDto<UserCarExceedLimitResultDto> userCarExceedLimit(UserCarExceedLimitRequestDto requestDto);

    /**
     * 用户解绑车辆
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto userUnbindCar(UserCarInfoUnBindRequestDto requestDto);

    /**
     * 用户修改默认车辆
     */
    ResultDto userUpdateDefaultCar(UserCarUpdateDefaultCarRequestDto requestDto);

    /**
     * 车牌颜色
     */
    ListResultDto<ComboboxItemDto> userPlateColor();

    /**
     * 根据限行尾号和车牌前缀获取车辆信息
     *
     * @param requestDto requestDto
     * @return TrafficRestrictionCarInfoResultDto
     */
    PagedResultDto<TrafficRestrictionCarInfoResultDto> getForTrafficRestriction(UserCarInfoGetByTailNumberRequestDto requestDto);

    /**
     * 根据车牌获取用户信息
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<CarOwnerGetResultDto> getCarOwnerList(CarOwnerListGetRequestDto requestDto);

    /**
     * 通过车牌获取车辆信息
     */
    ListResultDto<UserCarInfoResultDto> getCarInfoListByPlateNumber(UserCarInfoGetOnlyByPlateNumberRequestDto requestDto);

    /**
     * 通过多用户ID获取车辆信息列表
     */
    ListResultDto<UserCarViewResultDto> getUserCarByUserId(UserCarInfoGetByUserIdRequestDto requestDto);

    /**
     * 分页获取logo
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<UserCarImageResultDto> getCarImageViewList(UserCarImageQueryPagedResultRequestDto requestDto);

    /**
     * 修改logo的url
     *
     * @param requestDto
     * @return
     */
    ResultDto updateCarImage(UserCarImageUpdateRequestDto requestDto);

    /**
     * 分页获取logo
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<CarAuthImageResultDto> getCarAuthImageViewList(CarAuthImageQueryPagedResultRequestDto requestDto);

    /**
     * 修改logo的url
     *
     * @param requestDto
     * @return
     */
    ResultDto updateCarAuthImage(CarAuthImageUpdateRequestDto requestDto);

    /**
     * 根据id查询用户车辆个数
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    ObjectResultDto<UserCarCountResultDto> selectUserCarCountById(UserCarCountByIdRequestDto requestDto);

    /**
     * 用户车辆是否被解绑
     * @param requestDto UserCarUnbindRequestDto
     * @return UserCarUnbindResultDto
     */
    ObjectResultDto<UserCarUnbindResultDto> carUnbindStatus(UserCarUnbindRequestDto requestDto);
}
