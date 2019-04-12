package com.zhuyitech.parking.tool.dto.request.notification;


import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 通知消息列表请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationListGetRequestDto", description = "通知消息列表请求参数")
public class NotificationListGetRequestDto extends SessionPagedRequestDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 消息通知类型(1:通知 2:活动 3:互动 4:快报)
     */
    @ApiModelProperty(value = "消息通知类型(1:通知 2:活动 3:互动 4:快报)", required = true, allowableValues = "1,2,3,4")
    @NotNull(message = "消息通知类型不能为空")
    private Integer notifyType;

    /**
     * 消息日期
     */
    @ApiModelProperty(value = "消息日期(默认全部)")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date notifyDate;

}
