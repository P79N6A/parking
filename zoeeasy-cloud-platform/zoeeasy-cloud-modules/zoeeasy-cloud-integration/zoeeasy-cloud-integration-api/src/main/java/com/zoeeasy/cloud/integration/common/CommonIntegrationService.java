package com.zoeeasy.cloud.integration.common;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.integration.common.dto.*;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderStatusTypeRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderTypeRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassDataSourceTypeGetRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassTypeGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.AllParkingGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.VisitTypeGetRequestDto;

public interface CommonIntegrationService {

    /**
     * 获取商户状态选项
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getTenantStatus(TenantStatusListGetRequestDto requestDto);

    /**
     * 获取商户类型选项
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getTenantType(TenantTypeListGetRequestDto requestDto);

    /**
     * 获取用户状态选项
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getUserStatus(UserStatusListGetRequestDto requestDto);

    /**
     * 获取停车场类型
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getLotType(LotTypeGetRequestDto requestDto);

    /**
     * 获取停车场状态
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getParkingType(ParkingTypeGetRequestDto requestDto);

    /**
     * 获取是否收费
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getChargeFee(ChargeFeeGetRequestDto requestDto);

    /**
     * 获取收费模式
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getChargeMode(ChargeModeGetRequestDto requestDto);

    /**
     * 获取缴费模式
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getPayMode(PayModeGetRequestDto requestDto);

    /**
     * 获取支付方式
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getPayType(PayTypeGetRequestDto requestDto);

    /**
     * 获取是否可预约
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getAppointment(AppointmentGetRequestDto requestDto);

    /**
     * 获取过车类型枚举
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getPassingType(PassTypeGetRequestDto requestDto);

    /**
     * 获取过车数据源类型
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getPassDataSource(PassDataSourceTypeGetRequestDto requestDto);

    /**
     * 获取车辆类型枚举
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getCarType(CarTypeGetRequestDto requestDto);

    /**
     * 获取停车场等级
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getParkingLevel(ParkingLevelGetRequestDto requestDto);

    /**
     * 获取车牌类型选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getPlateTypeComboboxList(GetPlateTypeRequestDto requestDto);

    /**
     * 获取包期类型
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getPacketType(PacketTypeGetRequestDto requestDto);

    /**
     * 获取车牌颜色
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getPlateColor(PlateColorGetRequestDto requestDto);

    /**
     * 获取是否全部停车场
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getAllParking(AllParkingGetRequestDto requestDto);

    /**
     * 获取固定车类型
     */
    ListResultDto<ComboboxItemDto> getFixedType(FixedTypeGetRequestDto requestDto);

    /**
     * 获取访客车类型
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getVisitType(VisitTypeGetRequestDto requestDto);

    /**
     * 获取车辆颜色
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getCarColor(CarColorGetRequestDto requestDto);

    /**
     * 获取生效状态
     */
    ListResultDto<ComboboxItemDto> getEffectedStatusEnum(EffectedStatusGetRequestDto requestDto);

    /**
     * 获取审核状态下拉菜单
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getApproveStatus(ApproveStatusRequestDto requestDto);

    /**
     * 获取审核意见下拉菜单
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getApproveOpinion(ApproveStatusRequestDto requestDto);

    /**
     * 获取驳回原因下拉菜单
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getRejectReason(RejectReasonRequestDto requestDto);

    /**
     * 获取厂商列表
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getProviderName(ProviderListGetRequestDto requestDto);

    /**
     * 获取审核意见列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getAuditOpinion(AuditOpinionGetRequestDto requestDto);

    /**
     * 获取审核状态列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getAuditStatus(AuditStatusGetRequestDto requestDto);

    /**
     * 获取审核不通过原因列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getAuditNotPassReason(AuditNotPassReasonGetRequestDto requestDto);

    /**
     * 获取是否上架列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getPutAway(PutAwayGetRequestDto requestDto);

    /**
     * 订单状态列表 requestDto
     */
    ListResultDto<ComboboxItemDto> paringOrderStatus(ParkingOrderStatusTypeRequestDto requestDto);

    /**
     * 订单类型列表 requestDto
     */
    ListResultDto<ComboboxItemDto> paringOrderType(ParkingOrderTypeRequestDto requestDto);

    /**
     * 获取上架状态列表
     */
    ListResultDto<ComboboxItemDto> getRunStatus(RunStatusGetRequestDto requestDto);

    /**
     * 获取预约支付时限列表
     */
    ListResultDto<ComboboxItemDto> getPayLimitList(PayLimitListGetRequestDto requestDto);

    /**
     * 获取车场类型一
     */
    ListResultDto<ComboboxItemDto> getTypeList(TypeGetRequestDto requestDto);

    /**
     * 异常过车类型枚举
     */
    ListResultDto<ComboboxItemDto> getExceptionPassType(ExceptionPassTypeRequestDto requestDto);
}
