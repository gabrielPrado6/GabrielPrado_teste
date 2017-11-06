-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 02-Nov-2017 às 05:29
-- Versão do servidor: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testeback`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `TB_CUSTOMER_ACCOUNT`
--

CREATE TABLE `tb_customer_account` (
  `id_customer` int(11) NOT NULL,
  `cpf_cnpj` bigint(20) DEFAULT NULL,
  `nm_customer` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `vl_total` decimal(24,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `TB_CUSTOMER_ACCOUNT`
--

INSERT INTO `TB_CUSTOMER_ACCOUNT` (`id_customer`, `cpf_cnpj`, `nm_customer`, `is_active`, `vl_total`) VALUES
(12, 41241232454, 'João', 1, '43233.32'),
(13, 97645645323, 'Luiz', 1, '543234.44'),
(14, 74512349532, 'Luiz', 1, '1532.32'),
(15, 75645663412, 'Miguel', 1, '2345.43'),
(16, 54376534512, 'Larrisa ', 1, '2341.21'),
(17, 65423467834, 'Maria', 1, '2435.43'),
(18, 76523445665, 'Lizandra', 1, '2234.32'),
(19, 98787656534, 'Rafaela', 0, '1567.76'),
(1500, 12312312312, 'nome', 1, '1231.12'),
(1501, 23432342342, 'Siclano', 0, '5343.24'),
(1502, 64536545423, 'Fulano', 0, '7566.34'),
(1503, 12332323423, 'Abesco', 1, '234.43'),
(1504, 876456234545, 'Farias', 1, '234.43'),
(1505, 988675643, 'Thiago', 0, '567.12'),
(1506, 34556723445, 'Carina', 1, '456.65'),
(1507, 98775654123, 'Gabriela', 1, '865.34'),
(1508, 1, 'S', 1, '1.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `TB_CUSTOMER_ACCOUNT`
--
ALTER TABLE `TB_CUSTOMER_ACCOUNT`
  ADD PRIMARY KEY (`id_customer`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `TB_CUSTOMER_ACCOUNT`
--
ALTER TABLE `TB_CUSTOMER_ACCOUNT`
  MODIFY `id_customer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1509;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
