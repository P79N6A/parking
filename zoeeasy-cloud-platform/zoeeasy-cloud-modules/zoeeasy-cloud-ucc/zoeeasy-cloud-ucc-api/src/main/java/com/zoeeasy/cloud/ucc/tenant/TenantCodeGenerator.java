package com.zoeeasy.cloud.ucc.tenant;

/**
 * 租户代码生成服务
 *
 * @author walkman
 */
public interface TenantCodeGenerator {

    /**
     * 生成租户代码
     *
     * @param tenantName tenantName
     * @return
     */
    String generateCode(String tenantName);
}
