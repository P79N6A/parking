package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.utils.TreeUtils;
import com.zoeeasy.cloud.pms.area.AreaService;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import com.zoeeasy.cloud.pms.area.dto.request.*;
import com.zoeeasy.cloud.pms.area.dto.result.*;
import com.zoeeasy.cloud.pms.area.validator.AreaAddValidator;
import com.zoeeasy.cloud.pms.area.validator.AreaDeleteValidator;
import com.zoeeasy.cloud.pms.area.validator.AreaUpdateValidator;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingsGetResultDto;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域服务
 *
 * @author Kane
 */
@Service("areaService")
@Slf4j
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaCrudService areaCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    /**
     * 新增区域
     *
     * @param requestDto AreaAddRequestDto
     * @return 新增结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addArea(@FluentValid({AreaAddValidator.class}) AreaAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long parentId = areaCrudService.findAreaIdByAreaCode(requestDto.getParentCode());
            AreaEntity areaEntity = modelMapper.map(requestDto, AreaEntity.class);
            //判断上级区域是否存在
            if (parentId == null) {
                Long rootNodeId = areaCrudService.findAreaIdByAreaCode(AreaConstant.AREA_DEFAULT_ROOT_NODE_CODE);
                if (rootNodeId == null) {
                    AreaEntity rootNodeArea = rootNodeArea();
                    areaCrudService.insert(rootNodeArea);
                    parentId = areaCrudService.findAreaIdByAreaCode(AreaConstant.AREA_DEFAULT_ROOT_NODE_CODE);
                } else {
                    // 上级区域不存在且当前已建立全国根节点
                    resultDto.failed();
                    return resultDto;
                }
            }
            if (StringUtils.isEmpty(areaEntity.getCode())) {
                //code递增
                AreaEntity lastChild = areaCrudService.getLastChildOrNull(parentId);
                String parentCode = requestDto.getParentCode();
                String code = parentCode + "_" + String.format("%05d", AreaConstant.AREA_FIRST_CHILD_CODE);
                if (lastChild != null) {
                    String[] splits = lastChild.getCode().split("_");
                    int codeValue = Integer.valueOf(splits[splits.length - 1]) + 1;
                    code = parentCode + "_" + String.format("%05d", codeValue);
                    areaEntity.setPathCode(TreeUtils.calculateNextCode(lastChild.getPathCode()));
                    areaEntity.setLevel(lastChild.getLevel());
                }
                areaEntity.setCode(code);
            }
            areaEntity.setParentId(parentId);
            if (StringUtils.isEmpty(areaEntity.getPathCode())) {
                areaEntity.setPathCode(getNextChildCodeAsync(parentId));
            }
            areaCrudService.insert(areaEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("区域添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 插入默认的全国根节点
     *
     * @return 全国根节点id
     */
    private AreaEntity rootNodeArea() {
        AreaEntity rootNodeArea = new AreaEntity();
        rootNodeArea.setName("全国");
        rootNodeArea.setCode(AreaConstant.AREA_DEFAULT_ROOT_NODE_CODE);
        rootNodeArea.setLevel(RegionLevelEnum.COUNTRY.getValue());
        rootNodeArea.setPathCode(getNextChildCodeAsync(null));
        return rootNodeArea;
    }


    /**
     * 计算下一个子部门的pathCode
     *
     * @param parentId 父节点ID
     * @return 计算下一个子部门的pathCode
     */
    private String getNextChildCodeAsync(Long parentId) {
        AreaEntity lastChild = areaCrudService.getLastChildOrNull(parentId);
        if (lastChild != null) {
            return TreeUtils.calculateNextCode(lastChild.getPathCode());
        }
        String parentCode = null;
        if (parentId != null) {
            AreaEntity parentOrganization = areaCrudService.selectById(parentId);
            if (parentOrganization != null) {
                parentCode = parentOrganization.getPathCode();
            }
        }
        return TreeUtils.appendCode(parentCode, TreeUtils.createCode(1));
    }

    /**
     * 删除区域
     *
     * @param requestDto AreaDeleteRequestDto
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteArea(@FluentValid({AreaDeleteValidator.class}) AreaDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long id = areaCrudService.findAreaIdByAreaCode(requestDto.getCode());
            boolean success = areaCrudService.deleteById(id);
            resultDto = success ? resultDto.success() : resultDto.failed();
        } catch (Exception e) {
            log.error("区域删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新区域
     *
     * @param requestDto AreaUpdateRequestDto
     * @return 更新结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateArea(@FluentValid({AreaUpdateValidator.class}) AreaUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            AreaEntity areaEntity = new AreaEntity();
            areaEntity.setName(requestDto.getName());
            EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("code", requestDto.getCode());
            areaCrudService.update(areaEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("区域更新失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取区域
     *
     * @param requestDto AreaGetRequestDto
     * @return 获取的结果
     */
    @Override
    public ObjectResultDto<AreaResultDto> getArea(AreaGetRequestDto requestDto) {
        ObjectResultDto<AreaResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            AreaEntity areaEntity = areaCrudService.findByCode(requestDto.getCode());
            if (areaEntity != null) {
                AreaResultDto resultDto = modelMapper.map(areaEntity, AreaResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(PmsResultEnum.AREA_NOT_FOUND.getValue(),
                        PmsResultEnum.AREA_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取地区失败" + e.getMessage());
            objectResultDto.failed();

        }
        return objectResultDto;
    }

    /**
     * 分页获取子区域列表
     *
     * @param requestDto AreaQueryPagedResultRequestDto
     * @return 分页结果
     */
    @Override
    public PagedResultDto<AreaResultDto> getAreaPagedList(AreaQueryPagedResultRequestDto requestDto) {
        PagedResultDto<AreaResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
            if (areaCrudService.findAreaIdByAreaCode(requestDto.getParentCode()) == null) {
                if (AreaConstant.AREA_DEFAULT_ROOT_NODE_CODE.equals(requestDto.getParentCode())) {
                    pagedResultDto.setPageNo(requestDto.getPageNo());
                    pagedResultDto.setPageSize(requestDto.getPageSize());
                    pagedResultDto.setTotalCount(0L);
                    pagedResultDto.setItems(new ArrayList<>());
                    pagedResultDto.success();
                    return pagedResultDto;
                }
                return pagedResultDto.failed();
            }
            entityWrapper.eq("parentId", areaCrudService.findAreaIdByAreaCode(requestDto.getParentCode()));
            Page<AreaEntity> areaEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<AreaEntity> areaEntityPageList = areaCrudService.selectPage(areaEntityPage, entityWrapper);
            List<AreaResultDto> areaResultDtoList = modelMapper.map(areaEntityPageList.getRecords(), new TypeToken<List<AreaResultDto>>() {
            }.getType());
            pagedResultDto.setPageNo(areaEntityPageList.getCurrent());
            pagedResultDto.setPageSize(areaEntityPageList.getSize());
            pagedResultDto.setTotalCount(areaEntityPageList.getTotal());
            pagedResultDto.setItems(areaResultDtoList);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页地区列表失败", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取区域树
     *
     * @return 区域树
     */
    @Override
    public ListResultDto<TreeResultDto> getAreaTree(AreaTreeRequestDto requestDto) {
        ListResultDto<TreeResultDto> resultDto = new ListResultDto<>();
        try {
            List<TreeResultDto> treeResultDtoList = new ArrayList<>();
            //所有区域
            EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
            List<AreaEntity> areaEntityList = areaCrudService.selectList(entityWrapper);
            List<AreaTreeResultDto> areaTreeResultDtoList = modelMapper.map(areaEntityList,
                    new TypeToken<List<AreaTreeResultDto>>() {
                    }.getType());
            AreaTreeResultDto rootNodeArea = null;
            for (AreaTreeResultDto areaTreeResultDto : areaTreeResultDtoList) {
                if (AreaConstant.AREA_DEFAULT_ROOT_NODE_CODE.equals(areaTreeResultDto.getCode())) {
                    rootNodeArea = areaTreeResultDto;
                    break;
                }
            }
            if (rootNodeArea == null) {
                treeResultDtoList.add(modelMapper.map(rootNodeArea(), TreeResultDto.class));
            } else {
                areaTreeResultDtoList.remove(rootNodeArea);
                getChild(rootNodeArea, areaTreeResultDtoList);
                treeResultDtoList.add(modelMapper.map(rootNodeArea, TreeResultDto.class));
            }
            resultDto.setItems(treeResultDtoList);
            resultDto.success();
        } catch (Exception e) {
            log.error("区域树加载失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }


    /**
     * 获取不包含country的area树
     *
     * @return 不包含country的area树
     */
    @Override
    public ListResultDto<TreeResultDto> getAreaTreeNotIncludeCountry(AreaTreeNotIncludeCountryRequestDto requestDto) {
        ListResultDto<TreeResultDto> resultDto = new ListResultDto<>();
        try {
            List<TreeResultDto> treeResultDtoList = new ArrayList<>();
            //获取除了country的所有区域
            EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.ne("level", RegionLevelEnum.COUNTRY.getValue());
            List<AreaEntity> areaEntityList = areaCrudService.selectList(entityWrapper);
            //顶级区域
            List<AreaTreeResultDto> topAreaTreeResultDtoList = new ArrayList<>();
            if (areaEntityList != null) {
                List<AreaTreeResultDto> areaTreeResultDtoList = modelMapper.map(areaEntityList,
                        new TypeToken<List<AreaTreeResultDto>>() {
                        }.getType());
                for (AreaTreeResultDto areaTreeResultDto : areaTreeResultDtoList) {
                    if (areaTreeResultDto.getLevel().equals(RegionLevelEnum.PROVINCE.getValue())) {
                        topAreaTreeResultDtoList.add(areaTreeResultDto);
                    }
                }
                areaTreeResultDtoList.removeAll(topAreaTreeResultDtoList);
                for (AreaTreeResultDto areaTreeResultDto : topAreaTreeResultDtoList) {
                    getChild(areaTreeResultDto, areaTreeResultDtoList);
                    treeResultDtoList.add(modelMapper.map(areaTreeResultDto, TreeResultDto.class));
                }
            }
            resultDto.setItems(treeResultDtoList);
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("获取不包含全国的区域树失败" + e.getMessage());
        }
        return resultDto;
    }


    /**
     * 获取areaParking树
     *
     * @return areaParking树
     */
    @Override
    public ListResultDto<AreaParkingTreeResultDto> getAreaParkingTree(AreaParkingTreeRequestDto requestDto) {
        ListResultDto<AreaParkingTreeResultDto> resultDto = new ListResultDto<>();
        try {
            List<AreaParkingTreeResultDto> treeResultDtoList = new ArrayList<>();
            //所有区域
            EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
            List<AreaEntity> areaEntityList = areaCrudService.selectList(entityWrapper);
            List<AreaParkTreeResultDto> areaParkTreeResultDtoList = modelMapper.map(areaEntityList,
                    new TypeToken<List<AreaParkTreeResultDto>>() {
                    }.getType());
            //所有停车场
            EntityWrapper<ParkingInfoEntity> parkingEntityWrapper = new EntityWrapper<>();
            List<ParkingInfoEntity> parkingInfoEntities = parkingInfoCrudService.selectList(parkingEntityWrapper);
            List<ParkingListGetResultDto> parkingList = modelMapper.map(parkingInfoEntities, new TypeToken<List<ParkingListGetResultDto>>() {
            }.getType());
            AreaParkTreeResultDto rootNodeArea = null;
            for (AreaParkTreeResultDto areaParkTreeResultDto : areaParkTreeResultDtoList) {
                if (AreaConstant.AREA_DEFAULT_ROOT_NODE_CODE.equals(areaParkTreeResultDto.getCode())) {
                    rootNodeArea = areaParkTreeResultDto;
                    break;
                }
            }
            if (rootNodeArea == null) {
                treeResultDtoList.add(modelMapper.map(rootNodeArea(), AreaParkingTreeResultDto.class));
            } else {
                for (ParkingListGetResultDto aParkingList : parkingList) {
                    aParkingList.setCode(aParkingList.getId().toString());
                    aParkingList.setParkingId(aParkingList.getId());
                    aParkingList.setName(aParkingList.getFullName());
                }
                areaParkTreeResultDtoList.remove(rootNodeArea);
                List<ParkingListGetResultDto> parking = new ArrayList<>();
//                for (ParkingListGetResultDto parkingListGetResultDto : parkingList) {
//                    if (parkingListGetResultDto.getAreaId().equals(rootNodeArea.getId())) {
//                        parking.add(parkingListGetResultDto);
//                    }
//                }
                List<AreaParkTreeResultDto> areaParkTreeResultDtoTopList = modelMapper.map(parking,
                        new TypeToken<List<AreaParkTreeResultDto>>() {
                        }.getType());
                rootNodeArea.setChildList(areaParkTreeResultDtoTopList);
                getChild(rootNodeArea, areaParkTreeResultDtoList, parkingList);
                treeResultDtoList.add(modelMapper.map(rootNodeArea, AreaParkingTreeResultDto.class));
            }
            resultDto.setItems(treeResultDtoList);
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("获取停车场区域树失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 获取区域停车场子列表
     *
     * @param areaParkTreeResultDto     顶级区域
     * @param areaParkTreeResultDtoList 非顶级区域
     * @param parkingList               停车场
     */
    private void getChild(AreaParkTreeResultDto areaParkTreeResultDto,
                          List<AreaParkTreeResultDto> areaParkTreeResultDtoList,
                          List<ParkingListGetResultDto> parkingList) {
        List<AreaParkTreeResultDto> childList;
        if (areaParkTreeResultDto.getChildList() != null) {
            childList = areaParkTreeResultDto.getChildList();
        } else {
            childList = new ArrayList<>();
        }
        for (int i = 0; i < areaParkTreeResultDtoList.size(); i++) {
            AreaParkTreeResultDto leafNodeArea = areaParkTreeResultDtoList.get(i);
            //下级区域
            if (leafNodeArea.getParentId().equals(areaParkTreeResultDto.getId())) {
                //停车场
                List<ParkingListGetResultDto> parkingListGetResultDtoList = new ArrayList<>();
                for (int j = 0; j < parkingList.size(); j++) {
                    if (leafNodeArea.getId().equals(parkingList.get(j).getAreaId())) {
                        parkingListGetResultDtoList.add(parkingList.get(j));
                        parkingList.remove(parkingList.get(j));
                        j--;
                    }
                }
                List<AreaParkTreeResultDto> parking = modelMapper.map(parkingListGetResultDtoList,
                        new TypeToken<List<AreaParkTreeResultDto>>() {
                        }.getType());
                leafNodeArea.setChildList(parking);

                areaParkTreeResultDtoList.remove(leafNodeArea);
                getChild(leafNodeArea, areaParkTreeResultDtoList, parkingList);
                childList.add(leafNodeArea);
                i--;
            }
        }
        areaParkTreeResultDto.setChildList(childList);
    }

    /**
     * 获取areaParking树(parkingID有标识)
     *
     * @return areaParking树
     */
    @Override
    public ListResultDto<AreaParkingResultDto> getAreaParking(AreaParkingRequestDto requestDto) {
        ListResultDto<AreaParkingResultDto> resultDto = new ListResultDto<>();
        try {
            List<AreaParkingResultDto> treeResultDtoList = new ArrayList<>();
            //所有区域
            EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
            List<AreaEntity> areaEntityList = areaCrudService.selectList(entityWrapper);
            List<AreaParkResultDto> areaParkResultDtoList = modelMapper.map(areaEntityList,
                    new TypeToken<List<AreaParkResultDto>>() {
                    }.getType());
            //所有停车场
            EntityWrapper<ParkingInfoEntity> parkingEntityWrapper = new EntityWrapper<>();
            List<ParkingInfoEntity> parkingInfoEntities = parkingInfoCrudService.selectList(parkingEntityWrapper);
            List<ParkingsGetResultDto> parkingList = modelMapper.map(parkingInfoEntities, new TypeToken<List<ParkingsGetResultDto>>() {
            }.getType());
            AreaParkResultDto rootNodeArea = null;
            for (AreaParkResultDto areaParkResultDto : areaParkResultDtoList) {
                if (AreaConstant.AREA_DEFAULT_ROOT_NODE_CODE.equals(areaParkResultDto.getCode())) {
                    rootNodeArea = areaParkResultDto;
                    break;
                }
            }
            if (rootNodeArea == null) {
                treeResultDtoList.add(modelMapper.map(rootNodeArea(), AreaParkingResultDto.class));
            } else {
                for (ParkingsGetResultDto aParkingList : parkingList) {

                    aParkingList.setCode(aParkingList.getId().toString());
                    aParkingList.setName(aParkingList.getFullName());
                }
                areaParkResultDtoList.remove(rootNodeArea);
                List<ParkingsGetResultDto> parking = new ArrayList<>();
//                for (ParkingListGetResultDto parkingListGetResultDto : parkingList) {
//                    if (parkingListGetResultDto.getAreaId().equals(rootNodeArea.getId())) {
//                        parking.add(parkingListGetResultDto);
//                    }
//                }
                List<AreaParkResultDto> areaParkResultDtoTopList = modelMapper.map(parking,
                        new TypeToken<List<AreaParkResultDto>>() {
                        }.getType());
                rootNodeArea.setChildList(areaParkResultDtoTopList);
                getChild(rootNodeArea, areaParkResultDtoList, parkingList);
                treeResultDtoList.add(modelMapper.map(rootNodeArea, AreaParkingResultDto.class));
            }
            resultDto.setItems(treeResultDtoList);
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("获取停车场区域树失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 获取区域停车场子列表
     *
     * @param areaParkResultDto     顶级区域
     * @param areaParkResultDtoList 非顶级区域
     * @param parkingList           停车场
     */
    private void getChild(AreaParkResultDto areaParkResultDto,
                          List<AreaParkResultDto> areaParkResultDtoList,
                          List<ParkingsGetResultDto> parkingList) {
        List<AreaParkResultDto> childList;
        if (areaParkResultDto.getChildList() != null) {
            childList = areaParkResultDto.getChildList();
        } else {
            childList = new ArrayList<>();
        }
        for (int i = 0; i < areaParkResultDtoList.size(); i++) {
            AreaParkResultDto leafNodeArea = areaParkResultDtoList.get(i);
            //下级区域
            if (leafNodeArea.getParentId().equals(areaParkResultDto.getId())) {
                //停车场
                List<ParkingsGetResultDto> parkingListGetResultDtoList = new ArrayList<>();
                for (int j = 0; j < parkingList.size(); j++) {
                    if (leafNodeArea.getId().equals(parkingList.get(j).getAreaId())) {
                        parkingList.get(j).setCode("p" + parkingList.get(j).getId());
                        parkingListGetResultDtoList.add(parkingList.get(j));
                        parkingList.remove(parkingList.get(j));
                        j--;
                    }
                }
                List<AreaParkResultDto> parking = modelMapper.map(parkingListGetResultDtoList,
                        new TypeToken<List<AreaParkResultDto>>() {
                        }.getType());
                leafNodeArea.setChildList(parking);

                areaParkResultDtoList.remove(leafNodeArea);
                getChild(leafNodeArea, areaParkResultDtoList, parkingList);
                childList.add(leafNodeArea);
                i--;
            }
        }
        areaParkResultDto.setChildList(childList);
    }


    /**
     * 获取区域子列表
     *
     * @param areaTreeResultDto     顶级区域
     * @param areaTreeResultDtoList 非顶级区域
     */
    private void getChild(AreaTreeResultDto areaTreeResultDto, List<AreaTreeResultDto> areaTreeResultDtoList) {
        List<AreaTreeResultDto> childList = new ArrayList<>();
        for (int i = 0; i < areaTreeResultDtoList.size(); i++) {
            AreaTreeResultDto leafNodeArea = areaTreeResultDtoList.get(i);
            if (leafNodeArea.getParentId().equals(areaTreeResultDto.getId())) {
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
     * 获取详细地址
     *
     * @param requestDto DetailAddressRequestDto
     * @return ObjectResultDto<DetailAddressResultDto>
     */
    @Override
    public ObjectResultDto<DetailAddressResultDto> getDetailAddress(DetailAddressRequestDto requestDto) {
        ObjectResultDto<DetailAddressResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            String detailAddress = "";
            if (!StringUtils.isEmpty(requestDto.getProvinceCode())) {
                EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", requestDto.getProvinceCode());
                entityWrapper.eq("tenantId", requestDto.getTenantId());
                AreaEntity areaEntity = areaCrudService.findArea(entityWrapper);
                if (areaEntity != null) {
                    detailAddress += areaEntity.getName();
                }
            }
            if (!StringUtils.isEmpty(requestDto.getCityCode())) {
                EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", requestDto.getCityCode());
                entityWrapper.eq("tenantId", requestDto.getTenantId());
                AreaEntity areaEntity = areaCrudService.findArea(entityWrapper);
                if (areaEntity != null) {
                    detailAddress += areaEntity.getName();
                }
            }
            if (!StringUtils.isEmpty(requestDto.getCountyCode())) {
                EntityWrapper<AreaEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("code", requestDto.getCountyCode());
                entityWrapper.eq("tenantId", requestDto.getTenantId());
                AreaEntity areaEntity = areaCrudService.findArea(entityWrapper);
                if (areaEntity != null) {
                    detailAddress += areaEntity.getName();
                }
            }
            if (!StringUtils.isEmpty(requestDto.getAddress())) {
                detailAddress += requestDto.getAddress();
            }
            DetailAddressResultDto detailAddressResultDto = new DetailAddressResultDto();
            detailAddressResultDto.setDetailAddress(detailAddress);
            objectResultDto.setData(detailAddressResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取详细地址失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
