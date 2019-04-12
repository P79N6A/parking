package com.zoeeasy.cloud.core.utils;

import com.zoeeasy.cloud.core.enums.*;

/**
 * 枚举常量转换器
 *
 * @author walkman
 */
public class EnumConverter {

    private EnumConverter() {
    }

    /**
     * 海康过车类型转换为平台过车类型
     *
     * @param passingDirection
     * @return
     */
    public static PassingTypeEnum fromHikPassingDirection(PassingDirectionEnum passingDirection) {
        if (passingDirection == null) {
            return null;
        }
        PassingTypeEnum passingCarTypeEnum;
        switch (passingDirection) {
            case ENTRANCE:
                passingCarTypeEnum = PassingTypeEnum.ENTRY;
                break;
            case EXIT:
                passingCarTypeEnum = PassingTypeEnum.EXIT;
                break;
            default:
                passingCarTypeEnum = null;
        }
        return passingCarTypeEnum;
    }

    /**
     * 海康车牌颜色转换为平台车牌颜色
     *
     * @param plateColorStyle
     * @return
     */
    public static LicensePlateColorEnum fromHikPlateColorStyle(PlateColorStyleEnum plateColorStyle) {

        if (plateColorStyle == null) {
            return null;
        }
        LicensePlateColorEnum licensePlateColor;
        switch (plateColorStyle) {
            case OTHER:
                licensePlateColor = LicensePlateColorEnum.OTHER;
                break;
            case BLUE:
                licensePlateColor = LicensePlateColorEnum.BLUE;
                break;
            case YELLOW:
                licensePlateColor = LicensePlateColorEnum.YELLOW;
                break;
            case BLACK:
                licensePlateColor = LicensePlateColorEnum.BLACK;
                break;
            case WHITE:
                licensePlateColor = LicensePlateColorEnum.WHITE;
                break;
            case GREEN:
                licensePlateColor = LicensePlateColorEnum.GREEN;
                break;
            default:
                licensePlateColor = LicensePlateColorEnum.OTHER;
        }
        return licensePlateColor;
    }

    /**
     * 平台车牌颜色转换为海康车牌颜色
     *
     * @param licensePlateColor licensePlateColor
     * @return
     */
    public static PlateColorStyleEnum toHikPlateColorStyle(LicensePlateColorEnum licensePlateColor) {

        if (licensePlateColor == null) {
            return null;
        }
        PlateColorStyleEnum plateColorStyle;
        switch (licensePlateColor) {
            case OTHER:
                plateColorStyle = PlateColorStyleEnum.OTHER;
                break;
            case BLUE:
                plateColorStyle = PlateColorStyleEnum.BLUE;
                break;
            case YELLOW:
                plateColorStyle = PlateColorStyleEnum.YELLOW;
                break;
            case BLACK:
                plateColorStyle = PlateColorStyleEnum.BLACK;
                break;
            case WHITE:
                plateColorStyle = PlateColorStyleEnum.WHITE;
                break;
            case GREEN:
                plateColorStyle = PlateColorStyleEnum.GREEN;
                break;
            default:
                plateColorStyle = PlateColorStyleEnum.OTHER;
        }
        return plateColorStyle;
    }

    /**
     * 海康车辆类型转换为平台车辆类型
     *
     * @param vehicleLevelEnum
     * @return
     */
    public static CarTypeEnum fromHikCarType(VehicleLevelEnum vehicleLevelEnum) {

        if (vehicleLevelEnum == null) {
            return null;
        }
        CarTypeEnum vehicleLevel;
        switch (vehicleLevelEnum) {
            case OTHER:
                vehicleLevel = CarTypeEnum.OTHER_CAR;
                break;
            case SMALL:
                vehicleLevel = CarTypeEnum.SMALL_CAR;
                break;
            case LARGE:
                vehicleLevel = CarTypeEnum.BIG_CAR;
                break;
            default:
                vehicleLevel = CarTypeEnum.OTHER_CAR;
        }
        return vehicleLevel;
    }

}
