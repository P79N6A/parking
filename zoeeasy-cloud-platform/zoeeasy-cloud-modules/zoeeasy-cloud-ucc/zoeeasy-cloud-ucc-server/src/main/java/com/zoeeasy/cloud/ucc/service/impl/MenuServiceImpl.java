package com.zoeeasy.cloud.ucc.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.ucc.domain.MenuEntity;
import com.zoeeasy.cloud.ucc.menu.MenuService;
import com.zoeeasy.cloud.ucc.menu.dto.MenuResultDto;
import com.zoeeasy.cloud.ucc.menu.dto.request.*;
import com.zoeeasy.cloud.ucc.menu.validator.MenuCreateValidator;
import com.zoeeasy.cloud.ucc.service.MenuCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * menuService
 */
@Service("menuService")
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuCrudService menuCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取所有静态菜单
     *
     * @return
     */
    @Override
    public ListResultDto<MenuResultDto> getAllStaticMenu(AllStaticMenuListGetRequestDto requestDto) {
        ListResultDto<MenuResultDto> listResultDto = new PagedResultDto<>();
        try {
            List<MenuEntity> menuEntityList = this.menuCrudService.getAllStaticMenu();
            if (CollectionUtils.isNotEmpty(menuEntityList)) {
                List<MenuResultDto> menuDtoList = modelMapper.map(menuEntityList, new TypeToken<List<MenuResultDto>>() {
                }.getType());
                listResultDto.setItems(menuDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("分页获取菜单列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto.success();
    }

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    @Override
    public ListResultDto<MenuResultDto> getAllTenancySideStaticMenu(AllTenancyStaticMenuListGetRequestDto requestDto) {
        ListResultDto<MenuResultDto> listResultDto = new PagedResultDto<>();
        try {
            List<MenuEntity> menuEntityList = this.menuCrudService.getAllTenancySideStaticMenu();
            if (CollectionUtils.isNotEmpty(menuEntityList)) {
                List<MenuResultDto> menuDtoList = modelMapper.map(menuEntityList, new TypeToken<List<MenuResultDto>>() {
                }.getType());
                listResultDto.setItems(menuDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("分页获取菜单列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto.success();
    }

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    @Override
    public ListResultDto<MenuResultDto> getAllHostSideStaticMenu(AllHostStaticMenuListGetRequestDto requestDto) {
        ListResultDto<MenuResultDto> listResultDto = new PagedResultDto<>();
        try {
            List<MenuEntity> menuEntityList = this.menuCrudService.getAllHostSideStaticMenu();
            if (CollectionUtils.isNotEmpty(menuEntityList)) {
                List<MenuResultDto> menuDtoList = modelMapper.map(menuEntityList, new TypeToken<List<MenuResultDto>>() {
                }.getType());
                listResultDto.setItems(menuDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("分页获取菜单列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto.success();
    }

    /**
     * 新增菜单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto createMenu(@FluentValid(value = {MenuCreateValidator.class}) MenuCreateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MenuEntity menuEntity = modelMapper.map(requestDto, MenuEntity.class);
            menuCrudService.insert(menuEntity);
        } catch (Exception e) {
            log.error("菜单创建失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除菜单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto deleteMenu(MenuDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            menuCrudService.deleteById(requestDto.getId());
        } catch (Exception e) {
            log.error("菜单删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新菜单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto updateMenu(MenuEditRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MenuEntity menuEntity = this.menuCrudService.selectById(requestDto.getId());
            if (menuEntity != null) {
                menuEntity = this.modelMapper.map(requestDto, MenuEntity.class);
                this.menuCrudService.updateById(menuEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("编辑菜单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取菜单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<MenuResultDto> getMenu(MenuGetRequestDto requestDto) {
        ObjectResultDto<MenuResultDto> resultDto = new ObjectResultDto<>();
        try {
            MenuEntity menuEntity = this.menuCrudService.selectById(requestDto.getId());
            if (menuEntity != null) {
                MenuResultDto menuResultDto = this.modelMapper.map(menuEntity, MenuResultDto.class);
                resultDto.setData(menuResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取菜单详情失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取菜单列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MenuResultDto> getMenuPagedList(MenuQueryPagedResultRequestDto requestDto) {
        PagedResultDto<MenuResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<MenuEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("name", requestDto.getName());
            }
            Page<MenuEntity> menuEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MenuEntity> menuEntityPageList = this.menuCrudService.selectPage(menuEntityPage, entityWrapper);
            if (menuEntityPageList != null) {
                List<MenuResultDto> menuDtoList = modelMapper.map(menuEntityPageList.getRecords(), new TypeToken<List<MenuResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(menuEntityPageList.getCurrent());
                pagedResultDto.setPageSize(menuEntityPageList.getSize());
                pagedResultDto.setTotalCount(menuEntityPageList.getTotal());
                pagedResultDto.setItems(menuDtoList);
            }

            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取菜单列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto.success();
    }
}
