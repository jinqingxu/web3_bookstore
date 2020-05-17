/*
 Navicat MySQL Data Transfer

 Source Server         : bookstore2
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : mydb

 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 05/27/2017 13:58:18 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `bank`
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `bank`
-- ----------------------------
BEGIN;
INSERT INTO `bank` VALUES ('fname2', '111'), ('fname1', '111');
COMMIT;

-- ----------------------------
--  Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(20) DEFAULT NULL,
  `price` float(7,2) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `stockbalance` int(11) DEFAULT NULL,
  `publishhouse` varchar(20) DEFAULT NULL,
  `photopath` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `mylike` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookname` (`bookname`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `book`
-- ----------------------------
BEGIN;
INSERT INTO `book` VALUES ('1', 'english', '33.50', 'study|english', '82', 'shanghai education', 'images/english.jpg', ' a great book for student to learn english.', '25', '29'), ('2', 'math', '30.50', 'study|math', '1', 'shanghai education', 'images/math.jpg', ' a great book for student to learn math.', '13', '16'), ('3', 'chinese', '36.00', 'study|chinese', '1', 'beijing education', 'images/chinese.jpg', ' a great book for student to learn chinese.', '3', '6'), ('4', 'chinese chess', '25.00', 'other|skill', '1', 'beijing education', 'images/chess.jpg', ' a great book for student to learn chess.', '2', '2'), ('5', 'japan', '34.00', 'travel|', '1', 'jiangxi education', 'images/japan.jpg', 'a great book for student to know about japan.', '0', '0'), ('6', 'france', '30.50', 'travel|', '0', 'jiangxi education', 'images/france.jpg', ' a great book for student to know about france.', '6', '8'), ('7', 'java', '36.00', 'other|skill', '0', 'shanghai education', 'images/java.jpg', ' a great book for student to know about java.', '3', '3'), ('8', 'gone with the wind', '40.00', 'other|novel', '74', 'beijing education', 'images/novel.jpg', ' a great book for student to know about novel.', '17', '17');
COMMIT;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `action` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `log`
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES ('32', 'fname1', '2017-05-17 12:55:30', 'pay', 'fail'), ('33', 'fname1', '2017-05-17 12:55:44', 'pay', 'success'), ('34', 'fname1', '2017-05-17 12:56:11', 'insertorder', 'success'), ('35', 'fname1', '2017-05-17 12:57:14', 'insertorder', 'fail'), ('36', 'fname1', '2017-05-17 12:58:01', 'insertorder', 'fail'), ('37', 'fname1', '2017-05-17 13:01:12', 'insertorder', 'success'), ('38', 'fname1', '2017-05-18 08:45:03', 'insertorder', 'success'), ('39', 'fname1', '2017-05-18 08:54:14', 'insertorder', 'success'), ('40', 'fname1', '2017-05-25 00:12:15', 'insertorder', 'fail'), ('41', 'fname1', '2017-05-25 00:12:31', 'insertorder', 'success'), ('42', 'fname1', '2017-05-25 18:15:30', 'insertorder', 'success'), ('43', 'fname1', '2017-05-25 18:15:46', 'pay', 'fail'), ('44', 'fname1', '2017-05-25 19:04:34', 'insertorder', 'success'), ('45', 'fname1', '2017-05-25 19:04:42', 'pay', 'fail');
COMMIT;

-- ----------------------------
--  Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `orderid` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bid` (`bookid`),
  CONSTRAINT `bid` FOREIGN KEY (`bookid`) REFERENCES `book` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `orderitem`
-- ----------------------------
BEGIN;
INSERT INTO `orderitem` VALUES ('63', '1', '1', 'fname12017-05-10 15:00:27.053'), ('64', '7', '1', 'fname12017-05-10 15:01:53.798'), ('98', '8', '1', 'fname12017-05-18 08:53:12.032'), ('99', '8', '1', 'fname12017-05-25 00:12:31.614'), ('100', '6', '1', 'fname12017-05-25 18:15:29.772'), ('101', '8', '1', 'fname12017-05-25 19:04:34.022');
COMMIT;

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `sumprice` double DEFAULT NULL,
  `sumnumber` int(11) DEFAULT NULL,
  `orderid` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `orders`
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES ('108', 'fname1', '2017-05-10 15:00:27', '33.5', '1', 'fname12017-05-10 15:00:27.053'), ('109', 'fname1', '2017-05-10 15:01:53', '36', '1', 'fname12017-05-10 15:01:53.798'), ('146', 'fname1', '2017-05-18 08:53:12', '40', '1', 'fname12017-05-18 08:53:12.032'), ('147', 'fname1', '2017-05-25 00:12:31', '40', '1', 'fname12017-05-25 00:12:31.614'), ('148', 'fname1', '2017-05-25 18:15:29', '30.5', '1', 'fname12017-05-25 18:15:29.772'), ('149', 'fname1', '2017-05-25 19:04:34', '40', '1', 'fname12017-05-25 19:04:34.022');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `roleid` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'fname1', '13127875598', 'name1@gmail.com', 'xu9659', '1', '110'), ('2', 'fname2', '12345678567', 'name2@gmail.com', '111', '2', '4'), ('3', 'fname3', '87654321324', 'name3@gmail.com', '111', '3', '0'), ('4', 'fname4', '23213213223', 'name4@gmail.com', '111', '4', '1'), ('5', 'fname5', '55567789923', 'name5@gmail.com', '111', '5', '0'), ('6', 'fname6', '78563424356', 'name6@gmail.com', '123', '6', '0'), ('7', 'fname7', '89765635467', 'name7@gmail.com', '1234', '7', '0'), ('8', 'fname8', '90235627897', 'name8@gmail.com', '134', '8', '0'), ('9', 'fname9', '90235768657', 'name9@gmail.com', '111', '9', '0'), ('10', 'fname10', '26379078836', 'name10@gmail.com', '2343', '2', '0');
COMMIT;

-- ----------------------------
--  Procedure structure for `statisticbybook`
-- ----------------------------
DROP PROCEDURE IF EXISTS `statisticbybook`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `statisticbybook`(
	  IN  thebookname VARCHAR (10),
    OUT sumprice  DOUBLE,
	 OUT sumorder  INTEGER,
	 OUT sumnumber  INTEGER
)
BEGIN
	SELECT
		sum(price * number) AS sumprice,
		count(orderid) AS sumorder,
		sum(number) AS sumnumber
	FROM
		book,
		orderitem
	WHERE
		book.id = orderitem.bookid
	AND book.bookname = thebookname;


END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `statisticbycategory`
-- ----------------------------
DROP PROCEDURE IF EXISTS `statisticbycategory`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `statisticbycategory`(
	IN NAME VARCHAR (10),
	OUT sumprice DOUBLE,
	OUT sumorder INTEGER,
	OUT sumnumber INTEGER
)
    DETERMINISTIC
BEGIN
	SELECT
		sum(price * number) AS sumprice,
		count(orderid) AS sumorder,
		sum(number) AS sumnumber
	FROM
		book,
		orderitem
	WHERE
		book.id = orderitem.bookid
	AND category LIKE  CONCAT('%',NAME,'%');


END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `statisticbydate`
-- ----------------------------
DROP PROCEDURE IF EXISTS `statisticbydate`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `statisticbydate`(
	IN start date,
	IN end date,
	OUT sumprice DOUBLE,
	OUT sumorder INTEGER,
	OUT sumnumber INTEGER
)
BEGIN
	SELECT
		sum(price * number) AS sumprice,
		count(orders.orderid) AS sumorder,
		sum(number) AS sumnumber
	FROM
		orders,
		orderitem,
		book
	WHERE
		orders.orderid=orderitem.orderid 
	AND orderitem.bookid=book.id
	AND orders.date >=start
	AND orders.date<=end;


END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `statisticbyuser`
-- ----------------------------
DROP PROCEDURE IF EXISTS `statisticbyuser`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `statisticbyuser`(
	 IN theusername  VARCHAR (10),
	 OUT sumprice  DOUBLE,
	 OUT sumorder  INTEGER,
	 OUT sumnumber  INTEGER
)
BEGIN
	SELECT
		sum(price * number) AS sumprice,
		count(orderid) AS sumorder,
		sum(number) AS sumnumber
	FROM
		(
			SELECT
				bookid,
				number,
				orderitem.orderid
			FROM
				orders,
				orderitem
			WHERE
				orders.orderid = orderitem.orderid
			AND username = theusername
		) AS a,
		book
	WHERE
		a.bookid = book.id;


END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
