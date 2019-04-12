package com.zhuyitech.parking.ucc.dto.request.driverlicense;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修改用户驾驶证请求参数
 *
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
@ApiModel(value = "UserDriverLicenseUpdateRequestDto", description = "修改用户驾驶证请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDriverLicenseUpdateRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 证号
     */
    @ApiModelProperty("证号")
    @NotBlank(message = "证号")
    private String cardNumber;

    /**
     * 档案编号
     */
    @ApiModelProperty("档案编号")
    @NotBlank(message = "档案编号")
    private String archiveNumber;

    /**
     * 初次领证日期
     */
    @ApiModelProperty("初次领证日期")
    @NotNull(message = "初次领证日期")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date firstIssueDate;

    /**
     * 准驾车型
     */
    @ApiModelProperty("准驾车型")
    @NotBlank(message = "准驾车型")
    private String driveClass;

    /**
     * 扣分数
     */
    @ApiModelProperty("扣分数")
    private Integer score;

    /**
     * 有效期开始
     */
    @ApiModelProperty("有效期开始")
    @NotNull(message = "有效期开始")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date validateDateStart;
    
    /**
     * 有效期结束
     */
    @ApiModelProperty("有效期结束")
    @NotNull(message = "有效期结束")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date validateDateEnd;

}
