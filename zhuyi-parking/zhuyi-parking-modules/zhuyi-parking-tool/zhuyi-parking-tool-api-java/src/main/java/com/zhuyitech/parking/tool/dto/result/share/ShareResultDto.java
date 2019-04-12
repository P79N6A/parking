package com.zhuyitech.parking.tool.dto.result.share;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分享响应结果视图
 *
 * @author AkeemSuper
 * @date 2018/5/24 0024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "shareResultDto", description = "分享响应结果视图")
public class ShareResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 图片url
     */
    @ApiModelProperty("图片url")
    private String imageUrl;

    /**
     * 链接
     */
    @ApiModelProperty("链接")
    private String linkUrl;

}
