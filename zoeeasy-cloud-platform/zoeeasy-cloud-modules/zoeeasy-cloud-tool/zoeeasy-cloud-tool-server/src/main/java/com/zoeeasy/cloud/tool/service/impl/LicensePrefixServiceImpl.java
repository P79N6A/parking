package com.zoeeasy.cloud.tool.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.tool.domain.LicensePrefix;
import com.zoeeasy.cloud.tool.license.LicensePrefixService;
import com.zoeeasy.cloud.tool.license.dto.request.LicensePrefixViewGetRequestDto;
import com.zoeeasy.cloud.tool.license.dto.result.LicensePrefixViewResultDto;
import com.zoeeasy.cloud.tool.service.LicensePrefixCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车牌前缀服务
 *
 * @author zwq
 * @date 2017-01-02
 */
@Service("licensePrefixService")
@Slf4j
public class LicensePrefixServiceImpl implements LicensePrefixService {

    @Autowired
    private LicensePrefixCrudService licensePrefixCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取车牌前缀
     *
     * @return
     */
    @Override
    public ListResultDto<LicensePrefixViewResultDto> getLicensePrefixViewList(LicensePrefixViewGetRequestDto requestDto) {
        ListResultDto<LicensePrefixViewResultDto> listResultDto = new ListResultDto();
        try {
            List<LicensePrefix> licensePrefixList = licensePrefixCrudService.selectLicensePrefixList(requestDto.getName());
            if (null != licensePrefixList && !licensePrefixList.isEmpty()) {
                List<LicensePrefixViewResultDto> licensePrefixViewResultDtoList = modelMapper.map(licensePrefixList, new TypeToken<List<LicensePrefixViewResultDto>>() {
                }.getType());
                listResultDto.setItems(licensePrefixViewResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

}
