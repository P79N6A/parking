-- ----------------------------
-- Table structure for tenant
-- 租户表
-- ----------------------------
DROP TABLE IF EXISTS `ucc_tenant`;
CREATE TABLE `ucc_tenant` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '租户编号：暂定32位，编号生成算法确定后再修改',
  `name` varchar(64) NOT NULL COMMENT '租户名称',
  `type`   tinyint(4) unsigned NOT NULL COMMENT '商户类型：1 企业； 2 其他组织',
  `status` tinyint(4) unsigned NOT NULL COMMENT '运营商状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除',
  `domain` varchar(2000) DEFAULT NULL COMMENT '域名',
  `logo` varchar(2000) DEFAULT NULL COMMENT 'logo图片地址',
  `url` varchar(2000) DEFAULT NULL COMMENT 'url',
  `description` varchar(2000) DEFAULT NULL COMMENT '简介',
  `adminUserId` bigint(20) NOT NULL COMMENT '超级管理员用户',
  `beginTime` DATETIME NOT NULL COMMENT '租户生效时间',
  `endTime` DATETIME  NOT NULL COMMENT '租户到期时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) unsigned  DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='租户表';

-- ----------------------------
-- Table structure for tenant_info
-- 租户信息表
-- ----------------------------
DROP TABLE IF EXISTS `ucc_tenant_info`;
CREATE TABLE `ucc_tenant_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL  COMMENT '租户ID',
  `agencyName` varchar(2000) NOT NULL COMMENT '机构名称',
  `registrationNumber`  varchar(18) NOT NULL COMMENT '营业执照代码',
  `agencyCode` varchar(32) NOT NULL COMMENT '营业执照或者机构代码',
  `registerAddree` varchar(2000) NOT NULL COMMENT '企业注册地',
  `representativeName` varchar(32) NOT NULL COMMENT '法人代表姓名',
  `representativeIdNo` varchar(18) NOT NULL COMMENT '法人身份证号',
  `manager` varchar(64) NOT NULL COMMENT '负责人',
  `phoneNumber` varchar(32) NOT NULL COMMENT '联系电话，可为手机号码、固定电话号码',
  `phoneConfirmed` tinyint(1) unsigned NOT NULL COMMENT '手机号是否验证',
  `emailAddress` varchar(64) DEFAULT NULL COMMENT '电子邮箱地址',
  `emailAddressConfirmed` tinyint(1) unsigned NOT NULL COMMENT '电子邮箱是否验证',
  `licensePhotoUrl` varchar(256) NOT NULL COMMENT '营业执照或者机构代码图片路径',
  `provinceCode` varchar(32) DEFAULT NULL COMMENT '省代码',
  `cityCode` varchar(32) DEFAULT NULL COMMENT '市代码',
  `countryCode` varchar(32) DEFAULT NULL COMMENT '区县代码',
  `address` varchar(2000) DEFAULT NULL COMMENT '地址',
  `approveUserId` bigint DEFAULT NULL COMMENT '审核管理员id',
  `approveTime` DATETIME DEFAULT NULL COMMENT '审核通过时间',
  `approveInfo` varchar(2000) DEFAULT NULL COMMENT '审核详情',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_tenantId` (`tenantId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='租户信息表';


-- ----------------------------
-- Table structure for cys_tenant_apply_info
-- 租户申请表
-- ----------------------------
DROP TABLE IF EXISTS `ucc_tenant_apply_info`;
CREATE TABLE `tenant_apply_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '主键',
  `userId` bigint(20) unsigned NOT NULL COMMENT '申请人ID',
  `applyNo` varchar(32) NOT NULL COMMENT '申请编号',
  `applyTime` datetime NOT NULL COMMENT '申请时间',
  `status` tinyint(4) unsigned NOT NULL COMMENT '状态：0 申请中；1 已经结束',
  `agencyName` varchar(2000) NOT NULL COMMENT '机构名称',
  `registrationNumber`  varchar(18) NOT NULL COMMENT '营业执照代码',
  `agencyCode` varchar(32) NOT NULL COMMENT '营业执照或者机构代码',
  `registerAddree` varchar(128) NOT NULL COMMENT '企业注册地',
  `representativeName` varchar(32) NOT NULL COMMENT '法人代表姓名',
  `representativeIdNo` varchar(18) NOT NULL COMMENT '法人身份证号',
  `manager` varchar(64) NOT NULL COMMENT '负责人',
  `phoneNumber` varchar(32) NOT NULL COMMENT '联系电话，可为手机号码、固定电话号码',
  `phoneConfirmed` tinyint(1) unsigned NOT NULL COMMENT '手机号是否验证',
  `emailAddress` varchar(64) DEFAULT NULL COMMENT '电子邮箱地址',
  `emailAddressConfirmed` tinyint(1) unsigned NOT NULL COMMENT '电子邮箱是否验证',
  `licensePhotoUrl` varchar(256) NOT NULL COMMENT '营业执照或者机构代码图片路径',
  `provinceCode` varchar(32) DEFAULT NULL COMMENT '省代码',
  `cityCode` varchar(32) DEFAULT NULL COMMENT '市代码',
  `countryCode` varchar(32) DEFAULT NULL COMMENT '区县代码',
  `address` varchar(2000) DEFAULT NULL COMMENT '地址',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`app_id`),
  KEY `idx_tenantId` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户申请表';

-- ----------------------------
-- Table structure for tenant_approve_info
-- 租户审核表
-- ----------------------------
DROP TABLE IF EXISTS `ucc_tenant_approve_info`;
CREATE TABLE `cys_tenant_approve_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `applyId` bigint(20) unsigned NOT NULL COMMENT '申请ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `approveUserId` bigint(20) unsigned NOT NULL COMMENT '审批人ID',
  `approveUserName` bigint(20) unsigned NOT NULL COMMENT '审批人姓名',
  `approveTime` datetime NOT NULL COMMENT '审批时间',
  `result` tinyint(4) unsigned NOT NULL COMMENT '审批结果：0 不通过；1 通过',
  `remark` varchar(256) DEFAULT NULL COMMENT '审批不通过时的描述信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`applyId`),
  KEY `idx_applyId` (`applyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户审核表';


-- ----------------------------
-- Table structure for permission
-- 权限表
-- ----------------------------
DROP TABLE IF EXISTS `ucc_permission`;
CREATE TABLE `ucc_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `userId` bigint(20) unsigned DEFAULT  NULL COMMENT '用户ID',
  `code` varchar(64) NOT NULL COMMENT '权限编码',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `granted` tinyint(1) DEFAULT NULL COMMENT '是否已授权',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0、禁用 1、正常 ',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_tenantId` (`tenantId`),
  KEY `idx_userId` (`userId`),
  KEY `idx_code` (`code`),
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COMMENT='权限表';

