package com.zoeeasy.cloud.axino.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回数据model
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
@NoArgsConstructor
@AllArgsConstructor
public class ResultMode {

    /**
     * 返回码
     */
    @Getter
    @Setter
    private String code;

    /**
     * 描述
     */
    @Getter
    @Setter
    private String describe;

    /**
     * 返回值
     */
    @Getter
    @Setter
    private String result;

}
