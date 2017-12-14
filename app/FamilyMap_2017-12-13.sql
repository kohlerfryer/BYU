# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.12)
# Database: FamilyMap
# Generation Time: 2017-12-14 02:51:21 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table authentication
# ------------------------------------------------------------

DROP TABLE IF EXISTS `authentication`;

CREATE TABLE `authentication` (
  `id` varchar(250) NOT NULL,
  `token` text,
  `user_id` text,
  `time_stamp` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `authentication` WRITE;
/*!40000 ALTER TABLE `authentication` DISABLE KEYS */;

INSERT INTO `authentication` (`id`, `token`, `user_id`, `time_stamp`)
VALUES
	('SIVRMG5ENIWZ9I4IE1','2TG6IOJ7I658BOV5SD','sheila','2017-12-13 19:27:20.373');

/*!40000 ALTER TABLE `authentication` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` varchar(250) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `country` text,
  `city` text,
  `year` text,
  `person_id` text,
  `type` text,
  `descendant` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;

INSERT INTO `event` (`id`, `latitude`, `longitude`, `country`, `city`, `year`, `person_id`, `type`, `descendant`)
VALUES
	('1QKL0PKJN0AZX2M66A',43.6167,-115.8,'United States','Boise','2016','Happy_Birthday','marriage','patrick'),
	('4QYKKW4JJTC8ZT57JS',52.4833,-0.1,'United Kingdom','Birmingham','2017','Betty_White','death','sheila'),
	('7VVDOG2TQMDEFJHGUP',56.1167,101.6,'Russia','Bratsk','1948','Blaine_McGary','birth','sheila'),
	('9WKYVJCLAES55QZ124',-36.1833,144.9667,'Australia','Melbourne','1970','Sheila_Parker','birth','sheila'),
	('CRX8YJ2D09V8ENY9MI',41.7667,140.7333,'Japan','Hakodate','1970','Davis_Hyer','birth','sheila'),
	('D4VHOKJE5ZR8K65KBQ',77.4667,-68.7667,'Denmark','Qaanaaq','2014','Sheila_Parker','completed asteroids','sheila'),
	('L4MQTS2XOBN9YDLBGL',40.2444,111.6608,'United States','Provo','2015','Sheila_Parker','death','sheila'),
	('MXRM2H7L7V9EK1T9BL',43.6167,-115.8,'United States','Boise','2016','Golden_Boy','marriage','patrick'),
	('O63TR5310D45GQYTEI',76.4167,-81.1,'Canada','Grise Fiord','2016','Patrick_Spencer','birth','patrick'),
	('RJBSCMZEHOVDD0GKZJ',34.05,-117.75,'United States','Los Angeles','2012','Sheila_Parker','marriage','sheila'),
	('SOWXDPKWN5ATCNA554',74.4667,-60.7667,'Denmark','Qaanaaq','2014','Sheila_Parker','COMPLETED ASTEROIDS','sheila');

/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table person
# ------------------------------------------------------------

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` varchar(250) NOT NULL,
  `first_name` text,
  `last_name` text,
  `gender` text,
  `father_id` text,
  `mother_id` text,
  `spouse_id` text,
  `descendant` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;

INSERT INTO `person` (`id`, `first_name`, `last_name`, `gender`, `father_id`, `mother_id`, `spouse_id`, `descendant`)
VALUES
	('BettyWhite','Betty','White','f',NULL,NULL,'Blaine_McGary','sheila'),
	('BlaineMcGary','Blaine','McGary','m',NULL,NULL,'Betty_White','sheila'),
	('DavisHyer','Davis','Hyer','m',NULL,NULL,'Sheila_Parker','sheila'),
	('PatrickSpencer','Patrick','Spencer','m','Happy_Birthday','Golden_Boy',NULL,'patrick'),
	('PatrickWilson','Patrick','Wilson','m',NULL,NULL,'Golden_Boy','patrick'),
	('SheilaParker','Sheila','Parker','f','Blaine_McGary','Betty_White','Davis_Hyer','sheila'),
	('SpencerSeeger','Spencer','Seeger','f',NULL,NULL,'Happy_Birthday','patrick');

/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table schema_version
# ------------------------------------------------------------

DROP TABLE IF EXISTS `schema_version`;

CREATE TABLE `schema_version` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `schema_version` WRITE;
/*!40000 ALTER TABLE `schema_version` DISABLE KEYS */;

INSERT INTO `schema_version` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`)
VALUES
	(1,'1.1','creaetTables','SQL','migrations/V1.1__creaetTables.sql',-649814035,'FamilyMapApp','2017-12-13 16:56:49',1294,1),
	(2,'1.2','conformToUglyBYUProjectStandards','SQL','migrations/V1.2__conformToUglyBYUProjectStandards.sql',1232257147,'FamilyMapApp','2017-12-13 16:56:50',726,1),
	(3,'1.3','conformToUglyBYUProjectStandards','SQL','migrations/V1.3__conformToUglyBYUProjectStandards.sql',1638797152,'FamilyMapApp','2017-12-13 16:56:56',6406,1);

/*!40000 ALTER TABLE `schema_version` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(250) NOT NULL,
  `email` text,
  `password` text,
  `first_name` text,
  `last_name` text,
  `gender` text,
  `person_id` text,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`username`, `email`, `password`, `first_name`, `last_name`, `gender`, `person_id`)
VALUES
	('patrick','patrick@spencer.com','942b9a7505b687fbf417803b120702b2','Patrick','Spencer','m','PatrickSpencer'),
	('sheila','sheila@parker.com','d0204ad84e19075f95a176b65152ffde','Sheila','Parker','f','SheilaParker');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
