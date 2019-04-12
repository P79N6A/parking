package com.zhuyitech.parking.platform.controller.carbrand;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.annotation.SystemLog;
import com.zhuyitech.parking.pms.dto.request.car.*;
import com.zhuyitech.parking.pms.dto.result.car.CarBrandResultDto;
import com.zhuyitech.parking.pms.service.api.CarBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 车型控制器
 *
 * @author walkman
 */
@Api(value = "车型Api", description = "车型Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/carbrand")
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    /**
     * 新增车型
     *
     * @param requestDto
     * @return
     */
    @SystemLog("新增车型")
    @ApiOperation(value = "新增车型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultDto addCarBrand(CarBrandAddRequestDto requestDto) {
        return carBrandService.addCarBrand(requestDto);
    }

    /**
     * 删除车型
     *
     * @param requestDto
     * @return
     */
    @SystemLog("删除车型")
    @ApiOperation(value = "删除车型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultDto deleteCarBrand(CarBrandDeleteRequestDto requestDto) {
        return carBrandService.deleteCarBrand(requestDto);
    }

    /**
     * 更新车型
     *
     * @param requestDto
     * @return
     */
    @SystemLog("更新车型")
    @ApiOperation(value = "更新车型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultDto updateCarBrand(CarBrandUpdateRequestDto requestDto) {
        return carBrandService.updateCarBrand(requestDto);
    }

    /**
     * 获取车型
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ObjectResultDto.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ObjectResultDto<CarBrandResultDto> getCarBrand(CarBrandGetRequestDto requestDto) {
        return carBrandService.getCarBrand(requestDto);
    }

    /**
     * 获取车型列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取车型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ListResultDto<CarBrandResultDto> getCarBrandList(CarBrandListGetRequestDto requestDto) {
        return carBrandService.getCarBrandList(requestDto);
    }

    /**
     * 分页获取车型列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取车型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PagedResultDto.class)
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
    public PagedResultDto<CarBrandResultDto> getCarBrandPagedList(CarBrandQueryPagedResultRequestDto requestDto) {
        return carBrandService.getCarBrandPagedList(requestDto);
    }

    /**
     * 根据深度获取车型列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "根据深度获取车型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/getCarBrandList", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getCarBrandList(CarBrandDepthGetRequestDto requestDto) {
        return carBrandService.getCarBrandListByDepth(requestDto);
    }

}
