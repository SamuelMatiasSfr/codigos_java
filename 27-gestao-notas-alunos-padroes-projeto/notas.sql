-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13/11/2025 às 18:52
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `escola`
--
CREATE DATABASE IF NOT EXISTS `escola` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `escola`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `notas`
--

CREATE TABLE `notas` (
  `id` tinyint(4) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nota1` decimal(20,6) NOT NULL,
  `nota2` decimal(20,6) NOT NULL,
  `nota3` decimal(20,6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `notas`
--

INSERT INTO `notas` (`id`, `email`, `nota1`, `nota2`, `nota3`) VALUES
(1, 'eduling0@census.gov', 65.400000, 44.400000, 93.800000),
(2, 'dderx1@nymag.com', 40.300000, 66.800000, 39.700000),
(3, 'hwhybray2@mac.com', 22.600000, 47.200000, 18.200000),
(4, 'catlee3@java.com', 27.000000, 53.300000, 40.000000),
(5, 'avarian4@comcast.net', 26.400000, 45.100000, 77.300000),
(6, 'mzumfelde5@paypal.com', 48.500000, 76.300000, 65.600000),
(7, 'awhoston6@samsung.com', 51.400000, 60.200000, 16.200000),
(8, 'gjerrard7@storify.com', 67.000000, 72.600000, 56.800000),
(9, 'jspinozzi8@blogtalkradio.com', 70.200000, 53.400000, 28.200000),
(10, 'dstoneman9@google.co.uk', 45.500000, 57.300000, 84.800000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
