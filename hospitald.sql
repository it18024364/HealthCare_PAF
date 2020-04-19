-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 19, 2020 at 06:31 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospitald`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospitals`
--

DROP TABLE IF EXISTS `hospitals`;
CREATE TABLE IF NOT EXISTS `hospitals` (
  `hospitalid` varchar(200) NOT NULL,
  `hospitalname` varchar(200) NOT NULL,
  `hospitalphone` int(200) NOT NULL,
  `hospitalemail` varchar(200) NOT NULL,
  `hospitallocation` varchar(200) NOT NULL,
  PRIMARY KEY (`hospitalid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

--
-- Dumping data for table `hospitals`
--

INSERT INTO `hospitals` (`hospitalid`, `hospitalname`, `hospitalphone`, `hospitalemail`, `hospitallocation`) VALUES
('001', ' Wesley Hospital Ashfield', 99652314, 'Wesley@gmail.com', 'Ashfield NSW 2131, Australia'),
('002', 'Greenwich Hospital\r\n', 99874125, 'Greenwich@gmail.com\r\n', 'NSW 2065, Australia'),
('003', 'Sydney Adventist Hospital\r\n', 99321456, 'Adventist@gmail.com', ' NSW 2076, Australia'),
('004', 'Wolper Jewish Hospital\r\n', 99742361, 'Jewish@gmail.com ', 'NSW 2025, Australia');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
