package com.zhuyitech.parking.platform.controller.common;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zhuyitech.parking.pms.service.api.PmsLookupService;
import com.zhuyitech.sms.service.api.SmsLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author walkman
 */
@Api(value = "CommonLookup Api", description = "CommonLookup Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/common")
public class CommonLookupController {

    @Autowired
    private PmsLookupService pmsLookupService;

    @Autowired
    private SmsLookupService smsLookupService;

    /**
     * 获取所有短信客户端选择项
     *
     * @return
     */
    @ApiOperation(value = "获取短信客户端选择项", notes = "获取短信客户端选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/smsclients", name = "获取短信客户端选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getClientComboboxList() {
        return smsLookupService.getClientComboboxList();
    }

    /**
     * 获取模板类型选择项
     *
     * @return
     */
    @ApiOperation(value = "获取模板类型选择项", notes = "获取模板类型选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/smstemplatetypes", name = "获取模板类型选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getTemplateTypeComboboxList() {
        return smsLookupService.getTemplateTypeComboboxList();
    }

    /**
     * 获取验证码类型选择项
     *
     * @return
     */
    @ApiOperation(value = "获取验证码类型选择项", notes = "获取验证码类型选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/smsverifytypes", name = "获取验证码类型选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getVerifyCodeTypeComboboxList() {
        return smsLookupService.getVerifyCodeTypeComboboxList();
    }

    /**
     * 获取车型深度选择项
     *
     * @return
     */
    @ApiOperation(value = "获取车型深度选择项", notes = "获取车型深度选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/carDepthList", name = "获取车型深度选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getCarDepthList() {
        return pmsLookupService.getCarDepthComboboxList();
    }

    /**
     * 获取区域等级选择项
     *
     * @return
     */
    @ApiOperation(value = "获取区域等级选择项", notes = "获取区域等级选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/regionLevelList", name = "获取区域等级选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getRegionLevelComboboxList() {
        return pmsLookupService.getRegionLevelComboboxList();
    }

    /**
     * 获取车辆类型等级选择项
     *
     * @return
     */
    @ApiOperation(value = "获取车辆类型等级选择项", notes = "获取车辆类型等级选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/carlevel", name = "获取车辆类型等级选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getCarLevelComboboxList() {
        return pmsLookupService.getCarLevelComboboxList();
    }

    /**
     * 获取后台支付方式列表
     *
     * @return
     */
    @ApiOperation(value = "获取后台支付方式列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/getPayWayList", method = RequestMethod.POST, name = "获取后台支付方式列表")
    public ListResultDto<ComboboxItemDto> getPayWayList() {
        return pmsLookupService.getPayWayComboboxList();
    }

    /**
     * 获取后台预约订单类型列表
     *
     * @return
     */
    @ApiOperation(value = "获取后台预约订单类型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/assetLogType", method = RequestMethod.POST, name = "获取后台预约订单类型列表")
    public ListResultDto<ComboboxItemDto> assetLogType() {
        return pmsLookupService.getAssetLogTypeComboboxList();
    }

    /**
     * 获取车牌类型选择项
     *
     * @return
     */
    @ApiOperation(value = "获取车牌类型选择项", notes = "获取车牌类型选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/platetype", name = "获取车牌类型选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getPlateTypeComboboxList() {
        return pmsLookupService.getPlateTypeComboboxList();
    }

    /**
     * 获取车牌颜色选择项
     *
     * @return
     */
    @ApiOperation(value = "获取车牌颜色选择项", notes = "获取车牌颜色选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/platecolor", name = "获取车牌颜色选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getPlateColorComboboxList() {
        return pmsLookupService.getPlateColorComboboxList();
    }

}
