package com.zhuyitech.parking.pms.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.pms.dto.request.license.*;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixFirstResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixViewResultDto;

/**
 * @author zwq
 */
public interface LicensePrefixService {

    /**
     * 新增车牌前缀
     *
     * @param requestDto
     * @return
     */
    ResultDto addLicensePrefix(LicensePrefixAddRequestDto requestDto);

    /**
     * 删除车牌前缀
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteLicensePrefix(LicensePrefixDeleteRequestDto requestDto);

    /**
     * 更新车牌前缀
     *
     * @param requestDto
     * @return
     */
    ResultDto updateLicensePrefix(LicensePrefixUpdateRequestDto requestDto);

    /**
     * 获取车牌前缀
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<LicensePrefixResultDto> getLicensePrefix(LicensePrefixGetRequestDto requestDto);

    /**
     * 获取车牌前缀列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<LicensePrefixResultDto> getLicensePrefixList(LicensePrefixListGetRequestDto requestDto);

    /**
     * 分页获取车牌前缀列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<LicensePrefixResultDto> getLicensePrefixPagedList(LicensePrefixQueryPagedResultRequestDto requestDto);

    /**
     * 获取车牌前缀
     *
     * @param requestDto
     * @return
     */
    ListResultDto<LicensePrefixViewResultDto> getLicensePrefixViewList(LicensePrefixViewGetRequestDto requestDto);

    /**
     * 获取车品牌及其车型
     *
     * @param requestDto
     * @return
     */
    ListResultDto<LicensePrefixFirstResultDto> getLicencePrefixFirstList(LicensePrefixFirstGetRequestDto requestDto);

}
