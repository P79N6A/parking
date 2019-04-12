package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixFirstGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixListGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixPagedResultRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixRequestDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixFirstResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixResultDto;

/**
 * 车管局服务
 *
 * @Date: 2018/4/14
 * @author: yuzhicheng
 */
public interface LicenseOrganizationService {

    /**
     * 根据首字母和父id修改
     */
//    ResultDto updateLicenseOrganization(LicenseOrganizationUpdateRequestDto requestDto);

    /**
     * 获取车牌
     *
     * @param requestDto LicenseOrganizationPrefixRequestDto
     * @return LicenseOrganizationPrefixResultDto
     */
    ObjectResultDto<LicenseOrganizationPrefixResultDto> getPrefix(LicenseOrganizationPrefixRequestDto requestDto);

    /**
     * 获取车牌前缀列表
     *
     * @param requestDto LicenseOrganzationPrefixListGetRequestDto
     * @return LicenseOrganizationPrefixResultDto
     */
    ListResultDto<LicenseOrganizationPrefixResultDto> getPrefixList(LicenseOrganizationPrefixListGetRequestDto requestDto);

    /**
     * 分页获取车牌前缀列表
     *
     * @param requestDto LicenseOrganizationPrefixPagedResultRequestDto
     * @return LicenseOrganizationPrefixResultDto
     */
    PagedResultDto<LicenseOrganizationPrefixResultDto> getPrefixPagedList(LicenseOrganizationPrefixPagedResultRequestDto requestDto);

    /**
     * 获取前缀首字母
     *
     * @param requestDto LicenseOrganzationPrefixFirstGetRequestDto
     * @return LicenseOrganzationPrefixFirstResultDto
     */
    ListResultDto<LicenseOrganizationPrefixFirstResultDto> getPrefixLetter(LicenseOrganizationPrefixFirstGetRequestDto requestDto);
}
