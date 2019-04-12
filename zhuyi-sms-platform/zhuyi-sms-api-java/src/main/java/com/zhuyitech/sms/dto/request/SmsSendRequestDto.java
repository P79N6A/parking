package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 短信发送请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "", description = "短信发送请求参数")
public class SmsSendRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公共回传参数，在“消息返回”中会透传回该参数；
     * 举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
     * 可选	123456
     */
    private String extend;

    /**
     * 发送模式
     * 1:单条发送 2:  批量发送
     */
    private Integer sendMode = 1;

    /**
     * 必须 阿里大于
     * 短信签名，传入的短信签名必须是在阿里大于“管理中心-验证码/短信通知/推广短信-配置短信签名”中的可用签名。如“阿里大于”已在短信签名管理中通过审核，则可传入”阿里大于“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大于】欢迎使用阿里大于服务。
     */
    private String signName;

    /**
     * 可选
     * 短信模板变量，传参规则
     * <p>
     * {
     * "key":"value"
     * }，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码$
     * <p>
     * {
     * code
     * }，您正在进行$
     * <p>
     * {
     * product
     * }
     * <p>
     * 身份验证，打死不要告诉别人哦！”，传参时需传入
     * <p>
     * {
     * "code":"1234", "product":"alidayu"
     * }
     */
    private String paramString;

    /**
     * 必须13000000000短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
     */
    private String phoneNumber;

    /**
     * 必须 SMS_585014
     * 短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
     */
    private String templateCode;

    /**
     * 短信内容
     */
    private String content;

    /**
     *
     */
    private String outId;

}
