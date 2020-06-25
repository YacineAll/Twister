-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: firstDataBase
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Table structure for table `FRIEND`
--

DROP TABLE IF EXISTS `FRIEND`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FRIEND` (
  `idUser` int(11) NOT NULL,
  `idFriend` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`idUser`,`idFriend`),
  KEY `fk_FRIEND_2_idx` (`idFriend`),
  CONSTRAINT `fk_FRIEND_1` FOREIGN KEY (`idUser`) REFERENCES `USER` (`idUSER`),
  CONSTRAINT `fk_FRIEND_2` FOREIGN KEY (`idFriend`) REFERENCES `USER` (`idUSER`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FRIEND`
--

LOCK TABLES `FRIEND` WRITE;
/*!40000 ALTER TABLE `FRIEND` DISABLE KEYS */;
/*!40000 ALTER TABLE `FRIEND` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SESSION`
--

DROP TABLE IF EXISTS `SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SESSION` (
  `session_key` varchar(200) NOT NULL,
  `idUser` int(11) NOT NULL,
  `dateConnexion` datetime NOT NULL,
  `DateFin` datetime NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `fk_SESSION_1_idx` (`idUser`),
  CONSTRAINT `fk_SESSION_1` FOREIGN KEY (`idUser`) REFERENCES `USER` (`idUSER`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SESSION`
--

LOCK TABLES `SESSION` WRITE;
/*!40000 ALTER TABLE `SESSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `idUSER` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `login` varchar(100) DEFAULT NULL,
  `password` longblob NOT NULL,
  `sex` char(2) NOT NULL,
  `DATE_DE_NAISSANCE` datetime NOT NULL,
  `DATE_INSCRIPTION` datetime NOT NULL,
  PRIMARY KEY (`idUSER`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-24 10:58:20
