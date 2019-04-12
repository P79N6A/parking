package com.zoeeasy.cloud.core;

/**
 * 系统静态角色定义
 *
 * @author walkman
 * @date 2018-08-28
 */
public final class StaticRoles {

    private StaticRoles() {
    }

    /**
     * HOST
     */
    public static class Host {
        private Host() {
        }

        public static final String ADMIN = "HOST_ADMIN";
        public static final Long ADMIN_ID = 1L;
        public static final String ADMIN_NAME = "管理员";
        public static final String USER = "HOST_USER";
        public static final Long USER_ID = 2L;
        public static final String USER_NAME = "用户";
    }

    /**
     * Tenants
     */
    public static class Tenants {
        private Tenants() {
        }

        public static final String ADMIN = "TENANT_ADMIN";
        public static final Long ADMIN_ID = 3L;
        public static final String ADMIN_NAME = "管理员";

        public static final String USER = "TENANT_USER";
        public static final Long USER_ID = 4L;
        public static final String USER_NAME = "用户";
    }
}
