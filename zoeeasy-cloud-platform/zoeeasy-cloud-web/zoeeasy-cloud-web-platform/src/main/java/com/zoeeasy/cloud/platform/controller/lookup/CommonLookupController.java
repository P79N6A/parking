package com.zoeeasy.cloud.platform.controller.lookup;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.charge.chg.ChargeRuleService;
import com.zoeeasy.cloud.charge.chg.dto.request.GetChargeRuleTypeRequestDto;
import com.zoeeasy.cloud.charge.holiday.HolidayScheduleService;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayTypeGetRequestDto;
import com.zoeeasy.cloud.gather.magnetic.MagneticHeartBeatService;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.ChangeTypeListRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.ChangeTypeResultDto;
import com.zoeeasy.cloud.integration.common.CommonIntegrationService;
import com.zoeeasy.cloud.integration.common.dto.*;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.ProviderResultDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassDataSourceTypeGetRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassTypeGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.AllParkingGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.VisitTypeGetRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 下拉列表
 *
 * @author AkeemSuper
 * @since 2018/9/18 0018
 */
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/commonlookup")
@Api(value = "下拉列表Api", tags = "下拉列表Api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonLookupController {

    @Autowired
    private HolidayScheduleService holidayScheduleService;

    @Autowired
    private ChargeRuleService chargeRuleService;

    @Autowired
    private CommonIntegrationService commonIntegrationService;

    @Autowired
    private MagneticHeartBeatService magneticHeartBeatService;

    /**
     * 获取商户状态选项
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取商户状态选项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/tenantStatus")
    public ListResultDto<ComboboxItemDto> getTenantStatus(@RequestBody TenantStatusListGetRequestDto requestDto) {
        return this.commonIntegrationService.getTenantStatus(requestDto);
    }

    /**
     * 获取商户类型选项
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取商户类型选项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/tenantType")
    public ListResultDto<ComboboxItemDto> getTenantType(@RequestBody TenantTypeListGetRequestDto requestDto) {
        return this.commonIntegrationService.getTenantType(requestDto);
    }

    /**
     * 获取假期类型下拉菜单
     */
    @ApiOperation(value = "获取假期类型下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getHolidayTypeList")
    public ListResultDto<ComboboxItemDto> getHolidayTypeList(@RequestBody HolidayTypeGetRequestDto requestDto) {
        return holidayScheduleService.getHolidayTypeList(requestDto);
    }

    /**
     * 保存假期配置时下拉列表
     */
    @ApiOperation(value = "保存假期配置时下拉列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/saveHolidayTypeList")
    public ListResultDto<ComboboxItemDto> saveHolidayTypeList(@RequestBody HolidayTypeGetRequestDto requestDto) {
        return holidayScheduleService.saveHolidayTypeList(requestDto);
    }

    /**
     * 获取停车场类型下拉菜单
     */
    @ApiOperation(value = "获取停车场类型下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/lotType")
    public ListResultDto<ComboboxItemDto> getLotType(@RequestBody LotTypeGetRequestDto requestDto) {
        return commonIntegrationService.getLotType(requestDto);
    }

    /**
     * 获取停车场状态下拉菜单
     */
    @ApiOperation(value = "获取停车场状态下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/parkingStatus")
    public ListResultDto<ComboboxItemDto> getParkingType(@RequestBody ParkingTypeGetRequestDto requestDto) {
        return commonIntegrationService.getParkingType(requestDto);
    }

    /**
     * 获取是否收费下拉菜单
     */
    @ApiOperation(value = "获取是否收费下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/chargeFee")
    public ListResultDto<ComboboxItemDto> getChargeFee(@RequestBody ChargeFeeGetRequestDto requestDto) {
        return commonIntegrationService.getChargeFee(requestDto);
    }

    /**
     * 获取收费模式下拉菜单
     */
    @ApiOperation(value = "获取收费模式下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/chargeMode")
    public ListResultDto<ComboboxItemDto> getChargeMode(@RequestBody ChargeModeGetRequestDto requestDto) {
        return commonIntegrationService.getChargeMode(requestDto);
    }

    /**
     * 获取缴费模式下拉菜单
     */
    @ApiOperation(value = "获取缴费模式下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/payMode")
    public ListResultDto<ComboboxItemDto> getPayMode(@RequestBody PayModeGetRequestDto requestDto) {
        return commonIntegrationService.getPayMode(requestDto);
    }

    /**
     * 获取支付方式下拉菜单
     */
    @ApiOperation(value = "获取支付方式下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/payType")
    public ListResultDto<ComboboxItemDto> getPayType(@RequestBody PayTypeGetRequestDto requestDto) {
        return commonIntegrationService.getPayType(requestDto);
    }

    /**
     * 获取是否可预约下拉菜单
     */
    @ApiOperation(value = "获取是否可预约下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/appointment")
    public ListResultDto<ComboboxItemDto> getAppointment(@RequestBody AppointmentGetRequestDto requestDto) {
        return commonIntegrationService.getAppointment(requestDto);
    }

    /**
     * 获取过车类型枚举
     */
    @ApiOperation(value = "获取过车类型枚举", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getPassingType")
    public ListResultDto<ComboboxItemDto> getPassingType(@RequestBody PassTypeGetRequestDto requestDto) {
        return commonIntegrationService.getPassingType(requestDto);
    }

    /**
     * 获取过车数据源类型
     */
    @ApiOperation(value = "获取过车数据源类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getPassDataSource", name = "获取过车数据源类型")
    public ListResultDto<ComboboxItemDto> getPassDataSource(@RequestBody PassDataSourceTypeGetRequestDto requestDto) {
        return commonIntegrationService.getPassDataSource(requestDto);
    }

    /**
     * 车辆类型枚举
     */
    @ApiOperation(value = "车辆类型枚举", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getCarType", name = "车辆类型枚举")
    public ListResultDto<ComboboxItemDto> getCarType(@RequestBody CarTypeGetRequestDto requestDto) {
        return commonIntegrationService.getCarType(requestDto);
    }

    /**
     * 获取规则类型列表
     *
     * @return
     */
    @ApiOperation(value = "获取规则类型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/chargeruletype", name = "获取规则类型列表")
    public ListResultDto<ComboboxItemDto> getChargeRuleTypeComboboxList(@RequestBody GetChargeRuleTypeRequestDto requestDto) {
        return chargeRuleService.getChargeRuleTypeComboboxList(requestDto);
    }

    /**
     * 获取停车场等级
     *
     * @return
     */
    @ApiOperation(value = "获取停车场等级", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getParkingLevel", name = "获取规则类型列表")
    public ListResultDto<ComboboxItemDto> getParkingLevel(@RequestBody ParkingLevelGetRequestDto requestDto) {
        return commonIntegrationService.getParkingLevel(requestDto);
    }

    /**
     * 获取包期类型
     *
     * @return
     */
    @ApiOperation(value = "获取包期类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getPacketType", name = "获取包期类型列表")
    public ListResultDto<ComboboxItemDto> getPacketType(@RequestBody PacketTypeGetRequestDto requestDto) {
        return commonIntegrationService.getPacketType(requestDto);
    }

    /**
     * 获取车牌颜色
     *
     * @return
     */
    @ApiOperation(value = "获取车牌颜色下拉列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getPlateColor", name = "获取车牌颜色列表下拉列表")
    public ListResultDto<ComboboxItemDto> getPlateColor(@RequestBody PlateColorGetRequestDto requestDto) {
        return commonIntegrationService.getPlateColor(requestDto);
    }

    /**
     * 获取是否全部停车场
     *
     * @return
     */
    @ApiOperation(value = "获取是否全部停车场", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getAllParking", name = "获取是否全部停车场")
    public ListResultDto<ComboboxItemDto> getAllParking(@RequestBody AllParkingGetRequestDto requestDto) {
        return commonIntegrationService.getAllParking(requestDto);
    }

    /**
     * 获取车辆颜色下拉列表
     */
    @ApiOperation(value = "获取车辆颜色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getCarColor", name = "获取车辆颜色下拉列表")
    public ListResultDto<ComboboxItemDto> getCarColor(@RequestBody CarColorGetRequestDto requestDto) {
        return commonIntegrationService.getCarColor(requestDto);
    }

    /**
     * 获取访客车类型
     */
    @ApiOperation(value = "获取访客车类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getVisitType", name = "获取访客车类型")
    public ListResultDto<ComboboxItemDto> getVisitType(@RequestBody VisitTypeGetRequestDto requestDto) {
        return commonIntegrationService.getVisitType(requestDto);
    }

    /**
     * 获取固定车类型
     */
    @ApiOperation(value = "获取固定车类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getFixedType", name = "获取固定车类型")
    public ListResultDto<ComboboxItemDto> getFixedType(@RequestBody FixedTypeGetRequestDto requestDto) {
        return commonIntegrationService.getFixedType(requestDto);
    }

    /**
     * 获取车牌类型选择项
     *
     * @return
     */
    @ApiOperation(value = "获取车牌类型选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/platetype", name = "获取车牌类型选择项")
    public ListResultDto<ComboboxItemDto> getPlateTypeComboboxList(@RequestBody GetPlateTypeRequestDto requestDto) {
        return commonIntegrationService.getPlateTypeComboboxList(requestDto);
    }

    @ApiOperation(value = "获取审核状态下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/approveStatus", name = "获取审核状态下拉菜单")
    ListResultDto<ComboboxItemDto> getApproveStatus(@RequestBody ApproveStatusRequestDto requestDto) {
        return commonIntegrationService.getApproveStatus(requestDto);
    }

    @ApiOperation(value = "获取审核意见下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/approveOpinion", name = "获取审核意见下拉菜单")
    ListResultDto<ComboboxItemDto> getApproveOpinion(@RequestBody ApproveStatusRequestDto requestDto) {
        return commonIntegrationService.getApproveOpinion(requestDto);
    }

    @ApiOperation(value = "获取驳回原因下拉菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/rejectReason", name = "获取驳回原因下拉菜单")
    ListResultDto<ComboboxItemDto> getRejectReason(@RequestBody RejectReasonRequestDto requestDto) {
        return commonIntegrationService.getRejectReason(requestDto);
    }

    /**
     * 获取生效状态下拉列表
     */
    @ApiOperation(value = "获取生效状态下拉列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getEffectedStatus", name = "获取生效状态下拉列表")
    public ListResultDto<ComboboxItemDto> getEffectedStatusEnum(@RequestBody EffectedStatusGetRequestDto requestDto) {
        return commonIntegrationService.getEffectedStatusEnum(requestDto);
    }

    /**
     * 获取厂商列表
     *
     * @return
     */
    @ApiOperation(value = "获取厂商列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ProviderResultDto.class)
    @PostMapping(value = "/getProvider")
    public ListResultDto<ComboboxItemDto> getProviderName(@RequestBody ProviderListGetRequestDto requestDto) {
        return commonIntegrationService.getProviderName(requestDto);
    }

    /**
     * 出车类型列表
     *
     * @return
     */
    @ApiOperation(value = "出车类型列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ChangeTypeResultDto.class)
    @PostMapping(value = "/getPassing")
    public ListResultDto<ChangeTypeResultDto> getPassingTypeList(@RequestBody ChangeTypeListRequestDto requestDto) {
        return magneticHeartBeatService.getPassingTypeList(requestDto);
    }

    @ApiOperation(value = "获取审核停车场意见列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getAuditOpinion")
    public ListResultDto<ComboboxItemDto> getAuditOpinion(@RequestBody AuditOpinionGetRequestDto requestDto) {
        return commonIntegrationService.getAuditOpinion(requestDto);
    }

    @ApiOperation(value = "获取审核状态列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getAuditStatus")
    public ListResultDto<ComboboxItemDto> getAuditStatus(@RequestBody AuditStatusGetRequestDto requestDto) {
        return commonIntegrationService.getAuditStatus(requestDto);
    }

    @ApiOperation(value = "获取审核不通过原因列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getAuditNotPassReason")
    public ListResultDto<ComboboxItemDto> getAuditNotPassReason(@RequestBody AuditNotPassReasonGetRequestDto requestDto) {
        return commonIntegrationService.getAuditNotPassReason(requestDto);
    }

    @ApiOperation(value = "获取是否上架列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getPutAway")
    public ListResultDto<ComboboxItemDto> getPutAway(@RequestBody PutAwayGetRequestDto requestDto) {
        return commonIntegrationService.getPutAway(requestDto);
    }

    @ApiOperation(value = "获取上架状态列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getRunStatus")
    public ListResultDto<ComboboxItemDto> getRunStatus(@RequestBody RunStatusGetRequestDto requestDto) {
        return commonIntegrationService.getRunStatus(requestDto);
    }

    @ApiOperation(value = "获取预约支付时限列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getPayLimitList")
    public ListResultDto<ComboboxItemDto> getPayLimitList(@RequestBody PayLimitListGetRequestDto requestDto) {
        return commonIntegrationService.getPayLimitList(requestDto);
    }

    @ApiOperation(value = "获取车场类型一列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @PostMapping(value = "/getTypeList")
    public ListResultDto<ComboboxItemDto> getTypeList(@RequestBody TypeGetRequestDto requestDto) {
        return commonIntegrationService.getTypeList(requestDto);
    }

    @ApiOperation(value = "获取异常过车类型枚举", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response =
            ComboboxItemDto.class)
    @PostMapping(value = "/exceptionPassType")
    public ListResultDto<ComboboxItemDto> getExceptionPassType(@RequestBody ExceptionPassTypeRequestDto requestDto) {
        return commonIntegrationService.getExceptionPassType(requestDto);
    }

}
