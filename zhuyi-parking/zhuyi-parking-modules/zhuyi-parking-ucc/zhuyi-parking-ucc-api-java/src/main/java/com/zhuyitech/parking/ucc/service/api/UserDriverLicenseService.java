package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.driver.LicenseScoreQueryRequestDto;
import com.zhuyitech.parking.ucc.dto.request.driverlicense.*;
import com.zhuyitech.parking.ucc.dto.result.UserDriverLicenseExistResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserDriverLicenseResultDto;

/**
 * 用户驾驶证
 *
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
public interface UserDriverLicenseService {

    /**
     * 获取驾驶人准驾车型列表
     *
     * @return ListResultDto
     */
    ListResultDto<ComboboxItemDto> getDriverClassList();

    /**
     * 用户驾驶证是否存在
     *
     * @param requestDto 请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<UserDriverLicenseExistResultDto> existUserDriverLicense(UserDriverLicenseExistRequestDto requestDto);

    /**
     * 增加用户驾驶证信息
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto addUserDriverLicense(UserDriverLicenseAddRequestDto requestDto);

    /**
     * 修改用户驾驶证信息
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto updateUserDriverLicense(UserDriverLicenseUpdateRequestDto requestDto);

    /**
     * 删除用户驾驶证信息
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto deleteUserDriverLicense(UserDriverLicenseDeleteRequestDto requestDto);

    /**
     * 根据用户ID获取用户驾驶证信息
     *
     * @param requestDto 请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<UserDriverLicenseResultDto> getUserDriverLicense(UserDriverLicenseGetRequestDto requestDto);

    /**
     * 本地同步用户驾驶证扣分数
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto updateUserLicense(LicenseScoreQueryRequestDto requestDto);

}
