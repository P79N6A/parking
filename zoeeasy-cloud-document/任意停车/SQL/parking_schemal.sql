/*
Navicat MySQL Data Transfer

Source Server         : mycat@server242
Source Server Version : 50629
Source Host           : 192.168.0.242:8066
Source Database       : parking

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-06-22 14:00:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `DATABASECHANGELOG`;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(255) NOT NULL COMMENT '客户端ID',
  `tokenId` varchar(255) NOT NULL COMMENT '令牌ID',
  `authenticationId` varchar(255) NOT NULL COMMENT 'authenticationId',
  `username` varchar(32) DEFAULT NULL COMMENT 'username',
  `tokenType` varchar(255) DEFAULT 'Bearer' COMMENT 'tokenType',
  `tokenExpiredSeconds` int(11) DEFAULT '86400' COMMENT 'tokenExpiredSeconds',
  `refreshToken` varchar(255) DEFAULT NULL COMMENT 'refreshToken',
  `refreshTokenExpiredSeconds` int(11) DEFAULT '43200' COMMENT 'refreshTokenExpiredSeconds',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_tokenId` (`tokenId`),
  KEY `idx_tokenId` (`tokenId`)
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_detail
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_detail`;
CREATE TABLE `oauth_client_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(255) NOT NULL COMMENT '客户端ID',
  `clientSecret` varchar(255) NOT NULL COMMENT '客户端秘钥',
  `clientName` varchar(255) NOT NULL COMMENT '客户端名称',
  `clientUri` varchar(255) DEFAULT NULL COMMENT 'clientUri',
  `clientIconUri` varchar(255) DEFAULT NULL COMMENT '图标',
  `redirectUri` varchar(255) DEFAULT NULL COMMENT 'redirectUri',
  `resourceIds` varchar(2000) DEFAULT NULL COMMENT '资源ID',
  `scope` varchar(255) DEFAULT NULL COMMENT 'scope',
  `roles` varchar(255) DEFAULT NULL COMMENT 'roles',
  `grantTypes` varchar(255) DEFAULT NULL COMMENT '授权类型',
  `accessTokenValidity` int(11) DEFAULT '-1' COMMENT 'accessTokenValidity',
  `refreshTokenValidity` int(11) DEFAULT '-1' COMMENT 'refreshTokenValidity',
  `trusted` char(1) DEFAULT '0' COMMENT '是否受信任客户端\\n0：显示\\n1：隐藏',
  `description` varchar(4096) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_clientId` (`clientId`),
  KEY `idx_clientId` (`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=941192326458318860 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(255) NOT NULL COMMENT '客户端ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `authCode` varchar(255) NOT NULL COMMENT '授权码',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_authCode` (`authCode`),
  KEY `idx_authCode` (`authCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_alipay_access_token
-- ----------------------------
DROP TABLE IF EXISTS `up_alipay_access_token`;
CREATE TABLE `up_alipay_access_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alipayUserId` varchar(255) DEFAULT NULL COMMENT '支付宝用户的唯一userId',
  `accessToken` varchar(255) NOT NULL COMMENT '访问令牌',
  `tokenExpiredSeconds` int(11) DEFAULT '0' COMMENT '令牌有效期',
  `refreshToken` varchar(255) DEFAULT NULL COMMENT '刷新令牌',
  `refreshTokenExpiredSeconds` int(11) DEFAULT '0' COMMENT '刷新令牌有效期',
  `scope` varchar(32) DEFAULT NULL COMMENT 'scope',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_accessToken` (`accessToken`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8 COMMENT='支付宝访问令牌';

-- ----------------------------
-- Table structure for up_alipay_bill
-- ----------------------------
DROP TABLE IF EXISTS `up_alipay_bill`;
CREATE TABLE `up_alipay_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payType` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `orderNo` varchar(255) DEFAULT NULL COMMENT '订单号',
  `outTradeNo` varchar(255) DEFAULT NULL COMMENT '外部订单号',
  `subject` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '订单金额',
  `receiptAmount` decimal(12,2) DEFAULT NULL COMMENT '实收金额',
  `storeId` varchar(255) DEFAULT NULL COMMENT '商户的门店编号',
  `storeName` varchar(255) DEFAULT NULL COMMENT '商户的门店名称',
  `operatorId` varchar(255) DEFAULT NULL COMMENT '商户的操作员编号',
  `terminalId` varchar(255) DEFAULT NULL COMMENT '商户的终端编号',
  `zfbPacket` decimal(12,2) DEFAULT NULL COMMENT '支付宝红包',
  `setPointTreasure` decimal(12,2) DEFAULT NULL COMMENT '集分宝',
  `zfbDiscount` decimal(12,2) DEFAULT NULL COMMENT '支付宝优惠',
  `storeDiscount` decimal(12,2) DEFAULT NULL COMMENT '商家优惠',
  `ticketDiscount` decimal(12,2) DEFAULT NULL COMMENT '券优惠',
  `ticketName` varchar(255) DEFAULT NULL COMMENT '券名称',
  `storePacketAmount` decimal(12,2) DEFAULT NULL COMMENT '商家红包消费金额',
  `cardPacketAmount` decimal(12,2) DEFAULT NULL COMMENT '卡消费金额',
  `serviceCharge` decimal(12,2) DEFAULT NULL COMMENT '服务费',
  `feeSplitting` decimal(12,2) DEFAULT NULL COMMENT '分润',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `outRequestNo` varchar(255) DEFAULT NULL COMMENT '退款批次号/请求号',
  `startTime` datetime DEFAULT NULL COMMENT '订单开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '订单结束时间',
  `buyerLogonId` varchar(255) DEFAULT NULL COMMENT '买家支付宝账号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='支付宝对账单表';

-- ----------------------------
-- Table structure for up_alipay_message_log
-- ----------------------------
DROP TABLE IF EXISTS `up_alipay_message_log`;
CREATE TABLE `up_alipay_message_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `notifyTime` datetime DEFAULT NULL COMMENT '通知时间',
  `ip` varchar(2000) DEFAULT NULL COMMENT 'ip',
  `url` varchar(2000) DEFAULT NULL COMMENT 'url',
  `notifyType` varchar(255) DEFAULT NULL COMMENT '通知的类型',
  `notifyId` varchar(255) DEFAULT NULL COMMENT '通知校验ID',
  `appId` varchar(255) DEFAULT NULL COMMENT '支付宝分配给开发者的应用Id',
  `tradeNo` varchar(255) DEFAULT NULL COMMENT '支付宝交易号',
  `outTradeNo` varchar(255) DEFAULT NULL COMMENT '商户订单号',
  `outBizNo` varchar(255) DEFAULT NULL COMMENT '商户业务号',
  `content` text COMMENT '消息内容',
  `status` int(11) DEFAULT NULL COMMENT '消息处理状态',
  `result` varchar(2000) DEFAULT NULL COMMENT '消息处理结果说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='支付宝异步通知表';

-- ----------------------------
-- Table structure for up_alipay_order
-- ----------------------------
DROP TABLE IF EXISTS `up_alipay_order`;
CREATE TABLE `up_alipay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `tradeNo` varchar(255) DEFAULT NULL COMMENT '支付宝交易号',
  `outTradeNo` varchar(255) NOT NULL COMMENT '商户订单号',
  `outBizNo` varchar(255) DEFAULT NULL COMMENT '商户返回退款申请的流水号',
  `subject` varchar(255) NOT NULL COMMENT '订单标题',
  `tradeStatus` int(11) DEFAULT NULL COMMENT '交易状态',
  `totalAmount` decimal(12,2) NOT NULL COMMENT '订单金额',
  `receiptAmount` decimal(12,2) DEFAULT NULL COMMENT '实收金额',
  `invoiceAmount` decimal(12,2) DEFAULT NULL COMMENT '开票金额',
  `buyerPayAmount` decimal(12,2) DEFAULT NULL COMMENT '用户实际支付金额',
  `pointAmount` decimal(12,2) DEFAULT NULL COMMENT '集分宝金额',
  `refundFee` decimal(12,2) DEFAULT NULL COMMENT '总退款金额',
  `currencyType` varchar(255) DEFAULT NULL COMMENT '货币类型',
  `body` varchar(2000) DEFAULT NULL COMMENT '商品描述',
  `gmtCreate` datetime DEFAULT NULL COMMENT '交易创建时间',
  `gmtPayment` datetime DEFAULT NULL COMMENT '交易付款时间',
  `gmtRefund` datetime DEFAULT NULL COMMENT '交易退款时间',
  `gmtClose` datetime DEFAULT NULL COMMENT '交易结束时间',
  `fundBillList` varchar(2000) DEFAULT NULL COMMENT '支付金额信息',
  `passbackParams` varchar(2000) DEFAULT NULL COMMENT '回传参数',
  `voucherDetailList` varchar(2000) DEFAULT NULL COMMENT '优惠券信息',
  `timeoutExpress` varchar(255) DEFAULT NULL COMMENT '交易过期时间',
  `goodsType` varchar(16) DEFAULT NULL COMMENT '商品主类型：0—虚拟类商品，1—实物类商品',
  `promoParams` varchar(1000) DEFAULT NULL COMMENT '优惠参数',
  `extendParams` text COMMENT '业务扩展参数',
  `enablePayChannels` varchar(1000) DEFAULT NULL COMMENT '可用渠道',
  `disablePayChannels` varchar(1000) DEFAULT NULL COMMENT '禁用渠道',
  `storeId` varchar(1000) DEFAULT NULL COMMENT '商户门店编号',
  `extUserInfo` text COMMENT '外部指定买家',
  `buyerId` varchar(255) DEFAULT NULL COMMENT '买家支付宝用户号',
  `buyerLogonId` varchar(255) DEFAULT NULL COMMENT '买家支付宝账号',
  `sellerId` varchar(255) DEFAULT NULL COMMENT '卖家支付宝用户号',
  `sellerEmail` varchar(255) DEFAULT NULL COMMENT '卖家支付宝账号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=397 DEFAULT CHARSET=utf8 COMMENT='支付宝订单表';

-- ----------------------------
-- Table structure for up_app_version
-- ----------------------------
DROP TABLE IF EXISTS `up_app_version`;
CREATE TABLE `up_app_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `terminateType` char(1) NOT NULL COMMENT '客户端类型',
  `version` varchar(10) NOT NULL COMMENT '版本号',
  `versionName` varchar(64) DEFAULT '' COMMENT '版本名称',
  `updateDescription` text COMMENT '更新说明',
  `downloadUrl` varchar(1280) DEFAULT '' COMMENT '下载链接',
  `codeUrl` varchar(1280) DEFAULT '' COMMENT '二维码链接',
  `fileUrl` varchar(1280) DEFAULT '' COMMENT '服务器链接',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `publishTime` datetime DEFAULT NULL COMMENT '发布时间',
  `withdrawTime` datetime DEFAULT NULL COMMENT '下架时间',
  `forceUpdate` char(1) DEFAULT '0' COMMENT '是否强制更新',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='app版本表';

-- ----------------------------
-- Table structure for up_appointment_rule
-- ----------------------------
DROP TABLE IF EXISTS `up_appointment_rule`;
CREATE TABLE `up_appointment_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ruleCode` varchar(255) NOT NULL COMMENT '预约规则编号',
  `ruleName` varchar(255) NOT NULL COMMENT '预约规则名称',
  `startTime` varchar(255) DEFAULT '' COMMENT '开放预约开始时间',
  `endTime` varchar(255) DEFAULT '' COMMENT '开放预约结束时间',
  `unitAppointLength` int(11) DEFAULT '0' COMMENT '预定单位时长(分钟)',
  `maxAppointLength` int(11) DEFAULT '0' COMMENT '预定最大时长(分钟)',
  `unitPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单位时长(分钟)收取金额',
  `maxAppointAmount` decimal(12,2) DEFAULT '0.00' COMMENT '预定最大收费金额',
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
  `overTimeCancel` char(1) DEFAULT '0' COMMENT '超时是否取消',
  `unitOvertimeCancelLength` int(11) DEFAULT '0' COMMENT '预定取消单位时长(分钟)',
  `unitOvertimeCancelPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单位时长(分钟)收取金额',
  `maxOvertimeCancelAmount` decimal(12,2) DEFAULT '0.00' COMMENT '预定取消最大收费金额',
  `countOvertimeCancelPrice` decimal(12,2) DEFAULT '0.00' COMMENT '单次取消收取金额',
  `description` varchar(255) DEFAULT '0' COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `up_ruleCode` (`ruleCode`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='预约规则信息表';

-- ----------------------------
-- Table structure for up_car_brand
-- ----------------------------
DROP TABLE IF EXISTS `up_car_brand`;
CREATE TABLE `up_car_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `fullName` varchar(255) DEFAULT NULL COMMENT '全称',
  `initial` varchar(10) DEFAULT NULL COMMENT '首字母',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parentIds` varchar(2000) DEFAULT NULL COMMENT '父节点路径',
  `logo` varchar(2000) DEFAULT NULL COMMENT '图标',
  `depth` int(11) DEFAULT NULL COMMENT '深度 1品牌 2子公司 3车型 4具体车型',
  `price` varchar(255) DEFAULT NULL COMMENT '价格字符',
  `yearType` varchar(255) DEFAULT NULL COMMENT '年款',
  `sizeType` varchar(255) DEFAULT NULL COMMENT '车辆等级',
  `saleState` varchar(200) DEFAULT NULL COMMENT '销售状态',
  `productionState` varchar(255) DEFAULT NULL COMMENT '生产状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_parent` (`parentId`),
  KEY `idx_depth` (`depth`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=42546 DEFAULT CHARSET=utf8 COMMENT='车型大全表';

-- ----------------------------
-- Table structure for up_charge_rule
-- ----------------------------
DROP TABLE IF EXISTS `up_charge_rule`;
CREATE TABLE `up_charge_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ruleCode` varchar(255) NOT NULL COMMENT '收费规则编号',
  `ruleName` varchar(255) NOT NULL COMMENT '收费规则名称',
  `ruleType` int(11) NOT NULL COMMENT '规则类型 1 不收费,2 按时计费 3,按次计费 4,日夜分时分次计费',
  `carType` int(11) NOT NULL COMMENT '车辆类型',
  `plateNumberType` char(2) DEFAULT NULL COMMENT '车牌类型',
  `parkingLevel` int(11) NOT NULL COMMENT '停车点等级 1,不区分 ,2 一级,3 二级, 4三级',
  `holidayType` int(11) NOT NULL COMMENT '假期类型：  1 工作日，2 双休 3 小长假 4长假',
  `timesStartTime` varchar(255) DEFAULT '' COMMENT '计时开始时间',
  `timesEndTime` varchar(255) DEFAULT '' COMMENT '计时结束时间',
  `perStartTime` varchar(255) DEFAULT '' COMMENT '计次开始时间',
  `perEndTime` varchar(255) DEFAULT '' COMMENT '计次结束时间',
  `perPrice` decimal(12,2) DEFAULT '0.00' COMMENT '按次收费金额',
  `topStatus` char(1) DEFAULT '0' COMMENT '是否启用封顶金额：0,不启用1,启用',
  `freeTime` int(11) DEFAULT '0' COMMENT '免费时长',
  `overFreeTime` char(1) DEFAULT '0' COMMENT '超出免费时长时免费时长是否收费 0,不收费 1,收费',
  `existPartTime` char(1) DEFAULT '0' COMMENT '24小时内存在按时段收费则全按时段收费 0, 否 1是,',
  `inTimeTop` char(1) DEFAULT '0' COMMENT '24小时内是否启用封顶金额 0,不启用1,启用',
  `topAmount` decimal(12,2) DEFAULT '0.00' COMMENT '封顶金额',
  `dayTopAmount` decimal(12,2) DEFAULT '0.00' COMMENT '24小时封顶金额',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `up_charge_rule_ruleCode` (`ruleCode`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='收费规则信息表';

-- ----------------------------
-- Table structure for up_charge_rule_time
-- ----------------------------
DROP TABLE IF EXISTS `up_charge_rule_time`;
CREATE TABLE `up_charge_rule_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ruleId` bigint(20) NOT NULL COMMENT '收费规则ID',
  `timePart` int(11) DEFAULT '0' COMMENT '时间段',
  `price` decimal(12,2) DEFAULT '0.00' COMMENT '时间段的单价',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='收费规则时间分段表';

-- ----------------------------
-- Table structure for up_ges
-- ----------------------------
DROP TABLE IF EXISTS `up_ges`;
CREATE TABLE `up_ges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `longitude` varchar(10) NOT NULL COMMENT '经度',
  `latitude` varchar(10) NOT NULL COMMENT '维度',
  `gesType` char(1) NOT NULL COMMENT '类型',
  `address` varchar(128) DEFAULT '' COMMENT '地址',
  `name` varchar(128) DEFAULT '' COMMENT '名称',
  `telephone` varchar(30) DEFAULT '' COMMENT '电话',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电桩信息表';

-- ----------------------------
-- Table structure for up_hikvision_vehicle_log
-- ----------------------------
DROP TABLE IF EXISTS `up_hikvision_vehicle_log`;
CREATE TABLE `up_hikvision_vehicle_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(2000) DEFAULT NULL COMMENT 'ip',
  `url` varchar(2000) DEFAULT NULL COMMENT 'url',
  `messageType` varchar(100) DEFAULT NULL COMMENT '消息类型',
  `requestTime` datetime DEFAULT NULL COMMENT '请求时间',
  `parameter` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `responseTime` datetime DEFAULT NULL COMMENT '响应时间',
  `result` text COMMENT '响应内容',
  `status` int(11) DEFAULT NULL COMMENT '请求状态',
  `startTime` datetime DEFAULT NULL COMMENT '查询开始的过车时间',
  `endTime` datetime DEFAULT NULL COMMENT '查询结束的过车时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='海康过车记录接口调用记录';

-- ----------------------------
-- Table structure for up_hikvision_vehicle_record
-- ----------------------------
DROP TABLE IF EXISTS `up_hikvision_vehicle_record`;
CREATE TABLE `up_hikvision_vehicle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) DEFAULT NULL COMMENT '过车记录唯一ID',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `carType` int(11) DEFAULT NULL COMMENT '车辆类型',
  `passDirect` int(11) DEFAULT NULL COMMENT '过车方向',
  `passTime` datetime DEFAULT NULL COMMENT '通过时间',
  `parkCode` varchar(256) DEFAULT NULL COMMENT '停车场编号',
  `parkName` varchar(256) DEFAULT NULL COMMENT '停车场名称',
  `gateCode` varchar(256) DEFAULT NULL COMMENT '停车场出入口编号',
  `gateName` varchar(256) DEFAULT NULL COMMENT '停车场出入口名称',
  `laneCode` varchar(256) DEFAULT NULL COMMENT '车道编号',
  `laneName` varchar(256) DEFAULT NULL COMMENT '车道名称',
  `berthCode` varchar(256) DEFAULT NULL COMMENT '泊位编号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50853 DEFAULT CHARSET=utf8 COMMENT='海康平台过车记录';

-- ----------------------------
-- Table structure for up_holiday_schedule
-- ----------------------------
DROP TABLE IF EXISTS `up_holiday_schedule`;
CREATE TABLE `up_holiday_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `holidayType` int(11) NOT NULL COMMENT '假期类型：1工作日，2双休日，3小长假，4长假',
  `holidayName` varchar(255) NOT NULL COMMENT '假期名称',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='节假日表';

-- ----------------------------
-- Table structure for up_icon
-- ----------------------------
DROP TABLE IF EXISTS `up_icon`;
CREATE TABLE `up_icon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sourceType` varchar(255) NOT NULL COMMENT '资源类型',
  `displayName` varchar(255) NOT NULL COMMENT '显示名称',
  `className` varchar(255) NOT NULL COMMENT '类名',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_displayName` (`displayName`)
) ENGINE=InnoDB AUTO_INCREMENT=1127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_job_execute_log
-- ----------------------------
DROP TABLE IF EXISTS `up_job_execute_log`;
CREATE TABLE `up_job_execute_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobId` varchar(32) NOT NULL COMMENT '任务ID',
  `startTime` datetime DEFAULT NULL COMMENT '开始执行时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束执行时间',
  `retryTimes` int(11) DEFAULT NULL COMMENT '已经重试的次数',
  `repeatedCount` int(11) DEFAULT NULL COMMENT '已经重复的次数',
  `sequenceId` varchar(128) DEFAULT NULL COMMENT '执行的时序',
  `triggerTime` datetime DEFAULT NULL COMMENT '触发时间',
  `retry` char(1) DEFAULT NULL COMMENT '是否是重试',
  `success` char(1) DEFAULT NULL COMMENT '是否执行成功',
  `result` text COMMENT '执行结果',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125148 DEFAULT CHARSET=utf8 COMMENT='任务调度表';

-- ----------------------------
-- Table structure for up_license_organization
-- ----------------------------
DROP TABLE IF EXISTS `up_license_organization`;
CREATE TABLE `up_license_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) DEFAULT '0' COMMENT '父节点',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `type` int(11) DEFAULT '0' COMMENT '类型  1 前缀 2 首字母',
  `city` varchar(100) DEFAULT NULL COMMENT '管局名称',
  `engineNoLength` int(11) DEFAULT '0' COMMENT '发动机号：-1是指输入全部字段，0是指不需要输入，6是指输入后六位，以此类推',
  `vinLength` int(11) DEFAULT '0' COMMENT '车架号：-1是指输入全部字段，0是指不需要输入，6是指输入后六位，以此类推',
  `cityPrefix` varchar(8) DEFAULT NULL COMMENT '车牌前缀',
  `carTypes` varchar(8) DEFAULT NULL COMMENT '车辆类型',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_city` (`city`)
) ENGINE=InnoDB AUTO_INCREMENT=443 DEFAULT CHARSET=utf8 COMMENT='车管局信息表';

-- ----------------------------
-- Table structure for up_license_prefix
-- ----------------------------
DROP TABLE IF EXISTS `up_license_prefix`;
CREATE TABLE `up_license_prefix` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `type` int(11) DEFAULT NULL COMMENT '类型  1 前缀 2 首字母',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_parent` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=426 DEFAULT CHARSET=utf8 COMMENT='车牌前缀首字母表';

-- ----------------------------
-- Table structure for up_menu
-- ----------------------------
DROP TABLE IF EXISTS `up_menu`;
CREATE TABLE `up_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) NOT NULL COMMENT '菜单代码',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `type` varchar(100) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(255) NOT NULL COMMENT '地址',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parentIds` varchar(2000) DEFAULT NULL COMMENT '父节点路径',
  `shown` char(1) DEFAULT NULL COMMENT '是否显示\\nY：显示\\nN：隐藏',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parent` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_message
-- ----------------------------
DROP TABLE IF EXISTS `up_message`;
CREATE TABLE `up_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `messageId` varchar(255) NOT NULL COMMENT '消息ID',
  `traceId` varchar(255) DEFAULT NULL COMMENT 'traceId',
  `sender` varchar(255) DEFAULT NULL COMMENT '消息发送方',
  `exchange` varchar(255) DEFAULT NULL COMMENT '消息交换器',
  `routingKey` varchar(255) DEFAULT NULL COMMENT '路由键',
  `consumerQueue` varchar(255) DEFAULT NULL COMMENT '消息队列',
  `payload` text COMMENT '消息内容',
  `messageType` varchar(255) DEFAULT NULL COMMENT '消息类型',
  `sendTimes` int(11) DEFAULT NULL COMMENT '发送次数',
  `sendStatus` int(11) DEFAULT NULL COMMENT '发送状态',
  `alreadyDead` int(11) DEFAULT NULL COMMENT '是否死信',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_messageId` (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=186359 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_message_log
-- ----------------------------
DROP TABLE IF EXISTS `up_message_log`;
CREATE TABLE `up_message_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `url` varchar(2000) DEFAULT NULL COMMENT 'url',
  `messageType` varchar(100) DEFAULT NULL COMMENT '消息类型',
  `parameter` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `requestTime` datetime DEFAULT NULL COMMENT '请求时间',
  `result` text COMMENT '响应内容',
  `responseTime` datetime DEFAULT NULL COMMENT '响应时间',
  `status` int(11) DEFAULT NULL COMMENT '请求状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1607 DEFAULT CHARSET=utf8 COMMENT='第三方接口消息请求表';

-- ----------------------------
-- Table structure for up_notification
-- ----------------------------
DROP TABLE IF EXISTS `up_notification`;
CREATE TABLE `up_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `templateId` bigint(20) DEFAULT '0' COMMENT '模板ID',
  `userId` bigint(20) DEFAULT '0' COMMENT '用户编号',
  `notifyType` int(11) DEFAULT '1' COMMENT '消息通知类型(1:通知 2:活动 3:互动 4:快报)',
  `bizType` varchar(32) DEFAULT '' COMMENT '业务类型',
  `title` varchar(400) DEFAULT '' COMMENT '标题',
  `content` text COMMENT '内容',
  `sendTime` datetime DEFAULT NULL COMMENT '消息发送时间',
  `emailStatus` int(11) DEFAULT '-1' COMMENT '是否发送邮件：-1不发送 0 待发送，1已发送',
  `emailSendTime` datetime DEFAULT NULL COMMENT '发送邮件时间',
  `smsStatus` int(11) DEFAULT '-1' COMMENT '是否发送短信：-1不发送 0 待发送，1已发送',
  `smsSendTime` datetime DEFAULT NULL COMMENT '短信发送时间',
  `readStatus` char(1) DEFAULT '0' COMMENT '读标志\\nY：已读\\nN：未读',
  `readTime` datetime DEFAULT NULL COMMENT '阅读时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_templateId` (`templateId`),
  KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='通知消息表';

-- ----------------------------
-- Table structure for up_notification_template
-- ----------------------------
DROP TABLE IF EXISTS `up_notification_template`;
CREATE TABLE `up_notification_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `templateId` varchar(64) NOT NULL COMMENT '模板编号',
  `templateName` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `type` varchar(10) DEFAULT NULL COMMENT '模板类型',
  `content` varchar(2000) DEFAULT NULL COMMENT '模板内容',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='通知消息模板';

-- ----------------------------
-- Table structure for up_oil_price
-- ----------------------------
DROP TABLE IF EXISTS `up_oil_price`;
CREATE TABLE `up_oil_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `province` varchar(16) NOT NULL COMMENT '省份',
  `b90` decimal(6,2) DEFAULT NULL COMMENT '90号汽油油价(元)',
  `b93` decimal(6,2) DEFAULT NULL COMMENT '93号汽油油价(元)',
  `b97` decimal(6,2) DEFAULT NULL COMMENT '97号汽油油价(元)',
  `b0` decimal(6,2) DEFAULT NULL COMMENT '0号汽油油价(元)',
  `oilTime` datetime DEFAULT NULL COMMENT '拉取时间',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='各地区油价表';

-- ----------------------------
-- Table structure for up_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `up_operation_log`;
CREATE TABLE `up_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT NULL COMMENT '日志类型',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '日志内容',
  `logType` varchar(4) DEFAULT NULL COMMENT '操作方式',
  `remoteAddress` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `userAgent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `requestUrl` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(255) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Table structure for up_organization
-- ----------------------------
DROP TABLE IF EXISTS `up_organization`;
CREATE TABLE `up_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '机构代码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parentIds` varchar(2000) DEFAULT NULL COMMENT '父节点路径',
  `remarks` varchar(255) DEFAULT NULL COMMENT '说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_organization_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织表';

-- ----------------------------
-- Table structure for up_parking_appointment_order
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_appointment_order`;
CREATE TABLE `up_parking_appointment_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `parkingName` varchar(255) DEFAULT NULL COMMENT '停车场名称',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `parkingLotCode` varchar(255) DEFAULT NULL COMMENT '车位编号',
  `orderNo` varchar(64) DEFAULT NULL COMMENT '平台平台预约记录流水号',
  `chargeDescription` text COMMENT '收费说明',
  `appointDescription` text COMMENT '预约收费说明',
  `plateNumber` varchar(255) DEFAULT NULL COMMENT '车牌号',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `carStyle` int(11) DEFAULT NULL COMMENT '车辆类型',
  `scheduleDate` date DEFAULT NULL COMMENT '预约下单日期',
  `scheduleTime` datetime DEFAULT NULL COMMENT '预约预计开始时间',
  `scheduleLength` int(11) DEFAULT NULL COMMENT '预计停车时长(分钟)',
  `deadlineTime` datetime DEFAULT NULL COMMENT '预约有效截止时间',
  `payLimit` int(11) DEFAULT '15' COMMENT '支付时限(分钟)',
  `payLimitTime` datetime DEFAULT NULL COMMENT '支付截止时间',
  `appointStatus` int(11) DEFAULT NULL COMMENT '预约状态',
  `payStatus` int(11) DEFAULT '0' COMMENT '支付状态',
  `payTime` datetime DEFAULT NULL COMMENT '支付时间',
  `payAmount` decimal(12,2) DEFAULT '0.00' COMMENT '预约支付金额(元)',
  `actualPayAmount` decimal(12,2) DEFAULT '0.00' COMMENT '预约实付金额(元)',
  `refundAmount` decimal(12,2) DEFAULT '0.00' COMMENT '退还金额(元)',
  `canCancel` char(1) DEFAULT '0' COMMENT '是否允许用户手动取消',
  `overTimeCancel` char(1) DEFAULT '0' COMMENT '是否超时取消',
  `cancelTimeLimit` datetime DEFAULT NULL COMMENT '取消时限',
  `cancelTime` datetime DEFAULT NULL COMMENT '取消时间',
  `cancelFee` decimal(12,2) DEFAULT '0.00' COMMENT '取消费用',
  `cancelReason` varchar(2000) DEFAULT NULL COMMENT '取消原因',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`),
  KEY `idx_parkingId` (`parkingId`)
) ENGINE=InnoDB AUTO_INCREMENT=683 DEFAULT CHARSET=utf8 COMMENT='用户停车预约表';

-- ----------------------------
-- Table structure for up_parking_appointment_rule
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_appointment_rule`;
CREATE TABLE `up_parking_appointment_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ruleId` bigint(20) NOT NULL COMMENT '预定s规则ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `onlineTime` date DEFAULT NULL COMMENT '上线时间',
  `offlineTime` date DEFAULT NULL COMMENT '下线时间',
  `status` int(11) DEFAULT '0' COMMENT '上线状态 1:未上线 2:上线中 3:已上线',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='停车场预定规则';

-- ----------------------------
-- Table structure for up_parking_charge_rule
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_charge_rule`;
CREATE TABLE `up_parking_charge_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ruleId` bigint(20) NOT NULL COMMENT '收费规则ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `onlineTime` date DEFAULT NULL COMMENT '上线时间',
  `offlineTime` date DEFAULT NULL COMMENT '下线时间',
  `status` int(11) DEFAULT '0' COMMENT '上线状态 1:未上线 2:上线中 3:已上线',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_ruleId` (`ruleId`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='停车场收费规则';

-- ----------------------------
-- Table structure for up_parking_image
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_image`;
CREATE TABLE `up_parking_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `bizType` char(1) DEFAULT NULL COMMENT '业务类型  1.过车 2.停车 ',
  `bizId` bigint(20) DEFAULT NULL COMMENT '业务ID,过车或者停车记录ID',
  `imageUrl` varchar(2000) DEFAULT NULL COMMENT '图像URL',
  `dataOrigin` varchar(255) DEFAULT NULL COMMENT '图片来源',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_bizId` (`bizId`),
  KEY `idx_bizType` (`bizType`)
) ENGINE=InnoDB AUTO_INCREMENT=102391 DEFAULT CHARSET=utf8 COMMENT='过车停车图像表';

-- ----------------------------
-- Table structure for up_parking_info
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_info`;
CREATE TABLE `up_parking_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `aliParkId` varchar(255) DEFAULT '' COMMENT '支付宝平台停车场ID',
  `hikParkId` varchar(255) DEFAULT '' COMMENT '海康平台停车场ID',
  `code` varchar(255) DEFAULT '' COMMENT '编号',
  `name` varchar(255) DEFAULT '' COMMENT '简称',
  `fullName` varchar(2000) DEFAULT '' COMMENT '全称',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `platformSupport` char(1) DEFAULT '0' COMMENT '是否平台接入 0:否 1 是',
  `outTime` int(11) DEFAULT '0' COMMENT '用户支付未离场的超时时间(以分钟为单位)',
  `type` varchar(32) DEFAULT '' COMMENT '停车场类型((1为地面，2为地下，3为路边)（多个类型，中间用,隔开)',
  `lotType` varchar(32) DEFAULT '' COMMENT '停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场',
  `logo` varchar(2000) DEFAULT '' COMMENT 'LOGO',
  `openStartTime` varchar(255) DEFAULT '' COMMENT '开始营业时间，格式HH:mm:ss',
  `openEndTime` varchar(255) DEFAULT '' COMMENT '结束营业时间，格式HH:mm:ss',
  `chargeMode` int(11) DEFAULT '2' COMMENT '缴费模式 1: 离场后缴费 2: 缴费后离场',
  `payMode` varchar(64) DEFAULT '' COMMENT '收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）',
  `payType` varchar(64) DEFAULT '' COMMENT '支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔',
  `chargeRule` varchar(2000) DEFAULT '' COMMENT '收费规则',
  `chargeDescription` text COMMENT '收费描述信息',
  `supportAppointment` char(1) DEFAULT '0' COMMENT '是否支持预约',
  `appointmentRule` text COMMENT '预约规则',
  `lotAppointmentTotal` int(11) DEFAULT '0' COMMENT '支持预约车位总数',
  `lotAppointmentAvailable` int(11) DEFAULT '0' COMMENT '可预约车位数',
  `latitude` double DEFAULT '0' COMMENT '维度',
  `longitude` double DEFAULT '0' COMMENT '经度',
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
  `lotTotal` int(11) DEFAULT '0' COMMENT '车位总数',
  `lotFixed` int(11) DEFAULT '0' COMMENT '固定车总数',
  `lotAvailable` int(11) DEFAULT '0' COMMENT '可用车位数',
  `description` text COMMENT '描述信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  `chargeFee` char(1) DEFAULT '0' COMMENT '是否收费 0:不收费 1 收费',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_code` (`code`),
  KEY `idx_code` (`code`),
  KEY `idx_latitude` (`latitude`),
  KEY `idx_longitude` (`longitude`),
  KEY `idx_aliParkId` (`aliParkId`),
  KEY `idx_hikParkId` (`hikParkId`),
  KEY `idx_latitude_longitude` (`latitude`,`longitude`),
  KEY `idx_deleted` (`deleted`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=285187 DEFAULT CHARSET=utf8 COMMENT='停车场信息表';

CREATE INDEX idx_code ON up_parking_info(`idx_code`);
CREATE INDEX idx_latitude ON up_parking_info(`latitude`);
CREATE INDEX idx_longitude ON up_parking_info(`longitude`);
CREATE INDEX idx_aliParkId ON up_parking_info(`aliParkId`);
CREATE INDEX idx_hikParkId ON up_parking_info(`hikParkId`);
CREATE INDEX idx_latitude_longitude ON up_parking_info(`latitude`,`longitude`);
CREATE INDEX idx_deleted ON up_parking_info(`deleted`);
CREATE INDEX idx_name ON up_parking_info(`name`);
CREATE UNIQUE INDEX uq_code ON up_parking_info(`code`);

-- ----------------------------
-- Table structure for up_parking_lot_info
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_lot_info`;
CREATE TABLE `up_parking_lot_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `hikParkingLotId` varchar(255) DEFAULT '' COMMENT '海康平台泊位ID',
  `hikBerthCode` varchar(64) DEFAULT '' COMMENT '海康平台泊位号(停车场唯一)',
  `aliParkingLotId` varchar(255) DEFAULT NULL COMMENT '支付宝平台泊位ID',
  `code` varchar(255) DEFAULT '' COMMENT '编号',
  `name` varchar(255) DEFAULT '' COMMENT '简称',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`),
  KEY `idx_parkingId` (`parkingId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='停车场车位表';

-- ----------------------------
-- Table structure for up_parking_order
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_order`;
CREATE TABLE `up_parking_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recordNo` varchar(64) NOT NULL COMMENT '平台停车记录流水号',
  `orderNo` varchar(64) NOT NULL COMMENT '平台停车账单流水号',
  `parkingId` bigint(20) NOT NULL COMMENT '平台停车场ID',
  `parkingName` varchar(255) DEFAULT NULL COMMENT '停车场名称',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '平台泊位ID',
  `parkingLotCode` varchar(255) DEFAULT NULL COMMENT '车位编号',
  `plateNumber` varchar(255) DEFAULT NULL COMMENT '车牌号',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `carStyle` int(11) DEFAULT NULL COMMENT '车辆类型',
  `startTime` datetime DEFAULT NULL COMMENT '停车开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '停车结束时间',
  `parkingLength` bigint(20) DEFAULT NULL COMMENT '停车时长(秒)',
  `freeLength` bigint(20) DEFAULT '0' COMMENT '免费停车时长(秒)',
  `chargeLength` bigint(20) DEFAULT '0' COMMENT '收费停车时长(秒)',
  `chargeDescription` text COMMENT '收费详情',
  `chargeMode` int(11) DEFAULT NULL COMMENT '收费模式 1:离场前收费 2: 离场后收费',
  `status` int(11) DEFAULT NULL COMMENT '停车状态',
  `appointed` char(1) DEFAULT '0' COMMENT '是否预约停车 0 否 1 是',
  `appointOrderNo` varchar(64) DEFAULT NULL COMMENT '预约订单号',
  `settle` char(1) DEFAULT '0' COMMENT '是否已结算 0 未结算 1 已结算',
  `settleOrderNo` varchar(64) DEFAULT NULL COMMENT '结算订单号',
  `settleAmount` decimal(12,2) DEFAULT '0.00' COMMENT '结算金额(元)',
  `settleTime` datetime DEFAULT NULL COMMENT '结算时间',
  `limitFree` char(1) DEFAULT '0' COMMENT '是否限免停车 0 未结算 1 已结算',
  `payable` char(1) DEFAULT '1' COMMENT '是否可以支付0 不可支付 1 可以支付',
  `needPay` char(1) DEFAULT '1' COMMENT '是否需要支付0 无需支付 1 需要支付',
  `freePayReason` varchar(200) DEFAULT NULL COMMENT '免于支付说明',
  `payableAmount` decimal(12,2) DEFAULT '0.00' COMMENT '应付金额(元)',
  `payStatus` int(11) DEFAULT '0' COMMENT '支付状态 0 :未支付 1:已支付  2:支付中 ',
  `actualPayAmount` decimal(12,2) DEFAULT '0.00' COMMENT '实付金额(元)',
  `payTime` datetime DEFAULT NULL COMMENT '支付时间',
  `payedUserId` bigint(20) DEFAULT NULL COMMENT '支付用户ID',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_recordNo` (`recordNo`),
  KEY `idx_orderNo` (`orderNo`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`),
  KEY `idx_settleOrderNo` (`settleOrderNo`),
  KEY `idx_appointOrderNo` (`appointOrderNo`)
) ENGINE=InnoDB AUTO_INCREMENT=10658 DEFAULT CHARSET=utf8 COMMENT='平台停车账单表';

-- ----------------------------
-- Table structure for up_parking_record
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_record`;
CREATE TABLE `up_parking_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recordNo` varchar(64) NOT NULL COMMENT '停车记录流水号',
  `hikParkingRecordId` varchar(255) DEFAULT NULL COMMENT '海康平台停车记录ID',
  `aliParkingRecordId` varchar(255) DEFAULT NULL COMMENT '海康平台停车记录ID',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `hikParkingId` varchar(255) DEFAULT NULL COMMENT '海康平台停车场ID',
  `aliParkingId` varchar(255) DEFAULT NULL COMMENT '支付宝平台停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `hikParkingLotId` varchar(255) DEFAULT NULL COMMENT '海康平台泊位ID',
  `aliParkingLotId` varchar(255) DEFAULT NULL COMMENT '支付宝平台泊位ID',
  `intoRecordId` bigint(20) DEFAULT NULL COMMENT '入车记录ID',
  `outRecordId` bigint(20) DEFAULT NULL COMMENT '出车记录ID',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `carStyle` int(11) DEFAULT NULL COMMENT '车辆类型',
  `startTime` datetime DEFAULT NULL COMMENT '停车开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '停车结束时间',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `appointed` char(1) DEFAULT '0' COMMENT '是否预约停车 0 否 1 是',
  `appointOrderNo` varchar(64) DEFAULT NULL COMMENT '预约订单号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`),
  KEY `idx_appointOrderNo` (`appointOrderNo`)
) ENGINE=InnoDB AUTO_INCREMENT=39977 DEFAULT CHARSET=utf8 COMMENT='平台停车记录表';

-- ----------------------------
-- Table structure for up_parking_settle_record
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_settle_record`;
CREATE TABLE `up_parking_settle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recordNo` varchar(64) NOT NULL COMMENT '停车记录单号',
  `orderNo` varchar(64) DEFAULT NULL COMMENT '结算订单号',
  `startTime` datetime DEFAULT NULL COMMENT '结算开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '结算结束时间',
  `status` int(11) DEFAULT '0' COMMENT '结算状态',
  `settleMode` int(11) DEFAULT '0' COMMENT '结算模式(1 离场前结算 2 离场后结算 )',
  `settleTime` datetime DEFAULT NULL COMMENT '结算时间',
  `settleAmount` decimal(12,2) DEFAULT '0.00' COMMENT '结算金额(元)',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4523 DEFAULT CHARSET=utf8 COMMENT='停车订单结算记录';

-- ----------------------------
-- Table structure for up_parking_vehicle_record
-- ----------------------------
DROP TABLE IF EXISTS `up_parking_vehicle_record`;
CREATE TABLE `up_parking_vehicle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `intoRecordId` bigint(20) DEFAULT NULL COMMENT '入车记录ID',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `carType` int(11) DEFAULT NULL COMMENT '车辆类型',
  `startTime` datetime DEFAULT NULL COMMENT '停车开始时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`)
) ENGINE=InnoDB AUTO_INCREMENT=33343 DEFAULT CHARSET=utf8 COMMENT='停车场在停车辆表';

-- ----------------------------
-- Table structure for up_passing_vehicle_exception_record
-- ----------------------------
DROP TABLE IF EXISTS `up_passing_vehicle_exception_record`;
CREATE TABLE `up_passing_vehicle_exception_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `machine` varchar(255) DEFAULT NULL COMMENT '设备编号',
  `hikPassingId` varchar(255) DEFAULT NULL COMMENT '海康平台过车ID',
  `aliPassingId` varchar(255) DEFAULT NULL COMMENT '阿里平台过车ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `carType` int(11) DEFAULT NULL COMMENT '车辆类型',
  `proofStatus` char(1) DEFAULT NULL COMMENT '校对状态 0:未校对  1:已校对',
  `dataOrigin` varchar(255) DEFAULT NULL COMMENT '数据来源',
  `confidence` int(11) DEFAULT NULL COMMENT '过车置信度',
  `plateNumberConfidence` int(11) DEFAULT NULL COMMENT '车牌置信度',
  `passCarType` char(1) DEFAULT NULL COMMENT '过车类型  0.入车 1.出车 ',
  `passTime` datetime DEFAULT NULL COMMENT '过车时间',
  `description` text COMMENT '异常说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2959 DEFAULT CHARSET=utf8 COMMENT='异常过车记录表';

-- ----------------------------
-- Table structure for up_passing_vehicle_record
-- ----------------------------
DROP TABLE IF EXISTS `up_passing_vehicle_record`;
CREATE TABLE `up_passing_vehicle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `passingNo` varchar(64) NOT NULL COMMENT '平台过车流水号',
  `parkingId` bigint(20) NOT NULL COMMENT '停车场ID',
  `machine` varchar(255) DEFAULT NULL COMMENT '设备编号',
  `hikPassingId` varchar(255) DEFAULT NULL COMMENT '海康平台过车ID',
  `aliPassingId` varchar(255) DEFAULT NULL COMMENT '阿里平台过车ID',
  `parkingLotId` bigint(20) DEFAULT NULL COMMENT '泊位ID',
  `plateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `carType` int(11) DEFAULT NULL COMMENT '车辆类型',
  `proofStatus` char(1) DEFAULT NULL COMMENT '校对状态 0:未校对  1:已校对',
  `dataOrigin` varchar(255) DEFAULT NULL COMMENT '数据来源',
  `confidence` int(11) DEFAULT NULL COMMENT '过车置信度',
  `plateNumberConfidence` int(11) DEFAULT NULL COMMENT '车牌置信度',
  `passCarType` char(1) DEFAULT NULL COMMENT '过车类型  0.入车 1.出车 ',
  `passTime` datetime DEFAULT NULL COMMENT '过车时间',
  `entryTime` datetime DEFAULT NULL COMMENT '入车时间',
  `exitTime` datetime DEFAULT NULL COMMENT '出车时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_parkingId` (`parkingId`),
  KEY `idx_parkingLotId` (`parkingLotId`),
  KEY `idx_plateNumber` (`plateNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=213308 DEFAULT CHARSET=utf8 COMMENT='过车记录表';

-- ----------------------------
-- Table structure for up_permission
-- ----------------------------
DROP TABLE IF EXISTS `up_permission`;
CREATE TABLE `up_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) NOT NULL COMMENT '权限编码',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` char(1) DEFAULT NULL COMMENT '状态 0、正常 1、禁用',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父节点',
  `parentIds` varchar(2000) DEFAULT NULL COMMENT '父节点路径',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_up_permission_code` (`code`),
  KEY `idx_permission_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for up_push_device
-- ----------------------------
DROP TABLE IF EXISTS `up_push_device`;
CREATE TABLE `up_push_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `registrationId` varchar(255) NOT NULL COMMENT '极光推送的设备唯一标识',
  `terminateType` tinyint(4) DEFAULT '0' COMMENT '设备类型 1: Android 2 iPhone 3 iPad 4 Weixin',
  `userId` bigint(20) DEFAULT '0' COMMENT '用户ID',
  `deviceToken` varchar(255) DEFAULT NULL COMMENT '设备唯一识别码',
  `channelCode` varchar(128) DEFAULT NULL COMMENT '渠道编码',
  `imei` varchar(128) DEFAULT NULL COMMENT 'imei',
  `buildBrand` varchar(255) DEFAULT NULL COMMENT '品牌',
  `buildModel` varchar(255) DEFAULT NULL COMMENT '机器型号',
  `buildVersionRelease` varchar(128) DEFAULT NULL COMMENT '版本号',
  `buildVersionSdkInt` int(11) DEFAULT NULL COMMENT 'SDK版本',
  `screenWidth` int(11) DEFAULT NULL COMMENT '屏幕宽度',
  `screenHeight` int(11) DEFAULT NULL COMMENT '屏幕高度',
  `densityDpi` int(11) DEFAULT NULL COMMENT '屏幕的dpi',
  `packageName` varchar(255) DEFAULT NULL COMMENT '应用包名',
  `appName` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `appVersionName` varchar(255) DEFAULT NULL COMMENT '应用版本名称',
  `appVersionCode` int(11) DEFAULT NULL COMMENT '应用版本号',
  `status` int(11) DEFAULT NULL COMMENT '使用状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_registrationId` (`registrationId`),
  KEY `idx_deviceToken` (`deviceToken`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COMMENT='推送设备';

-- ----------------------------
-- Table structure for up_push_tag
-- ----------------------------
DROP TABLE IF EXISTS `up_push_tag`;
CREATE TABLE `up_push_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag` varchar(40) NOT NULL COMMENT '标签名称',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_tag` (`tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='极光推送标签';

-- ----------------------------
-- Table structure for up_region
-- ----------------------------
DROP TABLE IF EXISTS `up_region`;
CREATE TABLE `up_region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL COMMENT '地区代码',
  `name` varchar(255) NOT NULL COMMENT '地区名称',
  `parentId` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `nameEn` varchar(1000) DEFAULT NULL COMMENT '英语名称',
  `shortNameEn` varchar(200) DEFAULT NULL COMMENT '英语简称',
  `areaCode` varchar(32) DEFAULT NULL COMMENT '区号',
  `zipCode` varchar(32) DEFAULT NULL COMMENT '邮编',
  `adCode` varchar(20) DEFAULT NULL COMMENT '区域编码',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_regionCode` (`code`),
  KEY `idx_regionName` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_role
-- ----------------------------
DROP TABLE IF EXISTS `up_role`;
CREATE TABLE `up_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '角色代码',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `staticRole` char(1) DEFAULT '0' COMMENT '是否静态',
  `defaultRole` char(1) DEFAULT '0' COMMENT '是否默认',
  `enabled` char(1) DEFAULT NULL COMMENT '是否可用\\n1：可用\\n0：停用',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_up_role_code` (`code`),
  KEY `idx_role_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Table structure for up_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `up_role_menu`;
CREATE TABLE `up_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_up_role_menu` (`menuId`,`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `up_role_permission`;
CREATE TABLE `up_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(20) NOT NULL COMMENT '角色ID',
  `permissionId` bigint(20) NOT NULL COMMENT '用户ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_up_role_permission` (`permissionId`,`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='角色-权限表';

-- ----------------------------
-- Table structure for up_rpc_record
-- ----------------------------
DROP TABLE IF EXISTS `up_rpc_record`;
CREATE TABLE `up_rpc_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) DEFAULT NULL COMMENT 'uuid',
  `interfaceName` varchar(2000) DEFAULT NULL COMMENT '接口名称',
  `methodName` varchar(100) DEFAULT NULL COMMENT '方法名称',
  `provider` varchar(100) DEFAULT NULL COMMENT 'provider',
  `consumer` varchar(100) DEFAULT NULL COMMENT 'consumer',
  `parameter` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `requestTime` datetime DEFAULT NULL COMMENT '请求时间',
  `responseTime` datetime DEFAULT NULL COMMENT '响应时间',
  `result` text COMMENT '响应内容',
  `status` int(11) DEFAULT NULL COMMENT '请求状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='RPC调用记录';

-- ----------------------------
-- Table structure for up_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `up_schedule_job`;
CREATE TABLE `up_schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobId` varchar(128) DEFAULT NULL COMMENT '任务ID',
  `jobName` varchar(255) DEFAULT NULL COMMENT '任务名',
  `jobType` varchar(10) NOT NULL COMMENT '任务类型',
  `priority` int(11) DEFAULT '0' COMMENT '优先级',
  `maxRetryTimes` int(11) DEFAULT NULL COMMENT '最大重试次数',
  `relyOnPrevCycle` char(1) DEFAULT NULL COMMENT '是否依赖上一周期',
  `submitNodeGroup` varchar(64) DEFAULT NULL COMMENT '提交节点组',
  `taskTrackerNodeGroup` varchar(64) DEFAULT NULL COMMENT '执行节点组',
  `extParams` text COMMENT '用户参数JSON',
  `internalExtParams` text COMMENT '内部扩展JSON',
  `needFeedback` char(1) DEFAULT NULL COMMENT '反馈客户端',
  `cronExpression` varchar(128) DEFAULT NULL COMMENT 'Cron表达式',
  `triggerTime` datetime DEFAULT NULL COMMENT '触发时间',
  `repeatCount` int(11) DEFAULT '0' COMMENT '重复次数',
  `repeatInterval` bigint(20) DEFAULT '0' COMMENT '触发时间间隔(s)',
  `jobStatus` int(11) DEFAULT NULL COMMENT '任务状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_jobId` (`jobId`),
  KEY `idx_jobId` (`jobId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='任务调度表';

-- ----------------------------
-- Table structure for up_trade_payment_order
-- ----------------------------
DROP TABLE IF EXISTS `up_trade_payment_order`;
CREATE TABLE `up_trade_payment_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(255) DEFAULT NULL COMMENT '交易订单号',
  `transactionNo` varchar(255) DEFAULT NULL COMMENT '支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo',
  `orderFrom` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `bizOrderType` int(11) DEFAULT NULL COMMENT '交易业务类型 ：消费、充值等',
  `bizOrderNo` varchar(255) DEFAULT NULL COMMENT '交易业务订单号',
  `orderAmount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `productName` varchar(300) DEFAULT NULL COMMENT '商品名称',
  `orderDate` date DEFAULT NULL COMMENT '订单日期',
  `orderTime` datetime DEFAULT NULL COMMENT '订单时间',
  `orderIp` varchar(50) DEFAULT NULL COMMENT '下单IP(客户端IP,在网关页面获取)',
  `orderRefererUrl` varchar(100) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `returnUrl` varchar(600) DEFAULT NULL COMMENT '页面回调通知URL',
  `notifyUrl` varchar(600) DEFAULT NULL COMMENT '后台异步通知URL',
  `orderPeriod` smallint(6) DEFAULT NULL COMMENT '订单有效期(单位分钟)',
  `expireTime` datetime DEFAULT NULL COMMENT '到期时间',
  `cancelReason` varchar(600) DEFAULT NULL COMMENT '订单撤销原因',
  `cancelTime` datetime DEFAULT NULL COMMENT '订单撤销时间',
  `succeedPayTime` datetime DEFAULT NULL COMMENT '支付成功时间',
  `payWayCode` varchar(50) DEFAULT NULL COMMENT '支付通道编号',
  `payWayName` varchar(100) DEFAULT NULL COMMENT '支付通道名称',
  `payTypeCode` varchar(50) DEFAULT NULL COMMENT '支付方式类型编号',
  `payTypeName` varchar(100) DEFAULT NULL COMMENT '支付方式类型名称',
  `fundIntoType` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `refunded` char(1) DEFAULT '0' COMMENT '是否退款(0:否,1: 是,默认值为:0)',
  `refundTimes` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `successRefundAmount` decimal(20,6) DEFAULT '0.000000' COMMENT '成功退款总金额',
  `succeedRefundTime` datetime DEFAULT NULL COMMENT '退款成功时间',
  `status` int(11) DEFAULT NULL COMMENT '状态(参考枚举:PayStatusEnum)',
  `remark` varchar(200) DEFAULT NULL COMMENT '支付备注',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=524 DEFAULT CHARSET=utf8 COMMENT='平台支付订单表';

-- ----------------------------
-- Table structure for up_trade_payment_record
-- ----------------------------
DROP TABLE IF EXISTS `up_trade_payment_record`;
CREATE TABLE `up_trade_payment_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(255) DEFAULT NULL COMMENT '交易订单号',
  `transactionNo` varchar(255) DEFAULT NULL COMMENT '支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo',
  `orderFrom` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `bizOrderType` int(11) DEFAULT NULL COMMENT '交易业务类型 ：消费、充值等',
  `bizOrderNo` varchar(32) DEFAULT NULL COMMENT '交易业务订单号',
  `orderAmount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `productName` varchar(300) DEFAULT NULL COMMENT '商品名称',
  `payerUserId` bigint(20) DEFAULT NULL COMMENT '付款人ID',
  `payerUsername` varchar(60) DEFAULT NULL COMMENT '付款人名称',
  `payerPayAmount` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方支付金额',
  `payerFee` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方手续费',
  `payerAccountType` varchar(30) DEFAULT NULL COMMENT '付款方账户类型(参考账户类型枚举:AccountTypeEnum)',
  `receiverUserId` bigint(20) DEFAULT NULL COMMENT '收款人ID',
  `receiverUsername` varchar(60) DEFAULT NULL COMMENT '收款人名称',
  `receiverPayAmount` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方支付金额',
  `receiverFee` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方手续费',
  `receiverAccountType` varchar(30) DEFAULT NULL COMMENT '收款方账户类型(参考账户类型枚举:AccountTypeEnum)',
  `orderDate` date DEFAULT NULL COMMENT '订单日期',
  `orderTime` datetime DEFAULT NULL COMMENT '订单时间',
  `orderIp` varchar(50) DEFAULT NULL COMMENT '下单IP(客户端IP,在网关页面获取)',
  `orderRefererUrl` varchar(100) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `returnUrl` varchar(600) DEFAULT NULL COMMENT '页面回调通知URL',
  `notifyUrl` varchar(600) DEFAULT NULL COMMENT '后台异步通知URL',
  `platformIncome` decimal(20,6) DEFAULT '0.000000' COMMENT '平台收入',
  `platformCost` decimal(20,6) DEFAULT '0.000000' COMMENT '平台成本',
  `platformProfit` decimal(20,6) DEFAULT '0.000000' COMMENT '平台利润',
  `feeRate` decimal(20,6) DEFAULT '0.000000' COMMENT '费率',
  `payWayCode` varchar(50) DEFAULT NULL COMMENT '支付通道编号',
  `payWayName` varchar(100) DEFAULT NULL COMMENT '支付通道名称',
  `payTypeCode` varchar(50) DEFAULT NULL COMMENT '支付方式类型编号',
  `payTypeName` varchar(100) DEFAULT NULL COMMENT '支付方式类型名称',
  `succeedPayTime` datetime DEFAULT NULL COMMENT '支付成功时间',
  `completeTime` datetime DEFAULT NULL COMMENT '完成时间',
  `fundIntoType` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `refunded` char(1) DEFAULT '0' COMMENT '是否退款(0:否,1: 是,默认值为:0)',
  `refundTimes` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `successRefundAmount` decimal(20,6) DEFAULT '0.000000' COMMENT '成功退款总金额',
  `succeedRefundTime` datetime DEFAULT NULL COMMENT '退款成功时间',
  `status` varchar(30) DEFAULT NULL COMMENT '状态(参考枚举:PayStatusEnum)',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=842 DEFAULT CHARSET=utf8 COMMENT='平台支付记录表';

-- ----------------------------
-- Table structure for up_traffic_restriction
-- ----------------------------
DROP TABLE IF EXISTS `up_traffic_restriction`;
CREATE TABLE `up_traffic_restriction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL COMMENT '日期',
  `week` varchar(255) DEFAULT NULL COMMENT '星期',
  `city` varchar(255) DEFAULT NULL COMMENT '城市拼音',
  `cityName` varchar(255) DEFAULT NULL COMMENT '城市名称',
  `fine` varchar(255) DEFAULT NULL COMMENT '罚款描述',
  `remarks` varchar(255) DEFAULT NULL COMMENT '特殊描述',
  `trafficRestriction` char(1) DEFAULT NULL COMMENT '是否限行 1:是 0否',
  `holiday` varchar(255) DEFAULT NULL COMMENT '节假日',
  `tailNumber` varchar(255) DEFAULT NULL COMMENT '限行尾号',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `up_traffic_restriction_city` (`city`)
) ENGINE=InnoDB AUTO_INCREMENT=516 DEFAULT CHARSET=utf8 COMMENT='限行表';

-- ----------------------------
-- Table structure for up_traffic_restriction_city
-- ----------------------------
DROP TABLE IF EXISTS `up_traffic_restriction_city`;
CREATE TABLE `up_traffic_restriction_city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(255) DEFAULT NULL COMMENT '限行城市名称',
  `adCode` varchar(20) DEFAULT NULL COMMENT '区域编码',
  `pinyinName` varchar(2000) DEFAULT NULL COMMENT '拼音名称',
  `initial` varchar(20) DEFAULT NULL COMMENT '首字母',
  `trafficRestriction` char(1) DEFAULT '0' COMMENT '是否支持限行',
  `cityPrefix` varchar(255) DEFAULT NULL,
  `limitPattern` int(11) DEFAULT '-1' COMMENT '限行尾号处理方式',
  `carType` varchar(16) DEFAULT '' COMMENT '车辆类型',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_regionCity` (`cityName`),
  KEY `idx_regionadCode` (`adCode`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='限行城市表';

-- ----------------------------
-- Table structure for up_traffic_restriction_info
-- ----------------------------
DROP TABLE IF EXISTS `up_traffic_restriction_info`;
CREATE TABLE `up_traffic_restriction_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trafficRestrictionId` bigint(20) NOT NULL,
  `time` varchar(2000) DEFAULT NULL COMMENT '限行时间',
  `place` varchar(2000) DEFAULT NULL COMMENT '限行地段',
  `info` varchar(2000) DEFAULT NULL COMMENT '其他说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `up_traffic_restriction_info_trafficRestrictionId` (`trafficRestrictionId`)
) ENGINE=InnoDB AUTO_INCREMENT=721 DEFAULT CHARSET=utf8 COMMENT='限行信息表';

-- ----------------------------
-- Table structure for up_traffic_restriction_policy
-- ----------------------------
DROP TABLE IF EXISTS `up_traffic_restriction_policy`;
CREATE TABLE `up_traffic_restriction_policy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cityId` bigint(20) DEFAULT NULL COMMENT '限行城市ID',
  `carType` int(11) DEFAULT NULL COMMENT '车辆类型',
  `title` varchar(200) DEFAULT '' COMMENT '标题',
  `restrictionTime` varchar(2000) DEFAULT NULL COMMENT '限行时间',
  `restrictionArea` varchar(2000) DEFAULT NULL COMMENT '限行区域',
  `restrictionRule` varchar(2000) DEFAULT NULL COMMENT '限行规则',
  `restrictionDetail` mediumtext COMMENT '限行坐标',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `lastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_policy_trafficCityId` (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8 COMMENT='限行政策表';

-- ----------------------------
-- Table structure for up_user
-- ----------------------------
DROP TABLE IF EXISTS `up_user`;
CREATE TABLE `up_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) NOT NULL COMMENT 'UUID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `status` char(1) DEFAULT NULL COMMENT '用户的状态',
  `phoneNumber` varchar(11) DEFAULT NULL COMMENT '手机',
  `phoneNumberConfirmed` char(1) DEFAULT '0' COMMENT '手机号是否确认',
  `emailAddress` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `emailAddressConfirmed` char(1) DEFAULT '0' COMMENT '邮箱是否确认',
  `portrait` varchar(2000) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `accessAttemptCount` int(11) DEFAULT NULL COMMENT '失败登录次数',
  `defaultUser` char(1) DEFAULT '0' COMMENT '是否默认用户',
  `isAdministrator` char(1) DEFAULT '0' COMMENT '是否管理员',
  `userType` char(1) DEFAULT NULL COMMENT '用户类型',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `tradeSalt` varchar(64) DEFAULT NULL COMMENT '交易密码盐',
  `tradePassword` varchar(32) DEFAULT NULL COMMENT '交易密码',
  `tradeAttemptCount` int(11) DEFAULT NULL COMMENT '交易失败次数',
  `alias` varchar(255) DEFAULT '' COMMENT '别名(用于推送)',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_users_username` (`username`),
  KEY `idx_username` (`username`),
  KEY `idx_phoneNumber` (`phoneNumber`),
  KEY `idx_emailAddress` (`emailAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=282 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_user_asset
-- ----------------------------
DROP TABLE IF EXISTS `up_user_asset`;
CREATE TABLE `up_user_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户主键',
  `balance` double(16,8) DEFAULT '0.00000000' COMMENT '账户余额',
  `point` int(11) DEFAULT NULL COMMENT '用户积分',
  `couponCount` int(11) DEFAULT '0' COMMENT '可用优惠券数量',
  `couponBalance` double(16,8) DEFAULT '0.00000000' COMMENT '可用优惠券金额',
  `packetCount` int(11) DEFAULT '0' COMMENT '红包数量',
  `packetBalance` double(16,8) DEFAULT '0.00000000' COMMENT '红包金额',
  `unPaidCount` int(11) DEFAULT '0' COMMENT '未支付订单数',
  `unPaidBalance` double(16,8) DEFAULT '0.00000000' COMMENT '未支付金额',
  `violationCount` int(11) DEFAULT '0' COMMENT '违章停车数',
  `violationBalance` int(11) DEFAULT '0' COMMENT '违章停车金额',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COMMENT='用户账户信息';

-- ----------------------------
-- Table structure for up_user_asset_log
-- ----------------------------
DROP TABLE IF EXISTS `up_user_asset_log`;
CREATE TABLE `up_user_asset_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户主键',
  `balance` double(16,8) DEFAULT '0.00000000' COMMENT '账户余额',
  `amount` double(16,8) DEFAULT '0.00000000' COMMENT '变动额',
  `bizNo` varchar(255) DEFAULT NULL COMMENT '业务流水号',
  `transactionNo` varchar(255) DEFAULT NULL COMMENT '交易流水号',
  `bizType` int(11) DEFAULT NULL COMMENT '业务类型',
  `direction` int(11) DEFAULT NULL COMMENT '变动方向',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8 COMMENT='用户账户资金流水表';

-- ----------------------------
-- Table structure for up_user_auth_apply
-- ----------------------------
DROP TABLE IF EXISTS `up_user_auth_apply`;
CREATE TABLE `up_user_auth_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户主键',
  `realName` varchar(255) NOT NULL COMMENT '真实姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `cardNo` varchar(32) NOT NULL COMMENT '身份证号',
  `authStatus` int(11) DEFAULT NULL COMMENT '处理状态:1待认证  2已认证 3认证不通过',
  `cardFront` varchar(2000) DEFAULT NULL COMMENT '身份证正面照',
  `cardContrary` varchar(2000) DEFAULT NULL COMMENT '身份证反面照',
  `cardInHand` varchar(2000) DEFAULT NULL COMMENT '身份证手持照',
  `remark` varchar(2000) DEFAULT NULL COMMENT '是否管理员',
  `applyTime` datetime DEFAULT NULL COMMENT '申请时间',
  `authUserId` bigint(20) DEFAULT NULL COMMENT '审核用户ID',
  `authTime` datetime DEFAULT NULL COMMENT '审核时间',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_realName` (`realName`),
  KEY `idx_cardNo` (`cardNo`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='用户申请认证信息';

-- ----------------------------
-- Table structure for up_user_car_auth
-- ----------------------------
DROP TABLE IF EXISTS `up_user_car_auth`;
CREATE TABLE `up_user_car_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `carId` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `carBrand` varchar(255) DEFAULT NULL COMMENT '车辆品牌',
  `carType` varchar(255) DEFAULT NULL COMMENT '车辆型号',
  `carColor` int(11) DEFAULT NULL COMMENT '车辆颜色',
  `carLevel` int(11) DEFAULT NULL COMMENT '车辆等级',
  `plateType` varchar(2) DEFAULT NULL COMMENT '车牌类型',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `plateNumber` varchar(32) NOT NULL COMMENT '车牌号(后几位字母)',
  `vehicleNumber` varchar(255) NOT NULL COMMENT '车架号',
  `engineNumber` varchar(255) NOT NULL COMMENT '发动机号',
  `licensePhotoFront` varchar(2000) DEFAULT NULL COMMENT '行驶证正面图片',
  `licensePhotoContrary` varchar(2000) DEFAULT NULL COMMENT '行驶证反面图片',
  `registerDate` datetime DEFAULT NULL COMMENT '注册日期',
  `status` char(1) DEFAULT '1' COMMENT '处理状态  1: 认证中  2:已认证 3:认证不通过',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `applyTime` datetime DEFAULT NULL COMMENT '申请时间',
  `authUserId` bigint(20) DEFAULT NULL COMMENT '审核用户ID',
  `authTime` datetime DEFAULT NULL COMMENT '审核时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8 COMMENT='用户车辆认证表';

-- ----------------------------
-- Table structure for up_user_car_info
-- ----------------------------
DROP TABLE IF EXISTS `up_user_car_info`;
CREATE TABLE `up_user_car_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `carBrand` varchar(255) DEFAULT NULL COMMENT '车辆品牌',
  `carType` varchar(255) DEFAULT NULL COMMENT '车辆型号',
  `carColor` int(11) DEFAULT NULL COMMENT '车辆颜色',
  `carLevel` int(11) DEFAULT NULL COMMENT '车辆等级',
  `plateType` varchar(2) DEFAULT NULL COMMENT '车牌类型',
  `plateColor` int(11) DEFAULT NULL COMMENT '车牌颜色',
  `platePrefix` varchar(32) DEFAULT NULL COMMENT '车牌第一个中文',
  `plateInitial` varchar(32) DEFAULT NULL COMMENT '车牌首字母',
  `plateNumber` varchar(32) NOT NULL COMMENT '车牌号(后几位字母)',
  `plateLastNumber` varchar(2) DEFAULT NULL COMMENT '车牌最后一位数字',
  `vehicleNumber` varchar(255) DEFAULT NULL,
  `engineNumber` varchar(255) DEFAULT NULL,
  `carImageUrl` varchar(2000) DEFAULT NULL COMMENT '车辆图片URL',
  `licensePhotoFront` varchar(2000) DEFAULT NULL COMMENT '行驶证正面图片',
  `licensePhotoContrary` varchar(2000) DEFAULT NULL COMMENT '行驶证反面图片',
  `registerDate` datetime DEFAULT NULL COMMENT '注册日期',
  `defaultCar` char(1) DEFAULT '0' COMMENT '是否默认车辆 0:否 1 是',
  `hintTrafficLimit` char(1) DEFAULT '0' COMMENT '是否提醒限行',
  `hintViolation` char(1) DEFAULT '0' COMMENT '是否提醒违章',
  `hintYearCheck` char(1) DEFAULT '0' COMMENT '是否提醒年检',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT NULL COMMENT '处理状态  1: 已认证  2:待认证 3:认证不通过',
  `applyTime` datetime DEFAULT NULL COMMENT '申请时间',
  `authUserId` bigint(20) DEFAULT NULL COMMENT '审核用户ID',
  `authTime` datetime DEFAULT NULL COMMENT '审核时间',
  `totalFine` decimal(10,2) DEFAULT '0.00' COMMENT '未处理违章总罚款',
  `totalPoints` int(11) DEFAULT '0' COMMENT '未处理违章总扣分',
  `untreatedCount` int(11) DEFAULT '0' COMMENT '未处理违章条数',
  `totalCount` int(11) DEFAULT '0' COMMENT '违章总条数',
  `violationUpdateTime` datetime DEFAULT NULL COMMENT '最后一次违章同步时间',
  `violationUpdateCount` int(11) DEFAULT '0' COMMENT '一段时间内违章同步次数',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`),
  KEY `idx_plateNumber` (`platePrefix`,`plateInitial`,`plateNumber`),
  KEY `idx_plateColor` (`plateColor`)
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8 COMMENT='用户车辆信息';

-- ----------------------------
-- Table structure for up_user_driver_license
-- ----------------------------
DROP TABLE IF EXISTS `up_user_driver_license`;
CREATE TABLE `up_user_driver_license` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户主键',
  `cardNumber` varchar(32) NOT NULL COMMENT '证号',
  `archiveNumber` varchar(64) DEFAULT NULL COMMENT '档案编号',
  `firstIssueDate` datetime DEFAULT NULL COMMENT '初次领证日期',
  `driveClass` varchar(10) DEFAULT NULL COMMENT '准驾车型',
  `validateDateStart` datetime DEFAULT NULL COMMENT '有效期开始',
  `validateDateEnd` datetime DEFAULT NULL COMMENT '有效期结束',
  `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
  `score` int(11) DEFAULT '0' COMMENT '扣分数',
  `scoreUpdateTime` datetime DEFAULT NULL COMMENT '扣分更新时间',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`),
  KEY `idx_cardNumber` (`cardNumber`),
  KEY `idx_archiveNumber` (`archiveNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户驾驶证表';

-- ----------------------------
-- Table structure for up_user_info
-- ----------------------------
DROP TABLE IF EXISTS `up_user_info`;
CREATE TABLE `up_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户主键',
  `realName` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `cardNo` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `certificated` char(1) DEFAULT '0' COMMENT '是否实名认证',
  `certificatedDate` datetime DEFAULT NULL COMMENT '实名认证时间',
  `wxUuid` varchar(255) DEFAULT NULL COMMENT '微信Uuid',
  `wxOpenId` varchar(255) DEFAULT NULL COMMENT '微信OpenId',
  `wxUnionId` varchar(255) DEFAULT NULL COMMENT '用户统一标识',
  `wxNickname` varchar(255) DEFAULT NULL COMMENT '普通用户昵称',
  `wxSex` int(11) DEFAULT NULL COMMENT '普通用户性别，1为男性，2为女性',
  `wxCounty` varchar(255) DEFAULT NULL COMMENT '国家，如中国为CN',
  `wxProvince` varchar(255) DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `wxCity` varchar(255) DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `wxPrivilege` varchar(2000) DEFAULT NULL COMMENT '用户特权信息',
  `wxAuthorized` char(1) DEFAULT '0' COMMENT '是否微信认证',
  `aliNickname` varchar(255) DEFAULT NULL COMMENT '支付宝用户昵称',
  `aliUserId` varchar(255) DEFAULT NULL COMMENT '支付宝用户的唯一userId',
  `aliAvatar` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `aliProvince` varchar(255) DEFAULT NULL COMMENT '省份名称',
  `aliCity` varchar(255) DEFAULT NULL COMMENT '市名称',
  `aliIsStudentCertified` varchar(255) DEFAULT NULL COMMENT '是否是学生',
  `aliUserType` varchar(255) DEFAULT NULL COMMENT '用户类型1代表公司账户2代表个人账户',
  `aliUserStatus` varchar(10) DEFAULT NULL COMMENT '用户状态Q代表快速注册用户 T代表已认证用户 B代表被冻结账户 W代表已注册，未激活的账户',
  `aliIsCertified` varchar(255) DEFAULT NULL COMMENT '是否通过实名认证。T是通过 F是没有实名认证。',
  `aliGender` varchar(255) DEFAULT NULL COMMENT '性别（F：女性；M：男性）',
  `aliAuthorized` char(1) DEFAULT '0' COMMENT '是否支付宝认证',
  `qqNumber` varchar(255) DEFAULT NULL COMMENT 'QQ号码',
  `qqAuthorized` char(1) DEFAULT '0' COMMENT '是否QQ认证',
  `level` int(11) DEFAULT NULL COMMENT '用户等级',
  `invitedUserId` char(1) DEFAULT NULL COMMENT '邀请用户ID',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`),
  KEY `idx_OpenId` (`wxOpenId`),
  KEY `idx_UnionId` (`wxUnionId`),
  KEY `idx_aliUserId` (`aliUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Table structure for up_user_level_log
-- ----------------------------
DROP TABLE IF EXISTS `up_user_level_log`;
CREATE TABLE `up_user_level_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `oldLevel` int(11) DEFAULT NULL COMMENT '旧等级',
  `newLevel` int(11) DEFAULT NULL COMMENT '新等级',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户等级变动记录表';

-- ----------------------------
-- Table structure for up_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `up_user_organization`;
CREATE TABLE `up_user_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `organizationId` bigint(20) NOT NULL,
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_organization` (`userId`,`organizationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for up_user_password_log
-- ----------------------------
DROP TABLE IF EXISTS `up_user_password_log`;
CREATE TABLE `up_user_password_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `passwordType` int(11) DEFAULT NULL COMMENT '密码类型',
  `oldPassword` varchar(32) DEFAULT NULL COMMENT '旧密码',
  `newPassword` varchar(32) DEFAULT NULL COMMENT '新密码',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=285 DEFAULT CHARSET=utf8 COMMENT='密码修改记录表';

-- ----------------------------
-- Table structure for up_user_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `up_user_pay_order`;
CREATE TABLE `up_user_pay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `orderUuid` varchar(255) NOT NULL COMMENT '订单UUID',
  `orderNo` varchar(255) DEFAULT NULL COMMENT '系统支付订单号',
  `transactionNo` varchar(255) DEFAULT NULL COMMENT '交易订单号',
  `bizOrderType` int(11) DEFAULT NULL COMMENT '订单类型（1.充值，2，付款）',
  `bizOrderNo` varchar(32) DEFAULT NULL COMMENT ' 业务订单号（分别为充值订单号、缴费订单号）',
  `payWay` int(11) DEFAULT NULL COMMENT ' 支付方式（1. 支付宝，2，微信）',
  `payAmount` decimal(12,6) DEFAULT '0.000000' COMMENT '支付金额',
  `payAmountActual` decimal(12,6) DEFAULT '0.000000' COMMENT ' 实际支付金额',
  `payStatus` int(11) DEFAULT NULL COMMENT '支付状态',
  `payTime` datetime DEFAULT NULL COMMENT '支付时间',
  `succeedPayTime` datetime DEFAULT NULL COMMENT '支付成功时间',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=800 DEFAULT CHARSET=utf8 COMMENT='用户支付表';

-- ----------------------------
-- Table structure for up_user_point_log
-- ----------------------------
DROP TABLE IF EXISTS `up_user_point_log`;
CREATE TABLE `up_user_point_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `point` double(16,8) DEFAULT '0.00000000' COMMENT '积分余额',
  `amount` double(16,8) DEFAULT '0.00000000' COMMENT '变动额',
  `bizNo` varchar(255) DEFAULT NULL COMMENT '业务流水号',
  `transactionNo` varchar(255) DEFAULT NULL COMMENT '交易流水号',
  `bizType` int(11) DEFAULT NULL COMMENT '业务类型',
  `direction` int(11) DEFAULT NULL COMMENT '变动方向',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL,
  `LastModificationTime` datetime DEFAULT NULL,
  `deleterUserId` bigint(20) DEFAULT NULL,
  `deletionTime` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户积分流水表';

-- ----------------------------
-- Table structure for up_user_push_tag
-- ----------------------------
DROP TABLE IF EXISTS `up_user_push_tag`;
CREATE TABLE `up_user_push_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `tagId` bigint(20) NOT NULL COMMENT '标签ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签表';

-- ----------------------------
-- Table structure for up_user_recharge
-- ----------------------------
DROP TABLE IF EXISTS `up_user_recharge`;
CREATE TABLE `up_user_recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `orderUuid` varchar(255) NOT NULL COMMENT '订单UUID',
  `orderNo` varchar(255) NOT NULL COMMENT '订单号',
  `payOrderNo` varchar(255) NOT NULL COMMENT '支付订单号',
  `rechargeAmount` decimal(12,6) DEFAULT '0.000000' COMMENT '充值金额金额',
  `rechargeRealAmount` decimal(12,2) DEFAULT '0.00' COMMENT '充值到账金额',
  `rechargeType` int(11) DEFAULT NULL COMMENT '充值方式(1.支付宝;2.微信)',
  `rechargeChannel` int(11) DEFAULT NULL COMMENT '支付渠道',
  `rechargeTime` datetime DEFAULT NULL COMMENT '充值时间',
  `succeedTime` datetime DEFAULT NULL COMMENT '充值成功时间',
  `rechargeStatus` int(11) DEFAULT NULL COMMENT '充值状态',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=utf8 COMMENT='用户充值表';

-- ----------------------------
-- Table structure for up_user_role
-- ----------------------------
DROP TABLE IF EXISTS `up_user_role`;
CREATE TABLE `up_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `roleId` bigint(20) NOT NULL COMMENT '角色ID',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_up_user_role` (`userId`,`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='用户-角色表';

-- ----------------------------
-- Table structure for up_vehicle_record
-- ----------------------------
DROP TABLE IF EXISTS `up_vehicle_record`;
CREATE TABLE `up_vehicle_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carStyle` varchar(255) DEFAULT NULL COMMENT '车辆类型',
  `carBrand` varchar(255) DEFAULT NULL COMMENT '车辆品牌',
  `carType` varchar(255) DEFAULT NULL COMMENT '车辆型号',
  `carColor` int(11) DEFAULT NULL,
  `carLevel` int(11) DEFAULT NULL,
  `plateType` varchar(2) DEFAULT NULL COMMENT '车牌类型',
  `plateColor` int(11) DEFAULT NULL,
  `ownerName` varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `plateNumber` varchar(32) DEFAULT NULL COMMENT '车牌号(后几位字母)',
  `vehicleNumber` varchar(255) DEFAULT NULL COMMENT '车架号',
  `engineNumber` varchar(255) DEFAULT NULL COMMENT '发动机号',
  `dataOrigin` varchar(255) DEFAULT NULL COMMENT '数据来源(系统OR设备)',
  `proofStatus` char(1) DEFAULT '0' COMMENT '校对状态 0:未校对  1:已校对',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_plateNumber` (`plateNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8 COMMENT='平台车辆表';

-- ----------------------------
-- Table structure for up_vehicle_violation
-- ----------------------------
DROP TABLE IF EXISTS `up_vehicle_violation`;
CREATE TABLE `up_vehicle_violation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carId` bigint(20) DEFAULT NULL COMMENT '车辆id',
  `code` varchar(64) DEFAULT NULL COMMENT '违章编码,唯一，非违章条例码',
  `violationTime` datetime DEFAULT NULL COMMENT '违章时间',
  `fine` decimal(10,2) DEFAULT '0.00' COMMENT '罚款金额',
  `address` varchar(1000) DEFAULT NULL COMMENT '违章地址',
  `reason` varchar(1000) DEFAULT NULL COMMENT '违章处理原因',
  `point` int(11) DEFAULT NULL COMMENT '违章扣分',
  `violationCity` varchar(128) DEFAULT NULL COMMENT '违章发生城市，可能为空',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(128) DEFAULT NULL COMMENT '城市',
  `serviceFee` decimal(10,2) DEFAULT '0.00' COMMENT '服务费（与代缴有关，用户可忽略本字段）',
  `markFee` decimal(10,2) DEFAULT '0.00' COMMENT '代扣分费用（与代缴有关，用户可忽略本字段）',
  `canSelect` int(11) DEFAULT NULL COMMENT '能否勾选办理：0不可勾选, 1可勾选。（与代缴有关，用户可忽略本字段）',
  `processStatus` int(11) DEFAULT '1' COMMENT '违章处理状态：1：未处理，2：处理中，3：已处理，4：不支持（与代缴有关，用户可忽略本字段）',
  `violationNum` varchar(16) DEFAULT NULL COMMENT '违章官方编码',
  `organName` varchar(255) DEFAULT NULL COMMENT '执法机构',
  `paymentStatus` int(11) DEFAULT '1' COMMENT '违章缴费状态 不返回表示无法获取该信息，1-未缴费 2-已缴',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_carId` (`carId`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='车辆违章信息表';

-- ----------------------------
-- Table structure for up_visit_log
-- ----------------------------
DROP TABLE IF EXISTS `up_visit_log`;
CREATE TABLE `up_visit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL COMMENT '登录用户ID',
  `clientId` varchar(255) DEFAULT NULL COMMENT '客户端ID',
  `clientIp` varchar(255) DEFAULT NULL COMMENT '客户端IP',
  `clientOSVersion` varchar(255) DEFAULT NULL COMMENT '客户端系统版本',
  `clientOSArch` varchar(255) DEFAULT NULL COMMENT '客户端操作系统位数',
  `clientOSName` varchar(255) DEFAULT NULL COMMENT '客户端系统名称',
  `clientAgent` varchar(2000) DEFAULT NULL COMMENT '浏览器基本信息',
  `requestUrl` varchar(2000) DEFAULT NULL COMMENT '客户端发出请求时的完整URL',
  `requestUri` varchar(2000) DEFAULT NULL COMMENT '请求行中的资源名部分',
  `requestVerb` varchar(255) DEFAULT NULL COMMENT '客户机请求方式',
  `localAddress` varchar(255) DEFAULT NULL COMMENT 'WEB服务器的IP地址',
  `localName` varchar(255) DEFAULT NULL COMMENT 'WEB服务器的主机名',
  `sessionId` varchar(255) DEFAULT NULL COMMENT '当前会话请求的sessionId',
  `accessToken` varchar(255) DEFAULT NULL COMMENT '访问令牌',
  `refreshToken` varchar(255) DEFAULT NULL COMMENT '访问令牌',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15705 DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

-- ----------------------------
-- Table structure for up_weather_info
-- ----------------------------
DROP TABLE IF EXISTS `up_weather_info`;
CREATE TABLE `up_weather_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adcode` varchar(255) NOT NULL COMMENT '区域编码',
  `temperature` varchar(8) DEFAULT '' COMMENT '温度',
  `weather` varchar(16) DEFAULT '' COMMENT '天气现象（汉字描述）',
  `winddirection` varchar(8) DEFAULT '' COMMENT '风向，风向编码对应描述',
  `windpower` varchar(8) DEFAULT '' COMMENT '风力，此处返回的是风力编码，风力编码对应风力级别，单位：级',
  `humidity` varchar(8) DEFAULT '' COMMENT '空气湿度',
  `reporttime` datetime DEFAULT NULL COMMENT '拉取数据时间',
  `forecastTime` varchar(16) DEFAULT '' COMMENT '预报日期',
  `dayweather` varchar(16) DEFAULT '' COMMENT '白天天气现象',
  `nightweather` varchar(16) DEFAULT '' COMMENT '晚上天气现象',
  `daytemp` varchar(8) DEFAULT '' COMMENT '白天温度',
  `nighttemp` varchar(8) DEFAULT '' COMMENT '晚上温度',
  `daywind` varchar(8) DEFAULT '' COMMENT '白天风向',
  `nightwind` varchar(8) DEFAULT '' COMMENT '晚上风向',
  `daypower` varchar(8) DEFAULT '' COMMENT '白天风力',
  `nightpower` varchar(8) DEFAULT '' COMMENT '晚上风力',
  `reportType` int(11) DEFAULT '0' COMMENT '天气类型(0实时1预报)',
  `creatorUserId` bigint(20) DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1054867 DEFAULT CHARSET=utf8 COMMENT='各地区天气表';

-- ----------------------------
-- Table structure for up_weixin_access_token
-- ----------------------------
DROP TABLE IF EXISTS `up_weixin_access_token`;
CREATE TABLE `up_weixin_access_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL COMMENT 'openid',
  `unionid` varchar(255) DEFAULT NULL COMMENT 'unionid',
  `accessToken` varchar(255) NOT NULL COMMENT '访问令牌',
  `tokenExpiredSeconds` int(11) DEFAULT '0' COMMENT '令牌有效期',
  `refreshToken` varchar(255) DEFAULT NULL COMMENT '刷新令牌',
  `refreshTokenExpiredSeconds` int(11) DEFAULT '0' COMMENT '刷新令牌有效期',
  `scope` varchar(32) DEFAULT NULL COMMENT 'scope',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_accessToken` (`accessToken`)
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8 COMMENT='微信访问令牌';

-- ----------------------------
-- Table structure for up_weixin_message_log
-- ----------------------------
DROP TABLE IF EXISTS `up_weixin_message_log`;
CREATE TABLE `up_weixin_message_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(2000) DEFAULT NULL COMMENT 'ip',
  `url` varchar(2000) DEFAULT NULL COMMENT 'url',
  `messageType` varchar(255) DEFAULT NULL COMMENT '通知的类型',
  `outTradeNo` varchar(255) DEFAULT NULL COMMENT '通知校验ID',
  `outRefundNo` varchar(255) DEFAULT NULL COMMENT '支付宝分配给开发者的应用Id',
  `transactionId` varchar(255) DEFAULT NULL COMMENT '支付宝交易号',
  `refundId` varchar(255) DEFAULT NULL COMMENT '商户订单号',
  `content` text COMMENT '消息内容',
  `status` int(11) DEFAULT NULL COMMENT '消息处理状态',
  `result` varchar(2000) DEFAULT NULL COMMENT '消息处理结果说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1191 DEFAULT CHARSET=utf8 COMMENT='微信支付异步通知表';

-- ----------------------------
-- Table structure for up_weixin_pay_bill
-- ----------------------------
DROP TABLE IF EXISTS `up_weixin_pay_bill`;
CREATE TABLE `up_weixin_pay_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `outTradeNo` varchar(255) NOT NULL COMMENT '商户订单号',
  `transactionId` varchar(255) DEFAULT NULL COMMENT '微信支付订单号',
  `outRefundNo` varchar(255) DEFAULT NULL COMMENT '商户退款单号',
  `refundId` varchar(255) DEFAULT NULL COMMENT '微信退款单号',
  `openid` varchar(128) DEFAULT NULL COMMENT '用户标识',
  `deviceInfo` varchar(32) DEFAULT NULL COMMENT '设备号',
  `billDate` datetime DEFAULT NULL COMMENT '对账单日期',
  `billType` varchar(8) DEFAULT NULL COMMENT '账单类型',
  `tarType` varchar(8) DEFAULT NULL COMMENT '压缩账单',
  `tradeDate` datetime DEFAULT NULL COMMENT '交易时间',
  `tradeType` varchar(32) DEFAULT NULL COMMENT '交易类型',
  `tradeState` int(11) DEFAULT NULL COMMENT '交易状态',
  `bankType` varchar(255) DEFAULT NULL COMMENT '付款银行',
  `feeType` varchar(16) DEFAULT NULL COMMENT '货币种类',
  `totalAmount` decimal(12,2) DEFAULT NULL COMMENT '总金额',
  `couponAmount` decimal(12,2) DEFAULT NULL COMMENT '代金券或立减优惠金额',
  `refundAmount` decimal(12,2) DEFAULT NULL COMMENT '退款金额',
  `couponRefundAmount` decimal(12,2) DEFAULT NULL COMMENT '代金券或立减优惠退款金额',
  `refundType` varchar(255) DEFAULT NULL COMMENT '退款类型',
  `refundState` int(11) DEFAULT NULL COMMENT '退款状态',
  `feeRate` decimal(12,2) DEFAULT NULL COMMENT '手续费',
  `poundageRate` decimal(12,2) DEFAULT NULL COMMENT '费率',
  `productName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `body` varchar(255) DEFAULT NULL COMMENT '商户数据包',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='微信支付对账表';

-- ----------------------------
-- Table structure for up_weixin_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `up_weixin_pay_order`;
CREATE TABLE `up_weixin_pay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `outTradeNo` varchar(255) NOT NULL COMMENT '商户订单号',
  `transactionId` varchar(255) DEFAULT NULL COMMENT '微信支付订单号',
  `prepayId` varchar(255) DEFAULT NULL COMMENT '预支付交易会话标识',
  `tradeState` int(11) DEFAULT NULL COMMENT '交易状态',
  `tradeStateDesc` varchar(255) DEFAULT NULL,
  `body` varchar(255) NOT NULL COMMENT '商品描述',
  `detail` text COMMENT '商品详情',
  `attach` varchar(255) DEFAULT NULL COMMENT '附加数据',
  `tradeType` varchar(32) DEFAULT NULL COMMENT '交易类型',
  `limitPay` varchar(255) DEFAULT NULL COMMENT '指定支付方式',
  `sceneInfo` varchar(255) DEFAULT NULL COMMENT '场景信息',
  `deviceInfo` varchar(32) DEFAULT NULL COMMENT '设备号',
  `feeType` varchar(16) DEFAULT NULL COMMENT '货币类型',
  `totalFee` decimal(12,2) DEFAULT NULL COMMENT '总金额',
  `cashFee` decimal(12,2) DEFAULT NULL COMMENT '现金支付金额',
  `cashFeeType` varchar(16) DEFAULT NULL COMMENT '现金支付货币类型',
  `couponFee` int(11) DEFAULT NULL COMMENT '代金券使用数量',
  `settlementTotalFee` decimal(12,2) DEFAULT NULL COMMENT '应结订单金额',
  `spbillCreateIp` varchar(255) DEFAULT NULL COMMENT '终端IP',
  `timeStart` datetime DEFAULT NULL COMMENT '交易起始时间',
  `timeEnd` datetime DEFAULT NULL COMMENT '支付完成时间',
  `goodsTag` varchar(255) DEFAULT NULL COMMENT '订单优惠标记',
  `openid` varchar(128) DEFAULT NULL COMMENT '用户标识',
  `subscribed` char(1) DEFAULT '0' COMMENT '是否关注公众账号',
  `bankType` varchar(255) DEFAULT NULL COMMENT '付款银行',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT ' 版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8 COMMENT='微信支付订单表';

-- ----------------------------
-- Table structure for up_weixin_refund_order
-- ----------------------------
DROP TABLE IF EXISTS `up_weixin_refund_order`;
CREATE TABLE `up_weixin_refund_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `outTradeNo` varchar(255) NOT NULL COMMENT '商户订单号',
  `transactionId` varchar(255) DEFAULT NULL COMMENT '微信支付订单号',
  `outRefundNo` varchar(255) DEFAULT NULL COMMENT '商户退款单号',
  `refundId` varchar(255) DEFAULT NULL COMMENT '微信退款单号',
  `offset` int(11) DEFAULT NULL COMMENT '偏移量',
  `refundFee` decimal(12,2) DEFAULT NULL COMMENT '退款金额',
  `refundFeeType` varchar(8) DEFAULT NULL COMMENT '货币种类',
  `refundDesc` varchar(80) DEFAULT NULL COMMENT '退款原因',
  `settlementRefundFee` decimal(12,2) DEFAULT NULL COMMENT '应结退款金额',
  `totalFee` decimal(12,2) DEFAULT NULL COMMENT '标价金额 ',
  `settlementTotalFee` decimal(12,2) DEFAULT NULL COMMENT '应结订单金额',
  `feeType` varchar(30) DEFAULT NULL COMMENT '标价币种',
  `cashFee` decimal(12,2) DEFAULT NULL COMMENT '现金支付金额',
  `cashFeeType` varchar(16) DEFAULT NULL COMMENT '现金支付币种',
  `cashRefundFee` decimal(12,2) DEFAULT NULL COMMENT '现金退款金额',
  `refundStatus` int(11) DEFAULT NULL COMMENT '退款状态',
  `refundStatusDesc` varchar(20) DEFAULT NULL COMMENT '退款状态',
  `refundAccount` varchar(255) DEFAULT NULL COMMENT '退款资金来源',
  `refundReceiveAccount` varchar(255) DEFAULT NULL COMMENT '退款入账账户',
  `refundSuccessTime` datetime DEFAULT NULL COMMENT '退款成功时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  `version` bigint(20) DEFAULT '1' COMMENT ' 版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信退款订单表';

-- ----------------------------
-- Table structure for up_work_calendar
-- ----------------------------
DROP TABLE IF EXISTS `up_work_calendar`;
CREATE TABLE `up_work_calendar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL COMMENT '日期yyyy-MM-dd',
  `workDay` char(1) DEFAULT NULL COMMENT '是否工作日 0为非,1为工作日',
  `holidayId` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT '0' COMMENT '删除标记',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_date` (`date`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8 COMMENT='日历表';
