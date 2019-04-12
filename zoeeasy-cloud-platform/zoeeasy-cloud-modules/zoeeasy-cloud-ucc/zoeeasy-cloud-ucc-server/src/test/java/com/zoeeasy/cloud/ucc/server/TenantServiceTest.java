package com.zoeeasy.cloud.ucc.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.ucc.tenant.TenantService;
import com.zoeeasy.cloud.ucc.tenant.dto.request.*;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantListResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class TenantServiceTest {

    @Autowired
    private TenantService tenantService;

    /**
     * @return
     */
    @Test
    public void testCreateTenant() {
        TenantCreateRequestDto requestDto = new TenantCreateRequestDto();
        requestDto.setName("逐一科技");
//        requestDto.setType(TenantTypeEnum.SINGLE.getValue());
        requestDto.setDescription("任意停车");
        tenantService.createTenant(requestDto);
    }

    /**
     * @return
     */
    @Test
    public void testGetTenant() {
        TenantGetRequestDto requestDto = new TenantGetRequestDto();
        requestDto.setId(1L);
        ObjectResultDto<TenantResultDto> resultDto = tenantService.getTenant(requestDto);
        assertTrue(resultDto.isSuccess());
        TenantResultDto tenantResultDto = resultDto.getData();
        assertNotNull(tenantResultDto);
    }

    /**
     * @return
     */
    @Test
    public void testGetTenantList() {
        TenantPagedListRequestDto requestDto = new TenantPagedListRequestDto();
        PagedResultDto<TenantListResultDto> resultDto = tenantService.getTenantList(requestDto);
        assertTrue(resultDto.isSuccess());
        List<TenantListResultDto> tenantResultDto = resultDto.getItems();
        assertNotNull(tenantResultDto);
    }

    /**
     * @return
     */
    @Test
    public void testEditTenant() {
        TenantEditRequestDto requestDto = new TenantEditRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = tenantService.editTenant(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    /**
     * @return
     */
    @Test
    public void testDeleteTenant() {
        TenantDeleteRequestDto requestDto = new TenantDeleteRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = tenantService.deleteTenant(requestDto);
        assertTrue(resultDto.isSuccess());
    }
}
