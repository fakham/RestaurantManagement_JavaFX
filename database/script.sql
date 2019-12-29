# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.5.5-10.4.10-MariaDB)
# Database: restaurant
# Generation Time: 2019-12-29 22:13:42 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table COMMAND_DETAILS
# ------------------------------------------------------------

CREATE TABLE `COMMAND_DETAILS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_ITEM` int(11) NOT NULL,
  `ID_COMMAND` int(11) NOT NULL,
  `PRICE` float DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMMAND_DETAILS` (`ID_COMMAND`),
  KEY `FK_COMMAND_DETAILS2` (`ID_ITEM`),
  CONSTRAINT `FK_COMMAND_DETAILS` FOREIGN KEY (`ID_COMMAND`) REFERENCES `COMMANDS` (`ID`),
  CONSTRAINT `FK_COMMAND_DETAILS2` FOREIGN KEY (`ID_ITEM`) REFERENCES `ITEMS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


# Dump of table COMMANDS
# ------------------------------------------------------------

CREATE TABLE `COMMANDS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CLIENT` int(11) NOT NULL,
  `DATE` date DEFAULT NULL,
  `LIVRABLE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_EFFECTUER` (`ID_CLIENT`),
  CONSTRAINT `FK_EFFECTUER` FOREIGN KEY (`ID_CLIENT`) REFERENCES `USERS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


# Dump of table FACTURES
# ------------------------------------------------------------

CREATE TABLE `FACTURES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_COMMAND` int(11) DEFAULT NULL,
  `TOTAL` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONCERNE` (`ID_COMMAND`),
  CONSTRAINT `FK_CONCERNE` FOREIGN KEY (`ID_COMMAND`) REFERENCES `COMMANDS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table ITEMS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ITEMS`;

CREATE TABLE `ITEMS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` float DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `TYPE` varchar(30) DEFAULT NULL,
  `IMAGE` longblob DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `ITEMS` WRITE;
/*!40000 ALTER TABLE `ITEMS` DISABLE KEYS */;

INSERT INTO `ITEMS` (`ID`, `PRICE`, `DESCRIPTION`, `TYPE`, `IMAGE`)
VALUES
	(2,20,'Cola','Boisson',NULL),
	(3,20,'Fanta','Boisson',NULL),
	(4,30,'Coffee','Boisson',NULL),
	(5,20,'Sprite','Boisson',NULL),
	(6,30,'The','Boisson',NULL),
	(7,60,'Chef Salad','Plat Entree',NULL),
	(8,60,'Green Salad','Plat Entree',NULL),
	(9,60,'Pasta Salad','Plat Entree',NULL),
	(10,120,'Cheese Pizza','Plat Principal',NULL),
	(11,120,'Formaggio Pizza','Plat Principal',NULL),
	(12,120,'Pizza Fruit De Mer','Plat Principal',NULL),
	(13,120,'Frutti Di Mare','Plat Principal',NULL),
	(14,80,'Pancake Circulaire, Nutella','Dessert',NULL),
	(15,80,'Pancake Circulaire, Kiwi','Dessert',NULL),
	(16,80,'Pancake Circulaire, Strawberry','Dessert',NULL),
	(17,80,'Pancake Circulaire, Caramel','Dessert',NULL);

/*!40000 ALTER TABLE `ITEMS` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table RESERVATIONS
# ------------------------------------------------------------

CREATE TABLE `RESERVATIONS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CLIENT` int(11) NOT NULL,
  `ID_TABLE` int(11) NOT NULL,
  `TIME` time DEFAULT NULL,
  `NUMBER` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_RESERVATIONS` (`ID_CLIENT`),
  KEY `FK_RESERVATIONS2` (`ID_TABLE`),
  CONSTRAINT `FK_RESERVATIONS` FOREIGN KEY (`ID_CLIENT`) REFERENCES `USERS` (`ID`),
  CONSTRAINT `FK_RESERVATIONS2` FOREIGN KEY (`ID_TABLE`) REFERENCES `TABLES` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


# Dump of table TABLES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TABLES`;

CREATE TABLE `TABLES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `TABLES` WRITE;
/*!40000 ALTER TABLE `TABLES` DISABLE KEYS */;

INSERT INTO `TABLES` (`ID`, `DESCRIPTION`)
VALUES
	(1,'Table 1'),
	(2,'Table 2'),
	(3,'Table 3');

/*!40000 ALTER TABLE `TABLES` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table USERS
# ------------------------------------------------------------

CREATE TABLE `USERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(14) DEFAULT NULL,
  `LAST_NAME` varchar(14) DEFAULT NULL,
  `USERNAME` varchar(14) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `ROLE` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
