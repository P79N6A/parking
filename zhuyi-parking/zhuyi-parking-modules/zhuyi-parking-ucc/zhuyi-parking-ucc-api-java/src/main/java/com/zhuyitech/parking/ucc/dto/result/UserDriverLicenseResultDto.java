package com.zhuyitech.parking.ucc.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户驾驶证信息视图
 *
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
@ApiModel(value = "UserDriverLicenseResultDto", description = "用户驾驶证信息视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDriverLicenseResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("userId")
    private Long userId;

    /**
     * 证号
     */
    @ApiModelProperty("证号")
    private String cardNumber;

    /**
     * 档案编号
     */
    @ApiModelProperty("档案编号")
    private String archiveNumber;

    /**
     * 初次领证日期
     */
    @ApiModelProperty("初次领证日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date firstIssueDate;

    /**
     * 准驾车型
     */
    @ApiModelProperty("准驾车型")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date validateDateStart;

    /**
     * 有效期结束
     */
    @ApiModelProperty("有效期结束")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date validateDateEnd;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

}