-- ----------------------------
-- Table structure for menu
-- 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `ucc_menu`;
CREATE TABLE `ucc_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `code` varchar(100) NOT NULL COMMENT '菜单名称',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `type` varchar(100) NOT NULL COMMENT '资源类型',
  `url` varchar(255) NOT NULL COMMENT '地址',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parentIds` varchar(255) DEFAULT NULL COMMENT '父节点路径',
  `shown` tinyint(1) DEFAULT '0' COMMENT '是否显示 1：显示 0：隐藏',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0、禁用 1、正常 ',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_applyId` (`applyId`)
  KEY `idx_code` (`code`),
  KEY `idx_parent` (`parentId`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cys_role
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `ucc_role`;
CREATE TABLE `ucc_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `code` varchar(100) NOT NULL COMMENT '角色代码',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `staticRole` char(1) DEFAULT NULL COMMENT '是否静态',
  `defaultRole` char(1) DEFAULT NULL COMMENT '是否默认',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0、禁用 1、正常 ',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_role_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Table structure for ucc_organization
-- 组织结构表
-- ----------------------------
CREATE TABLE `ucc_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `code` varchar(100) NOT NULL COMMENT '机构代码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parentIds` varchar(2000) DEFAULT NULL COMMENT '父节点路径',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0、禁用 1、正常 ',
  `remarks` varchar(255) DEFAULT NULL COMMENT '说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
  KEY `idx_tenantId` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织表';


-- ----------------------------
-- Table structure for ucc_role_permission
-- 角色权限关系表
-- ----------------------------
CREATE TABLE `ucc_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `roleId` bigint(20) NOT NULL COMMENT '角色ID',
  `permissionId` bigint(20) NOT NULL COMMENT '用户ID',
  `creatorUserId` bigint(20) NOT NULL COMMENT '创建者',
  `creationTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_tenantId` (`tenantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限表';


-- ----------------------------
-- Table structure for ucc_role_menu
-- 角色菜单关系表
-- ----------------------------
CREATE TABLE `ucc_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `menuId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  `creatorUserId` bigint(20) NOT NULL,
  `creationTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for ucc_user
-- 用户表
-- ----------------------------
CREATE TABLE `ucc_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `userName` varchar(32) NOT NULL COMMENT '用户名',
  `account`  varchar(32) NOT NULL COMMENT '账户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt`   varchar(64) DEFAULT NULL COMMENT '盐',
  `userType` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '用户的状态',
  `registerChannel` tinyint(1) DEFAULT NULL COMMENT '用户注册渠道',
  `phoneNumber` varchar(11) DEFAULT NULL COMMENT '手机',
  `phoneNumberConfirmed` tinyint(1) DEFAULT NULL COMMENT '手机号是否确认',
  `emailAddress` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `emailAddressConfirmed` tinyint(1) DEFAULT NULL COMMENT '邮箱是否确认',
  `emailConfirmationCode` varchar(64) DEFAULT NULL COMMENT '邮箱确认码',
  `portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `accessAttemptCount` int(11) DEFAULT NULL COMMENT '失败登录次数',
  `passwordResetCode` varchar(64) DEFAULT NULL COMMENT '密码重置码',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`),
  KEY `idx_phoneNumber` (`phoneNumber`),
  KEY `idx_emailAddress` (`emailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for cys_user_organization
-- 用户组织关系表
-- ----------------------------
CREATE TABLE `ucc_user_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `userId`  bigint(20) NOT NULL,
  `organizationId` bigint(20) NOT NULL,
  `creatorUserId` bigint(20) NOT NULL,
  `creationTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for ucc_user_login_attempt
-- 用户尝试登陆表
-- ----------------------------
CREATE TABLE `ucc_user_login_attempt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `account`  varchar(32) NOT NULL COMMENT '账户名',
  `credential` varchar(32) NOT NULL COMMENT '登陆凭证',
  `channel` char(1) DEFAULT NULL COMMENT '登陆渠道',
  `clientId` varchar(64) DEFAULT NULL COMMENT '客户端ID',
  `clientName` varchar(64) DEFAULT NULL COMMENT '客户端ID',
  `browserInfo` varchar(64) DEFAULT NULL COMMENT '浏览器信息',
  `deviceInfo` varchar(64) DEFAULT NULL COMMENT '设备信息',
  `result` tinyint(4) DEFAULT NULL COMMENT '登陆结果',
  `remarks` varchar(255) DEFAULT NULL COMMENT '说明',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for ucc_user_login_log
-- 用户登陆日志表
-- ----------------------------
CREATE TABLE `ucc_user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `userId` bigint(20) DEFAULT NULL COMMENT '登录用户ID',
  `channel` char(1) DEFAULT NULL COMMENT '登陆渠道',
  `clientId` varchar(255) DEFAULT NULL COMMENT '客户端ID',
  `clientIp` varchar(255) DEFAULT NULL COMMENT '客户端IP',
  `deviceInfo` varchar(64) DEFAULT NULL COMMENT '设备信息',
  `browserInfo` varchar(2000) DEFAULT NULL COMMENT '浏览器信息',
  `requestUrl` varchar(2000) DEFAULT NULL COMMENT '客户端发出请求时的完整URL',
  `requestUri` varchar(2000) DEFAULT NULL COMMENT '请求行中的资源名部分',
  `requestVerb` varchar(255) DEFAULT NULL COMMENT '客户机请求方式',
  `localAddress` varchar(255) DEFAULT NULL COMMENT 'WEB服务器的IP地址',
  `localName` varchar(255) DEFAULT NULL COMMENT 'WEB服务器的主机名',
  `sessionId` varchar(255) DEFAULT NULL COMMENT '当前会话请求的sessionId',
  `accessToken` varchar(2000) DEFAULT NULL COMMENT '访问令牌',
  `refreshToken` varchar(2000) DEFAULT NULL COMMENT '访问令牌',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

-- ---------------------------- ---------------------------------------------- ----------------------------------
------------------------------              停车场管理相关                      ----------------------------------      
-- ---------------------------- ---------------------------------------------- ----------------------------------

-- ----------------------------
-- Table structure for pms_area
-- 自定义区域
-- ----------------------------
DROP TABLE IF EXISTS `pms_area`;
CREATE TABLE `pms_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `code` varchar(255) NOT NULL COMMENT '地区代码',
  `name` varchar(255) NOT NULL COMMENT '地区名称',
  `parentId` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `level` tinyint(4) DEFAULT NULL COMMENT '层级',
  `order` tinyint(4) DEFAULT NULL COMMENT '排序',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_parking_info
-- 停车场基本信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_info`;
CREATE TABLE `pms_parking_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `areaId` bigint(20) DEFAULT NULL COMMENT '区域ID',
  `aliParkId` varchar(255) DEFAULT '' COMMENT '支付宝平台停车场ID',
  `hikParkId` varchar(255) DEFAULT '' COMMENT '海康平台停车场ID',
  `code` varchar(255) DEFAULT '' COMMENT '编号',
  `name` varchar(255) DEFAULT '' COMMENT '简称',
  `fullName` varchar(2000) DEFAULT '' COMMENT '全称',
  `status` tinyint(4) DEFAULT 0 COMMENT '在线状态，0-下线，1-在线',
  `platformSupport` tinyint(1) DEFAULT '0' COMMENT '是否平台接入 0:否 1 是',
  `level` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT '停车场等级，0-其它，1-一级停车场，2-二级停车场，3-三级停车场',
  `outTime` smallint(11) DEFAULT '0' COMMENT '用户支付未离场的超时时间(以分钟为单位)',
  `type` varchar(32) DEFAULT '' COMMENT '停车场类型((1为地面，2为地下，3为路边)（多个类型，中间用,隔开)',
  `lotType` varchar(32) DEFAULT '' COMMENT '停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场',
  `logo` varchar(2000) DEFAULT '' COMMENT 'LOGO',
  `openStartTime` varchar(255) DEFAULT '' COMMENT '开始营业时间，格式HH:mm:ss',
  `openEndTime`   varchar(255) DEFAULT '' COMMENT '结束营业时间，格式HH:mm:ss',
  `chargeMode` tinyint(4) DEFAULT 2 COMMENT '缴费模式 1: 离场后缴费 2: 缴费后离场',
  `payMode` varchar(64) DEFAULT '' COMMENT '收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）',
  `payType` varchar(64) DEFAULT '' COMMENT '支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔',
  `latitude` double DEFAULT '0' COMMENT '纬度latitude',
  `longitude` double DEFAULT '0' COMMENT '经度longitude',
  `lotTotal` smallint(6) unsigned NOT NULL DEFAULT 0 COMMENT '车位总数',
  `lotFixed` smallint(6) unsigned NOT NULL DEFAULT 0 COMMENT '固定车总数',
  `description` text COMMENT '描述信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`),
  KEY `idx_aliParkId` (`aliParkId`),
  KEY `idx_hikParkId` (`hikParkId`),
  KEY `idx_latitude_longitude` (`latitude`,`longitude`),
  KEY `idx_deleted` (`deleted`),
  KEY `idx_name` (`name`),
  KEY `idx_latitude` (`latitude`) USING BTREE,
  KEY `idx_longitude` (`longitude`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='停车场信息表';

-- ----------------------------
-- Table structure for pms_parking_detail_info
-- 停车场详细信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_detail_info`;
CREATE TABLE `pms_parking_detail_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `provinceCode` varchar(64) DEFAULT '' COMMENT '省编码',
  `cityCode` varchar(64) DEFAULT '' COMMENT '市编码',
  `countyCode` varchar(64) DEFAULT '' COMMENT '区县编码',
  `address` varchar(2000) DEFAULT '' COMMENT '地址',
  `zipCode` varchar(64) DEFAULT '' COMMENT '邮编',
  `managerUnit` varchar(2000) DEFAULT '' COMMENT '管理单位',
  `ownerName` varchar(2000) DEFAULT '' COMMENT '所有人单位',
  `operatorUnit` varchar(2000) DEFAULT '' COMMENT '运营人单位',
  `chargerUnit` varchar(2000) DEFAULT '' COMMENT '收费单位',
  `contactName` varchar(256) DEFAULT '' COMMENT '联系人',
  `contactTel` varchar(64) DEFAULT '' COMMENT '联系电话',
  `contactPhone` varchar(64) DEFAULT '' COMMENT '联系手机',
  `contactEmail` varchar(64) DEFAULT '' COMMENT '联系人邮箱',
  `contactQQ` varchar(64) DEFAULT '' COMMENT '联系人QQ',
  `contactWeixin` varchar(64) DEFAULT '' COMMENT '联系人微信',
  `contactAlipay` varchar(64) DEFAULT '' COMMENT '联系人支付宝',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_tenantI_parking` (`tenantId`,`parkingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场详细信息';

-- ----------------------------
-- Table structure for pms_parking_charge_info
-- 停车场收费信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_charge_info`;
CREATE TABLE `pms_parking_charge_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `chargeFee` tinyint(1) DEFAULT 0 COMMENT '是否收费 0:不收费 1 收费',
  `freeTime` smallint(11) DEFAULT '20' COMMENT '免费停车时长：单位分钟',
  `chargeRule` varchar(2000) DEFAULT '' COMMENT '收费规则的简短描述',
  `chargeDescription` text COMMENT '收费描述信息',
  `relatedRule` tinyint(1) DEFAULT 0 COMMENT '是否关联收费规则，  0-否，1-是',
  `active` tinyint(1) NOT NULL COMMENT '是否当前有效的收费信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场收费信息';

-- ----------------------------
-- Table structure for pms_parking_appoint_info
-- 停车场预约信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_appoint_info`;
CREATE TABLE `pms_parking_appoint_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `supportAppointment` tinyint(1) unsigned DEFAULT 0 COMMENT '是否支持预约',
  `supportDeposit` tinyint(1) unsigned DEFAULT 0 DEFAULT 0 COMMENT '是否支持将停车预订订金作为停车费的一部分 0 否， 1 是',
  `lotAppointmentTotal` smallint(6) DEFAULT 0 COMMENT '支持预约车位总数',
  `appointmentRule` text COMMENT '预约规则的简短描述',
  `relatedRule` tinyint(1) DEFAULT 0 COMMENT '是否关联预约规则，  0-否，1-是',
  `active` tinyint(1) NOT NULL COMMENT '是否当前有效的收费信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场预约信息';

-- ----------------------------
-- Table structure for pms_parking_current_info
-- 停车场当前状态表
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_current_info`;
CREATE TABLE `pms_parking_current_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `operationState` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT '运营状态，0-正常营业，1-暂停营业',
  `lotAvailable` smallint(6) DEFAULT 0 COMMENT '可用车位数',
  `lotAppointmentAvailable` smallint(6) DEFAULT 0 COMMENT '可预订剩余车位数',
  `lotBagAvailable` smallint(6) DEFAULT 0 COMMENT '剩余可包期车的数量',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场当前状态表';


-- ----------------------------
-- Table structure for pms_parking_picture
-- 停车场图片表
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_picture`;
CREATE TABLE `pms_parking_picture` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `url` varchar(2000) NOT NULL COMMENT '照片URL',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场平面图：停车场出入口、外部道路情况、内部车位情况等照片信息';

-- ----------------------------
-- Table structure for pms_parking_garage_info
-- 停车场车库信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_garage_info`;
CREATE TABLE `pms_parking_garage_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `code` varchar(32) NOT NULL COMMENT '车库编号',
  `name` varchar(32) DEFAULT NULL COMMENT '车库名称',
  `gateCount` smallint(6) unsigned NOT NULL DEFAULT 0 COMMENT '出入口数量',
  `lotCount` smallint(6) unsigned NOT NULL DEFAULT 0 COMMENT '泊位数量',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '车库经度',
  `latitude`  decimal(9,6) DEFAULT NULL COMMENT '车库纬度',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`)
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场车库信息';

-- ----------------------------
-- Table structure for pms_parking_gate_info
-- 停车场出入口信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_gate_info`;
CREATE TABLE `pms_parking_gate_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `gargeId` bigint(20)   DEFAULT  NULL COMMENT '车库ID',
  `code` varchar(32) NOT NULL COMMENT '出入口编号',
  `name` varchar(32) NOT NULL COMMENT '出入口名称',
  `direction` tinyint(4) NOT NULL DEFAULT 3 COMMENT '出入口方向：，1-入口，2-出口 3 出入口',
  `laneCount` tinyint(4) DEFAULT 0 COMMENT '车道数量',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '出入口经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '出入口纬度',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场出入口信息';

-- ----------------------------
-- Table structure for pms_parking_lane_info
-- 停车场车道信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_lane_info`;
CREATE TABLE `pms_parking_lane_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `gargeId` bigint(20)   DEFAULT  NULL COMMENT '车库ID',
  `gateId` bigint(20) unsigned NOT NULL COMMENT '出入口ID',
  `code` varchar(32) NOT NULL COMMENT '车道序号',
  `name` varchar(32) NOT NULL COMMENT '车道名称',
  `direction` tinyint(4) NOT NULL DEFAULT 3 COMMENT '出入口方向：，1-入车道，2-出车道 3 出入车道',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`)
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场车道信息';

-- ----------------------------
-- Table structure for pms_parking_lot_info
-- 停车场泊位信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_lot_info`;
CREATE TABLE `pms_parking_lot_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `gargeId`  bigint(20)   DEFAULT  NULL COMMENT '车库ID',
  `hikParkingLotId` varchar(255) DEFAULT '' COMMENT '海康平台泊位ID',
  `hikBerthNumber` varchar(64) DEFAULT NULL COMMENT '海康平台泊位号(停车场唯一)',
  `aliParkingLotId` varchar(255) DEFAULT NULL COMMENT '支付宝平台泊位ID',
  `code`   varchar(64) DEFAULT '' COMMENT '编号(平台唯一)',
  `number` varchar(64) DEFAULT '' COMMENT '泊位编号：停车场内唯一',
  `name`   varchar(64) DEFAULT '' COMMENT '简称',
  `status` tinyint(4) DEFAULT 0 COMMENT '车位状态',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`),
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='停车场车位表';


