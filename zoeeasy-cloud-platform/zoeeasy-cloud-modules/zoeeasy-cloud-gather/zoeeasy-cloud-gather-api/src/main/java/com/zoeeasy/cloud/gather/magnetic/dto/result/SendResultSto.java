package com.zoeeasy.cloud.gather.magnetic.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.enums.SendResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推送数据返回参数
 *
 * @Date: 2018/12/7
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SendResultSto", description = "推送数据返回参数")
public class SendResultSto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("code")
    private Integer code;
    @ApiModelProperty("body")
    private ResultBody body;

    @Data
    @EqualsAndHashCode(callSuper = false)
    class ResultBody extends BaseDto {
        private static final long serialVersionUID = 1L;
        @ApiModelProperty("msg")
        private String msg;
    }

    public SendResultSto makeResult(Integer retCode, String retMsg) {
        if (retCode != null) {
            this.setCode(retCode);
        }
        if (retMsg != null) {
            ResultBody resultBody = new ResultBody();
            resultBody.setMsg(retMsg);
            this.setBody(resultBody);
        }
        return this;
    }

    public SendResultSto success() {
        return this.makeResult(SendResultEnum.SUCCESS.getValue(), SendResultEnum.SUCCESS.getComment());
    }

    public SendResultSto failed() {
        return this.makeResult(SendResultEnum.FAILED.getValue(), SendResultEnum.FAILED.getComment());
    }
}
