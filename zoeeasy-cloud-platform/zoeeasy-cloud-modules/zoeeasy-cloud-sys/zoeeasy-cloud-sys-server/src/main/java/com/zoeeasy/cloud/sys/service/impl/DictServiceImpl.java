package com.zoeeasy.cloud.sys.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.sys.dict.DictService;
import com.zoeeasy.cloud.sys.dict.dto.*;
import com.zoeeasy.cloud.sys.dict.validator.DictItemAddRequestDtoValidator;
import com.zoeeasy.cloud.sys.dict.validator.DictItemUpdateRequestDtoValidator;
import com.zoeeasy.cloud.sys.domain.DictItemEntity;
import com.zoeeasy.cloud.sys.domain.DictTypeEntity;
import com.zoeeasy.cloud.sys.enums.StatusEnum;
import com.zoeeasy.cloud.sys.service.DictItemCrudService;
import com.zoeeasy.cloud.sys.service.DictTypeCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典管理服务
 *
 * @author walkman
 */
@Slf4j
@Service("dictService")
public class DictServiceImpl implements DictService {

    @Autowired
    private DictTypeCrudService dictTypeCrudService;

    @Autowired
    private DictItemCrudService dictItemCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取字典类别列表
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ListResultDto<DictTypeResultDto> getDictTypeList(DictTypeListRequestDto requestDto) {
        ListResultDto<DictTypeResultDto> resultDto = new ListResultDto<>();
        try {
            //获取系统内置参数根列表
            List<DictTypeEntity> rootParamType = dictTypeCrudService.findStaticDictType("");
            if (CollectionUtils.isNotEmpty(rootParamType)) {
                ArrayList<DictTypeResultDto> root = new ArrayList<>();
                for (DictTypeEntity dictTypeEntity : rootParamType) {
                    DictTypeResultDto dictTypeResultDto = modelMapper.map(dictTypeEntity, DictTypeResultDto.class);
                    List<DictTypeEntity> childParamType = dictTypeCrudService.findStaticDictType(dictTypeEntity.getDictCode());
                    if (CollectionUtils.isNotEmpty(childParamType)) {
                        List<DictTypeResultDto> child = modelMapper.map(childParamType, new TypeToken<List<DictTypeResultDto>>() {
                        }.getType());
                        dictTypeResultDto.setChild(child);
                    }
                    root.add(dictTypeResultDto);
                }
                resultDto.setItems(root);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取字典类别列表失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取字典类别
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<DictTypeResultDto> getDictType(DictTypeGetRequestDto requestDto) {
        ObjectResultDto<DictTypeResultDto> resultDto = new ObjectResultDto<>();
        return resultDto;
    }

    /**
     * 检验字典类别是否可用
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<DictTypeCheckResultDto> checkDictType(DictTypeCheckRequestDto requestDto) {
        ObjectResultDto<DictTypeCheckResultDto> resultDto = new ObjectResultDto<>();
        try {
            DictTypeCheckResultDto dictTypeCheckResultDto = new DictTypeCheckResultDto();
            DictTypeEntity byDictCode = dictTypeCrudService.findByDictCode(requestDto.getDictCode());
            if (byDictCode != null) {
                if (StatusEnum.NOT_AVAILABLE.getValue().equals(byDictCode.getStatus())) {
                    dictTypeCheckResultDto.setMessage(StatusEnum.NOT_AVAILABLE.getComment());
                    dictTypeCheckResultDto.setStatus(byDictCode.getStatus());
                } else {
                    dictTypeCheckResultDto.setMessage(StatusEnum.AVAILABLE.getComment());
                    dictTypeCheckResultDto.setStatus(byDictCode.getStatus());
                }
            }
            resultDto.setData(dictTypeCheckResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("字典项效验失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加字典类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addDictType(DictTypeAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        return resultDto;
    }

    /**
     * 修改字典类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateDictType(DictTypeUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        return resultDto;
    }

    /**
     * 删除字典类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteDictType(DictTypeDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        return resultDto;
    }

    /**
     * 根据字典类型获取字典项列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<DictTypeItemListResultDto> getDictTypeItemList(DictTypeItemListRequestDto requestDto) {
        ListResultDto<DictTypeItemListResultDto> resultDto = new ListResultDto<>();
        try {
            List<DictItemEntity> staticDictCode = dictItemCrudService.findByDictCode(requestDto.getDictCode());
            //获取租户下字典数据
            Wrapper<DictItemEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("dictCode", requestDto.getDictCode());
            List<DictItemEntity> tenantDictItem = dictItemCrudService.selectList(entityWrapper);
            List<DictTypeItemListResultDto> dictTypeItemListResultDto;
            if (CollectionUtils.isNotEmpty(tenantDictItem)) {
                List<String> dictLabelList = tenantDictItem.stream().map(DictItemEntity::getDictLabel).collect(Collectors.toList());
                for (String dictLabel : dictLabelList) {
                    staticDictCode.removeIf(p -> p.getDictLabel().equals(dictLabel));
                }
                if (CollectionUtils.isNotEmpty(staticDictCode)) {
                    List<DictItemEntity> tempDictItem = new ArrayList<>(staticDictCode);
                    tempDictItem.addAll(tenantDictItem);
                    tempDictItem.sort(Comparator.comparing(DictItemEntity::getSort));
                    dictTypeItemListResultDto = modelMapper.map(tempDictItem, new TypeToken<List<DictTypeItemListResultDto>>() {
                    }.getType());
                } else {
                    dictTypeItemListResultDto = modelMapper.map(tenantDictItem, new TypeToken<List<DictTypeItemListResultDto>>() {
                    }.getType());
                }
            } else {
                dictTypeItemListResultDto = modelMapper.map(staticDictCode, new TypeToken<List<DictTypeItemListResultDto>>() {
                }.getType());
            }
            resultDto.setItems(dictTypeItemListResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据字典类型获取字典项列表失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据字典项ID获取字典
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<DictItemResultDto> getDictItem(DictItemGetRequestDto requestDto) {
        ObjectResultDto<DictItemResultDto> resultDto = new ObjectResultDto<>();
        try {
            DictItemEntity dictItemEntity = dictItemCrudService.findById(requestDto.getId());
            if (dictItemEntity != null) {
                DictItemResultDto map = modelMapper.map(dictItemEntity, DictItemResultDto.class);
                resultDto.setData(map);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("根据字典项ID获取字典失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 检验字典项是否可用
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<DictItemCheckResultDto> checkDictItem(DictItemCheckRequestDto requestDto) {
        ObjectResultDto<DictItemCheckResultDto> resultDto = new ObjectResultDto<>();
        try {
            DictItemCheckResultDto dictItemCheckResultDto = new DictItemCheckResultDto();
            DictItemEntity dictItemEntity = dictItemCrudService.findById(requestDto.getId());
            if (dictItemEntity != null) {
                if (StatusEnum.NOT_AVAILABLE.getValue().equals(dictItemEntity.getStatus())) {
                    dictItemCheckResultDto.setMessage(StatusEnum.NOT_AVAILABLE.getComment());
                    dictItemCheckResultDto.setStatus(dictItemEntity.getStatus());
                } else {
                    dictItemCheckResultDto.setMessage(StatusEnum.AVAILABLE.getComment());
                    dictItemCheckResultDto.setStatus(dictItemEntity.getStatus());
                }
            }
            resultDto.setData(dictItemCheckResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("字典项效验失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加字典项
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addDictItem(@FluentValid({DictItemAddRequestDtoValidator.class}) DictItemAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            DictItemEntity dictItemEntity = modelMapper.map(requestDto, DictItemEntity.class);
            dictItemCrudService.insert(dictItemEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加字典项失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改字典项
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateDictItem(@FluentValid({DictItemUpdateRequestDtoValidator.class}) DictItemUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Wrapper<DictItemEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            DictItemEntity dictItemEntity = dictItemCrudService.selectOne(entityWrapper);
            if (dictItemEntity != null) {
                DictItemEntity updateDictItem = new DictItemEntity();
                updateDictItem.setDictLabel(requestDto.getDictLabel());
                updateDictItem.setDictValue(requestDto.getDictValue());
                updateDictItem.setSort(requestDto.getSort());
                updateDictItem.setRemark(requestDto.getRemark());
                updateDictItem.setStatus(requestDto.getStatus());
                dictItemCrudService.update(updateDictItem, entityWrapper);
            } else {
                DictItemEntity staticDictItemEntity = dictItemCrudService.findByEntityWrapper(entityWrapper);
                DictItemEntity createDictItemEntity = modelMapper.map(staticDictItemEntity, staticDictItemEntity.getClass());
                createDictItemEntity.setTenantId(null);
                createDictItemEntity.setDictLabel(requestDto.getDictLabel());
                createDictItemEntity.setDictValue(requestDto.getDictValue());
                createDictItemEntity.setRemark(requestDto.getRemark());
                createDictItemEntity.setSort(requestDto.getSort());
                createDictItemEntity.setStatus(requestDto.getStatus());
                dictItemCrudService.insert(createDictItemEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("修改字典项失败" + e.getMessage());
            resultDto.failed();
        }

        return resultDto;
    }

    /**
     * 删除字典项
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteDictItem(DictItemDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            dictItemCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("删除字典项失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
