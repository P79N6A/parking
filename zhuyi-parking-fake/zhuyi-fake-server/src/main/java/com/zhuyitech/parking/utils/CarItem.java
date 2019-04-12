package com.zhuyitech.parking.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author AkeemSuper
 * @date 2018/11/29 0029
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CarItem {

    private String plateNumber;

    private Integer plateColor;

    private Integer carType;

}
