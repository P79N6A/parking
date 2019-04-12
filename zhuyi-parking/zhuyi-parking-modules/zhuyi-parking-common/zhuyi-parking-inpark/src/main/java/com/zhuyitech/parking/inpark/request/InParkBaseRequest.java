package com.zhuyitech.parking.inpark.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Inpark请求参数
 *
 * @author walkman
 */
@Data
public class InParkBaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String timePoint;

    private String sign;

}
