package com.zhuyitech.parking.pms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.pms.domain.CarBrand;
import com.zhuyitech.parking.pms.dto.request.car.*;
import com.zhuyitech.parking.pms.dto.result.*;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandResultDto;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandSubViewResultDto;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandViewResultDto;
import com.zhuyitech.parking.pms.enums.CarBrandLevelEnum;
import com.zhuyitech.parking.pms.enums.PmsResultEnum;
import com.zhuyitech.parking.pms.service.CarBrandCrudService;
import com.zhuyitech.parking.pms.service.api.CarBrandService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 车型服务
 *
 * @author walkman
 * @date 2017-12-01
 */
@Service("carBrandService")
@Slf4j
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandCrudService carBrandCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增车型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addCarBrand(CarBrandAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            if (StringUtils.isEmpty(requestDto.getName())) {
                return resultDto.makeResult(PmsResultEnum.CAR_BRAND_NAME_EMPTY.getValue(),
                        PmsResultEnum.CAR_BRAND_NAME_EMPTY.getComment());
            }
            CarBrand carBrandExist = carBrandCrudService.findByName(requestDto.getName());
            if (carBrandExist != null) {
                return resultDto.makeResult(PmsResultEnum.CAR_BRAND_NAME_EXISTS.getValue(),
                        PmsResultEnum.CAR_BRAND_NAME_EXISTS.getComment());
            }
            CarBrand carBrand = modelMapper.map(requestDto, CarBrand.class);
            if (requestDto.getParentId() == null) {
                carBrand.setParentId(0L);
            } else {
                CarBrand parentCarBrand = carBrandCrudService.selectById(requestDto.getParentId());
                if (parentCarBrand == null) {
                    return resultDto.makeResult(PmsResultEnum.CAR_BRAND_PARENT_NOT_FOUND.getValue(),
                            PmsResultEnum.CAR_BRAND_PARENT_NOT_FOUND.getComment());
                }
            }
            carBrandCrudService.insert(carBrand);
            resultDto.success();
        } catch (Exception e) {
            log.error("车型添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除车型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteCarBrand(CarBrandDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Integer childrenCount = carBrandCrudService.getCountByParentId(requestDto.getId());
            if (childrenCount > 0) {
                return resultDto.makeResult(PmsResultEnum.CAR_BRAND_CHILDREN_EXISTS.getValue(),
                        PmsResultEnum.CAR_BRAND_CHILDREN_EXISTS.getComment());
            }
            carBrandCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("车型删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新车型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateCarBrand(CarBrandUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getName())) {
                return resultDto.makeResult(PmsResultEnum.CAR_BRAND_NAME_EMPTY.getValue(),
                        PmsResultEnum.CAR_BRAND_NAME_EMPTY.getComment());
            }
            CarBrand carOld = carBrandCrudService.selectById(requestDto.getId());
            if (carOld == null) {
                return resultDto.makeResult(PmsResultEnum.CAR_BRAND_NOT_FOUND.getValue(),
                        PmsResultEnum.CAR_BRAND_NOT_FOUND.getComment());
            }
            if (!requestDto.getName().equals(carOld.getName())) {
                CarBrand carBrandExist = carBrandCrudService.findByName(requestDto.getName());
                if (carBrandExist != null) {
                    return resultDto.makeResult(PmsResultEnum.CAR_BRAND_NAME_EXISTS.getValue(),
                            PmsResultEnum.CAR_BRAND_NAME_EXISTS.getComment());
                }
            }
            CarBrand carBrand = modelMapper.map(requestDto, CarBrand.class);
            EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            carBrandCrudService.update(carBrand, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("车型更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取车型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<CarBrandResultDto> getCarBrand(CarBrandGetRequestDto requestDto) {

        ObjectResultDto<CarBrandResultDto> objectResultDto = new ObjectResultDto();
        try {
            CarBrand carBrand = carBrandCrudService.selectById(requestDto.getId());
            if (carBrand != null) {
                CarBrandResultDto resultDto = modelMapper.map(carBrand, CarBrandResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(PmsResultEnum.CAR_BRAND_NOT_FOUND.getValue(),
                        PmsResultEnum.CAR_BRAND_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("车型获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取车型列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<CarBrandResultDto> getCarBrandList(CarBrandListGetRequestDto requestDto) {
        ListResultDto<CarBrandResultDto> listResultDto = new ListResultDto();
        try {
            EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (!StringUtils.isEmpty(requestDto.getFullName())) {
                entityWrapper.like("fullName", requestDto.getFullName());
            }
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            if (requestDto.getDepth() != null) {
                entityWrapper.eq("depth", requestDto.getDepth());
            }
            if (!StringUtils.isEmpty(requestDto.getInitial())) {
                entityWrapper.eq("initial", requestDto.getInitial());
            }
            if (!StringUtils.isEmpty(requestDto.getYearType())) {
                entityWrapper.eq("yearType", requestDto.getYearType());
            }
            if (!StringUtils.isEmpty(requestDto.getSizeType())) {
                entityWrapper.eq("sizeType", requestDto.getSizeType());
            }
            if (!StringUtils.isEmpty(requestDto.getSaleState())) {
                entityWrapper.eq("saleState", requestDto.getSaleState());
            }
            if (!StringUtils.isEmpty(requestDto.getProductionState())) {
                entityWrapper.eq("productionState", requestDto.getProductionState());
            }
            List<CarBrand> carBrandList = carBrandCrudService.selectList(entityWrapper);
            if (!carBrandList.isEmpty()) {
                List<CarBrandResultDto> carBrandResultDtoList = modelMapper.map(carBrandList, new TypeToken<List<CarBrandResultDto>>() {
                }.getType());
                listResultDto.setItems(carBrandResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("车型获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取车型列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<CarBrandResultDto> getCarBrandPagedList(CarBrandQueryPagedResultRequestDto requestDto) {
        PagedResultDto<CarBrandResultDto> pagedResultDto = new PagedResultDto();
        try {

            EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (!StringUtils.isEmpty(requestDto.getFullName())) {
                entityWrapper.like("name", requestDto.getFullName());
            }
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            if (requestDto.getDepth() != null) {
                entityWrapper.eq("depth", requestDto.getDepth());
            }
            if (!StringUtils.isEmpty(requestDto.getInitial())) {
                entityWrapper.eq("initial", requestDto.getInitial());
            }
            if (!StringUtils.isEmpty(requestDto.getYearType())) {
                entityWrapper.eq("yearType", requestDto.getYearType());
            }
            if (!StringUtils.isEmpty(requestDto.getSizeType())) {
                entityWrapper.eq("sizeType", requestDto.getSizeType());
            }
            if (!StringUtils.isEmpty(requestDto.getSaleState())) {
                entityWrapper.eq("saleState", requestDto.getSaleState());
            }
            if (!StringUtils.isEmpty(requestDto.getProductionState())) {
                entityWrapper.eq("productionState", requestDto.getProductionState());
            }
            Page<CarBrand> carBrandPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<CarBrand> carBrandPageList = carBrandCrudService.selectPage(carBrandPage, entityWrapper);
            if (carBrandPageList != null) {

                List<CarBrandResultDto> carBrandDtoList = modelMapper.map(carBrandPageList.getRecords(), new TypeToken<List<CarBrandResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(carBrandPageList.getCurrent());
                pagedResultDto.setPageSize(carBrandPageList.getSize());
                pagedResultDto.setTotalCount(carBrandPageList.getTotal());
                pagedResultDto.setItems(carBrandDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("车型获取失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取车品牌
     *
     * @return
     */
    @Override
    public ListResultDto<CarBrandViewResultDto> getCarBrandViewList(CarBrandViewGetRequestDto requestDto) {
        ListResultDto<CarBrandViewResultDto> listResultDto = new ListResultDto();
        try {

            EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("depth", CarBrandLevelEnum.CAR_BRAND.getValue());
            if (StringUtils.isNotEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (StringUtils.isNotEmpty(requestDto.getName())) {
                entityWrapper.like("fullName", requestDto.getFullName());
            }
            if (StringUtils.isNotEmpty(requestDto.getInitial())) {
                entityWrapper.eq("initial", requestDto.getInitial());
            }
            List<CarBrand> carBrandList = carBrandCrudService.selectList(entityWrapper);
            if (!carBrandList.isEmpty()) {

                List<CarBrandViewResultDto> carBrandViewResultDtoList = modelMapper.map(carBrandList, new TypeToken<List<CarBrandViewResultDto>>() {
                }.getType());

                //按首字母排序
                listResultDto.setItems(carBrandViewResultDtoList.stream()
                        .sorted(Comparator.comparing(CarBrandViewResultDto::getInitial)).collect(Collectors.toList()));
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("品牌获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车品牌及其车型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<CarBrandSubViewResultDto> getCarBrandSubViewList(CarBrandSubViewGetRequestDto requestDto) {
        ListResultDto<CarBrandSubViewResultDto> listResultDto = new ListResultDto();
        try {

            EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("depth", CarBrandLevelEnum.CAR_SUB_BRAND.getValue());
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            }
            if (StringUtils.isNotEmpty(requestDto.getName())) {
                entityWrapper.eq("name", requestDto.getName());
            }
            if (StringUtils.isNotEmpty(requestDto.getName())) {
                entityWrapper.like("fullName", requestDto.getFullName());
            }
            if (StringUtils.isNotEmpty(requestDto.getInitial())) {
                entityWrapper.eq("initial", requestDto.getInitial());
            }
            List<CarBrand> carBrandSubList = carBrandCrudService.selectList(entityWrapper);
            List<CarBrandSubViewResultDto> carBrandSubViewResultDtoList = new ArrayList<>();
            if (!carBrandSubList.isEmpty()) {

                for (CarBrand carBrandSub : carBrandSubList) {

                    CarBrandSubViewResultDto carBrandSubView = modelMapper.map(carBrandSub, CarBrandSubViewResultDto.class);
                    EntityWrapper<CarBrand> ewCarType = new EntityWrapper<>();
                    ewCarType.eq("depth", CarBrandLevelEnum.CAR_MODEL.getValue());
                    ewCarType.eq("parentId", carBrandSub.getId());
                    List<CarBrand> carTypeList = carBrandCrudService.selectList(ewCarType);
                    if (carTypeList != null && !carTypeList.isEmpty()) {

                        List<CarTypeViewResultDto> carBrandViewResultDtoList = modelMapper.map(carTypeList, new TypeToken<List<CarTypeViewResultDto>>() {
                        }.getType());

                        //车型按首字母排序
                        carBrandSubView.setCarTypes(carBrandViewResultDtoList.stream()
                                .sorted(Comparator.comparing(CarTypeViewResultDto::getInitial)).collect(Collectors.toList()));
                    }
                    carBrandSubViewResultDtoList.add(carBrandSubView);
                }
                listResultDto.setItems(carBrandSubViewResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("车型获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }


    @Override
    public ListResultDto<ComboboxItemDto> getCarBrandListByDepth(CarBrandDepthGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            if (null == requestDto.getDepth()) {
                return listResultDto.makeResult(PmsResultEnum.DEPTH_EMPTY.getValue(), PmsResultEnum.DEPTH_EMPTY.getComment());
            }
            if (requestDto.getDepth().equals(CarBrandLevelEnum.CAR_BRAND.getValue())) {
                listResultDto.success();
                return listResultDto;
            }
            EntityWrapper<CarBrand> ewCarType = new EntityWrapper<>();
            ewCarType.eq("depth", requestDto.getDepth() - 1);
            List<CarBrand> list = carBrandCrudService.selectList(ewCarType);
            List<ComboboxItemDto> comboboxItemDtoList;
            if (null != list && !list.isEmpty()) {
                comboboxItemDtoList = modelMapper.map(list, new TypeToken<List<ComboboxItemDto>>() {
                }.getType());
                listResultDto.setItems(comboboxItemDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }


}
