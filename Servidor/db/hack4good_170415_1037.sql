-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-04-2015 a las 10:38:14
-- Versión del servidor: 5.6.15-log
-- Versión de PHP: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `hack4good`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimento`
--

CREATE TABLE IF NOT EXISTS `alimento` (
  `ID_alimento` int(11) NOT NULL,
  `nombre` text NOT NULL,
  PRIMARY KEY (`ID_alimento`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alimento`
--

INSERT INTO `alimento` (`ID_alimento`, `nombre`) VALUES
(1, 'Arroz'),
(2, 'Azúcar'),
(3, 'Leche'),
(4, 'Carne cerdo'),
(5, 'Carne pollo'),
(6, 'Pescado'),
(7, 'Lentejas'),
(8, 'Yogures'),
(9, 'Garbanzos'),
(10, 'Galletas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entidad`
--

CREATE TABLE IF NOT EXISTS `entidad` (
  `ID_entidad` int(11) NOT NULL,
  `nombre` text NOT NULL,
  `direccion` text NOT NULL,
  PRIMARY KEY (`ID_entidad`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `entidad`
--

INSERT INTO `entidad` (`ID_entidad`, `nombre`, `direccion`) VALUES
(1, 'Alcoholera', 'Callejón de la Alcoholera Naves 1-2\r\n47008 Valladolid'),
(2, 'Panaderos', 'C/Panaderos, 28, Colegio Cardenal Mendoza (Area de A.Social y Voluntariado)\r\n47008 Valladolid'),
(3, 'Polígono Argales', 'C/Vázquez de Menchaca. Parcela 28. Polígono de Argales. 47008. Valladolid. ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prioridad`
--

CREATE TABLE IF NOT EXISTS `prioridad` (
  `ID_entidad` int(11) NOT NULL,
  `ID_alimento` int(11) NOT NULL,
  `prioridad` int(11) NOT NULL,
  PRIMARY KEY (`ID_entidad`,`ID_alimento`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `prioridad`
--

INSERT INTO `prioridad` (`ID_entidad`, `ID_alimento`, `prioridad`) VALUES
(1, 1, 1),
(1, 3, 2),
(2, 3, 1),
(2, 5, 2),
(3, 1, 1),
(3, 2, 2),
(3, 4, 3),
(1, 6, 3),
(2, 2, 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
