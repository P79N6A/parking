package com.zhuyitech.parking.ucc.car.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AkeemSuper
 * @date 2018/4/20 0020
 */
@Data
public class UserCarPlateColor implements Serializable {

    private Integer plateColor;

    private String colorDetail;

    public UserCarPlateColor(Integer plateColor, String colorDetail) {
        this.plateColor = plateColor;
        this.colorDetail = colorDetail;
    }

}
