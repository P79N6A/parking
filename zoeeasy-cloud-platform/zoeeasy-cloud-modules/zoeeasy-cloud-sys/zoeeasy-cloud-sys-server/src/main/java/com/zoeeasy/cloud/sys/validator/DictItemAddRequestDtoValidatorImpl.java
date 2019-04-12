package com.zoeeasy.cloud.sys.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.sys.dict.cst.DictConstant;
import com.zoeeasy.cloud.sys.dict.dto.DictItemAddRequestDto;
import com.zoeeasy.cloud.sys.dict.validator.DictItemAddRequestDtoValidator;
import com.zoeeasy.cloud.sys.domain.DictItemEntity;
import com.zoeeasy.cloud.sys.service.DictItemCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @date: 2019/2/26.
 * @author：zm
 */
@Component
public class DictItemAddRequestDtoValidatorImpl extends ValidatorHandler<DictItemAddRequestDto> implements DictItemAddRequestDtoValidator {

    @Autowired
    private DictItemCrudService dictItemCrudService;

    @Override
    public boolean validate(ValidatorContext context, DictItemAddRequestDto requestDto) {
        List<DictItemEntity> byDictCode = dictItemCrudService.findByDictCode(requestDto.getDictCode());
        if(byDictCode == null){
            throw new ValidationException(DictConstant.DICT_CODE_EXIST);
        }
        DictItemEntity byDictLabel = dictItemCrudService.findByDictLabel(requestDto.getDictLabel(),requestDto.getDictCode());
        if(byDictLabel != null){
            throw new ValidationException(DictConstant.DICT_LABEL_EXIST);
        }
        DictItemEntity byDictValue = dictItemCrudService.findByDictValue(requestDto.getDictValue(),requestDto.getDictCode());
        if(byDictValue != null){
            throw new ValidationException(DictConstant.DICT_VALUE_EXIST);
        }
        return true;
    }
}
