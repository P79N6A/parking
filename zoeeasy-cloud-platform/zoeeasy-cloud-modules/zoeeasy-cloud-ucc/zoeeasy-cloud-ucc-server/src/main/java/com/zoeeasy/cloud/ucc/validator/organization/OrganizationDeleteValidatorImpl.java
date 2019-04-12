package com.zoeeasy.cloud.ucc.validator.organization;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.ucc.domain.OrganizationEntity;
import com.zoeeasy.cloud.ucc.domain.UserOrganizationEntity;
import com.zoeeasy.cloud.ucc.organization.cst.OrganizationConstant;
import com.zoeeasy.cloud.ucc.organization.dto.OrganizationDeleteRequestDto;
import com.zoeeasy.cloud.ucc.organization.validator.OrganizationDeleteValidator;
import com.zoeeasy.cloud.ucc.service.OrganizationCrudService;
import com.zoeeasy.cloud.ucc.service.UserOrganizationCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 部门删除校验器
 *
 * @author walkman
 * @since 2018-08-28
 */
@Component
public class OrganizationDeleteValidatorImpl extends ValidatorHandler<OrganizationDeleteRequestDto> implements OrganizationDeleteValidator {

    @Autowired
    private OrganizationCrudService organizationCrudService;

    @Autowired
    private UserOrganizationCrudService userOrganizationCrudService;

    @Override
    public boolean validate(ValidatorContext context, OrganizationDeleteRequestDto requestDto) {
        OrganizationEntity organizationEntity = this.organizationCrudService.selectById(requestDto.getId());
        if (organizationEntity == null) {
            throw new ValidationException(OrganizationConstant.ORGANIZATION_NOT_EXIST);
        }
        //存在子部门或部门存在用户不允许删除
        List<OrganizationEntity> childOrganization = this.organizationCrudService.getChildren(organizationEntity.getPathCode());
        if (!childOrganization.isEmpty()) {
            throw new ValidationException(OrganizationConstant.ORGANIZATION_CHILDERE_EXIST);
        }
        List<UserOrganizationEntity> userOrganizationEntityList = this.userOrganizationCrudService.findByOrganizationId(requestDto.getId());
        if (!userOrganizationEntityList.isEmpty()) {
            throw new ValidationException(OrganizationConstant.ORGANIZATION_USER_EXIST);
        }
        return super.validate(context, requestDto);
    }
}