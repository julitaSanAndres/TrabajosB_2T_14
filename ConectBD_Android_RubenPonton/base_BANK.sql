CREATE DATABASE  IF NOT EXISTS `BANK` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `BANK`;
-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: 192.168.0.46    Database: BANK
-- ------------------------------------------------------
-- Server version	5.1.71

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Account_type`
--

DROP TABLE IF EXISTS `Account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account_type` (
  `ID_Account_type` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_Account_type`),
  UNIQUE KEY `ID_Account_type_UNIQUE` (`ID_Account_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account_type`
--

LOCK TABLES `Account_type` WRITE;
/*!40000 ALTER TABLE `Account_type` DISABLE KEYS */;
INSERT INTO `Account_type` VALUES (1,'Current Account'),(2,'Individual Savings Account'),(3,'Loan account');
/*!40000 ALTER TABLE `Account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Clients`
--

DROP TABLE IF EXISTS `Clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Clients` (
  `ID_Client` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname_1` varchar(45) NOT NULL,
  `Surname_2` varchar(45) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `City` varchar(45) NOT NULL,
  `Postal_code` int(5) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  PRIMARY KEY (`ID_Client`),
  UNIQUE KEY `ID_Client_UNIQUE` (`ID_Client`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Clients`
--

LOCK TABLES `Clients` WRITE;
/*!40000 ALTER TABLE `Clients` DISABLE KEYS */;
INSERT INTO `Clients` VALUES (1,'John','Doe','Doe','3, Kilby St.','Boston',2043,'U12345678'),(2,'Jane','Doe','Doe','5, Kilby St.','Boston',2043,'U12345679'),(3,'Mary','Lou','Harris','7, Kilby St.','Boston',2043,'U12345680'),(4,'Peter','Hunt','Crown','9, Kilby St.','Boston',2043,'U12345681'),(5,'Elizabeth','Wellington','Singht','2, Water St.','Boston',2043,'U12345682'),(6,'Franklin','Anderson','Doyle','4, Water St.','Boston',2043,'U12345683'),(7,'Renee','Lawrence','Lekker','6, Water St.','Boston',2043,'U12345684'),(8,'Aaron','Smith','Johnson','8, Water St.','Boston',2043,'U12345685'),(9,'Melvin','Hornachek','Paxton','10, Water St.','Boston',2043,'U12345686'),(10,'Jeff','Stockton','Red','230, Avon Rd.','Philadelphia',19116,'U12345687'),(11,'Jim','Flint','Rumsfeld','232, Avon Rd.','Philadelphia',19116,'U12345688'),(12,'Abbie','Green','Fellow','234, Avon Rd.','Philadelphia',19116,'U12345689'),(13,'Alice','Wells','Martin','236, Avon Rd.','Philadelphia',19116,'U12345690'),(14,'Amanda','Morrison','Taylor','238, Avon Rd.','Philadelphia',19116,'U12345691'),(15,'Amber','Blair','Lodge','240, Avon Rd.','Philadelphia',19116,'U12345692'),(16,'Candice','Marshall','Hayes','231, Alliston Rd.','Philadelphia',19116,'U12345693'),(17,'Alfred','Thompson','Farrell','233, Alliston Rd.','Philadelphia',19116,'U12345694'),(18,'Alexander','Hawke','Stone','235, Alliston Rd.','Philadelphia',19116,'U12345695'),(19,'Arthur','Davenport','Jackson','237, Alliston Rd.','Philadelphia',19116,'U12345696'),(20,'Drew','Brown','Smither','239, Alliston Rd.','Philadelphia',19116,'U12345697');
/*!40000 ALTER TABLE `Clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Electronic_user`
--

DROP TABLE IF EXISTS `Electronic_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Electronic_user` (
  `ID_Electronic_user` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Client` int(11) NOT NULL,
  `User` varchar(12) NOT NULL,
  `Password` varchar(12) NOT NULL,
  PRIMARY KEY (`ID_Electronic_user`),
  UNIQUE KEY `ID_Electronic_user_UNIQUE` (`ID_Electronic_user`),
  UNIQUE KEY `ID_Client_UNIQUE` (`ID_Client`),
  KEY `fk_Electronic_user_client` (`ID_Client`),
  CONSTRAINT `fk_Electronic_user_client` FOREIGN KEY (`ID_Client`) REFERENCES `Clients` (`ID_Client`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Electronic_user`
--

LOCK TABLES `Electronic_user` WRITE;
/*!40000 ALTER TABLE `Electronic_user` DISABLE KEYS */;
INSERT INTO `Electronic_user` VALUES (1,1,'user1','psw'),(2,2,'user2','psw'),(3,3,'user3','psw'),(4,4,'user4','psw'),(5,5,'user5','psw'),(6,6,'user6','psw'),(7,7,'user7','psw'),(8,8,'user8','psw'),(9,9,'user9','psw'),(10,10,'user10','psw'),(11,11,'user11','psw'),(12,12,'user12','psw'),(13,13,'user13','psw'),(14,14,'user14','psw'),(15,15,'user15','psw'),(16,16,'user16','psw'),(17,17,'user17','psw'),(18,18,'user18','psw'),(19,19,'user19','psw'),(20,20,'user20','psw');
/*!40000 ALTER TABLE `Electronic_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Credit_cards`
--

DROP TABLE IF EXISTS `Credit_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Credit_cards` (
  `ID_Credit_cards` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Account` int(11) NOT NULL,
  `Number` varchar(16) NOT NULL,
  PRIMARY KEY (`ID_Credit_cards`),
  UNIQUE KEY `ID_Credit_cards_UNIQUE` (`ID_Credit_cards`),
  UNIQUE KEY `Number_UNIQUE` (`Number`),
  KEY `fk_Credit_cards_Account` (`ID_Account`),
  CONSTRAINT `fk_Credit_cards_Account` FOREIGN KEY (`ID_Account`) REFERENCES `Accounts` (`ID_Account`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Credit_cards`
--

LOCK TABLES `Credit_cards` WRITE;
/*!40000 ALTER TABLE `Credit_cards` DISABLE KEYS */;
INSERT INTO `Credit_cards` VALUES (1,1,'1234123412340001'),(2,2,'1234123412340002'),(3,3,'1234123412340003'),(4,4,'1234123412340004'),(5,5,'1234123412340005'),(6,6,'1234123412340006'),(7,7,'1234123412340007'),(8,8,'1234123412340008'),(9,9,'1234123412340009'),(10,10,'1234123412340010'),(11,11,'1234123412340011'),(12,12,'1234123412340012'),(13,13,'1234123412340013'),(14,14,'1234123412340014'),(15,15,'1234123412340015'),(16,16,'1234123412340016'),(17,17,'1234123412340017'),(18,18,'1234123412340018'),(19,19,'1234123412340019'),(20,20,'1234123412340020');
/*!40000 ALTER TABLE `Credit_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Accounts`
--

DROP TABLE IF EXISTS `Accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Accounts` (
  `ID_Account` int(11) NOT NULL AUTO_INCREMENT,
  `IBAN` varchar(20) NOT NULL,
  `ID_Client` int(11) NOT NULL,
  `Balance` double NOT NULL,
  `ID_Account_type` int(11) NOT NULL,
  PRIMARY KEY (`ID_Account`),
  UNIQUE KEY `ID_Account_UNIQUE` (`ID_Account`),
  UNIQUE KEY `IBAN_UNIQUE` (`IBAN`),
  KEY `fk_Accounts_Clients` (`ID_Client`),
  KEY `fk_Account_type` (`ID_Account_type`),
  CONSTRAINT `fk_Accounts_Clients` FOREIGN KEY (`ID_Client`) REFERENCES `Clients` (`ID_Client`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_type` FOREIGN KEY (`ID_Account_type`) REFERENCES `Account_type` (`ID_Account_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Accounts`
--

LOCK TABLES `Accounts` WRITE;
/*!40000 ALTER TABLE `Accounts` DISABLE KEYS */;
INSERT INTO `Accounts` VALUES (1,'12341234901234567890',1,500,1),(2,'12341234911234567891',1,1500,3),(3,'12341234921234567892',2,2000,1),(4,'12341234931234567893',2,100,3),(5,'12341234941234567894',3,1500,1),(6,'12341234951234567895',3,5300,2),(7,'12341234961234567896',4,1200,1),(8,'12341234971234567897',4,2450,2),(9,'12341234981234567898',5,1350,2),(10,'12341234991234567899',5,750,3),(11,'12341234001234567900',6,2735,1),(12,'12341234011234567901',6,15250,3),(13,'12341234021234567902',7,175,1),(14,'12341234031234567903',7,350,2),(15,'12341234041234567904',8,9737,1),(16,'12341234051234567905',8,12500,3),(17,'12341234061234567906',9,137,1),(18,'12341234071234567907',9,865,2),(19,'12341234081234567908',10,987,1),(20,'12341234091234567909',10,789,2),(21,'12341234101234567910',11,2873,1),(22,'12341234111234567911',11,91827,3),(23,'12341234121234567912',12,2121,1),(24,'12341234131234567913',12,43232,3),(25,'12341234141234567914',13,123,1),(26,'12341234151234567915',13,98702,3),(27,'12341234161234567916',14,9876,1),(28,'12341234171234567917',14,357256,3),(29,'12341234181234567918',15,9837,1),(30,'12341234191234567919',15,12312,3),(31,'12341234201234567920',16,4563,1),(32,'12341234211234567921',16,8765,2),(33,'12341234221234567922',17,432,1),(34,'12341234231234567923',17,1238,2),(35,'12341234241234567924',18,765,1),(36,'12341234251234567925',18,12321,3),(37,'12341234261234567926',19,312,1),(38,'12341234271234567927',19,5234,2),(39,'12341234281234567928',20,234,1),(40,'12341234291234567929',20,1500,2);
/*!40000 ALTER TABLE `Accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movement_type`
--

DROP TABLE IF EXISTS `Movement_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movement_type` (
  `ID_Movement_type` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_Movement_type`),
  UNIQUE KEY `ID_Movement_type_UNIQUE` (`ID_Movement_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movement_type`
--

LOCK TABLES `Movement_type` WRITE;
/*!40000 ALTER TABLE `Movement_type` DISABLE KEYS */;
INSERT INTO `Movement_type` VALUES (1,'Commerce'),(2,'Credit');
/*!40000 ALTER TABLE `Movement_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Values_card`
--

DROP TABLE IF EXISTS `Values_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Values_card` (
  `ID_Values_card` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Electronic_user` int(11) NOT NULL,
  `Number` int(11) NOT NULL,
  `A1` int(2) NOT NULL,
  `A2` int(2) NOT NULL,
  `A3` int(2) NOT NULL,
  `B1` int(2) NOT NULL,
  `B2` int(2) NOT NULL,
  `B3` int(2) NOT NULL,
  `C1` int(2) NOT NULL,
  `C2` int(2) NOT NULL,
  `C3` int(2) NOT NULL,
  PRIMARY KEY (`ID_Values_card`),
  UNIQUE KEY `ID_Values_card_UNIQUE` (`ID_Values_card`),
  KEY `fk_Values_card_Electronic_user` (`ID_Electronic_user`),
  CONSTRAINT `fk_Values_card_Electronic_user` FOREIGN KEY (`ID_Electronic_user`) REFERENCES `Electronic_user` (`ID_Electronic_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Values_card`
--

LOCK TABLES `Values_card` WRITE;
/*!40000 ALTER TABLE `Values_card` DISABLE KEYS */;
INSERT INTO `Values_card` VALUES (1,1,1001,11,22,33,44,55,66,77,88,99),(2,2,1002,11,22,33,44,55,66,77,88,99),(3,3,1003,11,22,33,44,55,66,77,88,99),(4,4,1004,11,22,33,44,55,66,77,88,99),(5,5,1005,11,22,33,44,55,66,77,88,99),(6,6,1006,11,22,33,44,55,66,77,88,99),(7,7,1007,11,22,33,44,55,66,77,88,99),(8,8,1008,11,22,33,44,55,66,77,88,99),(9,9,1009,11,22,33,44,55,66,77,88,99),(10,10,1010,11,22,33,44,55,66,77,88,99),(11,11,1011,11,22,33,44,55,66,77,88,99),(12,12,1012,11,22,33,44,55,66,77,88,99),(13,13,1013,11,22,33,44,55,66,77,88,99),(14,14,1014,11,22,33,44,55,66,77,88,99),(15,15,1015,11,22,33,44,55,66,77,88,99),(16,16,1016,11,22,33,44,55,66,77,88,99),(17,17,1017,11,22,33,44,55,66,77,88,99),(18,18,1018,11,22,33,44,55,66,77,88,99),(19,19,1019,11,22,33,44,55,66,77,88,99),(20,20,1020,11,22,33,44,55,66,77,88,99);
/*!40000 ALTER TABLE `Values_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movements`
--

DROP TABLE IF EXISTS `Movements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movements` (
  `ID_Movement` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Account` int(11) NOT NULL,
  `ID_Movement_type` int(11) NOT NULL,
  `Amount` double NOT NULL,
  `Current_balance` double NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`ID_Movement`),
  UNIQUE KEY `ID_Movement_UNIQUE` (`ID_Movement`),
  KEY `fk_Movement_Account` (`ID_Movement`),
  KEY `fk_Movement_type` (`ID_Movement_type`),
  KEY `fk_Movements_Accounts_idx` (`ID_Account`),
  CONSTRAINT `fk_Movements_Accounts` FOREIGN KEY (`ID_Account`) REFERENCES `Accounts` (`ID_Account`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movements_Types` FOREIGN KEY (`ID_Movement_type`) REFERENCES `Movement_type` (`ID_Movement_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movements`
--

LOCK TABLES `Movements` WRITE;
/*!40000 ALTER TABLE `Movements` DISABLE KEYS */;
INSERT INTO `Movements` VALUES (1,1,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(2,2,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(3,3,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(4,4,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(5,5,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(6,6,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(7,7,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(8,8,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(9,9,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(10,10,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(11,11,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(12,12,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(13,13,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(14,14,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(15,15,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(16,16,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(17,17,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(18,18,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(19,19,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(20,20,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(21,21,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(22,22,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(23,23,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(24,24,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(25,25,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(26,26,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(27,27,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(28,28,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(29,29,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(30,30,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(31,31,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(32,32,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(33,33,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(34,34,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(35,35,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(36,36,2,-500,1000,'Loan change','2014-01-01 00:00:00'),(37,37,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(38,38,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(39,39,1,500,1000,'Buy in commerce','2014-01-01 00:00:00'),(40,40,1,-500,1000,'Buy in commerce','2014-01-01 00:00:00'),(41,1,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(42,2,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(43,3,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(44,4,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(45,5,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(46,6,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(47,7,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(48,8,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(49,9,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(50,10,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(51,11,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(52,12,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(53,13,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(54,14,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(55,15,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(56,16,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(57,17,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(58,18,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(59,19,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(60,20,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(61,21,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(62,22,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(63,23,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(64,24,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(65,25,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(66,26,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(67,27,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(68,28,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(69,29,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(70,30,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(71,31,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(72,32,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(73,33,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(74,34,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(75,35,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(76,36,2,-500,1000,'Loan change','2014-01-02 00:00:00'),(77,37,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(78,38,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(79,39,1,500,1000,'Buy in commerce','2014-01-02 00:00:00'),(80,40,1,-500,1000,'Buy in commerce','2014-01-02 00:00:00'),(81,1,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(82,2,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(83,3,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(84,4,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(85,5,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(86,6,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(87,7,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(88,8,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(89,9,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(90,10,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(91,11,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(92,12,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(93,13,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(94,14,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(95,15,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(96,16,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(97,17,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(98,18,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(99,19,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(100,20,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(101,21,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(102,22,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(103,23,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(104,24,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(105,25,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(106,26,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(107,27,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(108,28,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(109,29,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(110,30,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(111,31,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(112,32,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(113,33,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(114,34,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(115,35,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(116,36,2,-500,1000,'Loan change','2014-01-03 00:00:00'),(117,37,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(118,38,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(119,39,1,500,1000,'Buy in commerce','2014-01-03 00:00:00'),(120,40,1,-500,1000,'Buy in commerce','2014-01-03 00:00:00'),(121,1,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(122,2,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(123,3,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(124,4,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(125,5,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(126,6,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(127,7,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(128,8,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(129,9,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(130,10,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(131,11,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(132,12,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(133,13,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(134,14,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(135,15,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(136,16,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(137,17,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(138,18,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(139,19,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(140,20,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(141,21,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(142,22,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(143,23,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(144,24,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(145,25,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(146,26,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(147,27,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(148,28,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(149,29,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(150,30,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(151,31,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(152,32,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(153,33,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(154,34,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(155,35,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(156,36,2,-500,1000,'Loan change','2014-01-04 00:00:00'),(157,37,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(158,38,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(159,39,1,500,1000,'Buy in commerce','2014-01-04 00:00:00'),(160,40,1,-500,1000,'Buy in commerce','2014-01-04 00:00:00'),(161,1,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(162,2,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(163,3,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(164,4,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(165,5,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(166,6,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(167,7,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(168,8,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(169,9,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(170,10,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(171,11,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(172,12,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(173,13,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(174,14,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(175,15,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(176,16,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(177,17,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(178,18,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(179,19,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(180,20,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(181,21,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(182,22,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(183,23,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(184,24,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(185,25,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(186,26,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(187,27,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(188,28,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(189,29,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(190,30,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(191,31,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(192,32,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(193,33,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(194,34,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(195,35,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(196,36,2,-500,1000,'Loan change','2014-01-05 00:00:00'),(197,37,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(198,38,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(199,39,1,500,1000,'Buy in commerce','2014-01-05 00:00:00'),(200,40,1,-500,1000,'Buy in commerce','2014-01-05 00:00:00'),(201,1,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(202,2,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(203,3,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(204,4,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(205,5,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(206,6,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(207,7,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(208,8,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(209,9,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(210,10,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(211,11,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(212,12,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(213,13,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(214,14,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(215,15,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(216,16,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(217,17,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(218,18,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(219,19,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(220,20,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(221,21,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(222,22,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(223,23,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(224,24,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(225,25,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(226,26,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(227,27,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(228,28,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(229,29,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(230,30,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(231,31,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(232,32,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(233,33,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(234,34,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(235,35,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(236,36,2,-500,1000,'Loan change','2014-01-06 00:00:00'),(237,37,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(238,38,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00'),(239,39,1,500,1000,'Buy in commerce','2014-01-06 00:00:00'),(240,40,1,-500,1000,'Buy in commerce','2014-01-06 00:00:00');
/*!40000 ALTER TABLE `Movements` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-04 17:48:33
