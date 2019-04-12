package com.zoeeasy.cloud.notify.notification.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 通知消息列表请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NotificationListGetRequestDto", description = "通知消息列表请求参数")
public class NotificationListGetRequestDto extends SignedSessionPagedRequestDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 消息通知类型(1:通知 2:活动 3:互动 4:快报)
     */
    @ApiModelProperty(value = "消息通知类型(1:通知 2:活动 3:互动 4:快报)", required = true, allowableValues = "1,2,3,4")
    @NotNull(message = NotifyConstant.NOTIFY_TYPE_NOT_NULL)
    private Integer notifyType;

    /**
     * 消息日期
     */
    @ApiModelProperty(value = "消息日期(默认全部)")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date notifyDate;

    /**
     * 读标志：N未读，Y已读
     */
    @ApiModelProperty(value = "读标志：N未读，Y已读")
    private Boolean readStatus;

}
