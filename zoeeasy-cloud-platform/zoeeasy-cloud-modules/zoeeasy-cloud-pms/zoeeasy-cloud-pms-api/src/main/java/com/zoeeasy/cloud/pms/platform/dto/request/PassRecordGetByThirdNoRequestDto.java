package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassRecordGetByThirdNoRequestDto", description = "查询平台过车记录请求参数")
public class PassRecordGetByThirdNoRequestDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 入车流水号
     */
    @ApiModelProperty(value = "过车记录流水号", required = true)
    @NotBlank(message = PassingConstant.PASSING_NUMBER_NOT_EMPTY)
    private String thirdPassingId;

    /**
     * 停车场code
     */
    @ApiModelProperty(value = "过车记录code", required = true)
    @NotBlank(message = PassingConstant.CLOUD_PARKING_CODE_NOT_EMPTY)
    private String cloudParkingCode;
}
