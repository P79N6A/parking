package com.zoeeasy.cloud.ucc.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class RegionServiceTest {

//    @Autowired
//    private RegionAppService regionAppService;

    /**
     * @return
     */
//    @Test
//    public void testGetRegionById() {
//
//        GetRegionDto getRegionDto = new GetRegionDto();
//        getRegionDto.setRegionId(13L);
//        ObjectResultDto<RegionDto> objectResultDto = regionAppService.GetRegionById(getRegionDto);
//
//        assertTrue(objectResultDto.isSuccess());
//        assertNotNull(objectResultDto.getData());
//        assertEquals(objectResultDto.getData().getRegionId().longValue(), 13);
//    }

//    @Test
//    public void testGetRegionListByParentId() {
//
//        GetRegionDto getRegionDto = new GetRegionDto();
//        getRegionDto.setParentId(13L);
//        ListResultDto<RegionDto> listResultDto = regionAppService.GetRegionListByParentId(getRegionDto);
//        assertTrue(listResultDto.isSuccess());
//        assertNotNull(listResultDto.getItems());
//    }

//    @Test
//    public void testGetPagedRegionListByParentId() {
//
//        GetRegionPagedResultRequestDto getRegionDto = new GetRegionPagedResultRequestDto();
//        getRegionDto.setParentId(13L);
//
//        PagedResultDto<RegionDto> pagedResultDto = regionAppService.GetPagedRegionListByParentId(getRegionDto);
//        assertTrue(pagedResultDto.isSuccess());
//        assertNotNull(pagedResultDto.getItems());
//    }

}
