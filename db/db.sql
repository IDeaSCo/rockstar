-- MySQL dump 10.13  Distrib 5.5.40, for Win64 (x86)
--
-- Host: localhost    Database: game
-- ------------------------------------------------------
-- Server version	5.5.40-log

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
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `badge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `badge_icon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `badge_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `display_order` int(11) NOT NULL,
  `is_evil` bit(1) NOT NULL,
  `level_one_points` int(11) NOT NULL,
  `level_three_points` int(11) NOT NULL,
  `level_two_points` int(11) NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `stars_awarded` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
INSERT INTO `badge` VALUES (1,1,'fa-line-chart','Test Champ',1,'\0',50,500,200,'Add JUnit tests to collect this badge.',1),(2,2,'fa-cloud-upload','Frequent Code Committer',2,'\0',50,350,150,'Frequently check in your code, integrate your code and produce a stable build to collect this badge.',1),(3,4,'fa-key','Delivery Champ',12,'\0',100,2000,800,'Deliver your stories/defects on time to collect this badge.',10),(4,2,'fa-lightbulb-o','ShipIt - Innovator',4,'\0',75,400,200,'Present your idea in ShipIt, come up with creative solutions and implement them to collect this badge.',100),(5,1,'fa-group','Team Player',5,'\0',20,300,100,'Help your colleague to solve their problems and claim this badge.',5),(6,1,'fa-file-movie-o','Presentation Champ',6,'\0',100,500,300,'Share your knowledge with the rest of the team and claim this badge.',30),(7,2,'fa-sort-alpha-asc','Process Champ',7,'\0',50,900,300,'Update Rally regularly to claim this badge.',0),(8,1,'fa-thumbs-up','Appreciator',8,'\0',50,900,300,'Appreciate others contributions to claim this badge.',2),(9,3,'fa-scissors','Build Breaker',101,'',50,500,200,'When you break a build, or introduce failing tests, you get this badge',10),(10,3,'fa-bomb','Spillover Champ',100,'',75,500,200,'When stories/defects spill over to next iteration, you get this badge.',20),(11,1,'fa-maxcdn','Mission Impossible',11,'\0',100,1000,500,'Complete the open missions on the leaderboard and claim the badge.',10),(12,3,'fa-stack-overflow','Community Contributor',4,'\0',100,500,300,'Contribute to opensource projects to claim this badge.',75),(13,2,'fa-wordpress','Blogger',5,'\0',100,900,400,'Write technical blogs, answer questions on stackoverflow to claim this badge.',10),(14,2,'fa-warning','Cheater',200,'',100,300,200,'When you attempt to cheat this system, you get this badge.',100),(15,2,'fa-bolt','Code Violator',103,'',100,300,200,'When sonar, findbugs detects violations in the code you checkin, you get this badge.',1),(16,2,'fa-magic','Code Saviour',3,'\0',100,300,200,'Fix violations highlighted by sonar, findbugs to claim this badge.',1),(17,3,'fa-cogs','Process Violator',104,'',300,3000,1000,'When you do not update rally regularly, you get this badge.',5),(18,0,'fa-check','Default',99,'\0',100,300,200,'',0),(19,1,'fa-fire-extinguisher','Fire Fighter',13,'\0',100,300,200,'Provide help, support in the situations of crisis and claim this badge.',10),(20,1,'fa-bug','Bug Master',105,'',100,300,200,'When the bugs you introduce get caught in production, you get this badge.',10),(21,1,'fa-binoculars','Proactive',10,'\0',100,600,300,'Proactively work on solving issues, improvising processes, solutions to claim this badge.',20),(22,2,'fa-binoculars','Test Case\\Strategy',30,'\0',100,500,300,'Write test cases to claim this badge.',1),(23,2,'fa-university','Upload Test Cases\\Scenarios in Rally',30,'\0',100,500,300,'Upload test cases in rally to claim this badge.',1),(24,1,'fa-info ','Training',30,'\0',100,400,250,'Provide release training to client facing teams to claim this badge.',10),(25,1,'fa-file ','Documentation',30,'\0',50,500,200,'Provide good quality release documentation to claim this badge.',10),(26,2,'fa-graduation-cap','Knowledge Quest',13,'\0',100,700,300,'Complete knowledge quest missions and claim this badge per mission.',20),(27,1,'fa-fire-extinguisher','Test Sets Regression',22,'\0',300,600,400,'To give points when regression related testing is done.',10),(28,0,'fa-lightbulb-o','ShipIt - Enthusiast',5,'\0',50,200,100,'Present your idea in ShipIt, come up with creative solutions and implement them to collect this badge on getting Enthusiast certificate.',50),(29,0,'fa-lightbulb-o','ShipIt - Champion',8,'\0',100,400,200,'Claim this badge when your idea gets implemented and delivered in the product.',100),(30,0,'fa-cloud-upload','Frequent Code Checker',2,'\0',50,350,150,'Check-in your code frequently, integrate frequently and collect this badge.',1),(31,0,'fa-lightbulb-o','Innovator',4,'\0',75,400,200,'Come up with a creative solution, idea, implement it to collect this badge.',10);
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `department_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `department_name` (`department_name`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (2,0,'Default'),(3,0,'rally'),(4,0,'jenkins');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_badges`
--

DROP TABLE IF EXISTS `department_badges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_badges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `badge_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `badge_id` (`badge_id`,`department_id`),
  KEY `FKF4C192BDA51C175D` (`badge_id`),
  KEY `FKF4C192BD276FC5D7` (`department_id`)
) ENGINE=MyISAM AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_badges`
--

LOCK TABLES `department_badges` WRITE;
/*!40000 ALTER TABLE `department_badges` DISABLE KEYS */;
INSERT INTO `department_badges` VALUES (1,0,4,2),(2,0,5,2),(3,0,6,2),(4,0,7,2),(5,0,8,2),(6,0,12,2),(7,0,13,2),(8,0,17,2);
/*!40000 ALTER TABLE `department_badges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iplookup`
--

DROP TABLE IF EXISTS `iplookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iplookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `email_address` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `ip_address` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `mission_statement` text COLLATE utf8_unicode_ci,
  `status` int(11) NOT NULL,
  `trophies` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3FBE818C116254F6` (`creator_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mission_accomplished`
--

DROP TABLE IF EXISTS `mission_accomplished`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mission_accomplished` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `accomplished_by_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `missionid_id` int(11) NOT NULL,
  `trophies` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEE3B5DF5DF76FA02` (`missionid_id`),
  KEY `FKEE3B5DF5B97A288E` (`accomplished_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `trophy_history`
--

DROP TABLE IF EXISTS `trophy_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trophy_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `trophies` int(11) NOT NULL,
  `trophies_given_by_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `badge_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1A111475B66650F7` (`user_id`),
  KEY `FK1A1114753AA841B8` (`trophies_given_by_id`),
  KEY `FK1A111475A51C175D` (`badge_id`),
  KEY `FK1EEB2185A51C175D` (`badge_id`),
  KEY `FK1EEB2185B66650F7` (`user_id`),
  KEY `FK1EEB21853AA841B8` (`trophies_given_by_id`)
) ENGINE=MyISAM AUTO_INCREMENT=64523 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `trophy_reason`
--

DROP TABLE IF EXISTS `trophy_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trophy_reason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `historyid_id` int(11) NOT NULL,
  `reason` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAE8E6243E56EB82` (`historyid_id`),
  KEY `FKAEB67333E7EFC72` (`historyid_id`)
) ENGINE=MyISAM AUTO_INCREMENT=64522 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `employee_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trophies` int(11) NOT NULL,
  `leadAndAbove` tinyint(1) DEFAULT '0',
  `department_id` int(11) DEFAULT NULL,
  `is_admin` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `employee_id` (`employee_id`),
  KEY `FK36EBCB276FC5D7` (`department_id`)
) ENGINE=MyISAM AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,35,'rally','rally.user@ideas.com','rally','Rally','User','Rally',0,0,3,'\0'),(2,0,'jenkins','jenkins.user@ideas.com','jenkins','Jenkins','User','Jenkins',0,0,4,'\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_badges`
--

DROP TABLE IF EXISTS `user_badges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_badges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `badge_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD8FA2024A51C175D` (`badge_id`),
  KEY `FKD8FA2024B66650F7` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=769 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



-- Dump completed on 2015-08-05 12:30:19
