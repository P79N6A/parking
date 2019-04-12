package com.zoeeasy.cloud.tool.constant;

import com.scapegoat.infrastructure.config.FundamentalConfigProvider;

/**
 * 七牛oss的配置
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
public class QiNiuConstant {

    private QiNiuConstant() {
    }

    /**
     * 七牛oss的AccessKey
     */
    public static final String QINIU_ACCESSKAY = FundamentalConfigProvider.getString(QiNiuPropertyKeys.QINIU_ACCESSKAY);

    /**
     * 七牛oss的SecretKey
     */
    public static final String QINIU_SECRETKAY = FundamentalConfigProvider.getString(QiNiuPropertyKeys.QINIU_SECRETKAY);

    /**
     * 七牛oss的Bucket
     */
    public static final String QINIU_BUCKET = FundamentalConfigProvider.getString(QiNiuPropertyKeys.QINIU_BUCKET);

    /**
     * 七牛oss的默认域名
     */
    public static final String QINIU_URL = FundamentalConfigProvider.getString(QiNiuPropertyKeys.QINIU_URL);

}
