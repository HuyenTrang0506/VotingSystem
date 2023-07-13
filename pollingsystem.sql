/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100427
 Source Host           : localhost:3306
 Source Schema         : pollingsystem

 Target Server Type    : MySQL
 Target Server Version : 100427
 File Encoding         : 65001

 Date: 13/07/2023 21:36:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for choice
-- ----------------------------
DROP TABLE IF EXISTS `choice`;
CREATE TABLE `choice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `poll_id` int NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pF_poll_id`(`poll_id` ASC) USING BTREE,
  CONSTRAINT `pF_poll_id` FOREIGN KEY (`poll_id`) REFERENCES `poll` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of choice
-- ----------------------------
INSERT INTO `choice` VALUES (46, 1, 'Ông A');
INSERT INTO `choice` VALUES (47, 1, 'Ông C');
INSERT INTO `choice` VALUES (48, 1, 'Ông D');
INSERT INTO `choice` VALUES (52, 2, 'Trang');
INSERT INTO `choice` VALUES (53, 2, 'Nhân');
INSERT INTO `choice` VALUES (54, 2, 'Duy');
INSERT INTO `choice` VALUES (55, 2, 'Tâm');
INSERT INTO `choice` VALUES (56, 3, 'A');
INSERT INTO `choice` VALUES (57, 3, 'B');
INSERT INTO `choice` VALUES (58, 3, 'C');
INSERT INTO `choice` VALUES (63, 4, 'a');
INSERT INTO `choice` VALUES (64, 4, 'b');
INSERT INTO `choice` VALUES (65, 4, 'c');
INSERT INTO `choice` VALUES (66, 4, 'd');

