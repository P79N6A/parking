package com.zoeeasy.cloud.ucc.validator.organization;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.OrganizationEntity;
import com.zoeeasy.cloud.ucc.organization.cst.OrganizationConstant;
import com.zoeeasy.cloud.ucc.organization.dto.OrganizationEditRequestDto;
import com.zoeeasy.cloud.ucc.organization.validator.OrganizationEditValidator;
import com.zoeeasy.cloud.ucc.service.OrganizationCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEditValidatorImpl extends ValidatorHandler<OrganizationEditRequestDto> implements OrganizationEditValidator {

    @Autowired
    private OrganizationCrudService organizationCrudService;

    @Override
    public boolean validate(ValidatorContext context, OrganizationEditRequestDto requestDto) {
        OrganizationEntity organizationEntity = organizationCrudService.selectById(requestDto.getId());
        if (organizationEntity == null) {
            throw new ValidationException(OrganizationConstant.ORGANIZATION_NOT_EXIST);
        }
        //部门名称租户唯一
        OrganizationEntity organizationEntityName = organizationCrudService.getByName(requestDto.getName());
        if (organizationEntityName != null && !organizationEntityName.getId().equals(organizationEntity.getId())) {
            throw new ValidationException(OrganizationConstant.ORGANIZATION_NAME_EXIST);
        }
        return super.validate(context, requestDto);
    }
}