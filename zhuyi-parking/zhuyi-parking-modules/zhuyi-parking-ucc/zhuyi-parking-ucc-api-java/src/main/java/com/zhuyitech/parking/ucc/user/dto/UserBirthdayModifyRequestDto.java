package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修改生日参数
 *
 * @author zwq
 */
@ApiModel(description = "修改生日参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBirthdayModifyRequestDto extends SessionDto {

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", required = true)
    @NotNull(message = "生日不能为空")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date birthday;

}
