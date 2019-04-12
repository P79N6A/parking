package com.zoeeasy.cloud.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.sys.domain.ParamItemEntity;
import com.zoeeasy.cloud.sys.domain.ParamTypeEntity;
import com.zoeeasy.cloud.sys.parameter.ParameterService;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamGetRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamGetResultDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemGetByIdRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemGetRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemUpdateRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamTypeRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.result.ParamItemResultDto;
import com.zoeeasy.cloud.sys.parameter.dto.result.ParamTypeResultDto;
import com.zoeeasy.cloud.sys.service.ParamItemCrudService;
import com.zoeeasy.cloud.sys.service.ParamTypeCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 操作日志服务
 *
 * @author walkman
 */
@Service("parameterService")
@Slf4j
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParamTypeCrudService paramTypeCrudService;

    @Autowired
    private ParamItemCrudService paramItemCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /*
     * 获取参数值
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParamGetResultDto> paramGet(ParamGetRequestDto requestDto) {
        ObjectResultDto<ParamGetResultDto> objectResultDto = new ObjectResultDto<>();
//        try {
//            EntityWrapper<ParameterEntity> entityWrapper = new EntityWrapper<>();
//            entityWrapper.eq("tenantId", requestDto.getTenantId());
//            entityWrapper.eq("level", ParamLevelEnum.PARAM.getValue());
//            entityWrapper.eq("type", requestDto.getType());
//            entityWrapper.eq("paramKey", requestDto.getParamKey());
//            ParameterEntity parameterEntity = parameterCrudService.getParam(entityWrapper);
//            if (null != parameterEntity) {
//                ParamGetResultDto paramGetResultDto = modelMapper.map(parameterEntity, ParamGetResultDto.class);
//                objectResultDto.setData(paramGetResultDto);
//                return objectResultDto.success();
//            }
//            EntityWrapper<ParameterEntity> entity = new EntityWrapper<>();
//            entity.eq("level", ParamLevelEnum.PARAM.getValue());
//            entity.eq("type", requestDto.getType());
//            entity.eq("paramKey", requestDto.getParamKey());
//            parameterEntity = parameterCrudService.getParam(entity);
//            if (null != parameterEntity) {
//                ParamGetResultDto paramGetResultDto = modelMapper.map(parameterEntity, ParamGetResultDto.class);
//                objectResultDto.setData(paramGetResultDto);
//            }
//            objectResultDto.success();
//        } catch (Exception e) {
//            log.error("获取参数值失败" + e.getMessage());
//            objectResultDto.failed();
//        }
        return objectResultDto;
    }

    /**
     * 修改参数
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto updateParam(ParamItemUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //根据KEY获取租户下的参数项
            Wrapper<ParamItemEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("paramKey", requestDto.getParamKey());
            ParamItemEntity tenementParamItemEntity = paramItemCrudService.selectOne(entityWrapper);
            if (tenementParamItemEntity != null) {
                //更新租户下的值
                ParamItemEntity updateParamItem = new ParamItemEntity();
                updateParamItem.setParamValue(requestDto.getParamValue());
                updateParamItem.setRemark(requestDto.getRemark());
                paramItemCrudService.update(updateParamItem, entityWrapper);
            } else {
                //获取静态模板的值
                ParamItemEntity staticParamItemEntity = paramItemCrudService.findByEntityWrapper(entityWrapper);
                //创建租户下的参数
                ParamItemEntity createParamItemEntity = modelMapper.map(staticParamItemEntity, staticParamItemEntity.getClass());
                createParamItemEntity.setId(null);
                createParamItemEntity.setParamValue(requestDto.getParamValue());
                createParamItemEntity.setRemark(requestDto.getRemark());
                paramItemCrudService.insert(createParamItemEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("修改参数失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取参数类型列表
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ListResultDto<ParamTypeResultDto> getParamTypeList(ParamTypeRequestDto requestDto) {
        ListResultDto<ParamTypeResultDto> resultDto = new ListResultDto<>();
        try {
            //获取系统内置参数根列表
            List<ParamTypeEntity> rootParamType = paramTypeCrudService.findStaticParamType("");
            if (CollectionUtils.isNotEmpty(rootParamType)) {
                ArrayList<ParamTypeResultDto> root = new ArrayList<>();
                for (ParamTypeEntity paramTypeEntity : rootParamType) {
                    ParamTypeResultDto paramTypeResultDto = modelMapper.map(paramTypeEntity, ParamTypeResultDto.class);
                    List<ParamTypeEntity> childParamType = paramTypeCrudService.findStaticParamType(paramTypeEntity.getParamCode());
                    if (CollectionUtils.isNotEmpty(childParamType)) {
                        List<ParamTypeResultDto> child = modelMapper.map(childParamType, new TypeToken<List<ParamTypeResultDto>>() {
                        }.getType());
                        paramTypeResultDto.setChild(child);
                    }
                    root.add(paramTypeResultDto);
                }
                resultDto.setItems(root);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取参数类型列表失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据参数类型获取参数数据列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParamItemResultDto> getParamItemList(ParamItemGetRequestDto requestDto) {
        ListResultDto<ParamItemResultDto> resultDto = new ListResultDto<>();
        try {
            List<ParamItemEntity> staticParamItems = paramItemCrudService.findByParamCode(requestDto.getParamCode());
            //获取租户下的参数数据
            Wrapper<ParamItemEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("paramCode", requestDto.getParamCode());
            List<ParamItemEntity> tenantParamItem = paramItemCrudService.selectList(entityWrapper);
            List<ParamItemResultDto> paramItemResultDtos;
            if (CollectionUtils.isNotEmpty(tenantParamItem)) {
                List<String> paramKeyList = tenantParamItem.stream().map(ParamItemEntity::getParamKey).collect(Collectors.toList());
                for (String paramKey : paramKeyList) {
                    staticParamItems.removeIf(p -> p.getParamKey().equals(paramKey));
                }
                if (CollectionUtils.isNotEmpty(staticParamItems)) {
                    List<ParamItemEntity> tempParamItem = new ArrayList<>(staticParamItems);
                    tempParamItem.addAll(tenantParamItem);
                    tempParamItem.sort(Comparator.comparing(ParamItemEntity::getSort));
                    paramItemResultDtos = modelMapper.map(tempParamItem, new TypeToken<List<ParamItemResultDto>>() {
                    }.getType());

                } else {
                    paramItemResultDtos = modelMapper.map(tenantParamItem, new TypeToken<List<ParamItemResultDto>>() {
                    }.getType());
                }
            } else {
                paramItemResultDtos = modelMapper.map(staticParamItems, new TypeToken<List<ParamItemResultDto>>() {
                }.getType());
            }
            resultDto.setItems(paramItemResultDtos);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据参数类型获取参数数据列表失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据参数项ID获取参数
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParamItemResultDto> getParamItemById(ParamItemGetByIdRequestDto requestDto) {
        ObjectResultDto<ParamItemResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParamItemEntity paramItemEntity = paramItemCrudService.findById(requestDto.getId());
            if (paramItemEntity != null) {
                ParamItemResultDto map = modelMapper.map(paramItemEntity, ParamItemResultDto.class);
                resultDto.setData(map);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("根据参数项ID获取参数失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
