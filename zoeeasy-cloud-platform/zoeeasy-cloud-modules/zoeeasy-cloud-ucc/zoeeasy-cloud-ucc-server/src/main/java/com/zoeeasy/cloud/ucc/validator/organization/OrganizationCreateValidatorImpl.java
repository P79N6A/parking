package com.zoeeasy.cloud.ucc.validator.organization;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.OrganizationEntity;
import com.zoeeasy.cloud.ucc.organization.cst.OrganizationConstant;
import com.zoeeasy.cloud.ucc.organization.dto.OrganizationCreateRequestDto;
import com.zoeeasy.cloud.ucc.organization.validator.OrganizationCreateValidator;
import com.zoeeasy.cloud.ucc.service.OrganizationCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationCreateValidatorImpl extends ValidatorHandler<OrganizationCreateRequestDto> implements OrganizationCreateValidator {

    @Autowired
    private OrganizationCrudService organizationCrudService;

    @Override
    public boolean validate(ValidatorContext context, OrganizationCreateRequestDto requestDto) {
        if (requestDto.getParentId() != null) {
            OrganizationEntity parentOrganizationEntity = organizationCrudService.selectById(requestDto.getParentId());
            if (parentOrganizationEntity == null) {
                throw new ValidationException(OrganizationConstant.ORGANIZATION_PARENT_NOT_EXIST);
            }
        }
        //部门名称租户唯一
        OrganizationEntity organizationEntity = organizationCrudService.getByName(requestDto.getName());
        if (organizationEntity != null) {
            throw new ValidationException(OrganizationConstant.ORGANIZATION_NAME_EXIST);
        }
        return super.validate(context, requestDto);
    }

}