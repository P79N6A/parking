package com.zoeeasy.cloud.gather.magnetic.dto.request.fushang;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.magnetic.cst.InMotionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 富尚推送车位状态请求参数
 *
 * @Date: 2018/12/5
 * @author: lhj
 */
@ApiModel(value = "FuShangParkStatusPushRequestDto", description = "富尚推送车位状态请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class FuShangParkStatusPushRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 指令编码
     */
    @ApiModelProperty(value = "指令编码")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_CMD_NOT_NULL)
    private String cmd;

    /**
     * 主体
     */
    @ApiModelProperty(value = "主体")
    private FuShangParkStatusBodyRequestDto body;
}
