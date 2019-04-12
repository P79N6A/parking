/*
Navicat MySQL Data Transfer

Source Server         : mycat@server242
Source Server Version : 50629
Source Host           : 192.168.0.242:8066
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-06-22 14:01:18
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
-- Table structure for sms_client
-- ----------------------------
DROP TABLE IF EXISTS `sms_client`;
CREATE TABLE `sms_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(64) NOT NULL COMMENT '客户端ID',
  `clientSecrete` varchar(255) NOT NULL COMMENT '客户端秘钥',
  `clientName` varchar(255) NOT NULL COMMENT '客户端名称',
  `channelProvider` varchar(255) DEFAULT NULL COMMENT '通道提供程序',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_clientId` (`clientId`),
  KEY `idx_clientId` (`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='短信客户端表';

-- ----------------------------
-- Table structure for sms_content
-- ----------------------------
DROP TABLE IF EXISTS `sms_content`;
CREATE TABLE `sms_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(64) NOT NULL COMMENT '客户端ID',
  `templateId` varchar(64) NOT NULL COMMENT '模板ID',
  `phoneNumber` varchar(255) DEFAULT NULL COMMENT '手机号',
  `content` varchar(255) DEFAULT NULL COMMENT '短信内容',
  `status` int(11) DEFAULT NULL COMMENT '发送状态',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_clientId` (`clientId`),
  KEY `idx_templateId` (`templateId`),
  KEY `idx_phoneNumber` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=932 DEFAULT CHARSET=utf8 COMMENT='短信内容表';

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(64) NOT NULL COMMENT '客户端ID',
  `templateId` varchar(64) NOT NULL COMMENT '模板ID',
  `templateName` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `signName` varchar(255) DEFAULT NULL COMMENT '签名名称',
  `type` varchar(10) DEFAULT NULL COMMENT '模板类型',
  `content` varchar(2000) DEFAULT NULL COMMENT '模板内容',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_templateId` (`templateId`),
  KEY `idx_clientId` (`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='组织表';

-- ----------------------------
-- Table structure for sms_verifycode
-- ----------------------------
DROP TABLE IF EXISTS `sms_verifycode`;
CREATE TABLE `sms_verifycode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(64) NOT NULL COMMENT '客户端ID',
  `templateId` varchar(64) NOT NULL COMMENT '模板ID',
  `verifyType` int(11) DEFAULT NULL COMMENT '验证码类型',
  `phoneNumber` varchar(255) DEFAULT NULL COMMENT '手机号',
  `verifyCode` varchar(2000) DEFAULT NULL COMMENT '验证码',
  `expiredSeconds` varchar(2000) DEFAULT NULL COMMENT '过期时间(s)',
  `status` int(11) DEFAULT NULL COMMENT '状态 0、正常 1、禁用',
  `used` int(11) DEFAULT NULL COMMENT '使用状态',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `creatorUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL COMMENT '更新者',
  `LastModificationTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleterUserId` bigint(20) DEFAULT NULL COMMENT '删除者',
  `deletionTime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_clientId` (`clientId`),
  KEY `idx_templateId` (`templateId`),
  KEY `idx_phoneNumber` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=929 DEFAULT CHARSET=utf8 COMMENT='验证码表';