-- ----------------------------
-- Table structure for pms_parking_lot_status
-- 停车场泊位当前状态信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_lot_status`;
CREATE TABLE `pms_parking_lot_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) unsigned NOT NULL COMMENT '泊位ID',
  `status` tinyint(4) NOT NULL COMMENT '泊位状态(0-空闲, 1-占用，2-未知)',
  `intoPassingId` bigint(20) unsigned DEFAULT NULL COMMENT '入车过车记录ID',
  `plateNumber` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `plateColor` tinyint(4) DEFAULT NULL COMMENT '车牌颜色',
  `occupyTime` datetime DEFAULT NULL COMMENT '占用时间：泊位状态从0，2 到1 的时设置，1到1 不更新，1到0 更新成null',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`,`parkingLotId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场泊位当前状态信息';


-- ----------------------------
-- Table structure for pms_parking_lot_config
-- 停车场泊位设备配置信息
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_lot_config`;
CREATE TABLE `pms_parking_lot_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) unsigned NOT NULL COMMENT '泊位ID',
  `cameraId` bigint(20) unsigned DEFAULT NULL COMMENT '枪球Id',
  `cameraNo` varchar(64) DEFAULT NULL COMMENT '枪设备编号',
  `cameraName` varchar(32) DEFAULT NULL COMMENT '枪设备编号',
  `managerId` bigint(20) unsigned DEFAULT NULL COMMENT  '地磁管理器Id',
  `detectorId` bigint(20) unsigned DEFAULT NULL COMMENT '地磁检测器Id',
  `dockingId` bigint(20) unsigned DEFAULT NULL COMMENT '对接网关ID',
  `lockId` bigint(20)   unsigned DEFAULT NULL COMMENT '车位锁ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`,`parkingLotId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场泊位设备配置信息';

