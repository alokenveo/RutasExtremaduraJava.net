CREATE DATABASE  IF NOT EXISTS `tw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tw`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: tw
-- ------------------------------------------------------
-- Server version	8.0.41


--
-- Dumping data for table `rutas`
--

LOCK TABLES `rutas` WRITE;
/*!40000 ALTER TABLE `rutas` DISABLE KEYS */;
INSERT INTO `rutas` VALUES (1,'Ruta de los Conquistadores','Ruta histórica en honor a los conquistadores extremeños, ideal para recorrer en varios medios y disfrutar de cultura y gastronomía.','https://www.rutaconquistadores.com/','2025-01-15 10:30:00',17,5,6902),(2,'Ruta del Jamón Ibérico','Viaje gastronómico para conocer la producción y degustación del jamón ibérico en Extremadura.','https://www.turismoextremadura.com/es/ven-a-extremadura/Ruta-del-Iberico/','2025-02-20 14:00:00',23,3,9331),(3,'Ruta de los Monasterios','Ruta espiritual por tres históricos monasterios de Cáceres, llenos de arte e historia.','https://www.turismocaceres.org/es/recomendaciones/ruta-monasterios-guadalupe-yuste-y-el-palancar','2025-03-10 09:15:00',21,5,4781),(4,'Ruta de los Castillos','Recorrido por castillos y fortalezas de Badajoz con fuerte carga histórica y arquitectónica.','https://hotelmonasteriorocamador.es/planes-actividades/ruta-castillos-badajoz/','2025-04-05 11:45:00',10,4,8204),(5,'Ruta Vía de la Plata','Ruta sobre la antigua vía romana desde Sevilla a Gijón, pasando por ciudades históricas.','https://www.turismoextremadura.com/es/explora/Ruta-Via-de-la-Plata/','2025-04-20 16:00:00',20,4,8235),(6,'Ruta de las Dehesas','Descubre la belleza natural de las dehesas extremeñas en un recorrido lleno de patrimonio.','https://mototurismo-rfme.com/ruta/ruta-de-las-dehesas/','2025-04-25 08:50:00',21,5,7473);
/*!40000 ALTER TABLE `rutas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Administrador','Okenve','admin','admin@gmail.com','admin','2004-05-04'),(2,'Alfredo Mituy','Okenve Obiang','fredy','fredy@gmail.com','fredy','2005-07-12'),(6,'Juan Pedro','Okenve Obiang','juan','juan@gmail.com','juan','2006-06-26'),(7,'Jacinta','Okenve','cintia','cintia@gmail.com','cintia','2005-03-27');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `fotos_ruta`
--

LOCK TABLES `fotos_ruta` WRITE;
/*!40000 ALTER TABLE `fotos_ruta` DISABLE KEYS */;
INSERT INTO `fotos_ruta` VALUES (1,1,'/img/rutas/conquistadores1.jpg'),(2,1,'/img/rutas/conquistadores2.jpg'),(3,1,'/img/rutas/conquistadores3.jpg'),(4,2,'/img/rutas/jamon1.jpg'),(5,2,'/img/rutas/jamon2.jpg'),(6,2,'/img/rutas/jamon3.jpg'),(7,3,'/img/rutas/monasterios1.jpg'),(8,3,'/img/rutas/monasterios2.jpg'),(9,4,'/img/rutas/castillos1.jpg'),(10,4,'/img/rutas/castillos2.jpg'),(11,4,'/img/rutas/castillos4.jpg'),(12,5,'/img/rutas/plata1.jpg'),(13,5,'/img/rutas/plata2.jpg'),(14,5,'/img/rutas/plata3.jpg'),(15,6,'/img/rutas/dehesas1.jpg'),(16,6,'/img/rutas/dehesas2.jpg'),(17,6,'/img/rutas/dehesas3.jpg');
/*!40000 ALTER TABLE `fotos_ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,2,1,'2025-05-01','mañana','Plaza Mayor de Trujillo',4,'Ninguna','Con ganas de la visita histórica.',NULL),(2,6,3,'2025-05-03','tarde','Puerta principal del Monasterio de Guadalupe',2,NULL,'Alergia al polvo, pero todo bien.',NULL),(3,7,2,'2025-05-05','mañana','Oficina de Turismo de Jabugo',3,'Frutos secos','Excursión familiar.',NULL),(4,2,5,'2025-05-07','tarde','Entrada principal de la Vía de la Plata',5,NULL,'Queremos saber si hay opción de guía.',NULL),(5,6,4,'2025-05-09','mañana','Castillo de Zafra',1,'Polen','¿Se permiten mascotas pequeñas?',NULL);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `valoraciones`
--

LOCK TABLES `valoraciones` WRITE;
/*!40000 ALTER TABLE `valoraciones` DISABLE KEYS */;
INSERT INTO `valoraciones` VALUES (1,2,2,3,NULL,'2025-04-26 17:12:07'),(2,6,2,1,NULL,'2025-04-26 17:13:48'),(3,2,3,5,'Una experiencia espiritual inolvidable.','2025-04-25 10:20:00'),(4,6,4,4,'Muy interesante, aunque algo larga.','2025-04-24 15:30:00'),(5,7,2,5,'¡Increíble experiencia gastronómica!','2025-04-26 11:45:00'),(6,7,6,4,'Muy bonito paisaje, volvería.','2025-04-25 18:10:00'),(7,1,5,3,'Un recorrido histórico impresionante.','2025-04-23 09:00:00'),(8,6,1,2,'No cumplió mis expectativas.','2025-04-22 14:45:00');
/*!40000 ALTER TABLE `valoraciones` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2025-04-27 14:37:07
