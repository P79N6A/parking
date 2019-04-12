package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 *     微信对账单
 * </pre>
 *
 * @author walkman
 * @date 2017-07-20-14:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatDownloadBillParams extends WeChatPayBaseParam {

    /**
     * 对账单日期
     */
    @NotNull
    private String billDate;

    /**
     * 账单类型
     */
    @NotNull
    private String billType;
}
