package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by song on 2018/10/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "EndDateResultDto", description = "结束时间视图模型")
public class EndDateResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = Const.FORMAT_DATETIME)
    private Date endDate;

}
