-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: payment
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (0,'ACTIVE'),(1,'BLOCKED');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `number` bigint NOT NULL,
  `money` int NOT NULL DEFAULT '0',
  `activity_id` int NOT NULL DEFAULT '0',
  `request_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cards_number_uindex` (`number`),
  KEY `cards___activity` (`activity_id`),
  KEY `cards__user` (`user_id`),
  KEY `cards_request_id_fk` (`request_id`),
  CONSTRAINT `cards___activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cards__user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cards_request_id_fk` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (2,3,'Hello',123123123123,25300,1,1),(3,3,'fdgfyg',321321321321,469580,0,0),(4,3,'Fello',478478478478,211015,0,0),(5,2,'sdrfgh',123456789632,23044,0,0),(6,7,'clientara',404933862487,1000,0,0),(7,3,'kuku',404967920208,0,0,0),(8,3,'kukusiki',404972017594,9500,0,0),(9,8,'stason',404933029455,2000,0,0),(10,4,'login',404906252451,0,0,0),(11,3,'new',404979862216,1200,0,0),(12,9,'adminsdasd',404959809201,0,0,0),(13,10,'adminassss',404941972940,1000,0,0),(14,11,'adminasdasdas',404973455955,3000,0,0),(15,12,'adminasdf',404944096447,2000,0,0),(16,3,'test',404956636854,2200,0,0),(17,3,'privet',404971047459,500,0,0),(18,13,'kukus',404997725753,0,0,0),(20,3,'KUKU',404902675602,1200,1,1),(21,15,'forma',404914349361,0,0,0),(22,3,'Popolnenie',404909524218,0,1,1),(23,16,'pattern',404990460843,0,0,0),(24,17,'приветик',404930283277,0,0,0),(25,18,'adminsвKK',404995825125,0,0,0),(26,3,'kukusik',404993647641,0,0,0),(27,3,'kukus',404984248980,0,0,0),(28,3,'igorek',404981801050,500,0,0),(29,19,'igorek',404961925770,0,0,0),(30,3,'testFormu',404938047395,0,0,0),(31,3,'testForm2',404957887743,0,0,0);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_id` int DEFAULT NULL,
  `card_destination_id` int NOT NULL,
  `money` int NOT NULL,
  `balance` int DEFAULT NULL,
  `date` datetime NOT NULL,
  `status_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `payments_cards` (`card_id`),
  KEY `payments_statuses_id_fk` (`status_id`),
  KEY `payments_cards_id_fk` (`card_destination_id`),
  CONSTRAINT `payments_cards` FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payments_cards_id_fk` FOREIGN KEY (`card_destination_id`) REFERENCES `cards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payments_statuses_id_fk` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,2,4,100,400,'2020-03-20 22:59:24',1),(3,2,4,300,500,'2020-03-21 17:15:02',1),(4,5,2,400,700,'2020-03-21 17:20:02',1),(5,NULL,3,500,700,'2020-03-22 11:18:05',1),(11,NULL,4,30000,210015,'2020-03-22 22:44:21',1),(12,NULL,3,464656,466780,'2020-03-22 22:55:50',1),(13,NULL,2,500,14544,'2020-03-22 22:56:38',1),(14,2,5,500,14044,'2020-03-23 16:00:48',1),(15,2,5,1000,13044,'2020-03-23 16:02:01',1),(16,3,4,1000,465780,'2020-03-23 16:16:58',1),(17,2,5,2000,11044,'2020-03-23 16:19:49',1),(18,2,5,4000,11044,'2020-03-23 17:35:20',1),(19,NULL,3,4000,469780,'2020-03-23 16:35:58',1),(20,2,5,4000,6000,'2020-03-23 17:37:34',1),(21,2,5,4000,11044,'2020-03-23 17:17:30',1),(22,3,2,200,469580,'2020-03-23 17:21:03',1),(23,3,2,200,469380,'2020-03-23 17:23:16',1),(24,4,2,4500,211015,'2020-03-23 17:34:20',1),(25,NULL,6,1000,1000,'2020-03-23 20:58:59',1),(28,NULL,4,4000,210515,'2020-03-23 22:55:41',1),(29,NULL,8,500,500,'2020-03-23 22:57:18',1),(30,3,4,500,468880,'2020-03-23 22:57:55',1),(32,2,4,4500,6000,'2020-03-23 23:00:55',0),(33,NULL,3,1200,470080,'2020-03-29 12:25:22',1),(34,NULL,9,4000,4000,'2020-04-01 23:18:36',1),(35,9,2,2000,2000,'2020-04-01 23:19:05',1),(36,NULL,8,4200,4700,'2020-04-02 12:30:07',1),(37,NULL,11,1200,1200,'2020-04-03 11:33:24',1),(38,2,16,1200,2800,'2020-04-03 11:40:31',1),(39,8,20,1200,3500,'2020-04-04 23:28:36',1),(40,8,14,500,3000,'2020-04-04 23:29:45',1),(41,8,14,500,2500,'2020-04-04 23:33:42',1),(42,8,14,500,9500,'2020-04-06 22:54:43',1),(43,8,14,500,2500,'2020-04-04 23:44:10',0),(44,8,14,500,2000,'2020-04-04 23:45:48',1),(45,8,14,500,2000,'2020-04-04 23:47:16',0),(46,8,14,500,1500,'2020-04-04 23:48:08',1),(47,8,12,500,1500,'2020-04-05 00:03:23',0),(48,8,13,500,1000,'2020-04-05 10:49:25',1),(49,8,13,500,1000,'2020-04-05 12:23:26',0),(50,8,13,500,500,'2020-04-05 12:23:52',1),(52,8,16,500,0,'2020-04-05 12:32:31',1),(53,NULL,8,500,500,'2020-04-05 12:57:35',1),(54,8,14,500,0,'2020-04-05 13:53:11',1),(55,NULL,8,5000,5000,'2020-04-05 14:17:48',1),(56,NULL,8,5000,10000,'2020-04-05 23:25:53',1),(57,2,15,1000,1800,'2020-04-06 21:41:36',1),(58,2,15,1000,800,'2020-04-06 21:41:51',1),(59,NULL,2,5000,5800,'2020-04-06 22:18:06',1),(60,NULL,2,5000,10800,'2020-04-06 22:18:11',1),(61,NULL,2,5000,15800,'2020-04-06 22:18:12',1),(62,NULL,2,5000,20800,'2020-04-06 22:18:14',1),(63,NULL,2,5000,25800,'2020-04-06 22:31:14',1),(64,2,17,500,25300,'2020-04-06 22:33:51',1),(65,NULL,28,500,500,'2020-04-06 22:56:15',1),(66,3,16,500,469580,'2020-04-06 22:56:51',1);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (0,'FALSE'),(1,'TRUE');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'ADMIN'),(1,'CLIENT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `id` int NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (0,'PREPARED'),(1,'SEND');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(250) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `role_id` int NOT NULL DEFAULT '1',
  `activity_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_login_uindex` (`login`),
  KEY `role` (`role_id`),
  KEY `activity` (`activity_id`),
  CONSTRAINT `activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','Sergey','Vasilchenko',0,0),(2,'admins','21232f297a57a5a743894a0e4a801fc3','weqw','asddass',1,0),(3,'adminsв','21232f297a57a5a743894a0e4a801fc3','приветChel','pokaМедвед',1,0),(4,'client','62608e08adc29a8d6dbc9754e659f125','Client','Clientskiy',1,0),(5,'clients','62608e08adc29a8d6dbc9754e659f125','qwerty','asdfg',1,0),(6,'kuku','f1534cd6b03bca4163d5773a988dc3bc','kukus','kukusnuy',1,0),(7,'clientara','62608e08adc29a8d6dbc9754e659f125','Lopa','Koka',1,0),(8,'stason','dcf0e92807a4e3e2026f95cfede19799','stat','statstsniy',1,0),(9,'adminsdasd','476be9702872987b11508359a031794d','assdasd','asdasd',1,0),(10,'adminassss','21232f297a57a5a743894a0e4a801fc3','asdasd','asdasd',1,0),(11,'adminasdasdas','a96bc5bf82f05e7b895698f572d730cd','asdasdasd','asdasdasd',1,0),(12,'adminasdf','6d12f11a326680cf19e10f9bd45170dc','sas','assaa',1,0),(13,'kukus','9887bcd31d1e770a3ae546a046749c02','kukus','kukus',1,0),(15,'forma','a05be3f47b0befe1bc3693f4d5caab03','forma','forma',1,0),(16,'pattern','240bf022e685b0ee30ad9fe9e1fb5d5b','кукусики','кукусики',1,0),(17,'приветик','64965f9b3cf618571453e705137965d3','куку','привет',1,0),(18,'adminsвKK','f07f4ae350724a596ce3ba48e524f7a9','kukIs','lakis',1,0),(19,'igorek','51bbef5881a6264a0741c9228be6ce1a','igorek','igorek',1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 12:12:52
