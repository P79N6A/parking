package com.zoeeasy.cloud.pay.wechat.result;

import com.google.common.base.Joiner;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zoeeasy.cloud.pay.wechat.util.XStreamInitializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * <pre>
 *   微信支付结果共用属性类
 * </pre>
 *
 * @author walkman
 * @date 2017-07-10-14:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayBaseResult extends BaseDto {

    private static final long serialVersionUID = 7326351589130536779L;

    /**
     * 返回状态码
     */
    @XStreamAlias("return_code")
    protected String returnCode;

    /**
     * 返回信息
     */
    @XStreamAlias("return_msg")
    protected String returnMsg;

    /**
     * 业务结果
     */
    @XStreamAlias("result_code")
    private String resultCode;

    /**
     * 错误代码
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;

    /**
     * 公众账号ID
     */
    @XStreamAlias("appid")
    private String appid;

    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;

    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    @XStreamAlias("sign")
    private String sign;

    // 以下为辅助属性
    /**
     * xml字符串
     */
    private String xmlString;

    /**
     * xml的Document对象，用于解析xml文本
     */
    private Document xmlDoc;

    /**
     * 从xml字符串创建bean对象
     *
     * @param stringXML
     * @param clz
     * @param <T>
     * @return
     */
    public static <T extends WeChatPayBaseResult> T fromXML(String stringXML, Class<T> clz) {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(clz);
        T result = (T) xstream.fromXML(stringXML);
        result.setXmlString(stringXML);
        return result;
    }

    /**
     * 将xml字符串转换成Document对象，以便读取其元素值
     */
    protected Document getXmlDoc() {
        if (this.xmlDoc != null) {
            return this.xmlDoc;
        }
        try {
            this.xmlDoc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(this.xmlString.getBytes("UTF-8")));
            return xmlDoc;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException("非法的xml文本内容：" + this.xmlString);
        }

    }

    /**
     * 获取xml中元素的值
     */
    protected String getXmlValue(String... path) {
        Document doc = this.getXmlDoc();
        String expression = String.format("/%s//text()", Joiner.on("/").join(path));
        try {
            return (String) XPathFactory
                    .newInstance()
                    .newXPath()
                    .compile(expression)
                    .evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new RuntimeException("未找到相应路径的文本：" + expression);
        }
    }

    /**
     * 获取xml中元素的值，作为int值返回
     */
    protected Integer getXmlValueAsInt(String... path) {
        String result = this.getXmlValue(path);
        if (StringUtils.isBlank(result)) {
            return null;
        }

        return Integer.valueOf(result);
    }

}