-- ---------------------------- ---------------------------------------------- ----------------------------------
------------------------------              停车场计费相关                        ----------------------------------      
-- ---------------------------- ---------------------------------------------- ----------------------------------

-- ----------------------------
-- Table structure for chg_holiday_schedule
-- 节假日配置
-- ----------------------------
CREATE TABLE `chg_holiday_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `holidayType` tinyint(11) NOT NULL COMMENT '假期类型：1工作日，2双休日，3小长假，4长假',
  `holidayName` varchar(255) NOT NULL COMMENT '假期名称',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节假日表';


-- ----------------------------
-- Table structure for chg_holiday_calendar
-- 节假日日历
-- ----------------------------
CREATE TABLE `chg_holiday_calendar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `date` varchar(20) DEFAULT NULL COMMENT '日期yyyy-MM-dd',
  `holidayId` bigint(20) DEFAULT NULL,
  `workDay` tinyint(1) DEFAULT NULL COMMENT '是否工作日 0为非,1为工作日',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='日历表';

-- ----------------------------
-- Table structure for chg_charge_rule
-- 收费规则配置信息
-- ----------------------------
CREATE TABLE `chg_charge_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `code` varchar(64) NOT NULL COMMENT '收费规则编号',
  `name` varchar(255) NOT NULL COMMENT '收费规则名称',
  `ruleType` tinyint(4) NOT NULL COMMENT '规则类型 1 不收费,2 按时计费 3,按次计费 4,日夜分时分次计费',
  `carType`  tinyint(4) NOT NULL COMMENT '车辆类型',
  `plateNumberType` char(2) DEFAULT NULL COMMENT '车牌类型',
  `parkingLevel` tinyint(4) NOT NULL COMMENT '停车点等级 1,不区分 ,2 一级,3 二级, 4三级',
  `holidayType` tinyint(4) NOT NULL COMMENT '假期类型：  1 工作日，2 双休 3 小长假 4长假',
  `timesStartTime` varchar(20) DEFAULT '' COMMENT '计时开始时间',
  `timesEndTime`   varchar(20) DEFAULT '' COMMENT '计时结束时间',
  `perStartTime`   varchar(20) DEFAULT '' COMMENT '计次开始时间',
  `perEndTime`     varchar(20) DEFAULT '' COMMENT '计次结束时间',
  `perPrice` smallint DEFAULT 0 COMMENT '按次收费金额(单位:分)',
  `topStatus` tinyint(1) DEFAULT 0 COMMENT '是否启用封顶金额：0,不启用1,启用',
  `freeTime` smallint(6) DEFAULT 0 COMMENT '免费时长(单位:分钟)',
  `overFreeTime` tinyint(1) DEFAULT 0 COMMENT '超出免费时长时免费时长是否收费 0,不收费 1,收费',
  `existPartTime` tinyint(1) DEFAULT 0 COMMENT '24小时内存在按时段收费则全按时段收费 0, 否 1是,',
  `inTimeTop` tinyint(1) DEFAULT 0 COMMENT '24小时内是否启用封顶金额 0,不启用1,启用',
  `topAmount` int(11) DEFAULT 0 COMMENT '封顶金额(单位:分)',
  `dayTopAmount` int(11) DEFAULT 0 COMMENT '24小时封顶金额(单位:分)',
  `unitTime` smallint(6) DEFAULT NULL COMMENT '最小计时单位(单位:分钟)',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `up_code` (`code`)
  KEY `idx_tenant` (`tenantId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='收费规则信息表';

