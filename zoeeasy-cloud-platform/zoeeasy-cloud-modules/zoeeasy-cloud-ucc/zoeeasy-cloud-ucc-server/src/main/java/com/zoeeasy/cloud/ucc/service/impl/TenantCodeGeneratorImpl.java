package com.zoeeasy.cloud.ucc.service.impl;

import com.zoeeasy.cloud.ucc.tenant.TenantCodeGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 租户代码生成服务
 *
 * @author walkman
 */
@Service("tenantCodeGenerator")
public class TenantCodeGeneratorImpl implements TenantCodeGenerator {

    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 这里暂时生成短8位的UUID
     *
     * @param tenantName tenantName
     * @return
     */
    @Override
    public String generateCode(String tenantName) {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

}
