package com.zhuyitech.parking.inpark.request;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取附近InPark停车场列表
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InParkParkingListRequest extends InParkBaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * accessToken
     */
    private String mobile;

    private String lat;

    private String lng;

    private String carNum;

}
