package com.zhuyitech.parking.platform.controller.oilprice;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.oilprice.OilPriceDetailRequestDto;
import com.zhuyitech.parking.tool.dto.request.oilprice.OilPriceListRequestDto;
import com.zhuyitech.parking.tool.dto.result.oilprice.OilPriceDetailResultDto;
import com.zhuyitech.parking.tool.dto.result.oilprice.OilPriceListResultDto;
import com.zhuyitech.parking.tool.service.api.OilPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 油价API
 *
 * @author zwq
 */
@Api(value = "油价API", description = "油价API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/oilprice")
public class OilPriceController {

    @Autowired
    private OilPriceService oilPriceService;

    /**
     * 油价查询列表
     *
     * @param requestDto OilPriceListRequestDto
     * @return OilPriceListResultDto
     */
    @ApiOperation(value = "城市油价列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = OilPriceListResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<OilPriceListResultDto> getOliPriceList(OilPriceListRequestDto requestDto) {
        return oilPriceService.getOliPriceList(requestDto);
    }

    /**
     * 油价详情
     *
     * @param requestDto OilPriceDetailRequestDto
     * @return OilPriceDetailResultDto
     */
    @ApiOperation(value = "油价详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = OilPriceDetailResultDto.class)
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<OilPriceDetailResultDto> getOilPriceDetail(OilPriceDetailRequestDto requestDto) {
        return oilPriceService.getOilPriceDetail(requestDto);
    }

}
