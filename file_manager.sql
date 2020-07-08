/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : file_manager

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 08/07/2020 17:43:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级部门id',
  `level` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '部门层级',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后一次操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '管理部门', 0, '1', '管理部门介绍', '123', '2020-07-08 16:52:43');
INSERT INTO `dept` VALUES (2, '技术部', 3, '', '23213', '123', '2020-07-08 17:23:38');
INSERT INTO `dept` VALUES (3, '4254', 4, '', '456', '123', '2020-07-08 13:00:19');
INSERT INTO `dept` VALUES (4, '1232', NULL, '1', 'fdsf', '123', '2020-07-08 12:58:52');

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件路径',
  `abstr` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文件摘要',
  `type` int(10) NOT NULL COMMENT '文件类别',
  `size` bigint(11) NOT NULL COMMENT '文件大小',
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '发布者',
  `publisher_dept` int(11) NOT NULL COMMENT '发布部门',
  `publisher_date` datetime(0) NOT NULL COMMENT '发布日期',
  `perm_id` int(11) NOT NULL COMMENT '文件访问权限',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '文件浏览次数',
  `enable_down` int(2) NULL DEFAULT 1 COMMENT '文件能否被下载',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document
-- ----------------------------
INSERT INTO `document` VALUES (1, '213', 'D:\\upload\\\\.gitconfig', '213', 2, 56, '123', 1, '2020-07-08 16:08:00', 1, NULL, NULL);
INSERT INTO `document` VALUES (3, '213', 'D:\\upload\\\\.gitconfig', '213', 2, 56, '123', 1, '2020-07-08 16:09:44', 1, NULL, NULL);
INSERT INTO `document` VALUES (4, '参考文档', 'D:\\upload\\\\.bash_history', '说明书', 1, 618, '123', 1, '2020-07-08 16:49:17', 4, NULL, NULL);
INSERT INTO `document` VALUES (5, 'gfhgfh', 'D:\\upload\\\\log4j-1.2.17.pom', 'fghfgh', 1, 21745, '123', 1, '2020-07-08 17:25:12', 4, NULL, NULL);

-- ----------------------------
-- Table structure for document_comment
-- ----------------------------
DROP TABLE IF EXISTS `document_comment`;
CREATE TABLE `document_comment`  (
  `id` int(11) NOT NULL,
  `file_id` int(11) NOT NULL COMMENT '文件的id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论的内容',
  `date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评论的时间',
  `user_id` int(11) NOT NULL COMMENT '评论的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document_comment
-- ----------------------------

-- ----------------------------
-- Table structure for document_type
-- ----------------------------
DROP TABLE IF EXISTS `document_type`;
CREATE TABLE `document_type`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document_type
-- ----------------------------
INSERT INTO `document_type` VALUES (1, '法律法规');
INSERT INTO `document_type` VALUES (2, '工作规范');
INSERT INTO `document_type` VALUES (3, '工作报表');
INSERT INTO `document_type` VALUES (4, '制度管理');

-- ----------------------------
-- Table structure for document_view_perm
-- ----------------------------
DROP TABLE IF EXISTS `document_view_perm`;
CREATE TABLE `document_view_perm`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document_view_perm
-- ----------------------------
INSERT INTO `document_view_perm` VALUES (1, '部门共享');
INSERT INTO `document_view_perm` VALUES (2, '部门及上级共享');
INSERT INTO `document_view_perm` VALUES (3, '部门及下级共享');
INSERT INTO `document_view_perm` VALUES (4, '完全共享');
INSERT INTO `document_view_perm` VALUES (5, '私有');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `perm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `perm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限名称',
  `perm_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限URL',
  PRIMARY KEY (`perm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名字',
  `telephone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `dept_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户部门id',
  `valid` tinyint(3) NOT NULL DEFAULT 1 COMMENT '用户的状态 1 ：正常  0：冻结',
  `operator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作人',
  `operator_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '232323344', '8eda1535b635e6b2c3672cc502249fe0', '', 1, 1, '', '2020-07-08 17:24:39', 1);
INSERT INTO `user` VALUES ('4234', '345435', 'rtrter', '', 3, 1, '', '2020-07-07 17:11:00', 2);
INSERT INTO `user` VALUES ('879', '879879', '67856', '5756', 3, 1, '', '2020-07-07 17:11:11', 3);
INSERT INTO `user` VALUES ('dsfds', '12542836', 'r6546546ty', '', 1, 0, '', '2020-07-08 17:24:37', 4);
INSERT INTO `user` VALUES ('sxcvvdf', '454353465', 'vgcbvc', '', 4, 1, '', '2020-07-07 17:08:49', 5);
INSERT INTO `user` VALUES ('zhangsan', '234234', '45435', '', 2, 1, '', '2020-07-07 17:08:25', 6);
INSERT INTO `user` VALUES ('324', '324', '23423', NULL, 1, 0, '123', '2020-07-08 17:24:41', 7);
INSERT INTO `user` VALUES ('dsfdsf', '123', 'dsfdsf', 'wewfdsfdsf', 3, 1, '123', '2020-07-08 11:12:33', 8);
INSERT INTO `user` VALUES ('567657', '768', '567658', '678', 1, 1, '123', '2020-07-08 11:14:22', 9);
INSERT INTO `user` VALUES ('32r4354', '435', '34543', '345', 4, 1, '123', '2020-07-08 11:16:49', 11);
INSERT INTO `user` VALUES ('1232', '123', '16d381429aebb69981f7600d0a2c3e33', '123', 1, 1, '123', '2020-07-08 11:21:39', 12);

SET FOREIGN_KEY_CHECKS = 1;
