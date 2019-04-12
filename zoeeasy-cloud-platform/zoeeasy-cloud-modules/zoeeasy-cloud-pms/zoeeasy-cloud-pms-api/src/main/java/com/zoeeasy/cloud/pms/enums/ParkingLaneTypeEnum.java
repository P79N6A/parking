package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ParkingLaneTypeEnum {

    IN_LANE(1, "入车道"),
    OUT_LANE(2, "出车道"),
    ACCESS_LANE(3, "出入车道"),
    ;

    @Getter
    private Integer value;

    @Getter
    private String comment;


    /**
     * 根据值获取对应枚举
     *
     * @param value
     * @return
     */
    public static ParkingLaneTypeEnum parse(Integer value) {
        if (value != null) {
            ParkingLaneTypeEnum[] array = values();
            if (array != null) {
                for (ParkingLaneTypeEnum temp : array) {
                    if (value.equals(temp.value)) {
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 根据注释获取对应枚举
     *
     * @param comment
     * @return
     */
    public static ParkingLaneTypeEnum getValue(String comment) {
        if (comment != null) {
            ParkingLaneTypeEnum[] array = values();
            if (array != null) {
                for (ParkingLaneTypeEnum temp : array) {
                    if (comment.equals(temp.comment)) {
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }


}
