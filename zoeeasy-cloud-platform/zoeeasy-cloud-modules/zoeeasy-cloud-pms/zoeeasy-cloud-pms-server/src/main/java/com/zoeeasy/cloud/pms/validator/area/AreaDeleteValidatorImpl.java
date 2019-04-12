package com.zoeeasy.cloud.pms.validator.area;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.area.cst.AreaConstant;
import com.zoeeasy.cloud.pms.area.dto.request.AreaDeleteRequestDto;
import com.zoeeasy.cloud.pms.area.validator.AreaDeleteValidator;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaDeleteValidatorImpl extends ValidatorHandler<AreaDeleteRequestDto> implements AreaDeleteValidator {
    @Autowired
    private AreaCrudService areaCrudService;
    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;


    @Override
    public boolean validate(ValidatorContext context, AreaDeleteRequestDto requestDto) {
        AreaEntity entity = areaCrudService.findByCode(requestDto.getCode());
        if (entity == null) {
            throw new ValidationException(AreaConstant.AREA_NOT_EXIT);
        }
        //存在下级区域或存在停车场，则删除失败
        int size = areaCrudService.getCountByParentId(entity.getId());
        if (size > 0) {
            throw new ValidationException(AreaConstant.AREA_CHILD_EXIT);
        }
        String pathCode = entity.getPathCode();
        EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.like("pathCode", pathCode, SqlLike.RIGHT);
        int parkingListSize = parkingInfoCrudService.selectCount(entityWrapper);
        if (parkingListSize > 0) {
            throw new ValidationException(AreaConstant.AREA_PARK_EXIT);
        }
        return super.validate(context, requestDto);
    }
}
