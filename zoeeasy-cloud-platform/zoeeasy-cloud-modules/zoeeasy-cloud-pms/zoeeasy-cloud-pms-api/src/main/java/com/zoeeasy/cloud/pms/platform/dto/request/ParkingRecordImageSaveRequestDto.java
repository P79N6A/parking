package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/25 0025
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "parkingRecordImageSaveRequestDto", description = "保存停车过车记录图片请求参数")
public class ParkingRecordImageSaveRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = true)
    @NotNull(message = "租户id不能为空")
    private Long tenantId;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = "停车场id不能为空")
    private Long parkingId;

    /**
     * 停车/过车记录ID
     */
    @ApiModelProperty(value = "停车/过车记录ID", required = true)
    @NotNull(message = "停车/过车记录ID不能为空")
    private Long bizId;
    /**
     * 停车/过车记录号
     */
    @ApiModelProperty(value = "停车/过车记录号", required = true)
    @NotBlank(message = "停车/过车号不能为空")
    private String bizNo;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型", required = true)
    @NotNull(message = "业务类型不能为空")
    private Integer bizType;

    /**
     * 图片唯一id
     */
    @ApiModelProperty(value = "图片唯一id")
    private String uuid;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

    /**
     * 图片文件类型
     */
    @ApiModelProperty(value = "图片文件类型", required = true)
    @NotNull(message = "图片文件类型不能为空")
    private Integer type;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

}
