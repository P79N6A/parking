package com.zoeeasy.cloud.sys.visit.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2019/2/22 0022
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitLogQueryPageRequestDto", description = "分页获取用户登录日志请求参数")
public class VisitLogQueryPageRequestDto extends SignedPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String account;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date endTime;
}
