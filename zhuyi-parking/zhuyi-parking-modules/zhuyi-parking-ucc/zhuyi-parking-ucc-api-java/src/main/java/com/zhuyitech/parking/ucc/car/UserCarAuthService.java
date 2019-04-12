package com.zhuyitech.parking.ucc.car;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.car.request.*;
import com.zhuyitech.parking.ucc.car.result.UserCarAuthResultDto;

/**
 * 用户车辆认证
 *
 * @author AkeemSuper
 * @date 2018/4/19 0019
 */
public interface UserCarAuthService {

    /**
     * 用户添加车辆认证
     *
     * @param requestDto UserCarAuthAddRequestDto
     * @return ResultDto
     */
    ResultDto addUserCarAuth(UserCarAuthAddRequestDto requestDto);

    /**
     * 用户修改车辆认证
     *
     * @param requestDto UserCarAuthUpdateRequestDto
     * @return ResultDto
     */
    ResultDto updateUserCarAuth(UserCarAuthUpdateRequestDto requestDto);

    /**
     * 分页获取车辆认证申请列表
     */
    PagedResultDto<UserCarAuthResultDto> getUserCarAuthPage(UserCarAuthQueryPageRequestDto requestDto);

    /**
     * 获取车辆认证信息
     */
    ObjectResultDto<UserCarAuthResultDto> getUserCarAuth(UserCarAuthGetRequestDto requestDto);

    /**
     * 车辆认证申请审核
     *
     * @param requestDto requestDto
     */
    ResultDto approveCar(UserCarAuthApproveRequestDto requestDto);

    /**
     * 车辆认证审核信息
     *
     * @return ComboboxItemDto
     */
    ListResultDto<ComboboxItemDto> getRejectReason();
}