-- ----------------------------
-- Table structure for poll
-- ----------------------------
DROP TABLE IF EXISTS `poll`;
CREATE TABLE `poll`  (
  `id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `max_choices` int NULL DEFAULT NULL,
  `max_voters` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of poll
-- ----------------------------
INSERT INTO `poll` VALUES (1, 'Bầu trưởng ban', '', '2023-07-12 08:40:00', '2023-07-12 08:49:27', 3, 3);
INSERT INTO `poll` VALUES (2, 'Bầu lớp trưởng ', 'bỏ phiếu của 22GIT1', '2023-07-12 09:32:00', '2023-07-13 08:30:00', 4, 4);
INSERT INTO `poll` VALUES (3, 'Cuộc bỏ phiếu số 3', '', '2023-07-12 09:41:00', '2023-07-13 09:40:00', 3, 3);
INSERT INTO `poll` VALUES (4, 'số 4', '', '2023-07-13 13:45:00', '2023-07-14 13:45:00', 4, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `decentralize` tinyint NULL DEFAULT NULL,
  `isActive` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'HuyenTrang0506', 'e10adc3949ba59abbe56e057f20f883e', 'trangnh.22git@vku.udn.vn', '0962225469', 1, 'active');
INSERT INTO `user` VALUES (2, 'QuocBao', 'e10adc3949ba59abbe56e057f20f883e', 'baohq.22git@vku.udn.vn', '0948151518', 2, 'active');
INSERT INTO `user` VALUES (3, 'TriDung', 'e10adc3949ba59abbe56e057f20f883e', 'dunghct.22git@vku.udn.vn', '0848305559', 2, 'active');
INSERT INTO `user` VALUES (4, 'DinhPhuc', 'e10adc3949ba59abbe56e057f20f883e', 'phucld.22git@vku.udn.vn', '0777911807', 2, 'active');
INSERT INTO `user` VALUES (5, 'DucDat', 'e10adc3949ba59abbe56e057f20f883e', 'datnd.22git@vku.udn.vn', '0826329970', 2, 'active');
INSERT INTO `user` VALUES (6, 'DinhTrung', 'e10adc3949ba59abbe56e057f20f883e', 'trungbd.22git@vku.udn.vn	', '0981313069', 2, 'active');
INSERT INTO `user` VALUES (7, 'VanDuc', 'e10adc3949ba59abbe56e057f20f883e', 'duclv.22git@vku.udn.vn	', '0905879890', 2, 'active');
INSERT INTO `user` VALUES (8, 'HoangGiang', 'e10adc3949ba59abbe56e057f20f883e', 'giangnh.22git@vku.udn.vn', '0905317713', 2, 'active');
INSERT INTO `user` VALUES (9, 'HoHuy', 'e10adc3949ba59abbe56e057f20f883e', 'huyht.22git@vku.udn.vn	', '0763619452', 2, 'active');
INSERT INTO `user` VALUES (10, 'NgocHuy', 'e10adc3949ba59abbe56e057f20f883e', 'huytn.22git@vku.udn.vn	', '0819598762', 2, 'active');
INSERT INTO `user` VALUES (11, 'CongNguyen', 'e10adc3949ba59abbe56e057f20f883e', 'nguyenlc.22git@vku.udn.vn	', '0348131802', 2, 'active');
INSERT INTO `user` VALUES (12, 'BaoNhan', 'e10adc3949ba59abbe56e057f20f883e', 'nhanhsb.22git@vku.udn.vn', '0845562771', 2, 'active');
INSERT INTO `user` VALUES (13, 'NhatQuynh', 'e10adc3949ba59abbe56e057f20f883e', 'quynhlhn.22git@vku.udn.vn	', '0795286852', 2, 'active');
INSERT INTO `user` VALUES (14, 'QuyenAnh', 'e10adc3949ba59abbe56e057f20f883e', 'anhtq.22git@vku.udn.vn', '0854326557', 2, 'active');
INSERT INTO `user` VALUES (15, 'HoangThanh', 'e10adc3949ba59abbe56e057f20f883e', 'thanhtch.22git@vku.udn.vn	', '0931959195', 2, 'active');
INSERT INTO `user` VALUES (16, 'HieuNhan', 'e10adc3949ba59abbe56e057f20f883e', 'nhanth.22git@vku.udn.vn	', '0929040304', 2, 'active');
INSERT INTO `user` VALUES (17, 'PhuThinh', 'e10adc3949ba59abbe56e057f20f883e', 'thinhnp.22git@vku.udn.vn', '0941306345', 2, 'active');
INSERT INTO `user` VALUES (18, 'HongDuc', 'e10adc3949ba59abbe56e057f20f883e', 'ducvh.22git@vku.udn.vn', '0944480204', 2, 'active');
INSERT INTO `user` VALUES (19, 'LeHuy', 'e10adc3949ba59abbe56e057f20f883e', 'huytl.22git@vku.udn.vn', '0707085732', 2, 'active');
INSERT INTO `user` VALUES (20, 'ThiLan', 'e10adc3949ba59abbe56e057f20f883e', 'lannt.22git@vku.udn.vn', '0369003049', 2, 'active');
INSERT INTO `user` VALUES (21, 'VanMinh', 'e10adc3949ba59abbe56e057f20f883e', 'minhvv.22git@vku.udn.vn', '0968435472', 2, 'active');

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `poll_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `choice_id` int NULL DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PK_poll_id`(`poll_id` ASC) USING BTREE,
  INDEX `PK_user_id`(`user_id` ASC) USING BTREE,
  INDEX `PK_choice_id`(`choice_id` ASC) USING BTREE,
  CONSTRAINT `PK_choice_id` FOREIGN KEY (`choice_id`) REFERENCES `choice` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `PK_poll_id` FOREIGN KEY (`poll_id`) REFERENCES `poll` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `PK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES (3, 1, 1, 46, '2023-07-12 08:40:22');
INSERT INTO `vote` VALUES (5, 1, 3, 46, '2023-07-12 08:47:14');
INSERT INTO `vote` VALUES (6, 1, 5, 48, '2023-07-12 08:47:48');
INSERT INTO `vote` VALUES (7, 2, 2, 53, '2023-07-12 09:36:44');
INSERT INTO `vote` VALUES (8, 2, 1, 52, '2023-07-12 09:36:55');

-- ----------------------------
-- Table structure for voter
-- ----------------------------
DROP TABLE IF EXISTS `voter`;
CREATE TABLE `voter`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `poll_id` int NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PK_userID`(`user_id` ASC) USING BTREE,
  INDEX `PK_pollID`(`poll_id` ASC) USING BTREE,
  CONSTRAINT `PK_pollID` FOREIGN KEY (`poll_id`) REFERENCES `poll` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `PK_userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of voter
-- ----------------------------
INSERT INTO `voter` VALUES (51, 1, 1, 'HuyenTrang0506');
INSERT INTO `voter` VALUES (53, 3, 1, 'TriDung');
INSERT INTO `voter` VALUES (55, 5, 1, 'DucDat');
INSERT INTO `voter` VALUES (56, 1, 2, 'HuyenTrang0506');
INSERT INTO `voter` VALUES (59, 6, 2, 'DinhTrung');
INSERT INTO `voter` VALUES (60, 8, 2, 'HoangGiang');
INSERT INTO `voter` VALUES (61, 10, 2, 'NgocHuy');
INSERT INTO `voter` VALUES (62, 1, 3, 'HuyenTrang0506');
INSERT INTO `voter` VALUES (63, 2, 3, 'QuocBao');
INSERT INTO `voter` VALUES (64, 3, 3, 'TriDung');
INSERT INTO `voter` VALUES (69, 1, 4, 'HuyenTrang0506');
INSERT INTO `voter` VALUES (70, 3, 4, 'TriDung');
INSERT INTO `voter` VALUES (71, 6, 4, 'DinhTrung');

SET FOREIGN_KEY_CHECKS = 1;
