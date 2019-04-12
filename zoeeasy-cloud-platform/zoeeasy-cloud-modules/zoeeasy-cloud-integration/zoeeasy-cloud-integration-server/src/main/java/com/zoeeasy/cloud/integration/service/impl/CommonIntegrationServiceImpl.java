package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.core.enums.*;
import com.zoeeasy.cloud.integration.common.CommonIntegrationService;
import com.zoeeasy.cloud.integration.common.dto.*;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderStatusTypeRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderTypeRequestDto;
import com.zoeeasy.cloud.pms.enums.PayTypeEnum;
import com.zoeeasy.cloud.pms.enums.*;
import com.zoeeasy.cloud.pms.passing.dto.request.PassDataSourceTypeGetRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassTypeGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.AllParkingGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.VisitTypeGetRequestDto;
import com.zoeeasy.cloud.ucc.enums.TenantStatusEnum;
import com.zoeeasy.cloud.ucc.enums.TenantTypeEnum;
import com.zoeeasy.cloud.ucc.enums.UserStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用业务集成服务
 *
 * @author walkman
 */
@Service(value = "commonIntegrationService")
@Slf4j
public class CommonIntegrationServiceImpl implements CommonIntegrationService {

    /**
     * 获取商户状态选项
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getTenantStatus(TenantStatusListGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (TenantStatusEnum lotTypeEnum : TenantStatusEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(lotTypeEnum.getValue()), lotTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取商户状态失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取商户类型选项
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getTenantType(TenantTypeListGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (TenantTypeEnum lotTypeEnum : TenantTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(lotTypeEnum.getValue()), lotTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取商户类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取用户状态选项
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getUserStatus(UserStatusListGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (UserStatusEnum lotTypeEnum : UserStatusEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(lotTypeEnum.getValue()), lotTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户状态失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取停车场类型
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getLotType(LotTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (LotTypeEnum lotTypeEnum : LotTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(lotTypeEnum.getValue()), lotTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取停车场状态
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getParkingType(ParkingTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (OperationStateEnum parkingTypeEnum : OperationStateEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(parkingTypeEnum.getValue()), parkingTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场状态失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取是否收费
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getChargeFee(ChargeFeeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (ChargeFeeEnum chargeFeeEnum : ChargeFeeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(chargeFeeEnum.getValue()), chargeFeeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取是否收费失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取收费模式
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getChargeMode(ChargeModeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (ChargeModeEnum chargeModeEnum : ChargeModeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(chargeModeEnum.getValue()), chargeModeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取收费模式失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取缴费模式
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPayMode(PayModeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (PayModeEnum payModeEnum : PayModeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(payModeEnum.getValue()), payModeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取缴费模式失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取支付方式
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPayType(PayTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            ComboboxItemDto comboboxItemDto1 = new ComboboxItemDto(String.valueOf(PayTypeEnum.ALIPAY_ONLINE.getValue()), PayTypeEnum.ALIPAY_ONLINE.getComment(), false);
            list.add(comboboxItemDto1);
            ComboboxItemDto comboboxItemDto2 = new ComboboxItemDto(String.valueOf(PayTypeEnum.ALIPAY_WITHHOLD.getValue()), PayTypeEnum.ALIPAY_WITHHOLD.getComment(), false);
            list.add(comboboxItemDto2);
            ComboboxItemDto comboboxItemDto3 = new ComboboxItemDto(String.valueOf(PayTypeEnum.ALIPAY_FACE.getValue()), PayTypeEnum.ALIPAY_FACE.getComment(), false);
            list.add(comboboxItemDto3);
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取支付方式失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取是否可预约
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getAppointment(AppointmentGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (AppointmentEnum appointmentEnum : AppointmentEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(appointmentEnum.getValue()), appointmentEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取是否可预约失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取过车类型枚举
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPassingType(PassTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PassingTypeEnum.ENTRY.getValue()), PassingTypeEnum.ENTRY.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PassingTypeEnum.EXIT.getValue()), PassingTypeEnum.EXIT.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取过车类型枚举失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取过车数据源类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPassDataSource(PassDataSourceTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PassingVehicleDataSourceEnum.HIKVISION.getValue()), PassingVehicleDataSourceEnum.HIKVISION.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PassingVehicleDataSourceEnum.ALIPAY.getValue()), PassingVehicleDataSourceEnum.ALIPAY.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PassingVehicleDataSourceEnum.ANY_PARKING.getValue()), PassingVehicleDataSourceEnum.ANY_PARKING.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PassingVehicleDataSourceEnum.MANUAL.getValue()), PassingVehicleDataSourceEnum.MANUAL.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取过车数据源类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车辆类型枚举
     */
    @Override
    public ListResultDto<ComboboxItemDto> getCarType(CarTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(CarTypeEnum.OTHER_CAR.getValue()), CarTypeEnum.OTHER_CAR.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(CarTypeEnum.SMALL_CAR.getValue()), CarTypeEnum.SMALL_CAR.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(CarTypeEnum.BIG_CAR.getValue()), CarTypeEnum.BIG_CAR.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(CarTypeEnum.ALL_TYPE.getValue()), CarTypeEnum.ALL_TYPE.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车辆类型枚举失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取停车场等级
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getParkingLevel(ParkingLevelGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ParkingLevelEnum.EQUALLY.getValue()), ParkingLevelEnum.EQUALLY.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ParkingLevelEnum.ONE_LEVEL.getValue()), ParkingLevelEnum.ONE_LEVEL.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ParkingLevelEnum.TWO_LEVEL.getValue()), ParkingLevelEnum.TWO_LEVEL.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ParkingLevelEnum.THREE_LEVEL.getValue()), ParkingLevelEnum.THREE_LEVEL.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场等级枚举失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车牌类型选择项
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPlateTypeComboboxList(GetPlateTypeRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (LicensePlateTypeEnum licensePlateTypeEnum : LicensePlateTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(licensePlateTypeEnum.getCode(), licensePlateTypeEnum.getName(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车牌类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取包期类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPacketType(PacketTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (PacketTypeEnum packetTypeEnum : PacketTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(packetTypeEnum.getValue()), packetTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取包期类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车牌颜色
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPlateColor(PlateColorGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (LicensePlateColorEnum plateColorEnum : LicensePlateColorEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(plateColorEnum.getValue()),
                        plateColorEnum.getName(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车牌颜色失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;

    }

    /**
     * 获取是否全部停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getAllParking(AllParkingGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (AllParkingEnum allParkingEnum : AllParkingEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(allParkingEnum.getValue()), allParkingEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取是否全部停车场失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取固定车类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getFixedType(FixedTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (FixedTypeEnum fixedTypeEnum : FixedTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(fixedTypeEnum.getValue()), fixedTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取固定车类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取访客车类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getVisitType(VisitTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (VisitTypeEnum visitTypeEnum : VisitTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(visitTypeEnum.getValue()), visitTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取访客车类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车辆颜色
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getCarColor(CarColorGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (CarColorEnum carColorEnum : CarColorEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(carColorEnum.getValue()), carColorEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车辆颜色失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取生效状态
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getEffectedStatusEnum(EffectedStatusGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (EffectedStatusEnum effectedStatusEnum : EffectedStatusEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(effectedStatusEnum.getValue()), effectedStatusEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取生效状态失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取审核状态下拉菜单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getApproveStatus(ApproveStatusRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (ApproveStatusEnum approveStatusEnum : ApproveStatusEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(approveStatusEnum.getValue()), approveStatusEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取审核状态下拉菜单失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取审核意见下拉菜单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getApproveOpinion(ApproveStatusRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            ComboboxItemDto comboboxItemDto1 = new ComboboxItemDto("1", "驳回", false);
            ComboboxItemDto comboboxItemDto2 = new ComboboxItemDto("2", "通过", false);
            list.add(comboboxItemDto1);
            list.add(comboboxItemDto2);
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取审核状态下拉菜单失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取驳回原因下拉菜单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getRejectReason(RejectReasonRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (RejectReasonEnum rejectReasonEnum : RejectReasonEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(rejectReasonEnum.getValue()), rejectReasonEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取驳回原因下拉菜单失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取厂商列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getProviderName(ProviderListGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(DeviceProviderEnum.HIKVISION.getValue()), DeviceProviderEnum.HIKVISION.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(DeviceProviderEnum.INMOTION.getValue()), DeviceProviderEnum.INMOTION.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(DeviceProviderEnum.AIRJOY.getValue()), DeviceProviderEnum.AIRJOY.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(DeviceProviderEnum.FUSHANG.getValue()), DeviceProviderEnum.FUSHANG.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车辆类型枚举失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取审核意见列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getAuditOpinion(AuditOpinionGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(AuditOpinionEnum.PASS.getValue()), AuditOpinionEnum.PASS.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(AuditOpinionEnum.NOT_PASS.getValue()), AuditOpinionEnum.NOT_PASS.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取审核意见列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取审核状态列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getAuditStatus(AuditStatusGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(AuditStatusEnum.AWAIT.getValue()), AuditStatusEnum.AWAIT.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(AuditStatusEnum.PASS.getValue()), AuditStatusEnum.PASS.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(AuditStatusEnum.NOT_PASS.getValue()), AuditStatusEnum.NOT_PASS.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取审核状态列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取审核不通过原因列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getAuditNotPassReason(AuditNotPassReasonGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (AuditNotPassReasonEnum auditNotPassReasonEnum : AuditNotPassReasonEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(auditNotPassReasonEnum.getValue()), auditNotPassReasonEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取审核不通过原因列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取是否上架列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPutAway(PutAwayGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            if (requestDto.getApplyType().equals(ApplyTypeEnum.UP.getValue())) {
                list.add(new ComboboxItemDto(String.valueOf(PutAwayStatusEnum.AWAIT_UP.getValue()), PutAwayStatusEnum.AWAIT_UP.getComment(), false));
                list.add(new ComboboxItemDto(String.valueOf(PutAwayStatusEnum.ALREADY_UP.getValue()), PutAwayStatusEnum.ALREADY_UP.getComment(), false));
            } else {
                list.add(new ComboboxItemDto(String.valueOf(PutAwayStatusEnum.ALREADY_UP.getValue()), PutAwayStatusEnum.ALREADY_UP.getComment(), false));
                list.add(new ComboboxItemDto(String.valueOf(PutAwayStatusEnum.ALREADY_DOWN.getValue()), PutAwayStatusEnum.ALREADY_DOWN.getComment(), false));
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取是否上架列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 订单状态列表
     *
     * @param requestDto ParkingOrderStatusTypeRequestDto
     * @return ListResultDto<ComboboxItemDto>
     */
    @Override
    public ListResultDto<ComboboxItemDto> paringOrderStatus(ParkingOrderStatusTypeRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            for (ParkingOrderStatusEnum parkingOrderStatusEnum : ParkingOrderStatusEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf
                        (parkingOrderStatusEnum.getValue()), parkingOrderStatusEnum.getComment(), false);
                itemDtoList.add(comboboxItemDto);
            }
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取订单类型失败" + e.getMessage());
            listResultDto.makeResult(OrderResultEnum.PARKING_ORDER_STATUS_GET_ERR.getValue(), OrderResultEnum.PARKING_ORDER_STATUS_GET_ERR.getComment());
        }
        return listResultDto;
    }

    /**
     * 订单类型列表
     *
     * @param requestDto ParkingOrderTypeRequestDto
     * @return ListResultDto<ComboboxItemDto>
     */
    @Override
    public ListResultDto<ComboboxItemDto> paringOrderType(ParkingOrderTypeRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            for (ParkingOrderTypeEnum parkingOrderTypeEnum : ParkingOrderTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf
                        (parkingOrderTypeEnum.getValue()), parkingOrderTypeEnum.getComment(), false);
                itemDtoList.add(comboboxItemDto);
            }
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取订单类型失败" + e.getMessage());
            listResultDto.makeResult(OrderResultEnum.PARKING_ORDER_TYPE_GET_ERR.getValue(), OrderResultEnum.PARKING_ORDER_TYPE_GET_ERR.getComment());
        }
        return listResultDto;
    }

    /**
     * 获取上架状态列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getRunStatus(RunStatusGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (RunStatusEnum runStatusEnum : RunStatusEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(runStatusEnum.getValue()), runStatusEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取上架状态列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取预约支付时限列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPayLimitList(PayLimitListGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (PayLimitEnum payLimitEnum : PayLimitEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(payLimitEnum.getValue()), payLimitEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取预约支付时限列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车场类型一
     */
    @Override
    public ListResultDto<ComboboxItemDto> getTypeList(TypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (TypeEnum typeEnum : TypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(typeEnum.getValue()), typeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车场类型一列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 异常过车类型枚举
     *
     * @param requestDto
     */
    @Override
    public ListResultDto<ComboboxItemDto> getExceptionPassType(ExceptionPassTypeRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (PassingExceptionTypeEnum passingExceptionTypeEnum : PassingExceptionTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(passingExceptionTypeEnum.getValue()), passingExceptionTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            listResultDto.failed();
        }
        return listResultDto;
    }
}
