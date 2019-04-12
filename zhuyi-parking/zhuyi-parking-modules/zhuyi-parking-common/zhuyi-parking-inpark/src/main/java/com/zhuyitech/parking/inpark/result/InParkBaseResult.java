package com.zhuyitech.parking.inpark.result;

import lombok.Data;

import java.io.Serializable;


/**
 * Inpark响应结果
 *
 * @author walkman
 */
@Data
public class InParkBaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String data;

    private String onceToken;

    private String info;

}