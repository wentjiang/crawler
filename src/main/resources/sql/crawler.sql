/*
Navicat MySQL Data Transfer

Source Server         : 本机数据库
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : crawler

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-12-09 14:39:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_book
-- ----------------------------
DROP TABLE IF EXISTS `db_book`;
CREATE TABLE `db_book` (
  `id` bigint(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publishing_house` varchar(255) NOT NULL,
  `publish_date` varchar(0) DEFAULT NULL,
  `average_star` float DEFAULT NULL,
  `start1` float DEFAULT NULL,
  `start2` float DEFAULT NULL,
  `start3` float DEFAULT NULL,
  `start4` float DEFAULT NULL,
  `start5` float DEFAULT NULL,
  `comment_num` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_book_tag
-- ----------------------------
DROP TABLE IF EXISTS `db_book_tag`;
CREATE TABLE `db_book_tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(32) NOT NULL,
  `describe` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_book_tag_mapping
-- ----------------------------
DROP TABLE IF EXISTS `db_book_tag_mapping`;
CREATE TABLE `db_book_tag_mapping` (
  `book_id` bigint(20) NOT NULL,
  `book_tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`book_tag_id`),
  KEY `book_id` (`book_id`),
  KEY `book_tag_id` (`book_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
