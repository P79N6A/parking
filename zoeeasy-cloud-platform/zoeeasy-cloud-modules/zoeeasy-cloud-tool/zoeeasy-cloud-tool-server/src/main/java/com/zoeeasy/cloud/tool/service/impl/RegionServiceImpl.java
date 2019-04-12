package com.zoeeasy.cloud.tool.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.tool.domain.RegionEntity;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import com.zoeeasy.cloud.tool.enums.RegionTypeEnum;
import com.zoeeasy.cloud.tool.enums.ToolResultEnum;
import com.zoeeasy.cloud.tool.region.RegionService;
import com.zoeeasy.cloud.tool.region.cst.RegionConstant;
import com.zoeeasy.cloud.tool.region.dto.*;
import com.zoeeasy.cloud.tool.service.RegionCrudService;
import com.zoeeasy.cloud.tool.validator.region.RegionAddDtoValidator;
import com.zoeeasy.cloud.tool.validator.region.RegionUpdateDtoValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域服务
 *
 * @author walkman
 */
@Service(value = "regionService")
public class RegionServiceImpl implements RegionService {

    private static final Logger LOG = LoggerFactory.getLogger(RegionServiceImpl.class);

    @Autowired
    private RegionCrudService regionCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增地区
     */
    @Override
    public ResultDto addRegion(@FluentValid({RegionAddDtoValidator.class}) RegionAddRequestDto requestDto) {

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
            RegionEntity regionEntityExist = regionCrudService.findByCode(requestDto.getCode());
            if (regionEntityExist != null) {
                return resultDto.makeResult(ToolResultEnum.REGION_CODE_EXISTS.getValue(),
                        ToolResultEnum.REGION_CODE_EXISTS.getComment());
            }
            RegionEntity regionEntity = modelMapper.map(requestDto, RegionEntity.class);
            if (requestDto.getParentCode() == null) {
                regionEntity.setParentCode("-1");
            }
            regionCrudService.insert(regionEntity);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("地区添加失败" + e.getMessage());
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
            LOG.error("地区删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新地区
     */
    @Override
    public ResultDto updateRegion(@FluentValid({RegionUpdateDtoValidator.class}) RegionUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getName())) {
                return resultDto.makeResult(ToolResultEnum.REGION_NAME_EMPTY.getValue(),
                        ToolResultEnum.REGION_NAME_EMPTY.getComment());
            }
            RegionEntity regionEntity = modelMapper.map(requestDto, RegionEntity.class);
            if (requestDto.getParentCode() == null) {
                regionEntity.setParentCode("-1");
            }
//            Integer parentCount = regionCrudService.getCountByParentId(regionEntity.getParentId());
//            regionEntity.getOrder(parentCount + 1);
            regionCrudService.updateById(regionEntity);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("地区更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取地区
     */
    @Override
    public ObjectResultDto<RegionResultDto> getRegion(RegionGetRequestDto requestDto) {
        ObjectResultDto<RegionResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            RegionEntity regionEntity = regionCrudService.selectById(requestDto.getId());
            if (regionEntity != null) {
                RegionResultDto resultDto = modelMapper.map(regionEntity, RegionResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(),
                        ToolResultEnum.REGION_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            LOG.error("获取地区失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取地区列表
     */
    @Override
    public ListResultDto<RegionResultDto> getRegionList(RegionListGetRequestDto requestDto) {

        ListResultDto<RegionResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
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
            List<RegionEntity> regionEntityList = regionCrudService.selectList(entityWrapper);
            if (!regionEntityList.isEmpty()) {
                List<RegionResultDto> regionResultDtoList = modelMapper.map(regionEntityList, new TypeToken<List<RegionResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取地区列表失败" + e.getMessage());
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
            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
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
            Page<RegionEntity> regionPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<RegionEntity> regionPageList = regionCrudService.selectPage(regionPage, entityWrapper);
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
            LOG.error("分页地区列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取省列表
     */
    @Override
    public ListResultDto<RegionViewResultDto> getProvinceList(ProvinceListGetRequestDto requestDto) {
        ListResultDto<RegionViewResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
            if (StringUtils.isNotEmpty(requestDto.getProvinceCode())) {
                entityWrapper.eq("code", requestDto.getProvinceCode());
            }
            List<RegionEntity> regionEntityList = regionCrudService.selectList(entityWrapper);
            if (!regionEntityList.isEmpty()) {
                List<RegionViewResultDto> regionResultDtoList = modelMapper.map(regionEntityList, new TypeToken<List<RegionViewResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取省列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取市列表
     */
    @Override
    public ListResultDto<RegionViewResultDto> getCityList(CityListGetRequestDto requestDto) {
        ListResultDto<RegionViewResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("level", RegionLevelEnum.CITY.getValue());
            if (StringUtils.isNotEmpty(requestDto.getCityCode())) {
                entityWrapper.eq("code", requestDto.getProvinceCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getProvinceCode())) {
                RegionEntity parentRegionEntity = regionCrudService.findByCode(requestDto.getProvinceCode());
                if (parentRegionEntity == null) {
                    return listResultDto.failed(ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                entityWrapper.eq("parentId", parentRegionEntity.getId());
            }
            List<RegionEntity> regionEntityList = regionCrudService.selectList(entityWrapper);
            if (!regionEntityList.isEmpty()) {
                List<RegionViewResultDto> regionResultDtoList = modelMapper.map(regionEntityList, new TypeToken<List<RegionViewResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取省列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取区县列表
     */
    @Override
    public ListResultDto<RegionViewResultDto> getCountyList(CountyListGetRequestDto requestDto) {
        ListResultDto<RegionViewResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("level", RegionLevelEnum.COUNTY.getValue());
            if (StringUtils.isNotEmpty(requestDto.getCountyCode())) {
                entityWrapper.eq("code", requestDto.getCountyCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getCityCode())) {
                RegionEntity parentRegionEntity = regionCrudService.findByCode(requestDto.getCityCode());
                if (parentRegionEntity == null) {
                    return listResultDto.failed(ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                entityWrapper.eq("parentId", parentRegionEntity.getId());
            }
            List<RegionEntity> regionEntityList = regionCrudService.selectList(entityWrapper);
            if (!regionEntityList.isEmpty()) {
                List<RegionViewResultDto> regionResultDtoList = modelMapper.map(regionEntityList, new TypeToken<List<RegionViewResultDto>>() {
                }.getType());
                listResultDto.setItems(regionResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取省列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
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
            if (RegionLevelEnum.COUNTRY.getValue().equals(requestDto.getLevel())) {
                return resultDto.success();
            }
            EntityWrapper<RegionEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("level", requestDto.getLevel() - 1);
            List<RegionEntity> regionEntities = regionCrudService.selectList(wrapper);
            if (null != regionEntities && !regionEntities.isEmpty()) {
                List<RegionGetUpperListResultDto> regionList = modelMapper.map(regionEntities, new TypeToken<List<RegionGetUpperListResultDto>>() {
                }.getType());
                resultDto.setItems(regionList);
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取上级区域列表失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ListResultDto<RegionListTotalGetResultDto> getRegionList(RegionListTotalGetRequestDto requestDto) {
        ListResultDto<RegionListTotalGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<RegionEntity> countryEntityWrapper = new EntityWrapper<>();
            countryEntityWrapper.eq("level", RegionLevelEnum.COUNTRY.getValue());
            List<RegionEntity> countryList = regionCrudService.selectList(countryEntityWrapper);
            if (!countryList.isEmpty()) {
                List<RegionListTotalGetResultDto> regionListTotalGetResultDtos = modelMapper.map(countryList, new TypeToken<List<RegionListTotalGetResultDto>>() {
                }.getType());
                for (RegionListTotalGetResultDto obj : regionListTotalGetResultDtos) {
                    EntityWrapper<RegionEntity> provinceEntityWrapper = new EntityWrapper<>();
                    provinceEntityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
                    provinceEntityWrapper.eq("parentId", obj.getId());
                    List<RegionEntity> provinceList = regionCrudService.selectList(provinceEntityWrapper);
                    if (!provinceList.isEmpty()) {
                        List<ProvinceListGetResultDto> provenceListGetResultDtoList = modelMapper.map(provinceList, new TypeToken<List<ProvinceListGetResultDto>>() {
                        }.getType());
                        obj.setList(provenceListGetResultDtoList);
                        for (ProvinceListGetResultDto proObj : provenceListGetResultDtoList) {
                            EntityWrapper<RegionEntity> cityEntityWrapper = new EntityWrapper<>();
                            cityEntityWrapper.eq("level", RegionLevelEnum.CITY.getValue());
                            cityEntityWrapper.eq("parentId", proObj.getId());
                            List<RegionEntity> cityList = regionCrudService.selectList(cityEntityWrapper);
                            if (!cityList.isEmpty()) {
                                List<CityListGetResultDto> cityListGetResultDtoList = modelMapper.map(cityList, new TypeToken<List<CityListGetResultDto>>() {
                                }.getType());
                                proObj.setList(cityListGetResultDtoList);
                                for (CityListGetResultDto cityObj : cityListGetResultDtoList) {
                                    EntityWrapper<RegionEntity> countyEntityWrapper = new EntityWrapper<>();
                                    countyEntityWrapper.eq("level", RegionLevelEnum.COUNTY.getValue());
                                    countyEntityWrapper.eq("parentId", cityObj.getId());
                                    List<RegionEntity> countyList = regionCrudService.selectList(countyEntityWrapper);
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
            LOG.error("获取region列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取子区域列表
     *
     * @param requestDto RegionGetChildListRequestDto
     * @return 子区域列表
     */
    @Override
    public ListResultDto<RegionGetChildListResultDto> getRegionChildList(RegionGetChildListRequestDto requestDto) {
        ListResultDto<RegionGetChildListResultDto> listResultDto = new ListResultDto<>();
        try {
            List<RegionEntity> items = new ArrayList<>();
            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getLevel().equals(RegionLevelEnum.COUNTRY.getValue())) {
                // 国家
                entityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
                items.addAll(regionCrudService.selectList(entityWrapper));
            } else {
                //省级
                if (!requestDto.getLevel().equals(RegionLevelEnum.PROVINCE.getValue())) {
                    return listResultDto.makeResult(ToolResultEnum.REGION_LEVEL_OUT.getValue(),
                            ToolResultEnum.REGION_LEVEL_OUT.getComment());
                }
                final RegionEntity regionEntity = regionCrudService.findByCode(requestDto.getParentCode());
                if (regionEntity == null || !regionEntity.getLevel().equals(requestDto.getLevel())) {
                    return listResultDto.makeResult(ToolResultEnum.REGION_NOT_FOUND.getValue(),
                            ToolResultEnum.REGION_NOT_FOUND.getComment());
                }
                //直辖市只查询区县作为子级，而不是把过渡级当做子级
                //河南、河北、湖北、海南、新疆五省都有省直辖县级行政区划（自治区直辖县级行政区划）
                entityWrapper.eq("parentCode", requestDto.getParentCode());
                List<RegionEntity> regionEntities = regionCrudService.selectList(entityWrapper);
                if (CollectionUtils.isNotEmpty(regionEntities)) {
                    for (RegionEntity childRegionEntity : regionEntities) {
                        if (RegionTypeEnum.TRANSITION.getValue().equals(childRegionEntity.getType())) {
                            EntityWrapper<RegionEntity> wrapper = new EntityWrapper<>();
                            wrapper.eq("parentCode", childRegionEntity.getCode());
                            items.addAll(regionCrudService.selectList(wrapper));
                        } else {
                            items.add(childRegionEntity);
                        }
                    }
                }
            }
            List<RegionGetChildListResultDto> regionList =
                    modelMapper.map(items, new TypeToken<List<RegionGetChildListResultDto>>() {
                    }.getType());
            listResultDto.setItems(regionList);
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取region子列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取区域地址
     *
     * @param requestDto requestDto
     */
    @Override
    public ObjectResultDto<RegionAddressResultDto> getRegionAddressApp(RegionAddressGetRequestDto requestDto) {

        ObjectResultDto<RegionAddressResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            String parkingAddress = "";
//            if (StringUtils.isNotEmpty(requestDto.getProvinceCode())) {
//                EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
//                entityWrapper.eq("code", requestDto.getProvinceCode());
//                entityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
//                RegionEntity regionEntity = regionCrudService.selectOne(entityWrapper);
//                if (null != regionEntity) {
//                    parkingAddress += regionEntity.getName();
//                }
//            }
//            if (StringUtils.isNotEmpty(requestDto.getCityCode())) {
//
//                EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
//                entityWrapper.eq("code", requestDto.getCityCode());
//                entityWrapper.eq("level", RegionLevelEnum.CITY.getValue());
//                RegionEntity regionEntity = regionCrudService.selectOne(entityWrapper);
//                if (null != regionEntity) {
//                    parkingAddress += regionEntity.getName();
//                }
//            }
//            if (StringUtils.isNotEmpty(requestDto.getCountyCode())) {
//                EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
//                entityWrapper.eq("code", requestDto.getCountyCode());
//                entityWrapper.eq("level", RegionLevelEnum.COUNTY.getValue());
//                RegionEntity regionEntity = regionCrudService.selectOne(entityWrapper);
//                if (null != regionEntity) {
//                    parkingAddress += regionEntity.getName();
//                }
//            }
            RegionAddressResultDto regionAddressResultDto = new RegionAddressResultDto();
            regionAddressResultDto.setAddress(parkingAddress);
            objectResultDto.setData(regionAddressResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            LOG.error("获取地址失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取区域树
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<TreeResultDto> getRegionTree(RegionTreeGetRequestDto requestDto) {
        ListResultDto<TreeResultDto> listResultDto = new ListResultDto<>();
        try {
            List<TreeResultDto> treeResultDtoList = new ArrayList<>();
            //获取除了country的所有区域
            EntityWrapper<RegionEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.ne("level", RegionLevelEnum.COUNTRY.getValue());
            entityWrapper.ne("level", RegionLevelEnum.CUSTOM.getValue());
            List<RegionEntity> regionEntityEntityList = regionCrudService.selectList(entityWrapper);
            //顶级区域
            List<RegionTreeResultDto> topAreaTreeResultDtoList = new ArrayList<>();
            if (regionEntityEntityList != null) {
                List<RegionTreeResultDto> areaTreeResultDtoList = modelMapper.map(regionEntityEntityList,
                        new TypeToken<List<RegionTreeResultDto>>() {
                        }.getType());
                for (RegionTreeResultDto areaTreeResultDto : areaTreeResultDtoList) {
                    if (areaTreeResultDto.getLevel().equals(RegionLevelEnum.PROVINCE.getValue())) {
                        topAreaTreeResultDtoList.add(areaTreeResultDto);
                    }
                }
                areaTreeResultDtoList.removeAll(topAreaTreeResultDtoList);
                for (RegionTreeResultDto areaTreeResultDto : topAreaTreeResultDtoList) {
                    getChild(areaTreeResultDto, areaTreeResultDtoList);
                    treeResultDtoList.add(modelMapper.map(areaTreeResultDto, TreeResultDto.class));
                }
            }
            listResultDto.setItems(treeResultDtoList);
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取区域树失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取区域子列表
     *
     * @param areaTreeResultDto     顶级区域
     * @param areaTreeResultDtoList 非顶级区域
     */
    private void getChild(RegionTreeResultDto areaTreeResultDto, List<RegionTreeResultDto> areaTreeResultDtoList) {
        List<RegionTreeResultDto> childList = new ArrayList<>();
        for (int i = 0; i < areaTreeResultDtoList.size(); i++) {
            RegionTreeResultDto leafNodeArea = areaTreeResultDtoList.get(i);
            if (leafNodeArea.getParentCode().equals(areaTreeResultDto.getCode())) {
                areaTreeResultDtoList.remove(leafNodeArea);
                if (leafNodeArea.getLevel().equals(RegionLevelEnum.CUSTOM.getValue())) {
                    leafNodeArea.setChild(null);
                } else {
                    getChild(leafNodeArea, areaTreeResultDtoList);
                }
                childList.add(leafNodeArea);
                i--;
            }
        }
        if (childList.isEmpty()) {
            childList = null;
        }
        areaTreeResultDto.setChild(childList);
    }

    /**
     * 根据code获取region
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<RegionResultDto> getRegionByCode(RegionRequestDto requestDto) {
        ObjectResultDto<RegionResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            RegionEntity regionEntity = regionCrudService.findByCode(requestDto.getCode());
            RegionResultDto regionResultDto = modelMapper.map(regionEntity, RegionResultDto.class);
            objectResultDto.setData(regionResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            LOG.error("根据code获取region失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取省市区编码
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<RegionCodeResultDto> getRegionCode(RegionRequestDto requestDto) {
        ObjectResultDto<RegionCodeResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            RegionCodeResultDto regionCodeResultDto = new RegionCodeResultDto();
            RegionEntity regionEntity = regionCrudService.findByCode(requestDto.getCode());
            while (regionEntity != null) {
                switch (regionEntity.getLevel()) {
                    case RegionConstant.PROVINCE_CODE:
                        regionCodeResultDto.setProvinceCode(regionEntity.getCode());
                        break;
                    case RegionConstant.CITY_CODE:
                        regionCodeResultDto.setCityCode(regionEntity.getCode());
                        break;
                    case RegionConstant.COUNTY_CODE:
                        regionCodeResultDto.setCountyCode(regionEntity.getCode());
                        break;
                    default:
                        break;
                }
                regionEntity = regionCrudService.findByCode(regionEntity.getParentCode());
            }
            objectResultDto.setData(regionCodeResultDto);
        } catch (Exception e) {
            LOG.error("获取省市区编码失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
