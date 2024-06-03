
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

drop table if exists administrator;
drop table if exists category;
drop table if exists computer;
drop table if exists customer;
drop table if exists section;
drop table if exists vipc;

-- ----------------------------
-- Table structure for arrange
-- ----------------------------
DROP TABLE IF EXISTS `arrange`;
CREATE TABLE `arrange`  (
  `comid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `customerid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stime` datetime NOT NULL,
  INDEX `movarr`(`comid` ASC) USING BTREE,
  INDEX `customerarr`(`customerid` ASC) USING BTREE,
  CONSTRAINT `customerarr` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comarr` FOREIGN KEY (`comid`) REFERENCES `computer` (`comid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of arrange
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('c00', '联想');
INSERT INTO `category` VALUES ('c01', '华硕');
INSERT INTO `category` VALUES ('c02', '小米');
INSERT INTO `category` VALUES ('c03', '华为');
INSERT INTO `category` VALUES ('c04', '苹果');
INSERT INTO `category` VALUES ('c05', '宏碁');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customerid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `customername` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`customerid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('#01', '哆啦A梦');
INSERT INTO `customer` VALUES ('#02', '光头强');
INSERT INTO `customer` VALUES ('#03', '阿尼亚');
INSERT INTO `customer` VALUES ('#04', '芙莉莲');

-- ----------------------------
-- Table structure for computer
-- ----------------------------
DROP TABLE IF EXISTS `computer`;
CREATE TABLE `computer`  (
  `comid` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `comname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cid` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `buydate` date NOT NULL,
  `runtime` smallint NOT NULL,
  PRIMARY KEY (`comid`) USING BTREE,
  INDEX `comc`(`cid` ASC) USING BTREE,
  CONSTRAINT `comc` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of computer
-- ----------------------------
INSERT INTO `computer` VALUES ('#01', '神机', 'c03', '2023-01-01', 120);
INSERT INTO `computer` VALUES ('#02', '砖石', 'c00', '2023-05-31', 120);
INSERT INTO `computer` VALUES ('#05', '拉吉', 'c01', '2023-05-20', 120);
INSERT INTO `computer` VALUES ('#07', '水杯', 'c00', '2023-09-01', 120);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pw` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('admin', 'admin');
INSERT INTO `administrator` VALUES ('mumian', '20040220');

-- ----------------------------
-- Table structure for vipc
-- ----------------------------
DROP TABLE IF EXISTS `vipc`;
CREATE TABLE `vipc`  (
  `customerid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `clike` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `customerid`(`customerid` ASC) USING BTREE,
  CONSTRAINT `customerid` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vipc
-- ----------------------------
INSERT INTO `vipc` VALUES ('#03', '坚果');

-- ----------------------------
-- View structure for arr
-- ----------------------------
DROP VIEW IF EXISTS `arr`;
CREATE VIEW `arr` AS
    (select comname,cname,customerid,stime,arrange.comid
     from arrange
         left join computer on arrange.comid=computer.comid
         left join category on computer.cid=category.cid) ;

-- ----------------------------
-- View structure for index_com
-- ----------------------------
DROP VIEW IF EXISTS `index_com`;
CREATE VIEW `index_com` AS
    (select comid,comname,computer.cid,cname,buydate,runtime
    from computer left join category on computer.cid=category.cid) ;

-- ----------------------------
-- Procedure structure for addcom
-- ----------------------------
DROP PROCEDURE IF EXISTS addcom;
delimiter ;;
CREATE PROCEDURE addcom(
    in mycomid varchar(5),
	in mycomname varchar(20),
	in mycid varchar(5),
    in mycname varchar(10),
	in mybuydate DATE,
	in myruntime SMALLINT,
	out rtn int)
BEGIN
	if EXISTS (select * from category where cid=mycid COLLATE utf8mb4_0900_ai_ci) then #电脑类型存在
		BEGIN
			if mycname in (select cname from category where cid=mycid COLLATE utf8mb4_0900_ai_ci) then #电脑类型需要存在才能添加
			    BEGIN
					if EXISTS (select * from computer where comid=mycomid COLLATE utf8mb4_0900_ai_ci) then#电脑若存在则更新
						BEGIN
							if mycomname in (select comname from computer where comid=mycomid COLLATE utf8mb4_0900_ai_ci) then
								BEGIN
									UPDATE computer set cid=mycid,buydate=mybuydate,runtime=myruntime where comid=mycomid;
									set rtn=1;
								END;
							ELSE
								BEGIN
									set rtn=2;
								END;
							end if;
						END;
					ELSE#电脑若不存在则插入
						BEGIN
							INSERT into computer values (mycomid,mycomname,mycid,mybuydate,myruntime);
							set rtn=3;
						END;
					end if;
				END;

			ELSE
			    BEGIN
					set rtn=0;
				END;
			END if;
		END;
	ELSE#电脑类型不存在
		BEGIN
			insert into category values (mycid,mycname);#电脑类型不存在则添加电脑类型
			if EXISTS (select * from computer where comid=mycomid COLLATE utf8mb4_0900_ai_ci) then
				BEGIN
					if mycomname in (select comname from computer where comid=mycomid COLLATE utf8mb4_0900_ai_ci) then
						BEGIN
							UPDATE computer set cid=mycid,buydate=mybuydate,runtime=myruntime where comid=mycomid;
							set rtn=1;
						END;
					ELSE
						BEGIN
							set rtn=2;
						END;
					end if;
				END;
			ELSE
				BEGIN
					INSERT into computer values (mycomid,mycomname,mycid,mybuydate,myruntime);
					set rtn=3;
				END;
			end if;
		END;
	end if;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table arrange
-- ----------------------------
DROP TRIGGER IF EXISTS `addarr`;
delimiter ;;
CREATE TRIGGER `addarr` BEFORE INSERT ON `arrange` FOR EACH ROW
BEGIN
if NEW.comid not in (select comid from computer)
or NEW.customerid not in (select customerid from customer)
or EXISTS (select * from (select arrange.comid,customerid,stime,(DATE_ADD(stime,INTERVAL runtime MINUTE))as endtime
                          from arrange left join computer on arrange.comid = computer.comid) t1
                    where new.stime BETWEEN t1.stime and t1.endtime)#新加入的安排在旧电脑中有重复
THEN
SIGNAL SQLSTATE 'HY000'; #一种异常
end if;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table movie
-- ----------------------------
DROP TRIGGER IF EXISTS `computertrigger`;
delimiter ;;
CREATE TRIGGER `computertrigger` BEFORE INSERT ON `computer` FOR EACH ROW begin
if NEW.cid not in (select cid from category) or NEW.comid = '' or NEW.comname = ''
THEN
SIGNAL SQLSTATE 'HY000';
end if;
end
;;
delimiter ;

# ALTER TABLE category CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
# ALTER TABLE computer CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
#
# ALTER TABLE arrange CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
# ALTER TABLE vipc CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

show variables where Variable_name like 'collation%';

SET FOREIGN_KEY_CHECKS = 1;
