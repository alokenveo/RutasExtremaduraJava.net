CREATE DATABASE  IF NOT EXISTS `tw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tw`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: tw
-- ------------------------------------------------------
-- Server version	8.0.41


--
-- Table structure for table `fotos_ruta`
--

DROP TABLE IF EXISTS `fotos_ruta`;
CREATE TABLE `fotos_ruta` (
  `idFoto` int NOT NULL AUTO_INCREMENT,
  `idRuta` int NOT NULL,
  `pathImagen` varchar(255) NOT NULL,
  PRIMARY KEY (`idFoto`),
  KEY `idRuta` (`idRuta`),
  CONSTRAINT `fotos_ruta_ibfk_1` FOREIGN KEY (`idRuta`) REFERENCES `rutas` (`idRuta`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE `reservas` (
  `idReserva` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `idRuta` int NOT NULL,
  `fecha` date NOT NULL,
  `horario` enum('mañana','tarde') NOT NULL,
  `puntoEncuentro` varchar(255) NOT NULL,
  `personas` int NOT NULL,
  `alergias` varchar(255) DEFAULT NULL,
  `comentarios` text,
  `serviciosExtras` json DEFAULT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idRuta` (`idRuta`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`idRuta`) REFERENCES `rutas` (`idRuta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `rutas`
--

DROP TABLE IF EXISTS `rutas`;
CREATE TABLE `rutas` (
  `idRuta` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `enlace` varchar(255) NOT NULL,
  `fechaIncorporacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `maximoUsuario` int NOT NULL,
  `dificultad` int NOT NULL,
  `metros` int NOT NULL,
  PRIMARY KEY (`idRuta`),
  CONSTRAINT `rutas_chk_1` CHECK ((`dificultad` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `valoraciones`
--

DROP TABLE IF EXISTS `valoraciones`;
CREATE TABLE `valoraciones` (
  `idValoracion` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `idRuta` int NOT NULL,
  `valoracion` int NOT NULL,
  `comentario` text,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idValoracion`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idRuta` (`idRuta`),
  CONSTRAINT `valoraciones_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `valoraciones_ibfk_2` FOREIGN KEY (`idRuta`) REFERENCES `rutas` (`idRuta`),
  CONSTRAINT `valoraciones_chk_1` CHECK ((`valoracion` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;