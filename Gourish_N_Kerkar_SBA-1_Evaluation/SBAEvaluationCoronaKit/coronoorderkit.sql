-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2020 at 04:19 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coronoorderkit`
--

-- --------------------------------------------------------

--
-- Table structure for table `customerdb`
--

CREATE TABLE `Customer_Order_Info` (
  `OrderId` int(11) NOT NULL,
  `CustName` varchar(250) NOT NULL,
  `CustEmail` varchar(250) NOT NULL,
  `custPhone` varchar(12) DEFAULT NULL,
  `CustAddress` varchar(300) NOT NULL,
  `OrderDate` date NOT NULL DEFAULT current_timestamp(),
  `TotalAmount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customerdb`
--

INSERT INTO `Customer_Order_Info` (`OrderId`, `CustName`, `CustEmail`, `CustPhone`, `CustAddress`, `OrderDate`, `TotalAmount`) VALUES
(1, 'John', 'John@testmail.com', '9999999999', 'SF', '2020-08-22', 100),
(2, 'Bond', 'Bond@testmail.com', '8888888888', 'NJ', '2020-08-22', 300),
(3, 'Steve', 'Steve@testmail.com', '7777777777', 'NWK', '2020-08-23', 90),
(4, 'Karan', 'Karan@testmail.com', '6666666666', 'NJ', '2020-08-23', 180),
(5, 'James', 'James@testmail.com', '5555555555', 'TX', '2020-08-23', 70);



-- --------------------------------------------------------

--
-- Table structure for table `productmaster`
--

CREATE TABLE `Product_Info` (
  `Id` int(11) NOT NULL,
  `ProductName` varchar(250) NOT NULL,
  `Cost` varchar(250) NOT NULL,
  `ProductDescription` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productmaster`
--

INSERT INTO `Product_Info` (`Id`, `ProductName`, `Cost`, `ProductDescription`) VALUES
(1, 'N95 Mask', '50', 'Surgical Face Mask'),
(2, 'Dettol Sanitizer', '80', 'Sanitizer Bottle'),
(3, 'Lifeboy Disinfectant Spray', '100', 'Disinfectant Spray'),
(4, 'Gloves', '20', 'Paracetamol tablets'),
(5, 'Hand Wash Liquid', '100', 'Dettol HandWash');


--
-- Indexes for dumped tables
--

--
-- Indexes for table `customerdb`
--
ALTER TABLE `Customer_Order_Info`
  ADD PRIMARY KEY (`OrderId`);

--
-- Indexes for table `productmaster`
--
ALTER TABLE `Product_Info`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customerdb`
--
ALTER TABLE `Customer_Order_Info`
  MODIFY `OrderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Product_Info`
--
ALTER TABLE `Product_Info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
