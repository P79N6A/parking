package com.zhuyitech.parking.pms.service.impl;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zhuyitech.parking.pms.service.api.PmsLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用选项数据
 *
 * @author walkman
 * @date 2017-12-01
 */
@Service("pmsLookupService")
@Slf4j
public class PmsLookupServiceImpl implements PmsLookupService {

    /**
     * 获取车型深度选择项
     */
    @Override
    public ListResultDto<ComboboxItemDto> getCarDepthComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {

            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "车品牌", false));
            itemDtoList.add(new ComboboxItemDto("2", "车子品牌", false));
            itemDtoList.add(new ComboboxItemDto("3", "车型", false));
            itemDtoList.add(new ComboboxItemDto("4", "车款", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("车型深度获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取区域等级选择项
     */
    @Override
    public ListResultDto<ComboboxItemDto> getRegionLevelComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "省(直辖市)", false));
            itemDtoList.add(new ComboboxItemDto("2", "市", false));
            itemDtoList.add(new ComboboxItemDto("3", "区县", false));
            itemDtoList.add(new ComboboxItemDto("4", "乡镇", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("区域等级获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车牌类型选择项
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPlateTypeComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("01", "大型汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("02", "小型汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("03", "使馆汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("04", "领馆汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("05", "境外汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("06", "外籍汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("07", "两、三轮摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("08", "轻便摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("09", "使馆摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("10", "领馆摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("11", "境外摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("12", "外籍摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("13", "农用运输车号牌", false));
            itemDtoList.add(new ComboboxItemDto("14", "拖拉机号牌", false));
            itemDtoList.add(new ComboboxItemDto("15", "挂车号牌", false));
            itemDtoList.add(new ComboboxItemDto("16", "教练汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("17", "教练摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("18", "试验汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("19", "试验摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("20", "临时入境汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("21", "临时入境摩托车号牌", false));
            itemDtoList.add(new ComboboxItemDto("22", "临时行驶车号牌", false));
            itemDtoList.add(new ComboboxItemDto("23", "警用汽车号牌", false));
            itemDtoList.add(new ComboboxItemDto("24", "警用摩托号牌", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车牌类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车牌颜色选择项
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPlateColorComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "蓝色", false));
            itemDtoList.add(new ComboboxItemDto("2", "黄色", false));
            itemDtoList.add(new ComboboxItemDto("3", "白色", false));
            itemDtoList.add(new ComboboxItemDto("4", "黑色", false));
            itemDtoList.add(new ComboboxItemDto("5", "绿色", false));
            itemDtoList.add(new ComboboxItemDto("6", "新能源", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车牌颜色失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取车辆类型等级选择项
     */
    @Override
    public ListResultDto<ComboboxItemDto> getCarLevelComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("0", "其他", false));
            itemDtoList.add(new ComboboxItemDto("1", "小型汽车", false));
            itemDtoList.add(new ComboboxItemDto("2", "大型汽车", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车辆类型等级失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取后台支付方式列表
     */
    @Override
    public ListResultDto<ComboboxItemDto> getPayWayComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "支付宝", false));
            itemDtoList.add(new ComboboxItemDto("2", "微信", false));
            itemDtoList.add(new ComboboxItemDto("3", "钱包支付", false));
            itemDtoList.add(new ComboboxItemDto("4", "银联支付", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取后台支付方式列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取后台预约订单类型列表
     */
    @Override
    public ListResultDto<ComboboxItemDto> getAssetLogTypeComboboxList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("1", "全部", false));
            itemDtoList.add(new ComboboxItemDto("2", "充值", false));
            itemDtoList.add(new ComboboxItemDto("3", "支付账单", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取后台预约订单类型列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

}
