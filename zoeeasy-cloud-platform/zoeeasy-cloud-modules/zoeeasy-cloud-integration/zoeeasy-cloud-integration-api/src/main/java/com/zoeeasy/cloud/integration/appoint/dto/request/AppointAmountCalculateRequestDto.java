package com.zoeeasy.cloud.integration.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 预约金额计算请求参数
 *
 * @author zwq
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointAmountCalculateRequestDto", description = "预约金额计算请求参数")
public class AppointAmountCalculateRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = IntegrationConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间,(时间格式yyyy-MM-dd HH:mm:ss)")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    @NotNull(message = IntegrationConstant.APPOINT_TIME_NOT_EMPTY)
    private Date appointTime;
}
