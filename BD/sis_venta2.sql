-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.24-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for sis_venta
CREATE DATABASE IF NOT EXISTS `sis_venta` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `sis_venta`;

-- Dumping structure for table sis_venta.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sis_venta.cliente: ~2 rows (approximately)
INSERT INTO `cliente` (`idcliente`, `nombre`, `telefono`, `direccion`, `usuario_id`, `estado`) VALUES
	(1, 'Sebastian Condori', '8296826298', 'Tacna - Pocollay', 1, 1),
	(2, 'Angel Flores', '2147483647', 'Lima - Huari', 2, 1);

-- Dumping structure for table sis_venta.configuracion
CREATE TABLE IF NOT EXISTS `configuracion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` text COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sis_venta.configuracion: ~0 rows (approximately)
INSERT INTO `configuracion` (`id`, `nombre`, `telefono`, `email`, `direccion`) VALUES
	(1, 'Tienda Deportiva OUT FIT', '925491523', 'info.tiendadeportoutfit@gmail.com', 'Tacna');

-- Dumping structure for table sis_venta.detalle_permisos
CREATE TABLE IF NOT EXISTS `detalle_permisos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_permiso` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sis_venta.detalle_permisos: ~4 rows (approximately)
INSERT INTO `detalle_permisos` (`id`, `id_permiso`, `id_usuario`) VALUES
	(8, 3, 2),
	(9, 4, 2),
	(10, 5, 2),
	(11, 6, 2);

-- Dumping structure for table sis_venta.detalle_temp
CREATE TABLE IF NOT EXISTS `detalle_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_venta` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sis_venta.detalle_temp: ~0 rows (approximately)

-- Dumping structure for table sis_venta.detalle_venta
CREATE TABLE IF NOT EXISTS `detalle_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sis_venta.detalle_venta: ~4 rows (approximately)
INSERT INTO `detalle_venta` (`id`, `id_producto`, `id_venta`, `cantidad`, `precio`) VALUES
	(4, 2, 3, 1, 150.00),
	(5, 3, 3, 1, 200.00),
	(6, 5, 4, 1, 100.00),
	(7, 6, 4, 1, 59.00);

-- Dumping structure for table sis_venta.permisos
CREATE TABLE IF NOT EXISTS `permisos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sis_venta.permisos: ~6 rows (approximately)
INSERT INTO `permisos` (`id`, `nombre`) VALUES
	(1, 'configuración'),
	(2, 'usuarios'),
	(3, 'clientes'),
	(4, 'productos'),
	(5, 'ventas'),
	(6, 'nueva_venta');

-- Dumping structure for table sis_venta.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `codproducto` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `medida` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `existencia` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`codproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sis_venta.producto: ~5 rows (approximately)
INSERT INTO `producto` (`codproducto`, `codigo`, `descripcion`, `medida`, `precio`, `existencia`, `usuario_id`, `estado`) VALUES
	(2, '1002', 'Zapatillas Blancas Addidas (U)', '38', 150.00, 4, 1, 1),
	(3, '1003', 'Buzo Addidas', 'M', 200.00, 7, 1, 1),
	(4, '1004', 'Balon Addidas', '5', 150.00, 10, 1, 1),
	(5, '1005', 'Poleras Addidas (U)', 'M', 100.00, 11, 1, 1),
	(6, '1006', 'Mancuernas', '2KG', 59.00, 4, 0, 1),
	(7, '1007', 'Maleta Addidas', NULL, 179.00, 5, 1, 1),
	(8, '1008', 'Polera con Capucha (M)', 'L', 199.00, 5, 1, 1),
	(9, '1009', 'Adilette', '40', 159.00, 10, 1, 1),
	(10, '1010', 'Casaca Marathon', 'M', 319.00, 3, 1, 1),
	(11, '1011', 'Gorra Addidas', NULL, 63.00, 15, 1, 1),
	(12, '1012', 'Mochila Addidas', NULL, 183.00, 5, 1, 1),
	(13, '1013', 'Medias Largas Addidas', 'S', 31.00, 12, 1, 1),
	(14, '1014', 'Zapatillas Adizero', '42', 233.00, 4, 1, 1),
	(15, '1015', 'Soga de Saltar', NULL, 15.00, 12, 1, 1),
	(16, '1016', 'Pelota De Yoga', '55 cm', 40.00, 6, 1, 1);

-- Dumping structure for table sis_venta.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Dumping data for table sis_venta.usuario: ~5 rows (approximately)
INSERT INTO `usuario` (`idusuario`, `nombre`, `correo`, `usuario`, `clave`, `estado`) VALUES
	(1, 'Cesar Chavez', 'fabianch2600@gmail.com', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1),
	(2, 'Tell Casilla', 'tellcasilla2303@gmail.com', 'tell', '21232f297a57a5a743894a0e4a801fc3', 1),
	(3, 'Albert Apaza', 'albertapaza456@gmail.com', 'albert', '21232f297a57a5a743894a0e4a801fc3', 1),
	(4, 'Ivan Malaga', 'ivanmalaga478@gmail.com', 'ivan', '21232f297a57a5a743894a0e4a801fc3', 1),
	(5, 'Ximena Ortiz', 'ximenaortiz787@gmail.com', 'xime', '21232f297a57a5a743894a0e4a801fc3', 1),
	(6, 'Nilda Garcia', 'nildagracia789@gmail.com', 'nilda', '21232f297a57a5a743894a0e4a801fc3', 1),
	(7, 'Alexander Tarqui', 'alextarqui156@gmail.com', 'alex', '21232f297a57a5a743894a0e4a801fc3', 1),
	(8, 'Alfredo Huallpa', 'alfredohuallpa@gmail.com', 'alfred', '21232f297a57a5a743894a0e4a801fc3', 1),
	(9, 'Cristian Pazos', 'crispazos1245@gmail.com', 'crisp', '21232f297a57a5a743894a0e4a801fc3', 1),
	(10, 'Allison Herrera', 'allisonh5687@gmail.com', 'alli', '21232f297a57a5a743894a0e4a801fc3', 1);

-- Dumping structure for table sis_venta.ventas
CREATE TABLE IF NOT EXISTS `ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sis_venta.ventas: ~2 rows (approximately)
INSERT INTO `ventas` (`id`, `id_cliente`, `total`, `id_usuario`, `fecha`) VALUES
	(3, 1, 350.00, 1, '2022-09-22 13:04:09'),
	(4, 2, 159.00, 1, '2022-09-22 13:07:21');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