-- ----------------------------
-- Table structure for chg_charge_rule_time
-- 收费规则时段配置信息
-- ----------------------------
CREATE TABLE `chg_charge_rule_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `ruleId` bigint(20) NOT NULL COMMENT '收费规则ID',
  `timePart` int(11) DEFAULT 0 COMMENT '时间段(单位：分钟)',
  `price` int(11) DEFAULT 0 COMMENT '时间段的单价(单位：分)',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='收费规则时间分段表';

-- ----------------------------
-- Table structure for chg_appointment_rule
-- 预约规则配置信息
-- ----------------------------
CREATE TABLE `chg_appointment_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `ruleCode` varchar(255) NOT NULL COMMENT '预约规则编号',
  `ruleName` varchar(255) NOT NULL COMMENT '预约规则名称',
  `startTime` varchar(20) DEFAULT '' COMMENT '开放预约开始时间',
  `endTime` varchar(20) DEFAULT '' COMMENT '开放预约结束时间',
  `unitAppointLength` int(11) DEFAULT '0' COMMENT '预定单位时长(分钟)',
  `maxAppointLength`  int(11) DEFAULT '0' COMMENT '预定最大时长(分钟)',
  `unitPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单位时长(分钟)收取金额',
  `maxAppointAmount`  decimal(12,2) DEFAULT '0.00' COMMENT '预定最大收费金额',
  `countAppointPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单次收取金额',
  `fee` decimal(12,2) DEFAULT '0.00' COMMENT '预定收取手续费',
  `feeFreeLength` int(11) DEFAULT '0' COMMENT '免收手续费时长',
  `payLimit` int(11) DEFAULT '0' COMMENT '预定支付时限(分钟)',
  `cancelLimit` int(11) DEFAULT '0' COMMENT '预定取消时限(分钟),以下单时间开始',
  `unitCancelLength` int(11) DEFAULT '0' COMMENT '预定取消单位时长(分钟)',
  `unitCancelPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单位时长(分钟)收取金额',
  `maxCancelAmount` decimal(12,2) DEFAULT '0.00' COMMENT '预定取消最大收费金额',
  `countCancelPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单次取消收取金额',
  `overTimeLimit` int(11) DEFAULT '0' COMMENT '超时时限(分钟),以预计入场时间开始',
  `overTimeCancel` tinyint(1) DEFAULT 0 COMMENT '超时是否取消',
  `unitOvertimeCancelLength` int(11) DEFAULT '0' COMMENT '预定取消单位时长(分钟)',
  `unitOvertimeCancelPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单位时长(分钟)收取金额',
  `maxOvertimeCancelAmount` decimal(12,2) DEFAULT '0.00' COMMENT '预定取消最大收费金额',
  `countOvertimeCancelPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单次取消收取金额',
  `flexTime` int(11) DEFAULT '0' COMMENT '预约弹性入场时间',
  `refundLimit` int(11) DEFAULT '0' COMMENT '已支付可退款时长',
  `canRefund` tinyint(1) DEFAULT 1 COMMENT '是否退款',
  `chargeType` int(11) DEFAULT '1' COMMENT '收费类型',
  `used` tinyint(1) DEFAULT 0 COMMENT '是否关联',
  `description` varchar(1000) DEFAULT '',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `up_ruleCode` (`ruleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约规则信息表';

-- ----------------------------
-- Table structure for chg_parking_charge_rule
-- 停车场收费规则
-- ----------------------------
CREATE TABLE `chg_parking_charge_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `ruleId` bigint(20) NOT NULL COMMENT '收费规则ID',
  `onlineTime` date DEFAULT NULL COMMENT '上线时间',
  `offlineTime` date DEFAULT NULL COMMENT '下线时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='停车场收费规则';

-- ----------------------------
-- Table structure for chg_parking_charge_rule_history
-- 停车场历史收费规则
-- ----------------------------
CREATE TABLE `chg_parking_charge_rule_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `ruleId` bigint(20) NOT NULL COMMENT '收费规则ID',
  `onlineTime` date DEFAULT NULL COMMENT '上线时间',
  `offlineTime` date DEFAULT NULL COMMENT '下线时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='停车场历史收费规则';

-- ----------------------------
-- Table structure for chg_parking_appointment_rule
-- 停车场预约规则
-- ----------------------------
CREATE TABLE `chg_parking_appointment_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `ruleId` bigint(20) NOT NULL COMMENT '预定规则ID',
  `onlineTime` date DEFAULT NULL COMMENT '上线时间',
  `offlineTime` date DEFAULT NULL COMMENT '下线时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='停车场预定规则';


-- ----------------------------
-- Table structure for chg_parking_appointment_rule_history
-- 停车场历史预约规则
-- ----------------------------
CREATE TABLE `chg_parking_appointment_rule_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `ruleId` bigint(20) NOT NULL COMMENT '预定规则ID',
  `onlineTime` date DEFAULT NULL COMMENT '上线时间',
  `offlineTime` date DEFAULT NULL COMMENT '下线时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='停车场预定规则';


-- ---------------------------- ---------------------------------------------- ----------------------------------
------------------------------              设备管理相关                        ----------------------------------      
-- ---------------------------- ---------------------------------------------- ----------------------------------

-- ----------------------------
-- Table structure for pds_magnetic_manager
-- 地磁管理器表
-- ----------------------------
DROP TABLE IF EXISTS `pds_magnetic_manager`;
CREATE TABLE `pds_magnetic_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NULL COMMENT '停车场ID',
  `parkingAreaId` bigint(20) DEFAULT NULL COMMENT '停车区域ID',
  `code` varchar(64) NOT NULL COMMENT '地磁管理器编号(平台唯一)',
  `provider` smallint(6) DEFAULT NULL COMMENT '地磁管理器类型(厂商) 数据字典',
  `serialNumber` varchar(64) DEFAULT NULL COMMENT '设备序列号(厂家唯一)',
  `ipAddress` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL COMMENT '端口号',
  `simNo` varchar(32) DEFAULT NULL COMMENT 'SIM卡号',
  `registered` tinyint(1) DEFAULT '0' COMMENT '是否已注册 0-未注册，1-已注册',
  `lastHeartbeatTime` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `heartbeatInteval` smallint(6) DEFAULT '0' COMMENT '心跳间隔时间(秒)',
  `installPosition` varchar(256) DEFAULT '' COMMENT '安装位置',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '纬度',
  `versionNumber` varchar(64) DEFAULT NULL COMMENT '设备版本号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_manager_tenantId` (`tenantId`,`parkingId`),
  KEY `idx_manager_code` (`code`),
  KEY `idx_manager_provider` (`provider`,`serialNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁管理器表';


-- ----------------------------
-- Table structure for pds_magnetic_detector
-- 地磁检测器表
-- ----------------------------
DROP TABLE IF EXISTS `pds_magnetic_detector`;
CREATE TABLE `pds_magnetic_detector` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `parkingAreaId` bigint(20) DEFAULT NULL COMMENT '所属的区域ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `managerId` bigint(20) unsigned DEFAULT NULL COMMENT '关联地磁管理器id',
  `code` varchar(64) DEFAULT NULL COMMENT '地磁检测器编号(平台唯一)',
  `provider` smallint(6) DEFAULT NULL COMMENT '地磁管理器类型(厂商) 数据字典',
  `ipAddress` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL COMMENT '端口号',
  `simNo` varchar(32) DEFAULT NULL COMMENT 'SIM卡号',
  `registered` tinyint(1) DEFAULT '0' COMMENT '是否已注册 0-未注册，1-已注册',
  `serialNumber` varchar(50) DEFAULT NULL COMMENT '(厂商)设备序列号',
  `installPosition` varchar(256) DEFAULT '' COMMENT '安装位置',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '纬度',
  `versionNumber` varchar(64) DEFAULT NULL COMMENT '设备版本号',
  `lastHeartbeatTime` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `heartbeatInteval` smallint(6) DEFAULT '0' COMMENT '心跳间隔时间(秒)',
  `electricity` int(11) DEFAULT NULL COMMENT '电量',
  `status` smallint(6) DEFAULT NULL COMMENT '地磁检测器当前状态，-1：未知，0：正常，4：失联',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  `version` bigint(20) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_detector_tenantId` (`tenantId`,`parkingId`),
  KEY `idx_detector_code` (`code`),
  KEY `idx_detector_provider` (`provider`,`serialNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁检测器表';


-- ---------------------------- ---------------------------------------------- ----------------------------------
------------------------------              设备对接相关                        ----------------------------------      
-- ---------------------------- ---------------------------------------------- ----------------------------------

-- ----------------------------
-- Table structure for pds_magnetic_detector
-- 地磁检测器心跳数据
-- ----------------------------

CREATE TABLE `gat_magnetic_heart_beat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) DEFAULT NULL COMMENT '停车场Id',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位Id',
  `detectorId` bigint(20) NOT NULL COMMENT '检测器ID',
  `provider` smallint(6) DEFAULT NULL COMMENT '地磁管理器类型(厂商) 数据字典',
  `serialNumber` varchar(50) DEFAULT NULL COMMENT '(厂商)设备序列号',
  `commandCode` varchar(20) DEFAULT NULL COMMENT '指令编码',
  `beatTime` datetime DEFAULT NULL COMMENT '心跳时间',
  `errorCode` tinyint(4) DEFAULT NULL COMMENT '设备状态异常码',
  `battery` int(11) DEFAULT NULL COMMENT '电量  默认单位是%',
  `occupyStatus` tinyint(4) DEFAULT NULL COMMENT '占用状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
  KEY `idx_heart_tenantId` (`tenantId`,`parkingId`,`detectorId`),
  KEY `idx_heart_provider` (`provider`,`serialNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for gat_magnetic_status_record
-- 地磁检测器状态变更数据
-- ----------------------------

DROP TABLE IF EXISTS `gat_magnetic_status_record`;
CREATE TABLE `gat_magnetic_status_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) DEFAULT NULL COMMENT '停车场Id',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位Id',
  `detectorId` bigint(20) NOT NULL COMMENT '检测器ID',
  `provider` smallint(6) DEFAULT NULL COMMENT '地磁管理器类型(厂商) 数据字典',
  `serialNumber` varchar(50) DEFAULT NULL COMMENT '(厂商)设备序列号',
  `changeTime` datetime DEFAULT NULL COMMENT '变更时间，地磁检测到车位状态变更上传数据的时间',
  `changeType` tinyint(4) DEFAULT NULL COMMENT '泊位业务变更原因(1-车辆到达, 2-车辆离开)',
  `rssi` int(11) DEFAULT NULL COMMENT '信号强度',
  `passTime` int(11) DEFAULT NULL COMMENT '车位状态产生时长',
  `sequence` bigint(20) DEFAULT NULL COMMENT '车位状态序号',
  `battery` varchar(64) DEFAULT NULL COMMENT '电量',
  `occupyStatus` tinyint(4) DEFAULT NULL COMMENT '占用状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_magnetic_tenantId` (`tenantId`,`parkingId`,`detectorId`),
  KEY `idx_magnetic_provider` (`provider`,`serialNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁检测器状态变更数据';


-- ----------------------------
-- Table structure for gat_magnetic_report_record
-- 地磁检测记录
-- ----------------------------

DROP TABLE IF EXISTS `gat_magnetic_report_record`;
CREATE TABLE `gat_magnetic_report_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) DEFAULT NULL COMMENT '停车场Id',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位Id',
  `detectorId` bigint(20) NOT NULL COMMENT '检测器ID',
  `provider` smallint(6) DEFAULT NULL COMMENT '地磁管理器类型(厂商) 数据字典',
  `serialNumber` varchar(50) DEFAULT NULL COMMENT '(厂商)设备序列号',
  `changeTime` datetime DEFAULT NULL COMMENT '变更时间，地磁检测到车位状态变更上传数据的时间',
  `changeType` tinyint(4) DEFAULT NULL COMMENT '泊位业务变更原因(1-车辆到达, 2-车辆离开)',
  `occupyStatus` tinyint(4) DEFAULT NULL COMMENT '占用状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_magnetic_tenantId` (`tenantId`,`parkingId`,`detectorId`),
  KEY `idx_magnetic_provider` (`provider`,`serialNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁检测器状态变更数据';


-- ----------------------------
-- Table structure for spe_magnetic_error_report
-- 地磁误报处理记录表
-- ----------------------------
DROP TABLE IF EXISTS `spe_magnetic_error_report`;
CREATE TABLE `gat_magnetic_error_report` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) DEFAULT NULL COMMENT '停车场Id',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位Id',
  `detectorId` bigint(20) NOT NULL COMMENT '检测器ID',
  `provider` smallint(6) DEFAULT NULL COMMENT '地磁管理器类型(厂商) 数据字典',
  `serialNumber` varchar(50) DEFAULT NULL COMMENT '(厂商)设备序列号',
  `terminalId` varchar(64) DEFAULT NULL COMMENT '手持终端编号',
  `reportTime` datetime DEFAULT NULL COMMENT '地磁报告时间',
  `reportType` tinyint(4) DEFAULT NULL COMMENT '地磁报告类型(1-车辆到达, 2-车辆离开)',
  `inspectUserId` bigint(20) DEFAULT NULL COMMENT '收费员或巡检员ID',
  `inspectUserName` varchar(64) DEFAULT NULL COMMENT '收费员或巡检员姓名',
  `processtime` datetime DEFAULT NULL COMMENT '误报处理时间',
  `processReason` smallint(6) DEFAULT NULL COMMENT '误报原因，0-地磁误报进车（进车时），1-地磁误报出车（出车时），2-连续上报',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`record_id`),
  KEY `idx_magnetic_tenantId` (`tenantId`,`parkingId`,`detectorId`),
  KEY `idx_magnetic_provider` (`provider`,`serialNumber`),
  KEY `idx_magnetic_userId` (`tenantId`,`inspectUserId`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁误报处理记录表';


-- ---------------------------- ---------------------------------------------- ----------------------------------
------------------------------              运营数据相关                        ----------------------------------      
-- ---------------------------- ---------------------------------------------- ----------------------------------

-- ----------------------------------------------------
-- Table structure for pms_passing_vehicle_record
--  平台过车记录 
-- ----------------------------------------------------
CREATE TABLE `pms_passing_vehicle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `gateId` bigint(20) unsigned DEFAULT NULL COMMENT '出入口ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `passingNo` varchar(64) NOT NULL COMMENT '平台过车流水号',
  `passingUuid` varchar(64) DEFAULT NULL COMMENT '平台过车唯一编号',
  `hikPassingId` varchar(255) DEFAULT NULL COMMENT '海康平台过车ID',
  `aliPassingId` varchar(255) DEFAULT NULL COMMENT '阿里平台过车ID',
  `cardNumber`  varchar(32) DEFAULT NULL COMMENT '停车卡号',
  `codeNumber`  varchar(32) DEFAULT NULL COMMENT '停车码',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` tinyint(4) unsigned DEFAULT NULL COMMENT '车牌颜色',
  `carType` tinyint(4) unsigned DEFAULT NULL COMMENT '车辆类型',
  `plateNumberConfidence` int(11) DEFAULT NULL COMMENT '车牌置信度',
  `passingType` tinyint(4) unsigned DEFAULT 0  COMMENT '过车类型  0 未知 1.入车 2.出车 ',
  `confidence` tinyint(4) unsigned DEFAULT NULL COMMENT '过车置信度',
  `parkingType` tinyint(4) unsigned DEFAULT NULL COMMENT '停车类型',
  `abnormal` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否异常放行：0 正常；1异常放行',
  `passTime` datetime DEFAULT NULL COMMENT '过车时间',
  `entryTime` datetime DEFAULT NULL COMMENT '入车时间',
  `exitTime` datetime DEFAULT NULL COMMENT '出车时间',
  `dataSource` tinyint(4) DEFAULT '1' COMMENT '过车数据来源',
  `photoUploaded` tinyint(4) unsigned DEFAULT 0 COMMENT '过车图片是否上传',
  `photoCount` tinyint(4) unsigned DEFAULT '0' COMMENT '图片数量',
  `uploadedDate` datetime DEFAULT NULL COMMENT '图片上传时间',
  `proofStatus` tinyint(1) DEFAULT 0 COMMENT '校对状态 0:未校对  1:已校对',
  `proofUserId` bigint(20) COMMENT '校对用户',
  `proofDate` datetime DEFAULT NULL COMMENT '校对时间',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_tenantId` (`tenantId`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`),
  KEY `idx_passingUuid` (`passingUuid`),
  KEY `idx_passingNo` (`passingNo`),
  KEY `idx_dataSource` (`dataSource`),
  KEY `idx_photoUploaded` (`photoUploaded`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='过车记录表';

-- ----------------------------------------------------
-- Table structure for pms_passing_vehicle_exception_record
--  平台异常过车记录 
-- ----------------------------------------------------
CREATE TABLE `pms_passing_vehicle_exception_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `passingId` bigint(20) DEFAULT NULL COMMENT '过车记录Id',
  `hikPassingId` varchar(255) DEFAULT NULL COMMENT '海康平台过车ID',
  `aliPassingId` varchar(255) DEFAULT NULL COMMENT '阿里平台过车ID',
  `parkingId` bigint(20) DEFAULT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `cardNo`  varchar(32) DEFAULT NULL COMMENT '停车卡号',
  `codeNo`  varchar(32) DEFAULT NULL COMMENT '停车码',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` tinyint(4) DEFAULT NULL COMMENT '车牌颜色',
  `carType` tinyint(4) DEFAULT NULL COMMENT '车辆类型',
  `passingType` tinyint(4) DEFAULT NULL COMMENT '过车类型',
  `passTime` datetime DEFAULT NULL COMMENT '过车时间',
  `description` text COMMENT '异常说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常过车记录表';


-- ----------------------------------------------------
-- Table structure for pms_parking_vehicle_record
--  停车场在场车辆 
-- ----------------------------------------------------
CREATE TABLE `pms_parking_vehicle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `intoRecordId` bigint(20) DEFAULT NULL COMMENT '入车记录ID',
  `cardNumber`  varchar(32) DEFAULT NULL COMMENT '停车卡号',
  `codeNumber`  varchar(32) DEFAULT NULL COMMENT '停车码',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` tinyint(4) DEFAULT NULL COMMENT '车牌颜色',
  `carType` tinyint(4) DEFAULT NULL COMMENT '车辆类型',
  `startTime` datetime DEFAULT NULL COMMENT '停车开始时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`),
  KEY `idx_plateColor` (`plateColor`),
  KEY `idx_carType` (`carType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场在停车辆表';

-- ----------------------------------------------------
-- Table structure for pms_passing_vehicle_exception_record
--  平台停车记录 
-- ----------------------------------------------------
CREATE TABLE `pms_parking_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `recordNo` varchar(64) NOT NULL COMMENT '停车记录流水号',
  `hikParkingRecordId` varchar(255) DEFAULT NULL COMMENT '海康平台停车记录ID',
  `aliParkingRecordId` varchar(255) DEFAULT NULL COMMENT '海康平台停车记录ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `intoRecordId` bigint(20) DEFAULT NULL COMMENT '入车记录ID',
  `outRecordId` bigint(20) DEFAULT NULL COMMENT '出车记录ID',
  `cardNumber`  varchar(32) DEFAULT NULL COMMENT '停车卡号',
  `codeNumber`  varchar(32) DEFAULT NULL COMMENT '停车码',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` tinyint(4) unsigned DEFAULT NULL COMMENT '车牌颜色',
  `carType` tinyint(4) unsigned DEFAULT NULL COMMENT '车辆类型',
  `parkingType` tinyint(4) unsigned DEFAULT NULL COMMENT '停车类型',
  `startTime` datetime DEFAULT NULL COMMENT '停车开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '停车结束时间',
  `periodLength` int(11) unsigned DEFAULT 0 COMMENT '停车时长：分钟',
  `status` tinyint(4) DEFAULT 0 COMMENT '状态',
  `appointed` tinyint(1) DEFAULT 0 COMMENT '是否预约停车 0 否 1 是',
  `appointOrderNo` varchar(64) DEFAULT NULL COMMENT '预约订单号',
  `appointRuleId` bigint(20) unsigned DEFAULT NULL COMMENT '预约收费规则ID：入车时的预约规则',
  `chargeId` bigint(20) unsigned DEFAULT NULL COMMENT '收费规则ID：入车时的收费规则',
  `payableAmount` int(11) unsigned DEFAULT 0 COMMENT '总金额：单位分',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`),
  KEY `idx_appointOrderNo` (`appointOrderNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='平台停车记录表';


-- ----------------------------
-- Table structure for pms_parking_record_history
-- 停车记录历史表
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_record_history`;
CREATE TABLE `pms_parking_record_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `recordId` bigint(20) unsigned NOT NULL COMMENT '停车记录ID',
  `recordNo` varchar(64) NOT NULL COMMENT '停车记录流水号',
  `hikParkingRecordId` varchar(255) DEFAULT NULL COMMENT '海康平台停车记录ID',
  `aliParkingRecordId` varchar(255) DEFAULT NULL COMMENT '海康平台停车记录ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `intoRecordId` bigint(20) DEFAULT NULL COMMENT '入车记录ID',
  `outRecordId` bigint(20) DEFAULT NULL COMMENT '出车记录ID',
  `cardNumber`  varchar(32) DEFAULT NULL COMMENT '停车卡号',
  `codeNumber`  varchar(32) DEFAULT NULL COMMENT '停车码',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` tinyint(4) DEFAULT NULL COMMENT   '车牌颜色',
  `carType` tinyint(4) DEFAULT NULL COMMENT '车辆类型',
  `parkingType` tinyint(4) unsigned DEFAULT NULL COMMENT '停车类型',
  `startTime` datetime DEFAULT NULL COMMENT '停车开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '停车结束时间',
  `periodLength` int(11) DEFAULT 0 COMMENT '停车时长：分钟',
  `status` tinyint(4) DEFAULT 0 COMMENT '状态',
  `appointed` tinyint(1) DEFAULT 0 COMMENT '是否预约停车 0 否 1 是',
  `appointOrderNo` varchar(64) DEFAULT NULL COMMENT '预约订单号',
  `appointRuleId` bigint(20) unsigned DEFAULT NULL COMMENT '预约收费规则ID：入车时的预约规则',
  `chargeId` bigint(20) unsigned DEFAULT NULL COMMENT '收费规则ID：入车时的收费规则',
  `payableAmount` int(11) DEFAULT NULL COMMENT '总金额：单位分',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史停车记录表,巡检人员和系统管理员会处理停车数据，金额或车牌等改变会产生新的停车记录';

-- ----------------------------
-- Table structure for pms_parking_record_image
-- 停车过车图片表 
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_record_image`;
CREATE TABLE `pms_parking_record_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `bizId` bigint(20) unsigned NOT NULL COMMENT '停车记录ID',
  `bizType` tinyint(4) NOT NULL COMMENT '图片文件类型',
  `uuid` varchar(64) DEFAULT NULL COMMENT '图片唯一ID',
  `url` varchar(256) DEFAULT NULL COMMENT '图片URL',
  `type` tinyint(4) NOT NULL COMMENT '图片文件类型',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `pms_parking_image_tenantId` (`tenantId`,`uuid`),
  KEY `pms_parking_image_bizId` (`bizId`,`bizType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车照片';


-- ---------------------------- ---------------------------------------------- ----------------------------------
------------------------------     巡检及收费业务相关                            ----------------------------------      
-- ---------------------------- ---------------------------------------------- ----------------------------------

-- ----------------------------
-- Table structure for spe_park_collector
-- ----------------------------
DROP TABLE IF EXISTS `spe_park_collector`;
CREATE TABLE `spe_park_collector` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `userId` bigint(20) unsigned NOT NULL COMMENT '系统用户ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_collector_tenantId` (`tenantId`),
  KEY `idx_collector_parkingId` (`tenantId`,`parkingId`),
  KEY `idx_collector_userId` (`tenantId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场收费员信息';

-- ----------------------------
-- Table structure for spe_park_inspector
-- ----------------------------
DROP TABLE IF EXISTS `spe_park_inspector`;
CREATE TABLE `spe_park_inspector` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `userId` bigint(20) unsigned NOT NULL COMMENT '系统用户ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_inspector_tenantId` (`tenantId`),
  KEY `idx_inspector_parkingId` (`tenantId`,`parkingId`),
  KEY `idx_inspector_userId` (`tenantId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='巡检员,停车场中间关联表';

-- ----------------------------
-- Table structure for spe_park_sign_record
-- 巡检员收费员签到记录
-- ----------------------------
DROP TABLE IF EXISTS `spe_park_sign_record`;
CREATE TABLE `spe_park_sign_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `userId` bigint(20) unsigned NOT NULL COMMENT '巡检员收费员ID',
  `userName` varchar(64) NOT NULL COMMENT '巡检员收费员编号',
  `signType` tinyint(4) DEFAULT NULL COMMENT '操作类型：1签到；2签退',
  `signDate` datetime DEFAULT NULL COMMENT '操作时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_sign_record_tenantId` (`tenantId`),
  KEY `idx_sign_record_parkingId` (`tenantId`,`parkingId`),
  KEY `idx_sign_record_userId` (`tenantId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='巡检员签到签退记录表';

-- ----------------------------
-- Table structure for spe_inspect_record
-- 巡检记录表
-- ----------------------------
DROP TABLE IF EXISTS `spe_inspect_record`;
CREATE TABLE `spe_inspect_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `recordId` bigint(20) unsigned NOT NULL COMMENT '停车记录ID',
  `recordNo` varchar(64) NOT NULL COMMENT '停车记录流水号',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `cardNumber`  varchar(32) DEFAULT NULL COMMENT '停车卡号',
  `codeNumber`  varchar(32) DEFAULT NULL COMMENT '停车码',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` tinyint(4) unsigned DEFAULT NULL COMMENT '车牌颜色',
  `carType` tinyint(4) unsigned DEFAULT NULL COMMENT '车辆类型',
  `parkingType` tinyint(4) unsigned DEFAULT NULL COMMENT '停车类型',
  `startTime` datetime DEFAULT NULL COMMENT '停车开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '停车结束时间',
  `status` tinyint(4) DEFAULT 0 COMMENT '状态',
  `inspectUserId` bigint(20) unsigned NOT NULL COMMENT '巡检员收费员ID',
  `inspectTime` datetime DEFAULT NULL COMMENT '巡查时间',
  `inspectReason` smallint(6) DEFAULT NULL COMMENT '异常原因，1 -剩余车位数不正确，2 -车牌号码不匹配 3 车牌颜色不匹配 4 泊位不匹配 5 车型不匹配 6 入场时间不匹配',
  `inspectResult` smallint(6) DEFAULT NULL COMMENT '巡查结果，1 已处理 2 未处理',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_inspect_tenantId` (`tenantId`),
  KEY `idx_inspect_recordId` (`tenantId`,`recordId`),
  KEY `idx_inspect_parkingId` (`tenantId`,`parkingId`),
  KEY `idx_inspect_userId` (`tenantId`,`userId`)
  KEY `idx_inspect_plateNumber` (`plateNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='巡检记录表';


-- ---------------------------- ---------------------------------------------- ----------------------------------
------------------------------     包期及特殊车辆相关                            -------------------------------------      
-- ---------------------------- ---------------------------------------------- ----------------------------------

-- ----------------------------
-- Table structure for pms_vehicle_record
-- 平台车辆表
-- ----------------------------
DROP TABLE IF EXISTS `pms_vehicle_record`;
CREATE TABLE `pms_vehicle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenantId` bigint(20) unsigned DEFAULT NULL COMMENT '租户ID',
  `carBrand` varchar(255) DEFAULT NULL COMMENT '车辆品牌',
  `carStyle` varchar(255) DEFAULT NULL COMMENT '车辆类型',
  `plateNumber` varchar(10) NOT NULL COMMENT '车牌编号',
  `plateColor` tinyint(4) unsigned NOT NULL COMMENT '车牌颜色',
  `plateType` char(2) DEFAULT NULL COMMENT '车牌类型',
  `carType` tinyint(4) unsigned DEFAULT NULL COMMENT '车辆类型',
  `carColor` tinyint(4) unsigned DEFAULT NULL COMMENT '车辆颜色',
  `vehicleNumber` varchar(32) DEFAULT NULL COMMENT '车架号',
  `engineNumber` varchar(32) DEFAULT NULL COMMENT '发动机号',
  `ownerName`    varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `ownerPhone`   varchar(32) DEFAULT NULL COMMENT '车主手机',
  `ownerAddress`   varchar(32) DEFAULT NULL COMMENT '车主手机',
  `ownerCardNo`   varchar(32) DEFAULT NULL COMMENT '车主身份证号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_vehicle_tenantId` (`tenantId`),
  KEY `idx_vehicle_plateNumber` (`plateNumber`,`plateColor`),
  KEY `idx_vehicle_deleted` (`deleted`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='平台车辆表';


-- ----------------------------
-- Table structure for pms_special_vehicle
-- 特殊车辆表
-- ----------------------------
DROP TABLE IF EXISTS `pms_special_vehicle`;
CREATE TABLE `pms_special_vehicle` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `parkingLotNumber` varchar(20) DEFAULT NULL COMMENT '泊位编号',
  `parkingLotNumber` bigint(20) DEFAULT NULL COMMENT '泊位编号',
  `specialType` tinyint(4) DEFAULT '1' COMMENT '特殊车辆类型(1 : 白名单 2 黑名单 3 固定车 4 访客车)',
  `fixedType` tinyint(4) DEFAULT NULL COMMENT '固定车类型 1 业主车, 2 内部车 ',
  `plateNumber` varchar(10) NOT NULL COMMENT '车牌编号',
  `plateColor`  tinyint(4) unsigned NOT NULL COMMENT '车牌颜色',
  `plateType`   char(2) DEFAULT NULL COMMENT '车牌类型',
  `carType`   tinyint(4) unsigned DEFAULT NULL COMMENT '车辆类型',
  `carColor`  tinyint(4) unsigned DEFAULT NULL COMMENT '车辆颜色',
  `ownerName` varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `ownerPhone` varchar(32) DEFAULT NULL COMMENT '车主手机',
  `ownerAddress`   varchar(32) DEFAULT NULL COMMENT '车主手机',
  `beginTime` date DEFAULT NULL COMMENT '开始日期',
  `endTime`   date DEFAULT NULL COMMENT '结束日期',
  `status` tinyint(4) DEFAULT '1' COMMENT '生效状态(1 : 未生效 2 已生效 3 已失效)',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_special_plate` (`specialType`,`plateNumber`,`plateColor`),
  KEY `idx_special_tenantId` (`tenantId`,`parkingId`),
  KEY `idx_special_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特殊车辆表';


-- ----------------------------
-- Table structure for pms_packet_rule
-- 包期规则表
-- ----------------------------
DROP TABLE IF EXISTS `pms_packet_rule`;
CREATE TABLE `pms_packet_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId`   bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `packetName` varchar(32) NOT NULL COMMENT '规则名称',
  `packetType` tinyint(4) unsigned NOT NULL COMMENT '包期类型，1：包月，2：包年',
  `naturalPeriod` tinyint(1) unsigned NOT NULL COMMENT '是否自然年月',
  `plateColor`  tinyint(4) unsigned NOT NULL COMMENT '车牌颜色',
  `plateType`   char(2) DEFAULT NULL COMMENT '车牌类型',
  `carType`   tinyint(4) unsigned DEFAULT NULL COMMENT '车辆类型',
  `price` int(11) NOT NULL COMMENT '包期价格，单位：分',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_packet_tenantId` (`tenantId`),
  KEY `idx_packet_rule` (`tenantId`,`packetName`,`packetType`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包期规则表';


-- ----------------------------
-- Table structure for pms_parking_packet_rule
-- 停车场包期规则表
-- ----------------------------
DROP TABLE IF EXISTS `pms_parking_packet_rule`;
CREATE TABLE `pms_parking_packet_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `ruleId` bigint(20) unsigned NOT NULL COMMENT '包期规则ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_parking` (`tenantId`,`parkingId`),
  KEY `idx_tenant_ruleId` (`tenantId`,`ruleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场包期规则表';


-- ----------------------------
-- Table structure for pms_packet_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `pms_packet_vehicle`;
CREATE TABLE `pms_packet_vehicle` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `ruleId` bigint(20) NOT NULL COMMENT '包期规则ID',
  `plateNumber` varchar(10) NOT NULL COMMENT '车牌编号',
  `plateColor`  tinyint(4) unsigned NOT NULL COMMENT '车牌颜色',
  `plateType`   char(2) DEFAULT NULL COMMENT '车牌类型',
  `carType`   tinyint(4) unsigned DEFAULT NULL COMMENT '车辆类型',
  `carColor`  tinyint(4) unsigned DEFAULT NULL COMMENT '车辆颜色',
  `beginDate` date DEFAULT NULL COMMENT '开始日期',
  `endDate` date DEFAULT NULL COMMENT '结束日期',
  `effecteStatus` tinyint(4) unsigned NOT NULL COMMENT '是否有效：1 未生效；2 已生效 ；3 已失效',
  `topUpStatus` tinyint(1) unsigned NOT NULL COMMENT '是否充值：0 未充值；1 已充值 ',
  `customerUserId` bigint(20) DEFAULT NULL COMMENT '车主用户ID',
  `ownerName` varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `ownerPhone` varchar(32) DEFAULT NULL COMMENT '车主电话',
  `ownerEmail` varchar(64) DEFAULT NULL COMMENT '车主邮件',
  `ownerAddress` varchar(64) DEFAULT NULL COMMENT '车主地址',
  `ownerCardNo`   varchar(32) DEFAULT NULL COMMENT '车主身份证号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_tenantId` (`tenantId`),
  KEY `idx_tenantId_parkingId` (`tenantId`,`tenantId`),
  KEY `idx_plateNumber` (`plateNumber`,`plateColor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包期车辆表';


-- ----------------------------
-- Table structure for pms_packet_approve
-- ----------------------------
DROP TABLE IF EXISTS `pms_packet_approve`;
CREATE TABLE `pms_packet_approve` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenantId` bigint(20) unsigned NOT NULL COMMENT '租户ID',
  `packetId` bigint(20) unsigned NOT NULL COMMENT '包期车ID',
  `approveTime` datetime DEFAULT NULL COMMENT '申请时间',
  `approveStatus` tinyint(4) DEFAULT NULL COMMENT '审核状态：0 待审核, 1 审核驳回, 2 审核通过',
  `rejectReason` tinyint(4) DEFAULT NULL COMMENT '驳回原因：0 其他 1 未到期，暂不能取消包期',
  `reason` varchar(255) DEFAULT NULL COMMENT '其他原因',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL,
  `lastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包期车辆审核表';







