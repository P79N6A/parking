package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.tool.domain.LicenseOrganization;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixFirstGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixListGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixPagedResultRequestDto;
import com.zhuyitech.parking.tool.dto.request.license.LicenseOrganizationPrefixRequestDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixFirstResultDto;
import com.zhuyitech.parking.tool.dto.result.license.LicenseOrganizationPrefixResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.LicenseOrganizationCrudService;
import com.zhuyitech.parking.tool.service.api.LicenseOrganizationService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车管局服务
 *
 * @Date: 2018/4/14
 * @author: yuzhicheng
 */
@Service("licenseOrganizationService")
@Slf4j
public class LicenseOrganizationServiceImpl implements LicenseOrganizationService {

    @Autowired
    private LicenseOrganizationCrudService licenseOrganizationCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取车牌前缀
     *
     * @param requestDto LicenseOrganizationPrefixRequestDto
     * @return LicenseOrganizationPrefixResultDto
     */
    @Override
    public ObjectResultDto<LicenseOrganizationPrefixResultDto> getPrefix(LicenseOrganizationPrefixRequestDto requestDto) {
        ObjectResultDto<LicenseOrganizationPrefixResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            LicenseOrganization licenseOrganization = licenseOrganizationCrudService.selectById(requestDto.getId());
            if (licenseOrganization != null) {
                LicenseOrganizationPrefixResultDto resultDto = modelMapper.map(licenseOrganization, LicenseOrganizationPrefixResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(ToolResultEnum.LICENSE_PREFIX_NOT_FOUND.getValue(),
                        ToolResultEnum.LICENSE_PREFIX_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取车牌前缀列表
     *
     * @param requestDto LicenseOrganizationPrefixListGetRequestDto
     * @return LicenseOrganizationPrefixResultDto
     */
    @Override
    public ListResultDto<LicenseOrganizationPrefixResultDto> getPrefixList(LicenseOrganizationPrefixListGetRequestDto requestDto) {
        ListResultDto<LicenseOrganizationPrefixResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<LicenseOrganization> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("type", 1);
            entityWrapper.eq("parentId", 0);
            List<LicenseOrganization> licenseOrganizations = licenseOrganizationCrudService.selectList(entityWrapper);
            if (!licenseOrganizations.isEmpty()) {
                List<LicenseOrganizationPrefixResultDto> licenseOrganizationPrefixResultDtos = modelMapper.map(licenseOrganizations, new TypeToken<List<LicenseOrganizationPrefixResultDto>>() {
                }.getType());
                listResultDto.setItems(licenseOrganizationPrefixResultDtos);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取车牌前缀列表
     *
     * @param requestDto LicenseOrganizationPrefixPagedResultRequestDto
     * @return LicenseOrganizationPrefixResultDto
     */
    @Override
    public PagedResultDto<LicenseOrganizationPrefixResultDto> getPrefixPagedList(LicenseOrganizationPrefixPagedResultRequestDto requestDto) {
        PagedResultDto<LicenseOrganizationPrefixResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<LicenseOrganization> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (null != requestDto.getType() && (requestDto.getType() == 1 || requestDto.getType() == 2)) {
                entityWrapper.eq("type", requestDto.getType());
            }
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            Page<LicenseOrganization> licensePrefixPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<LicenseOrganization> licenseOrganizationPage = licenseOrganizationCrudService.selectPage(licensePrefixPage, entityWrapper);
            if (licenseOrganizationPage != null) {
                List<LicenseOrganizationPrefixResultDto> prefixResultDtos = modelMapper.map(licenseOrganizationPage.getRecords(), new TypeToken<List<LicenseOrganizationPrefixResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(licenseOrganizationPage.getCurrent());
                pagedResultDto.setPageSize(licenseOrganizationPage.getSize());
                pagedResultDto.setTotalCount(licenseOrganizationPage.getTotal());
                pagedResultDto.setItems(prefixResultDtos);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀获取失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取前缀首字母
     *
     * @param requestDto LicenseOrganizationPrefixFirstGetRequestDto
     * @return LicenseOrganizationPrefixFirstResultDto
     */
    @Override
    public ListResultDto<LicenseOrganizationPrefixFirstResultDto> getPrefixLetter(LicenseOrganizationPrefixFirstGetRequestDto requestDto) {
        ListResultDto<LicenseOrganizationPrefixFirstResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<LicenseOrganization> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parentId", requestDto.getParentId());
            entityWrapper.eq("type", 2);
            List<LicenseOrganization> licenseOrganizationList = licenseOrganizationCrudService.selectList(entityWrapper);
            if (licenseOrganizationList != null && !licenseOrganizationList.isEmpty()) {
                List<LicenseOrganizationPrefixFirstResultDto> licensePrefixViewResultDtoList = modelMapper.map(licenseOrganizationList, new TypeToken<List<LicenseOrganizationPrefixFirstResultDto>>() {
                }.getType());
                listResultDto.setItems(licensePrefixViewResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("前缀首字母获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

}
