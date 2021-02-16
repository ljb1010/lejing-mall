/*
 Navicat Premium Data Transfer

 Source Server         : vm-132-docker
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 192.168.40.132:33306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 16/02/2021 17:01:39
*/

#CREATE DATABASE `config_info` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (30, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', 'lejing:\n  user:\n    name: 开发环境\n    age: 35', '0d5806c04dbbd6ae2510143c332c5850', '2021-02-09 01:25:47', '2021-02-09 01:28:51', NULL, '192.168.2.1', '', '0bd2c26a-5824-4ca3-9047-34efded1fef2', '开发环境', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (31, 'lejing-coupon-test.yml', 'DEFAULT_GROUP', 'lejing:\n  user:\n    name: 测试环境\n    age: 35', '4e65c10554ac4ebf8085a902f69a4d9f', '2021-02-09 01:26:16', '2021-02-09 01:28:26', NULL, '192.168.2.1', '', '3cc8cdfb-61b2-4ad3-82d0-2afa1aacba6e', '测试环境', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (32, 'lejing-coupon-prod.yml', 'DEFAULT_GROUP', 'lejing:\n  user:\n    name: 生产环境\n    age: 35', '94682d22c6fc1f2e4a8697201b0c6d5b', '2021-02-09 01:26:55', '2021-02-09 01:28:36', NULL, '192.168.2.1', '', 'da8bf5ee-d649-4192-afb7-222dcbd63fa8', '生产环境', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (38, 'lejing-coupon.yml', 'DEFAULT_GROUP', 'lejing:\n  user:\n    name: 默认分组配置\n    age: 28', '704446a5a83d64f91cb33a9b2d402712', '2021-02-09 01:33:46', '2021-02-09 02:36:05', NULL, '192.168.2.1', '', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (42, 'lejing-coupon.yml', 'DEV', 'lejing:\n  user:\n    name: 开发环境\n    age: 666', 'be0838a331590f0aeb2fa34497f9e5a5', '2021-02-09 01:46:23', '2021-02-09 02:29:17', NULL, '192.168.2.1', '', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', '开发环境', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (43, 'lejing-coupon.yml', 'TEST', 'lejing:\n  user:\n    name: 测试环境\n    age: 28', '73fcb2eecb35e9fe4f56c4665350e8cf', '2021-02-09 01:47:05', '2021-02-09 01:48:04', NULL, '192.168.2.1', '', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', '测试环境', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (44, 'lejing-coupon.yml', 'PROD', 'lejing:\n  user:\n    name: 生产环境\n    age: 28', 'bf50bcb80bd0ac94863b2ab0273e9b8a', '2021-02-09 01:47:38', '2021-02-09 01:47:52', NULL, '192.168.2.1', '', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', '生产环境', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (48, 'datasource.yml', 'dev', 'spring:\n  datasource:\n    driverClassName: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.40.132:33306/lejing_sms?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&allowMultiQueries=true\n    username: root\n    password: 123456\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '113cc58037594ac5d15aa24c1221baf5', '2021-02-09 02:16:47', '2021-02-09 02:19:15', NULL, '192.168.2.1', '', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (52, 'mybatis.yml', 'dev', 'mybatis-plus:\r\n  mapper-locations: classpath:mapper/**/*Mapper.xml,mapper/**/*Dao.xml\r\n  type-aliases-package: com.**.coupon.domain,cn.**.coupon.domain,com.**.coupon.entity,cn.**.coupon.entity\r\n  global-config:\r\n    db-config:\r\n      id-type: auto\r\n  configuration:\r\n    map-underscore-to-camel-case: on\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '439cb3eaaef48d18440ed2b5147d0c20', '2021-02-09 02:22:47', '2021-02-09 02:22:47', NULL, '192.168.2.1', '', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', 'mybatis', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (53, 'common.yml', 'DEFAULT_GROUP', 'server:\r\n  port: 7000\r\nspring:\r\n  profiles:\r\n    active: dev\r\n  jackson:\r\n    time-zone: GMT+8\r\n    date-format: yyyy-MM-dd HH:mm:ss    \r\n#全局跨域设置\r\ncors:\r\n  config:\r\n    #是否允许所有域名跨域：true|false\r\n    is-allows-all-domain: true\r\n    #允许跨域的域名集合\r\n    allowed-origins:\r\n      - http://127.0.0.1\r\n      - http://127.0.0.1:7000\r\n      - http://api.alphahub.cn\r\n      - http://www.alphahub.cn', 'b7a88dab244bbf91f00ab2f2782892e7', '2021-02-09 02:23:38', '2021-02-09 02:23:38', NULL, '192.168.2.1', '', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', '公用', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (57, 'lejing-gateway.yml', 'DEFAULT_GROUP', 'spring:\r\n  application:\r\n    name: lejing-gateway', 'abb43386eedb2a663225d6e669460117', '2021-02-10 00:10:02', '2021-02-10 00:10:02', NULL, '192.168.2.1', '', '591b0ebc-5653-4403-ba6f-aca717e73df4', '网关配置文件', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (58, 'oss.yml', 'DEFAULT_GROUP', '#阿里云OSS参数\nalibaba:\n  cloud:\n    access-key: LTAI4GFKKZSC4w9gCLfTcAZY\n    secret-key: Vc8yXYHrVs9ioJ4AUNKFZZ4SykCTfA\n    bucket-name: alphahub-test-bucket\n    host-prefix: https://${alibaba.cloud.bucket-name}.${alibaba.cloud.oss.endpoint}\n    oss:\n      endpoint: oss-cn-shanghai.aliyuncs.com', 'd9eea02d0aaa9672e7be6656cd19c28f', '2021-02-13 22:32:50', '2021-02-14 21:44:07', NULL, '192.168.2.1', '', 'cc925dd8-8be2-4be5-b3f1-8a43ea0e6827', 'aliyun oss yml', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 18', '8118aaf675c31bfa94ce2c7325367361', '2021-02-09 00:12:26', '2021-02-09 00:12:27', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 18', '8118aaf675c31bfa94ce2c7325367361', '2021-02-09 00:16:29', '2021-02-09 00:16:30', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 3, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 18', 'cff4bc8662d43d6456f379779ae561e8', '2021-02-09 00:16:47', '2021-02-09 00:16:48', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 4, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 22', '53d9c4e0f91b2ad649aebb94124fdbb7', '2021-02-09 00:18:15', '2021-02-09 00:18:16', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 5, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 22', '53d9c4e0f91b2ad649aebb94124fdbb7', '2021-02-09 00:18:43', '2021-02-09 00:18:45', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 6, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三1\n    age: 222', '1907006a068444c1adb821fd32d84178', '2021-02-09 00:22:19', '2021-02-09 00:22:20', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 7, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 22', '3c8024d64e1e466763e409f4cd1d8fa9', '2021-02-09 00:23:30', '2021-02-09 00:23:32', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (6, 8, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 22', '3c8024d64e1e466763e409f4cd1d8fa9', '2021-02-09 00:26:16', '2021-02-09 00:26:17', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 9, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 119', 'f451d78728f44f75fdd61e6c942f0c3c', '2021-02-09 00:28:50', '2021-02-09 00:28:51', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 10, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 119', 'f451d78728f44f75fdd61e6c942f0c3c', '2021-02-09 00:32:41', '2021-02-09 00:32:42', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 11, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 119', 'f451d78728f44f75fdd61e6c942f0c3c', '2021-02-09 00:35:41', '2021-02-09 00:35:42', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 12, 'application-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 119', 'f451d78728f44f75fdd61e6c942f0c3c', '2021-02-09 00:35:52', '2021-02-09 00:35:53', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 13, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name:zhangsan\r\nlejing.user.age:18', 'e4e9ae5c2f55e225c06bf11b805258f3', '2021-02-09 00:36:17', '2021-02-09 00:36:18', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 14, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    age: 188\r\n    name: zhangsan', 'c0532ab8e537a9221aa9ae5d92e6329c', '2021-02-09 00:39:57', '2021-02-09 00:39:58', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (12, 15, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    age: 188\r\n    name: zhangsan', 'c0532ab8e537a9221aa9ae5d92e6329c', '2021-02-09 00:44:23', '2021-02-09 00:44:24', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 16, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name:zhangsan\r\nlejing.user.age:18', 'e4e9ae5c2f55e225c06bf11b805258f3', '2021-02-09 00:45:17', '2021-02-09 00:45:18', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (12, 17, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    age: 188\n    name: zhangsan', '95c77fa8861fe82f053db39684f79f1d', '2021-02-09 00:45:46', '2021-02-09 00:45:47', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (11, 18, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name:zhangsan\nlejing.user.age:188', 'a7617beb324efdda7a4f1255dcde578f', '2021-02-09 00:46:00', '2021-02-09 00:46:01', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (12, 19, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    age: 189\n    name: zhangsan', '403e9712dc047f0bf92fc1a5377db709', '2021-02-09 00:46:00', '2021-02-09 00:46:01', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 20, 'lejing-coupon-dev.yaml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: zhangsan\r\n    age: 112', '5648dc566407b2149a0e7ac63669a064', '2021-02-09 00:47:00', '2021-02-09 00:47:01', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (16, 21, 'lejing-coupon-dev.yaml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: zhangsan\r\n    age: 112', '5648dc566407b2149a0e7ac63669a064', '2021-02-09 00:48:06', '2021-02-09 00:48:07', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 22, 'lejing-coupon-dev.yaml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: zhangsan\n    age: 112', '39d4719940ae1e56e29d031f05e28221', '2021-02-09 00:51:00', '2021-02-09 00:51:01', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 23, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: zhangsan\r\n    age: 18', 'bfbae957471931eafb0b77314d774e8d', '2021-02-09 00:54:21', '2021-02-09 00:54:22', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (18, 24, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: zhangsan\r\n    age: 18', 'bfbae957471931eafb0b77314d774e8d', '2021-02-09 00:54:50', '2021-02-09 00:54:51', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 25, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name=zhangsan\r\nlejing.user.age=188', '8e21a08073470fa11ee0eae500db972f', '2021-02-09 00:55:42', '2021-02-09 00:55:43', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (18, 26, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: zhangsan\n    age: 188', '1ae31b76bf37b64fb4c8986266295997', '2021-02-09 00:56:01', '2021-02-09 00:56:02', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (20, 27, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name=zhangsan\r\nlejing.user.age=188', '8e21a08073470fa11ee0eae500db972f', '2021-02-09 00:56:01', '2021-02-09 00:56:02', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 28, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name=zhangsan\r\nlejing.user.age=188', '8e21a08073470fa11ee0eae500db972f', '2021-02-09 00:56:20', '2021-02-09 00:56:21', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (21, 29, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name=zhangsan\r\nlejing.user.age=188', '8e21a08073470fa11ee0eae500db972f', '2021-02-09 00:56:54', '2021-02-09 00:56:55', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (21, 30, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name=zhangsan\nlejing.user.age=12', '93ca97464a54b739c644a27954134e56', '2021-02-09 00:58:00', '2021-02-09 00:58:01', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (21, 31, 'lejing-coupon-dev.properties', 'DEFAULT_GROUP', '', 'lejing.user.name=zhangsan\nlejing.user.age=1312', '92e9e6b3900c3ed14edc67516ee2337b', '2021-02-09 01:07:40', '2021-02-09 01:07:41', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 32, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    age: 1312\r\n    name: zhangsan', 'd78b1fb5fa4bfa1e01fd596f7d82f463', '2021-02-09 01:09:17', '2021-02-09 01:09:19', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (24, 33, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    age: 1312\r\n    name: zhangsan', 'd78b1fb5fa4bfa1e01fd596f7d82f463', '2021-02-09 01:16:17', '2021-02-09 01:16:18', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (24, 34, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: zhangsan\n    age: 121', '3f4e9a1a858e87a86a5d0f8ca10a8651', '2021-02-09 01:16:45', '2021-02-09 01:16:46', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (24, 35, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 21', 'd81133faa88b37e047435213a39f3af8', '2021-02-09 01:18:22', '2021-02-09 01:18:23', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 36, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 28', 'd6bbb8d271297b05f3bb1f1050fafd94', '2021-02-09 01:24:19', '2021-02-09 01:24:20', NULL, '192.168.2.1', 'I', '');
INSERT INTO `his_config_info` VALUES (24, 37, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 28', '3cae87c1e570088643b32f394d13706f', '2021-02-09 01:24:28', '2021-02-09 01:24:29', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (28, 38, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 28', 'd6bbb8d271297b05f3bb1f1050fafd94', '2021-02-09 01:24:44', '2021-02-09 01:24:45', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 39, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 35', '615c3ac8f3741ad3c811872df911f3f0', '2021-02-09 01:25:46', '2021-02-09 01:25:47', NULL, '192.168.2.1', 'I', '0bd2c26a-5824-4ca3-9047-34efded1fef2');
INSERT INTO `his_config_info` VALUES (0, 40, 'lejing-coupon-test.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 35', '615c3ac8f3741ad3c811872df911f3f0', '2021-02-09 01:26:15', '2021-02-09 01:26:16', NULL, '192.168.2.1', 'I', '3cc8cdfb-61b2-4ad3-82d0-2afa1aacba6e');
INSERT INTO `his_config_info` VALUES (0, 41, 'lejing-coupon-prod.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 35', '615c3ac8f3741ad3c811872df911f3f0', '2021-02-09 01:26:53', '2021-02-09 01:26:55', NULL, '192.168.2.1', 'I', 'da8bf5ee-d649-4192-afb7-222dcbd63fa8');
INSERT INTO `his_config_info` VALUES (30, 42, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 35', '615c3ac8f3741ad3c811872df911f3f0', '2021-02-09 01:28:04', '2021-02-09 01:28:05', NULL, '192.168.2.1', 'U', '0bd2c26a-5824-4ca3-9047-34efded1fef2');
INSERT INTO `his_config_info` VALUES (31, 43, 'lejing-coupon-test.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 35', '615c3ac8f3741ad3c811872df911f3f0', '2021-02-09 01:28:25', '2021-02-09 01:28:26', NULL, '192.168.2.1', 'U', '3cc8cdfb-61b2-4ad3-82d0-2afa1aacba6e');
INSERT INTO `his_config_info` VALUES (32, 44, 'lejing-coupon-prod.yml', 'DEFAULT_GROUP', '', 'lejing:\r\n  user:\r\n    name: 张三\r\n    age: 35', '615c3ac8f3741ad3c811872df911f3f0', '2021-02-09 01:28:35', '2021-02-09 01:28:36', NULL, '192.168.2.1', 'U', 'da8bf5ee-d649-4192-afb7-222dcbd63fa8');
INSERT INTO `his_config_info` VALUES (30, 45, 'lejing-coupon-dev.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 开发环境配置文件\n    age: 35', '1edd413d28eb3ac835eeb905ba15aa1a', '2021-02-09 01:28:50', '2021-02-09 01:28:51', NULL, '192.168.2.1', 'U', '0bd2c26a-5824-4ca3-9047-34efded1fef2');
INSERT INTO `his_config_info` VALUES (28, 46, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 张三\n    age: 35', 'e9258002427fa6846bebc2eb74d00a75', '2021-02-09 01:29:08', '2021-02-09 01:29:09', NULL, '192.168.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 47, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 默认环境\n    age: 35', 'f002fcb87ae278bb8e73f281cacafe36', '2021-02-09 01:33:45', '2021-02-09 01:33:46', NULL, '192.168.2.1', 'I', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (28, 48, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 默认环境\n    age: 35', 'f002fcb87ae278bb8e73f281cacafe36', '2021-02-09 01:35:20', '2021-02-09 01:35:21', NULL, '192.168.2.1', 'D', '');
INSERT INTO `his_config_info` VALUES (38, 49, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 默认环境\n    age: 35', 'f002fcb87ae278bb8e73f281cacafe36', '2021-02-09 01:36:29', '2021-02-09 01:36:30', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (38, 50, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 会员\n    age: 35', '0d811b9211dbb464011737eb36fe71f7', '2021-02-09 01:37:29', '2021-02-09 01:37:30', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (38, 51, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 会员\n    age: 18', '2e18e1f77521066ac450e77430442202', '2021-02-09 01:42:30', '2021-02-09 01:42:31', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (0, 52, 'lejing-coupon.yml', 'DEV', '', 'lejing:\r\n  user:\r\n    name: 会员\r\n    age: 28', '4056ef04b6fad1dc25ac46aca246c2b9', '2021-02-09 01:46:22', '2021-02-09 01:46:23', NULL, '192.168.2.1', 'I', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (0, 53, 'lejing-coupon.yml', 'TEST', '', 'lejing:\r\n  user:\r\n    name: 测试\r\n    age: 28', 'eb774366555fb0d7bc063a95c9030d01', '2021-02-09 01:47:03', '2021-02-09 01:47:05', NULL, '192.168.2.1', 'I', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (0, 54, 'lejing-coupon.yml', 'PROD', '', 'lejing:\r\n  user:\r\n    name: 会员\r\n    age: 28', '4056ef04b6fad1dc25ac46aca246c2b9', '2021-02-09 01:47:37', '2021-02-09 01:47:38', NULL, '192.168.2.1', 'I', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (44, 55, 'lejing-coupon.yml', 'PROD', '', 'lejing:\r\n  user:\r\n    name: 会员\r\n    age: 28', '4056ef04b6fad1dc25ac46aca246c2b9', '2021-02-09 01:47:51', '2021-02-09 01:47:52', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (43, 56, 'lejing-coupon.yml', 'TEST', '', 'lejing:\r\n  user:\r\n    name: 测试\r\n    age: 28', 'eb774366555fb0d7bc063a95c9030d01', '2021-02-09 01:48:03', '2021-02-09 01:48:04', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (42, 57, 'lejing-coupon.yml', 'DEV', '', 'lejing:\r\n  user:\r\n    name: 会员\r\n    age: 28', '4056ef04b6fad1dc25ac46aca246c2b9', '2021-02-09 01:48:13', '2021-02-09 01:48:14', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (0, 58, 'datasource.yml', 'dev', '', 'spring:\r\n  datasource:\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://192.168.40.132:33306/lejing_sms?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&allowMultiQueries=true\r\n    username: root\r\n    password: 123456\r\nmybatis-plus:\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'b930758ab96286597db88075bfa5b89b', '2021-02-09 02:16:46', '2021-02-09 02:16:47', NULL, '192.168.2.1', 'I', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (48, 59, 'datasource.yml', 'dev', '', 'spring:\r\n  datasource:\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://192.168.40.132:33306/lejing_sms?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&allowMultiQueries=true\r\n    username: root\r\n    password: 123456\r\nmybatis-plus:\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'b930758ab96286597db88075bfa5b89b', '2021-02-09 02:18:36', '2021-02-09 02:18:37', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (48, 60, 'datasource.yml', 'dev', '', 'spring:\n  datasource:\n    driverClassName: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.40.132:333306/lejing_sms?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&allowMultiQueries=true\n    username: root\n    password: 123456\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', 'a163ad1c31805751044529c57a3aa8db', '2021-02-09 02:18:54', '2021-02-09 02:18:55', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (48, 61, 'datasource.yml', 'dev', '', 'spring:\n  datasource:\n    driverClassName: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.40.132:3306/lejing_sms?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&allowMultiQueries=true\n    username: root\n    password: 123456\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '8a16e73d19d22de691715d001e8c7aec', '2021-02-09 02:19:14', '2021-02-09 02:19:15', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (0, 62, 'mybatis.yml', 'dev', '', 'mybatis-plus:\r\n  mapper-locations: classpath:mapper/**/*Mapper.xml,mapper/**/*Dao.xml\r\n  type-aliases-package: com.**.coupon.domain,cn.**.coupon.domain,com.**.coupon.entity,cn.**.coupon.entity\r\n  global-config:\r\n    db-config:\r\n      id-type: auto\r\n  configuration:\r\n    map-underscore-to-camel-case: on\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '439cb3eaaef48d18440ed2b5147d0c20', '2021-02-09 02:22:46', '2021-02-09 02:22:47', NULL, '192.168.2.1', 'I', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (0, 63, 'common.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 7000\r\nspring:\r\n  profiles:\r\n    active: dev\r\n  jackson:\r\n    time-zone: GMT+8\r\n    date-format: yyyy-MM-dd HH:mm:ss    \r\n#全局跨域设置\r\ncors:\r\n  config:\r\n    #是否允许所有域名跨域：true|false\r\n    is-allows-all-domain: true\r\n    #允许跨域的域名集合\r\n    allowed-origins:\r\n      - http://127.0.0.1\r\n      - http://127.0.0.1:7000\r\n      - http://api.alphahub.cn\r\n      - http://www.alphahub.cn', 'b7a88dab244bbf91f00ab2f2782892e7', '2021-02-09 02:23:36', '2021-02-09 02:23:38', NULL, '192.168.2.1', 'I', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (42, 64, 'lejing-coupon.yml', 'DEV', '', 'lejing:\n  user:\n    name: 开发环境\n    age: 28', '6428d3ec06234583cfe994970007a20f', '2021-02-09 02:29:02', '2021-02-09 02:29:03', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (42, 65, 'lejing-coupon.yml', 'DEV', '', 'lejing:\n  user:\n    name: 开发环境\n    age: 2888', '99c360abe38ef0f9a418a40c2fca6905', '2021-02-09 02:29:16', '2021-02-09 02:29:17', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (38, 66, 'lejing-coupon.yml', 'DEFAULT_GROUP', '', 'lejing:\n  user:\n    name: 会员\n    age: 28', '131600a8e8bc6b4f17d98786bc20b184', '2021-02-09 02:36:04', '2021-02-09 02:36:05', NULL, '192.168.2.1', 'U', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d');
INSERT INTO `his_config_info` VALUES (0, 67, 'lejing-gateway.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  application:\r\n    name: lejing-gateway', 'abb43386eedb2a663225d6e669460117', '2021-02-10 00:10:01', '2021-02-10 00:10:02', NULL, '192.168.2.1', 'I', '591b0ebc-5653-4403-ba6f-aca717e73df4');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (4, '1', '8b27fdfc-4307-4b3e-8e5b-3f31bb9d9d8d', 'coupon', '优惠系统', 'nacos', 1612805559314, 1612805559314);
INSERT INTO `tenant_info` VALUES (5, '1', '51cb74aa-c99f-483b-8758-4f8083391413', 'member', '会员系统', 'nacos', 1612805575933, 1612805575933);
INSERT INTO `tenant_info` VALUES (6, '1', '591b0ebc-5653-4403-ba6f-aca717e73df4', 'gateway', '网关', 'nacos', 1612886865193, 1612886865193);
INSERT INTO `tenant_info` VALUES (7, '1', 'd2277150-0a8e-4b7b-aa3f-00482fadda0e', 'product', '商品服务', 'nacos', 1613120539629, 1613120539629);
INSERT INTO `tenant_info` VALUES (8, '1', 'cc925dd8-8be2-4be5-b3f1-8a43ea0e6827', 'third-party', '第三方服务', 'nacos', 1613226594383, 1613226594383);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
