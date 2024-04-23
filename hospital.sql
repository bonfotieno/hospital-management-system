-- MySQL dump 10.13  Distrib 8.0.26, for Linux (x86_64)
--
-- Host: localhost    Database: hospital_mgmnt
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `profile` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,NULL,'bonny@mail.com','0789454623',NULL);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auths`
--

DROP TABLE IF EXISTS `auths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auths` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `admin_id` bigint DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnk2l7f5s3d66pa7nhqbj3rhtr` (`admin_id`),
  KEY `FKn8nxygvv2lpciwlfmnka47hi` (`patient_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auths`
--

LOCK TABLES `auths` WRITE;
/*!40000 ALTER TABLE `auths` DISABLE KEYS */;
INSERT INTO `auths` VALUES (1,NULL,'ad946509b77f655c87e5871db084bf22','ACTIVE','bonny@mail.com',1,NULL),(5,NULL,'fcb7a10fa2c120e942ffac49cd79e6a5','ACTIVE','kevo@gmail.com',NULL,3),(6,'2022-11-23 09:08:08','fcb7a10fa2c120e942ffac49cd79e6a5','ACTIVE','bonnyotieno10@gmail.com',NULL,5),(7,'2022-11-23 16:21:15','c63f24079f1d5e4cae3fdc1a29116a7b','ACTIVE','ebt1.00026.18@student.tharaka.ac.ke',NULL,6),(8,'2022-11-23 17:21:44','49e0435a5b6a9236d70089f7a4600a4c','ACTIVE','moseg95@gmail.com',NULL,7);
/*!40000 ALTER TABLE `auths` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beds`
--

DROP TABLE IF EXISTS `beds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beds` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime(6) DEFAULT NULL,
  `bed_description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `bed_no` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `bed_status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2tg7eb23xlsy3mkhcqlp15aha` (`room_id`),
  CONSTRAINT `FK2tg7eb23xlsy3mkhcqlp15aha` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beds`
--

LOCK TABLES `beds` WRITE;
/*!40000 ALTER TABLE `beds` DISABLE KEYS */;
INSERT INTO `beds` VALUES (3,'2022-11-20 16:39:46.000000','Normal bed','37','Available',3),(4,'2022-11-21 13:33:41.000000','For Physically Challenged','36','Available',5),(5,'2022-11-21 13:33:41.000000','Normal bed','35','Available',5),(6,'2022-11-21 22:22:49.000000','Normal bed','34','Available',5),(7,'2022-11-21 22:22:49.000000','For Physically Challenged','33','Available',5),(8,'2022-11-21 22:22:49.000000','For Physically Challenged','32','Available',6),(9,'2022-11-21 22:22:49.000000','For childrens under the age of 5','31','Available',6),(10,'2022-11-21 22:22:49.000000','Normal bed','30','Available',6);
/*!40000 ALTER TABLE `beds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billings`
--

DROP TABLE IF EXISTS `billings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime(6) DEFAULT NULL,
  `pathology_charges` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `discharge_date` date DEFAULT NULL,
  `misc_charges` double DEFAULT NULL,
  `other_charges` double DEFAULT NULL,
  `bill_no` double DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnk5g6ljwmexmh0l3h26aww3or` (`patient_id`),
  CONSTRAINT `FKnk5g6ljwmexmh0l3h26aww3or` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billings`
--

LOCK TABLES `billings` WRITE;
/*!40000 ALTER TABLE `billings` DISABLE KEYS */;
INSERT INTO `billings` VALUES (1,'2022-11-22 14:38:00.000000','34TY','2022-11-15',50,100,340,3),(2,'2022-11-22 14:38:00.000000','35TY','2022-11-22',30,200,200,3),(4,'2022-11-22 14:34:57.000000','36TY',NULL,50,200,300,2);
/*!40000 ALTER TABLE `billings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (4,'2022-11-09 12:32:22','We do that one too','Theatre'),(5,'2022-11-09 12:32:22','Deals body scanning','Therapy'),(7,'2022-11-10 21:11:03','Deal brains its health','Neorology'),(8,'2022-11-20 23:40:48','Drugs and medicines','Phamacy'),(9,'2022-12-02 00:24:49','This project is for emergency situations','Emergency');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl2mro81neln9topymd898urh1` (`department_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'2022-11-09 13:12:44','45, California Ave. Silicon Valley','bonny@mail.com','Bonface Otieno','bonny255','0798564534',4),(2,'2022-11-09 20:51:35','34, Mainstream, California Ave.','toy@mail.com','Jeck Mainia','tototoy34','0789345673',5),(3,'2022-11-09 20:02:04','45, California Ave. Silicon Valley','bon@mail.com','Bonface','Erray567','0789345673',4);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurses`
--

DROP TABLE IF EXISTS `nurses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime(6) DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdbnuj95q8r9h0i4duplh3lael` (`room_id`),
  CONSTRAINT `FKdbnuj95q8r9h0i4duplh3lael` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurses`
--

LOCK TABLES `nurses` WRITE;
/*!40000 ALTER TABLE `nurses` DISABLE KEYS */;
INSERT INTO `nurses` VALUES (2,'2022-11-21 01:13:52.000000','49285-00100 Nairobi GPO, Nairobi, Nairobi','hexgon@mail.com','Hexagon','0789345673',6);
/*!40000 ALTER TABLE `nurses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pathologies`
--

DROP TABLE IF EXISTS `pathologies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pathologies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime(6) DEFAULT NULL,
  `MRI_results` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `blood_test_results` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CT_scan_results` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `general_symptoms` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ultra_sound_results` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `x_ray_results` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn8exsbr0g0y6qm7ucjllyh5ox` (`patient_id`),
  CONSTRAINT `FKn8exsbr0g0y6qm7ucjllyh5ox` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pathologies`
--

LOCK TABLES `pathologies` WRITE;
/*!40000 ALTER TABLE `pathologies` DISABLE KEYS */;
INSERT INTO `pathologies` VALUES (4,'2022-11-21 22:15:01.000000','                                                        Not Done\r\n                                                    ','                                                        Not Done\r\n                                                    ','                                                        Not Done\r\n                                                    ','Headache','                                                        Not Done\r\n                                                    ','                                                        Not Done\r\n                                                    ',2),(5,'2022-11-22 09:52:26.000000','Not Done','Typhoid Bacteria positive','Not Done','Diarrhea\r\n','Not Done','Not Done',3),(6,'2022-11-23 17:27:32.000000','Not Done','Not Done','Not Done','headache\r\nfever\r\nseizures\r\nitchiness\r\nnausea\r\n','viable featus','Not Done',7);
/*!40000 ALTER TABLE `pathologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime(6) DEFAULT NULL,
  `referred_to` varchar(200) COLLATE utf8mb4_general_ci DEFAULT 'Not Referred',
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `age` int DEFAULT NULL,
  `blood_group` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reason_of_visit` varchar(300) COLLATE utf8mb4_general_ci DEFAULT 'Not Stated',
  `bedAdmitted_id` bigint DEFAULT NULL,
  `roomAdmitted_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKodbybftilf18ftxce6vl91mnm` (`bedAdmitted_id`),
  KEY `FKgi8kp1b9gcek445ijk1l0deqv` (`roomAdmitted_id`),
  CONSTRAINT `FKgi8kp1b9gcek445ijk1l0deqv` FOREIGN KEY (`roomAdmitted_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `FKodbybftilf18ftxce6vl91mnm` FOREIGN KEY (`bedAdmitted_id`) REFERENCES `beds` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (2,'2022-11-22 01:26:58.000000','Jeck Mainia','Africa 118, Westlands Business Centre, 7 Floor, Chiromo Lane, Westlands, Nairobi.','2022-11-15',23,'AB+','data@gmail.com','Female','Data Manging','0780563423','Treatment',4,5),(3,'2022-11-23 15:04:22.000000','Bonface Otieno','Africa 118, Westlands Business Centre, 7 Floor, Chiromo Lane, Westlands, Nairobi.','2022-11-22',34,'O+','kevo@gmail.com','Male','Kevo Kevin','0756452341','Eye inspection',4,5),(5,'2022-11-23 15:04:22.000000','Jeck Mainia','45, California Ave. Silicon Valley','2022-11-22',20,'AB+','bonnyotieno10@gmail.com','Male','Jay jay','0767456632','Health Check up',NULL,NULL),(6,'2022-11-23 16:21:15.000000',NULL,'AFC Hse, 403-30200, Kitale, Kenya',NULL,23,NULL,'ebt1.00026.18@student.tharaka.ac.ke','Non-binary','Weru Muhindu','0789345673',NULL,NULL,NULL),(7,'2022-11-23 17:33:24.000000','Jeck Mainia','45, California Ave. Silicon Valley',NULL,36,'AB+','moseg95@gmail.com','Male','Moses Gitau','0765342527','Ich',NULL,NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_created` datetime(6) DEFAULT NULL,
  `room_description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_no` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (3,'2022-11-20 16:38:37.000000','Normal Ward for patients','8','Available'),(5,'2022-11-20 16:38:37.000000','Normal Ward for patients','2','Available'),(6,'2022-11-20 16:38:37.000000','Normal Ward for patients','7','Available'),(7,'2022-11-20 16:38:37.000000','Normal Ward for patients','5','Available'),(9,'2022-11-20 16:38:37.000000','ICU','4','Available'),(10,'2022-11-21 13:34:44.000000','First room','1','Available');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-25 23:39:31
