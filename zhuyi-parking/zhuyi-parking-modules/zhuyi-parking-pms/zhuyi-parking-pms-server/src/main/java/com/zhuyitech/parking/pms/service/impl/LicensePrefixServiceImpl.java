package com.zhuyitech.parking.pms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.pms.domain.LicensePrefix;
import com.zhuyitech.parking.pms.dto.request.license.*;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixFirstResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixResultDto;
import com.zhuyitech.parking.pms.dto.result.liscense.LicensePrefixViewResultDto;
import com.zhuyitech.parking.pms.enums.PmsResultEnum;
import com.zhuyitech.parking.pms.service.LicensePrefixCrudService;
import com.zhuyitech.parking.pms.service.api.LicensePrefixService;
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
     * 新增车牌前缀
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addLicensePrefix(LicensePrefixAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            if (StringUtils.isEmpty(requestDto.getName())) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_NAME_EMPTY.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_NAME_EMPTY.getComment());
            }
            if (null == requestDto.getType()) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_TYPE_EMPTY.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_TYPE_EMPTY.getComment());
            }
            if (requestDto.getType() != 1 && requestDto.getType() != 2) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_TYPE_ERR.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_TYPE_ERR.getComment());
            }
            if (null == requestDto.getParentId()) {
                requestDto.setParentId(0L);
            }
            LicensePrefix licensePrefix = new LicensePrefix();
            licensePrefix.setType(requestDto.getType());
            licensePrefix.setName(requestDto.getName());
            licensePrefix.setParentId(requestDto.getParentId());
            List<LicensePrefix> licensePrefixExist = licensePrefixCrudService.findByLicensePrefix(licensePrefix);
            if (null != licensePrefixExist && licensePrefixExist.size() > 0) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_PARENTNUM_EXISTS.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_PARENTNUM_EXISTS.getComment());
            }
            licensePrefixCrudService.insert(licensePrefix);
            resultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除车牌前缀
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteLicensePrefix(LicensePrefixDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Integer childrenCount = licensePrefixCrudService.getCountByParentId(requestDto.getId());
            if (childrenCount > 0) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_CHILDREN_EXISTS.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_CHILDREN_EXISTS.getComment());
            }
            licensePrefixCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新车牌前缀
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateLicensePrefix(LicensePrefixUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null != requestDto.getType() && requestDto.getType() != 1 && requestDto.getType() != 2) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_TYPE_ERR.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_TYPE_ERR.getComment());
            }
            LicensePrefix licensePrefixOld = licensePrefixCrudService.selectById(requestDto.getId());
            if (licensePrefixOld == null) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_NOT_FOUND.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_NOT_FOUND.getComment());
            }
            if (null == requestDto.getParentId()) {
                requestDto.setParentId(0L);
            }
            LicensePrefix licensePrefix = new LicensePrefix();
            licensePrefix.setType(requestDto.getType());
            licensePrefix.setName(requestDto.getName());
            licensePrefix.setParentId(requestDto.getParentId());
            List<LicensePrefix> licensePrefixExist = licensePrefixCrudService.findByLicensePrefix(licensePrefix);
            if (null != licensePrefixExist && licensePrefixExist.size() > 0) {
                return resultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_PARENTNUM_EXISTS.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_PARENTNUM_EXISTS.getComment());
            }
            LicensePrefix licensePrefixEnd = modelMapper.map(requestDto, LicensePrefix.class);
            EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            licensePrefixCrudService.update(licensePrefixEnd, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取车牌前缀
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<LicensePrefixResultDto> getLicensePrefix(LicensePrefixGetRequestDto requestDto) {
        ObjectResultDto<LicensePrefixResultDto> objectResultDto = new ObjectResultDto();
        try {
            LicensePrefix licensePrefix = licensePrefixCrudService.selectById(requestDto.getId());
            if (licensePrefix != null) {
                LicensePrefixResultDto resultDto = modelMapper.map(licensePrefix, LicensePrefixResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(PmsResultEnum.LICENSE_PREFIX_NOT_FOUND.getValue(),
                        PmsResultEnum.LICENSE_PREFIX_NOT_FOUND.getComment());
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
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<LicensePrefixResultDto> getLicensePrefixList(LicensePrefixListGetRequestDto requestDto) {
        ListResultDto<LicensePrefixResultDto> listResultDto = new ListResultDto();
        try {
            EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (null != requestDto.getType() && (requestDto.getType() == 1 || requestDto.getType() == 2)) {
                entityWrapper.eq("type", requestDto.getType());
            }
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            List<LicensePrefix> licensePrefixList = licensePrefixCrudService.selectList(entityWrapper);
            if (!licensePrefixList.isEmpty()) {
                List<LicensePrefixResultDto> licensePrefixResultDtoList = modelMapper.map(licensePrefixList, new TypeToken<List<LicensePrefixResultDto>>() {
                }.getType());
                listResultDto.setItems(licensePrefixResultDtoList);
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
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<LicensePrefixResultDto> getLicensePrefixPagedList(LicensePrefixQueryPagedResultRequestDto requestDto) {
        PagedResultDto<LicensePrefixResultDto> pagedResultDto = new PagedResultDto();
        try {

            EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (null != requestDto.getType() && (requestDto.getType() == 1 || requestDto.getType() == 2)) {
                entityWrapper.eq("type", requestDto.getType());
            }
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            Page<LicensePrefix> licensePrefixPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<LicensePrefix> licensePrefixPageList = licensePrefixCrudService.selectPage(licensePrefixPage, entityWrapper);
            if (licensePrefixPageList != null) {
                List<LicensePrefixResultDto> licensePrefixDtoList = modelMapper.map(licensePrefixPageList.getRecords(), new TypeToken<List<LicensePrefixResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(licensePrefixPageList.getCurrent());
                pagedResultDto.setPageSize(licensePrefixPageList.getSize());
                pagedResultDto.setTotalCount(licensePrefixPageList.getTotal());
                pagedResultDto.setItems(licensePrefixDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("车牌前缀获取失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取车牌前缀
     *
     * @return
     */
    @Override
    public ListResultDto<LicensePrefixViewResultDto> getLicensePrefixViewList(LicensePrefixViewGetRequestDto requestDto) {
        ListResultDto<LicensePrefixViewResultDto> listResultDto = new ListResultDto();
        try {
            EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("type", 1);
            if (StringUtils.isNotEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            List<LicensePrefix> licensePrefixList = licensePrefixCrudService.selectList(entityWrapper);
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

    /**
     * 获取前缀首字母
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<LicensePrefixFirstResultDto> getLicencePrefixFirstList(LicensePrefixFirstGetRequestDto requestDto) {
        ListResultDto<LicensePrefixFirstResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<LicensePrefix> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parentId", requestDto.getParentId());
            entityWrapper.eq("type", 2);
            List<LicensePrefix> licensePrefixFirstList = licensePrefixCrudService.selectList(entityWrapper);
            if (licensePrefixFirstList != null && !licensePrefixFirstList.isEmpty()) {
                List<LicensePrefixFirstResultDto> licensePrefixViewResultDtoList = modelMapper.map(licensePrefixFirstList, new TypeToken<List<LicensePrefixFirstResultDto>>() {
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
