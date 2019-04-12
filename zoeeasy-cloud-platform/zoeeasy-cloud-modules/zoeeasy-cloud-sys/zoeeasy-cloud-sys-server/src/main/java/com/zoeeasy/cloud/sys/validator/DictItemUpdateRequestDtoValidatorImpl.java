package com.zoeeasy.cloud.sys.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.sys.dict.cst.DictConstant;
import com.zoeeasy.cloud.sys.dict.dto.DictItemUpdateRequestDto;
import com.zoeeasy.cloud.sys.dict.validator.DictItemUpdateRequestDtoValidator;
import com.zoeeasy.cloud.sys.domain.DictItemEntity;
import com.zoeeasy.cloud.sys.service.DictItemCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2019/2/26.
 * @author：zm
 */
@Component
public class DictItemUpdateRequestDtoValidatorImpl extends ValidatorHandler<DictItemUpdateRequestDto> implements DictItemUpdateRequestDtoValidator {

    @Autowired
    private DictItemCrudService dictItemCrudService;

    @Override
    public boolean validate(ValidatorContext context, DictItemUpdateRequestDto requestDto) {
        DictItemEntity dictItemEntity = dictItemCrudService.findById(requestDto.getId());
        if(dictItemEntity == null){
            throw new ValidationException(DictConstant.DICT_ID_NOT_EXIST);
        }
        DictItemEntity byDictLabel = dictItemCrudService.findByDictLabel(requestDto.getDictLabel(),dictItemEntity.getDictCode());
        if(byDictLabel != null && !requestDto.getId().equals(byDictLabel.getId())){
            throw new ValidationException(DictConstant.DICT_LABEL_EXIST);
        }
        DictItemEntity byDictValue = dictItemCrudService.findByDictValue(requestDto.getDictValue(),dictItemEntity.getDictCode());
        if(byDictValue != null && !requestDto.getId().equals(byDictValue.getId())){
            throw new ValidationException(DictConstant.DICT_VALUE_EXIST);
        }
        return true;
    }

}
