package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.tool.domain.Region;
import com.zhuyitech.parking.tool.dto.request.region.*;
import com.zhuyitech.parking.tool.dto.result.region.*;
import com.zhuyitech.parking.tool.enums.RegionLevelEnum;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.RegionCrudService;
import com.zhuyitech.parking.tool.service.api.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区域服务
 *
 * @author walkman
 */
@Service("regionService")
@Slf4j
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionCrudService regionCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增地区
     */
    @Override
    public ResultDto addRegion(RegionAddRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getCode())) {
                return resultDto.makeResult(ToolResultEnum.REGION_CODE_EMPTY.getValue(),
                        ToolResultEnum.REGION_CODE_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getName())) {
                return resultDto.makeResult(ToolResultEnum.REGION_NAME_EMPTY.getValue(),
                        ToolResultEnum.REGION_NAME_EMPTY.getComment());
            }
            Region regionExist = regionCrudService.findByCode(requestDto.getCode());
            if (regionExist != null) {
                return resultDto.makeResult(ToolResultEnum.REGION_CODE_EXISTS.getValue(),
                        ToolResultEnum.REGION_CODE_EXISTS.getComment());
            }
            Region region = modelMapper.map(requestDto, Region.class);
            if (requestDto.getParentId() == null) {
                region.setParentId(0L);
            }
            regionCrudService.insert(region);
            resultDto.success();
        } catch (Exception e) {
            log.error("地区添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除地区
     */
    @Override
    public ResultDto deleteRegion(RegionDeleteRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            regionCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("地区删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新地区
     */
    @Override
    public ResultDto updateRegion(RegionUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getName())) {
                return resultDto.makeResult(ToolResultEnum.REGION_NAME_EMPTY.getValue(),
                        ToolResultEnum.REGION_NAME_EMPTY.getComment());
            }
            Region region = modelMapper.map(requestDto, Region.class);
            if (requestDto.getParentId() == null) {
                region.setParentId(0L);
            }
//            Integer parentCount = regionCrudService.getCountByParentId(region.getParentId());
//            region.getOrder(parentCount + 1);
            regionCrudService.updateById(region);
            resultDto.success();
        } catch (Exception e) {
            log.error("地区更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取地区
     */
    @Override
    public ObjectResultDto<RegionResultDto> getRegion(RegionGetRequestDto requestDto) {
        ObjectResultDto<RegionResultDto> objectResultDto = new ObjectResultDto();
        try {
            Region region = regionCrudService.selectById(requestDto.getId());
            if (region != null) {
                RegionResultDto resultDto = modelMapper.map(region, RegionResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(),
                        ToolResultEnum.REGION_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取地区失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取地区列表
     */
    @Override
    public ListResultDto<RegionResultDto> getRegionList(RegionListGetRequestDto requestDto) {

        ListResultDto<RegionResultDto> listResultDto = new ListResultDto();
        try {

            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.eq("code", requestDto.getCode());
            }
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (requestDto.getLevel() != null) {
                entityWrapper.eq("level", requestDto.getLevel());
            }
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            if (null != requestDto.getTrafficRestriction()) {
                entityWrapper.eq("trafficrestriction", requestDto.getTrafficRestriction());
            }
            List<Region> regionList = regionCrudService.selectList(entityWrapper);
            if (!regionList.isEmpty()) {
                List<RegionResultDto> regionResultDtoList = modelMapper.map(regionList, new TypeToken<List<RegionResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取地区列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取地区列表
     */
    @Override
    public PagedResultDto<RegionResultDto> getRegionPagedList(RegionQueryPagedResultRequestDto requestDto) {

        PagedResultDto<RegionResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.eq("code", requestDto.getCode());
            }
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (requestDto.getLevel() != null) {
                entityWrapper.eq("level", requestDto.getLevel());
            }
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            Page<Region> regionPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<Region> regionPageList = regionCrudService.selectPage(regionPage, entityWrapper);
            if (regionPageList != null) {

                List<RegionResultDto> regionDtoList = modelMapper.map(regionPageList.getRecords(), new TypeToken<List<RegionResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(regionPageList.getCurrent());
                pagedResultDto.setPageSize(regionPageList.getSize());
                pagedResultDto.setTotalCount(regionPageList.getTotal());
                pagedResultDto.setItems(regionDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页地区列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取省列表
     */
    @Override
    public ListResultDto<RegionViewResultDto> getProvinceList(ProvinceListGetRequestDto requestDto) {
        ListResultDto<RegionViewResultDto> listResultDto = new ListResultDto();
        try {

            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
            if (StringUtils.isNotEmpty(requestDto.getProvinceCode())) {
                entityWrapper.eq("code", requestDto.getProvinceCode());
            }
            List<Region> regionList = regionCrudService.selectList(entityWrapper);
            if (!regionList.isEmpty()) {
                List<RegionViewResultDto> regionResultDtoList = modelMapper.map(regionList, new TypeToken<List<RegionViewResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取省列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取市列表
     */
    @Override
    public ListResultDto<RegionViewResultDto> getCityList(CityListGetRequestDto requestDto) {
        ListResultDto<RegionViewResultDto> listResultDto = new ListResultDto();
        try {

            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("level", RegionLevelEnum.CITY.getValue());
            if (StringUtils.isNotEmpty(requestDto.getCityCode())) {
                entityWrapper.eq("code", requestDto.getProvinceCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getProvinceCode())) {
                Region parentRegion = regionCrudService.findByCode(requestDto.getProvinceCode());
                if (parentRegion == null) {
                    return listResultDto.failed(ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                entityWrapper.eq("parentId", parentRegion.getId());
            }
            List<Region> regionList = regionCrudService.selectList(entityWrapper);
            if (!regionList.isEmpty()) {
                List<RegionViewResultDto> regionResultDtoList = modelMapper.map(regionList, new TypeToken<List<RegionViewResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取省列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取区县列表
     */
    @Override
    public ListResultDto<RegionViewResultDto> getCountyList(CountyListGetRequestDto requestDto) {
        ListResultDto<RegionViewResultDto> listResultDto = new ListResultDto();
        try {

            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("level", RegionLevelEnum.COUNTY.getValue());
            if (StringUtils.isNotEmpty(requestDto.getCountyCode())) {
                entityWrapper.eq("code", requestDto.getCountyCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getCityCode())) {
                Region parentRegion = regionCrudService.findByCode(requestDto.getCityCode());
                if (parentRegion == null) {
                    return listResultDto.failed(ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                entityWrapper.eq("parentId", parentRegion.getId());
            }
            List<Region> regionList = regionCrudService.selectList(entityWrapper);
            if (!regionList.isEmpty()) {
                List<RegionViewResultDto> regionResultDtoList = modelMapper.map(regionList, new TypeToken<List<RegionViewResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取省列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    @Override
    public ObjectResultDto<RegionViewByAdcodeResultDto> getProvinceByAdcode(ProvinceGetByAdcodeRequestDto requestDto) {
        ObjectResultDto<RegionViewByAdcodeResultDto> objectResultDto = new ObjectResultDto();
        try {
            RegionProvinceNameGetRequestDto regionProvinceNameGetRequestDto = new RegionProvinceNameGetRequestDto();
            regionProvinceNameGetRequestDto.setAdcode(requestDto.getAdcode());
            ObjectResultDto<RegionProvinceNameGetResultDto> objectResultDto1 = getRegionProvinceName(regionProvinceNameGetRequestDto);
            if (objectResultDto1.isFailed() || null == objectResultDto1.getData()) {
                return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
            }
            RegionProvinceNameGetResultDto regionProvinceNameGetResultDto = objectResultDto1.getData();
            RegionViewByAdcodeResultDto regionViewByAdcodeResultDto = new RegionViewByAdcodeResultDto();
            regionViewByAdcodeResultDto.setAdcode(regionProvinceNameGetResultDto.getAdCode());
            regionViewByAdcodeResultDto.setName(regionProvinceNameGetResultDto.getName());
            objectResultDto.setData(regionViewByAdcodeResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取省简称失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取区域地址
     *
     * @param requestDto requestDto
     */
    @Override
    public ObjectResultDto<RegionAddressResultDto> getRegionAddress(RegionAddressGetRequestDto requestDto) {

        ObjectResultDto<RegionAddressResultDto> objectResultDto = new ObjectResultDto();
        try {

            String parkingAddress = "";
            if (StringUtils.isNotEmpty(requestDto.getProvinceCode())) {
                EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", requestDto.getProvinceCode());
                entityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
                Region region = regionCrudService.selectOne(entityWrapper);
                if (null != region) {
                    parkingAddress += region.getName();
                }
            }
            if (StringUtils.isNotEmpty(requestDto.getCityCode())) {

                EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", requestDto.getCityCode());
                entityWrapper.eq("level", RegionLevelEnum.CITY.getValue());
                Region region = regionCrudService.selectOne(entityWrapper);
                if (null != region) {
                    parkingAddress += region.getName();
                }
            }
            if (StringUtils.isNotEmpty(requestDto.getCountyCode())) {
                EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", requestDto.getCountyCode());
                entityWrapper.eq("level", RegionLevelEnum.COUNTY.getValue());
                Region region = regionCrudService.selectOne(entityWrapper);
                if (null != region) {
                    parkingAddress += region.getName();
                }
            }
            RegionAddressResultDto regionAddressResultDto = new RegionAddressResultDto();
            regionAddressResultDto.setAddress(parkingAddress);
            objectResultDto.setData(regionAddressResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取地址失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<RegionCityPinyinGetResultDto> getRegionCityPingyin(RegionCityPingyinGetRequestDto requestDto) {
        ObjectResultDto<RegionCityPinyinGetResultDto> objectResultDto = new ObjectResultDto<>();
        RegionCityPinyinGetResultDto regionCityPingyinGetResultDto = null;
        try {
            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("adcode", requestDto.getAdcode());
            Region region = regionCrudService.selectOne(entityWrapper);
            if (null == region) {
                return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
            }
            if (region.getLevel() == RegionLevelEnum.CITY.getValue()) {
                regionCityPingyinGetResultDto = modelMapper.map(region, RegionCityPinyinGetResultDto.class);
            } else if (region.getLevel() == RegionLevelEnum.COUNTY.getValue()) {
                EntityWrapper<Region> entityWrapper1 = new EntityWrapper<>();
                entityWrapper1.eq("id", region.getParentId());
                region = regionCrudService.selectOne(entityWrapper1);
                if (null == region) {
                    return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                regionCityPingyinGetResultDto = modelMapper.map(region, RegionCityPinyinGetResultDto.class);
            } else if (region.getLevel() == RegionLevelEnum.PROVINCE.getValue()) {
                EntityWrapper<Region> entityWrapper1 = new EntityWrapper<>();
                entityWrapper1.eq("parentId", region.getId());
                entityWrapper1.eq("level", RegionLevelEnum.CITY.getValue());
                region = regionCrudService.selectOne(entityWrapper1);
                if (null == region) {
                    return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                regionCityPingyinGetResultDto = modelMapper.map(region, RegionCityPinyinGetResultDto.class);
            }
            objectResultDto.setData(regionCityPingyinGetResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取省简称失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<RegionProvinceNameGetResultDto> getRegionProvinceName(RegionProvinceNameGetRequestDto requestDto) {
        ObjectResultDto<RegionProvinceNameGetResultDto> objectResultDto = new ObjectResultDto<>();
        RegionProvinceNameGetResultDto regionProvinceNameGetResultDto = null;
        try {
            EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("adcode", requestDto.getAdcode());
            Region region = regionCrudService.selectOne(entityWrapper);
            if (null == region) {
                return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
            }
            if (region.getLevel() == RegionLevelEnum.PROVINCE.getValue()) {
                regionProvinceNameGetResultDto = modelMapper.map(region, RegionProvinceNameGetResultDto.class);
            } else if (region.getLevel() == RegionLevelEnum.COUNTY.getValue()) {
                EntityWrapper<Region> entityWrapper1 = new EntityWrapper<>();
                entityWrapper1.eq("id", region.getParentId());
                region = regionCrudService.selectOne(entityWrapper1);
                if (null == region) {
                    return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                if (region.getLevel() == RegionLevelEnum.CITY.getValue()) {
                    EntityWrapper<Region> entityWrapper2 = new EntityWrapper<>();
                    entityWrapper2.eq("id", region.getParentId());
                    region = regionCrudService.selectOne(entityWrapper2);
                    if (null == region) {
                        return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
                    }
                    regionProvinceNameGetResultDto = modelMapper.map(region, RegionProvinceNameGetResultDto.class);
                } else if (region.getLevel() == RegionLevelEnum.PROVINCE.getValue()) {
                    regionProvinceNameGetResultDto = modelMapper.map(region, RegionProvinceNameGetResultDto.class);
                }
            } else if (region.getLevel() == RegionLevelEnum.CITY.getValue()) {
                EntityWrapper<Region> entityWrapper1 = new EntityWrapper<>();
                entityWrapper1.eq("id", region.getParentId());
                region = regionCrudService.selectOne(entityWrapper1);
                if (null == region) {
                    return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                regionProvinceNameGetResultDto = modelMapper.map(region, RegionProvinceNameGetResultDto.class);
            } else if (region.getLevel() == RegionLevelEnum.COUNTRY.getValue()) {
                EntityWrapper<Region> entityWrapper1 = new EntityWrapper<>();
                entityWrapper1.eq("parentId", region.getId());
                entityWrapper1.eq("level", RegionLevelEnum.PROVINCE.getValue());
                region = regionCrudService.selectOne(entityWrapper1);
                if (null == region) {
                    return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(), ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                regionProvinceNameGetResultDto = modelMapper.map(region, RegionProvinceNameGetResultDto.class);
            }
            objectResultDto.setData(regionProvinceNameGetResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取省简称失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取上级区域列表
     *
     * @param requestDto RegionGetUpperListRequestDto
     * @return ListResultDto
     */
    @Override
    public ListResultDto<RegionGetUpperListResultDto> getUpperList(RegionGetUpperListRequestDto requestDto) {
        ListResultDto<RegionGetUpperListResultDto> resultDto = new ListResultDto<>();
        try {
            if (null == requestDto.getLevel()) {
                return resultDto.makeResult(ToolResultEnum.REGION_LEVEL_EMPTY.getValue(), ToolResultEnum.REGION_LEVEL_EMPTY.getComment());
            }
            if (requestDto.getLevel() < RegionLevelEnum.COUNTRY.getValue() || requestDto.getLevel() > RegionLevelEnum.COUNTY.getValue()) {
                return resultDto.failed();
            }
            if (RegionLevelEnum.COUNTRY.getValue() == requestDto.getLevel()) {
                return resultDto.success();
            }
            EntityWrapper<Region> wrapper = new EntityWrapper<>();
            wrapper.eq("level", requestDto.getLevel() - 1);
            List<Region> regions = regionCrudService.selectList(wrapper);
            if (null != regions && !regions.isEmpty()) {
                List<RegionGetUpperListResultDto> regionList = modelMapper.map(regions, new TypeToken<List<RegionGetUpperListResultDto>>() {
                }.getType());
                resultDto.setItems(regionList);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取上级区域列表失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 通过市name获取adcode
     *
     * @param requestDto requestDto
     */
    @Override
    public ObjectResultDto<RegionProvinceNameGetResultDto> getRegionAdcode(RegionProvinceAdCodeGetRequestDto requestDto) {
        ObjectResultDto<RegionProvinceNameGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getCityName())) {
                return objectResultDto.makeResult(ToolResultEnum.REGION_CITY_NAME_EMPTY.getValue(), ToolResultEnum.REGION_CITY_NAME_EMPTY.getComment());
            }
            EntityWrapper<Region> wrapper = new EntityWrapper<>();
            wrapper.like("name", requestDto.getCityName());
            Region region = regionCrudService.selectOne(wrapper);
            RegionProvinceNameGetResultDto getResultDto = modelMapper.map(region, RegionProvinceNameGetResultDto.class);
            if (null != getResultDto) {
                objectResultDto.setData(getResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取省简称失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ListResultDto<RegionListTotalGetResultDto> getRegionList(RegionListTotalGetRequestDto requestDto) {
        ListResultDto<RegionListTotalGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<Region> countryEntityWrapper = new EntityWrapper<>();
            countryEntityWrapper.eq("level", RegionLevelEnum.COUNTRY.getValue());
            List<Region> countryList = regionCrudService.selectList(countryEntityWrapper);
            if (!countryList.isEmpty()) {
                List<RegionListTotalGetResultDto> regionListTotalGetResultDtos = modelMapper.map(countryList, new TypeToken<List<RegionListTotalGetResultDto>>() {
                }.getType());
                for (RegionListTotalGetResultDto obj : regionListTotalGetResultDtos) {
                    EntityWrapper<Region> provinceEntityWrapper = new EntityWrapper<>();
                    provinceEntityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
                    provinceEntityWrapper.eq("parentId", obj.getId());
                    List<Region> provinceList = regionCrudService.selectList(provinceEntityWrapper);
                    if (!provinceList.isEmpty()) {
                        List<ProvenceListGetResultDto> provenceListGetResultDtoList = modelMapper.map(provinceList, new TypeToken<List<ProvenceListGetResultDto>>() {
                        }.getType());
                        obj.setList(provenceListGetResultDtoList);
                        for (ProvenceListGetResultDto proObj : provenceListGetResultDtoList) {
                            EntityWrapper<Region> cityEntityWrapper = new EntityWrapper<>();
                            cityEntityWrapper.eq("level", RegionLevelEnum.CITY.getValue());
                            cityEntityWrapper.eq("parentId", proObj.getId());
                            List<Region> cityList = regionCrudService.selectList(cityEntityWrapper);
                            if (!cityList.isEmpty()) {
                                List<CityListGetResultDto> cityListGetResultDtoList = modelMapper.map(cityList, new TypeToken<List<CityListGetResultDto>>() {
                                }.getType());
                                proObj.setList(cityListGetResultDtoList);
                                for (CityListGetResultDto cityObj : cityListGetResultDtoList) {
                                    EntityWrapper<Region> countyEntityWrapper = new EntityWrapper<>();
                                    countyEntityWrapper.eq("level", RegionLevelEnum.COUNTY.getValue());
                                    countyEntityWrapper.eq("parentId", cityObj.getId());
                                    List<Region> countyList = regionCrudService.selectList(countyEntityWrapper);
                                    if (!countyList.isEmpty()) {
                                        List<CountyListGetResultDto> countyListGetResultDtoList = modelMapper.map(countyList, new TypeToken<List<CountyListGetResultDto>>() {
                                        }.getType());
                                        cityObj.setList(countyListGetResultDtoList);
                                    }
                                }
                            }
                        }
                    }
                }
                listResultDto.setItems(regionListTotalGetResultDtos);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取region列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * @param getRegionDto
     * @return
     */
//    @Override
//    public ListResultDto<RegionResultDto> getRegionListByParentId(RegionGetRequestDto getRegionDto) {
//
//        ListResultDto<RegionResultDto> listResultDto = new ListResultDto<>();
//        EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
//        entityWrapper.eq("parentId", getRegionDto.getParentId());
//
//        List<Region> regionList = regionCrudService.selectList(entityWrapper);
//        if (regionList != null) {
//            List<RegionResultDto> regionDtoList = modelMapper.map(regionList, new TypeToken<List<RegionResultDto>>() {
//            }.getType());
//            listResultDto.setItems(regionDtoList);
//        }
//        return listResultDto.success();
//    }

    /**
     * @param getRegionPagedResultRequestDto
     * @return
     */
//    @Override
//    public PagedResultDto<RegionResultDto> getPagedRegionListByParentId(RegionQueryPagedResultRequestDto getRegionPagedResultRequestDto) {
//
//        PagedResultDto<RegionResultDto> pagedResultDto = new PagedResultDto<>();
//        EntityWrapper<Region> entityWrapper = new EntityWrapper<>();
//        entityWrapper.eq("parentId", getRegionPagedResultRequestDto.getParentId());
//        Page<Region> regionPage = new Page<>(getRegionPagedResultRequestDto.getPageNo(), getRegionPagedResultRequestDto.getPageSize());
//        Page<Region> regionList = regionCrudService.selectPage(regionPage, entityWrapper);
//
//        if (regionList != null) {
//            List<RegionResultDto> regionDtoList = modelMapper.map(regionList.getRecords(), new TypeToken<List<RegionResultDto>>() {
//            }.getType());
//            pagedResultDto.setPageNo(regionPage.getCurrent());
//            pagedResultDto.setPageSize(regionPage.getSize());
//            pagedResultDto.setTotalCount(regionPage.getTotal());
//            pagedResultDto.setItems(regionDtoList);
//        }
//        return pagedResultDto.success();
//    }

}
