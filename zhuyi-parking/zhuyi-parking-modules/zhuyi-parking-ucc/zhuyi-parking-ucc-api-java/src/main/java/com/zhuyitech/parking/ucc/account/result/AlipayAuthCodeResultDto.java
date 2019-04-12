package com.zhuyitech.parking.ucc.account.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 授权请求参数返回
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayAuthCodeResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * urlNeeded
     */
    private String urlNeeded;
}
