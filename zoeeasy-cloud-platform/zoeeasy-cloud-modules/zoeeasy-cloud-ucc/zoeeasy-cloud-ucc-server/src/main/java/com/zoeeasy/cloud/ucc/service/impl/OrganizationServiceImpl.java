package com.zoeeasy.cloud.ucc.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.core.utils.TreeUtils;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import com.zoeeasy.cloud.ucc.domain.OrganizationEntity;
import com.zoeeasy.cloud.ucc.organization.OrganizationService;
import com.zoeeasy.cloud.ucc.organization.dto.*;
import com.zoeeasy.cloud.ucc.organization.validator.OrganizationCreateValidator;
import com.zoeeasy.cloud.ucc.organization.validator.OrganizationDeleteValidator;
import com.zoeeasy.cloud.ucc.organization.validator.OrganizationEditValidator;
import com.zoeeasy.cloud.ucc.service.OrganizationCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("organizationService")
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationCrudService organizationCrudService;

    @Autowired
    private IdService idService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagedResultDto<OrganizationListResultDto> getOrganizationList(OrganizationPagedRequestDto requestDto) {
        PagedResultDto<OrganizationListResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<OrganizationEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            //查询指定部门下的直接子部门,不包括自身及孙部门
            if (requestDto.getParentId() != null) {
                entityWrapper.eq("parentId", requestDto.getParentId());
            } else {
                //不包括根部门
                entityWrapper.ne("parentId", 0L);
            }
            //升序排列
            entityWrapper.orderBy("pathCode");
            Page<OrganizationEntity> organizationEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<OrganizationEntity> organizationEntityPageList = this.organizationCrudService.selectPage(organizationEntityPage, entityWrapper);
            if (organizationEntityPageList != null) {
                List<OrganizationListResultDto> organizationDtoList = modelMapper.map(organizationEntityPageList.getRecords(), new TypeToken<List<OrganizationListResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(organizationEntityPageList.getCurrent());
                pagedResultDto.setPageSize(organizationEntityPageList.getSize());
                pagedResultDto.setTotalCount(organizationEntityPageList.getTotal());
                pagedResultDto.setItems(organizationDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取部门列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    @Override
    public ObjectResultDto<OrganizationResultDto> getOrganization(OrganizationGetRequestDto requestDto) {
        ObjectResultDto<OrganizationResultDto> resultDto = new ObjectResultDto<>();
        try {
            OrganizationEntity organizationEntity = this.organizationCrudService.selectById(requestDto.getId());
            if (organizationEntity != null) {
                OrganizationResultDto tenantResultDto = this.modelMapper.map(organizationEntity, OrganizationResultDto.class);
                resultDto.setData(tenantResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取部门详情失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto createOrganization(@FluentValid(value = {OrganizationCreateValidator.class})
                                                OrganizationCreateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            OrganizationEntity organizationEntity = new OrganizationEntity();
            organizationEntity.setCode(String.valueOf(idService.genId()));
            organizationEntity.setName(requestDto.getName());
            if (requestDto.getParentId() == null) {
                //如果根部门存在，默认创建在根部门下
                OrganizationEntity rootOrganization = this.organizationCrudService.getRoot();
                if (rootOrganization != null) {
                    organizationEntity.setParentId(rootOrganization.getId());
                } else {
                    //否则创建为根部门
                    organizationEntity.setParentId(0L);
                }
            } else {
                organizationEntity.setParentId(requestDto.getParentId());
            }
            String nextChildCode = this.getNextChildCodeAsync(organizationEntity.getParentId());
            organizationEntity.setPathCode(nextChildCode);
            organizationEntity.setRemarks(requestDto.getDescription());
            this.organizationCrudService.insert(organizationEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("部门创建失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto editOrganization(@FluentValid(value = {OrganizationEditValidator.class}) OrganizationEditRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            OrganizationEntity organizationEntity = new OrganizationEntity();
            organizationEntity.setId(requestDto.getId());
            organizationEntity.setName(requestDto.getName());
            organizationEntity.setRemarks(requestDto.getDescription());
            EntityWrapper<OrganizationEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq("id", requestDto.getId());
            this.organizationCrudService.update(organizationEntity, entityEntityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("部门编辑失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除部门
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteOrganization(@FluentValid(value = {OrganizationDeleteValidator.class}) OrganizationDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            this.organizationCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("部门删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取部门树列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<OrganizationTreeResultDto> getOrganizationTree(OrganizationTreeRequestDto requestDto) {
        ListResultDto<OrganizationTreeResultDto> resultDto = new ListResultDto<>();
        try {
            //查询所有部门
            List<OrganizationEntity> allOrganizations = new ArrayList<>();
            //根节点
            OrganizationEntity rootOrganization = null;
            //根节点列表
            List<OrganizationEntity> rootOrganizations = new ArrayList<>();
            //查询指定部门下的子部门
            if (requestDto.getParentId() != null) {
                rootOrganization = this.getById(requestDto.getParentId());
                if (rootOrganization != null) {

                    rootOrganizations.add(rootOrganization);
                    //查询根节点下的所有子节点
                    EntityWrapper<OrganizationEntity> entityWrapper = new EntityWrapper<>();
                    entityWrapper.like("pathCode", rootOrganization.getPathCode(), SqlLike.RIGHT);
                    allOrganizations = this.organizationCrudService.selectList(entityWrapper);
                }
            }
            //如果根节点不存在
            if (rootOrganization == null) {
                //查询所有节点
                allOrganizations = this.organizationCrudService.getAll();
                for (OrganizationEntity organizationEntity : allOrganizations) {
                    if (organizationEntity.getParentId() == null || organizationEntity.getParentId().equals(0L)) {
                        //找出所有的根节点
                        rootOrganizations.add(organizationEntity);
                    }
                }
            }
            List<OrganizationTreeResultDto> rootOrganizationNodes = modelMapper.map(rootOrganizations, new TypeToken<List<OrganizationTreeResultDto>>() {
            }.getType());
            if (CollectionUtils.isNotEmpty(rootOrganizationNodes)) {
                /* 根据OrganizationTreeResultDto类的pathCode排序 */
                Collections.sort(rootOrganizationNodes, sortByPathCode());
                //为根节点设置子节点，getChild是递归调用的
                for (OrganizationTreeResultDto nav : rootOrganizationNodes) {
                    /* 获取根节点下的所有子节点 使用getChild方法*/
                    List<OrganizationTreeResultDto> childList = getChild(nav.getId(), allOrganizations);
                    //给根节点设置子节点
                    nav.setChildren(childList);
                }
                resultDto.setItems(rootOrganizationNodes);
            } else {
                resultDto.setItems(new ArrayList<>());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取部门树失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 计算下一个子部门的pathCode
     *
     * @param parentId
     * @return
     */
    public String getNextChildCodeAsync(Long parentId) {
        OrganizationEntity lastChild = this.getLastChildOrNull(parentId);
        if (lastChild != null) {
            return TreeUtils.calculateNextCode(lastChild.getPathCode());
        }
        String parentCode = null;
        if (parentId != null) {
            OrganizationEntity parentOrganization = this.getById(parentId);
            if (parentOrganization != null) {
                parentCode = parentOrganization.getPathCode();
            }
        }
        return TreeUtils.appendCode(parentCode, TreeUtils.createCode(1));
    }

    /**
     * 获取最后一个子部门
     *
     * @param parentId
     * @return
     */
    public OrganizationEntity getLastChildOrNull(Long parentId) {
        return this.organizationCrudService.getLastChildOrNull(parentId);
    }

    /**
     * 获取部门的PathCode
     *
     * @param id
     * @return
     */
    private OrganizationEntity getById(Long id) {
        OrganizationEntity organizationEntity = this.organizationCrudService.selectById(id);
        if (organizationEntity != null) {
            return organizationEntity;
        }
        return null;
    }

    /**
     * 获取子节点
     *
     * @param parentId         父节点id
     * @param allOrganizations 所有部门列表
     * @return 每个根节点下，所有子部门列表
     */
    public List<OrganizationTreeResultDto> getChild(Long parentId, List<OrganizationEntity> allOrganizations) {
        //子菜单
        List<OrganizationTreeResultDto> childList = new ArrayList<>();
        for (OrganizationEntity organizationEntity : allOrganizations) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点
            if (organizationEntity.getParentId().equals(parentId)) {
                OrganizationTreeResultDto organizationTreeNode = new OrganizationTreeResultDto();
                organizationTreeNode.setId(organizationEntity.getId());
                organizationTreeNode.setCode(organizationEntity.getCode());
                organizationTreeNode.setName(organizationEntity.getName());
                childList.add(organizationTreeNode);
            }
        }
        //递归
        for (OrganizationTreeResultDto organizationTreeNode : childList) {
            organizationTreeNode.setChildren(getChild(organizationTreeNode.getId(), allOrganizations));
        }
        Collections.sort(childList, sortByPathCode());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (CollectionUtils.isEmpty(childList)) {
            return null;
        }
        return childList;
    }

    /*
     * 排序,根据order排序
     */
    private Comparator<OrganizationTreeResultDto> sortByPathCode() {

        return new Comparator<OrganizationTreeResultDto>() {

            /**
             *
             * @param organization1 organization1
             * @param organization2 organization2
             * @return
             */
            @Override
            public int compare(OrganizationTreeResultDto organization1, OrganizationTreeResultDto organization2) {
                String pathCode1 = organization1.getPathCode();
                String pathCode2 = organization2.getPathCode();
                int result = 0;
                if (StringUtils.isNotEmpty(pathCode1) && StringUtils.isNotEmpty(pathCode2)) {
                    result = pathCode1.compareTo(pathCode2);
                } else {
                    if (StringUtils.isEmpty(pathCode1) && StringUtils.isNotEmpty(pathCode2)) {
                        result = -1;
                    } else if (StringUtils.isNotEmpty(pathCode2) && StringUtils.isEmpty(pathCode2)) {
                        result = 1;
                    }
                }
                return result;
            }
        };
    }

}
