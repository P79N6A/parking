package com.zhuyitech.parking.inpark.request;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取accessToken参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InParkOrderListRequest extends InParkBaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * accessToken
     */
    private String mobile;

}
