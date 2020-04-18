-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 17, 2020 at 06:03 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restx`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_data`
--

CREATE TABLE `admin_data` (
  `category` varchar(200) NOT NULL,
  `item_menu` varchar(200) NOT NULL,
  `price` float NOT NULL,
  `discount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_data`
--

INSERT INTO `admin_data` (`category`, `item_menu`, `price`, `discount`) VALUES
('Starter', 'Chicken Momo', 180, 5),
('Biryani', 'Chicken Normal Biryani', 180, 3),
('Kabab', 'Paneer Tikka Kabab', 100, 3),
('Curries', 'Butter Chicken Boneless', 200, 5),
('Biryani', 'Mutton Biryani', 220, 10),
('Curries', 'Fish Curry', 180, 2),
('Desert', 'Vanilla Scotch', 80, 10),
('Kabab', 'Chicken Reshmi Kabab', 150, 0),
('Biryani', 'Hyderabadi Biryani', 190, 0),
('Starter', 'Fish Finger', 130, 0),
('Desert', 'Butter Scotch', 80, 2),
('Starter', 'Grilled Chicken (4 pc.)', 300, 0);

-- --------------------------------------------------------

--
-- Table structure for table `item_avail`
--

CREATE TABLE `item_avail` (
  `category` varchar(200) NOT NULL,
  `menu_item` varchar(200) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item_avail`
--

INSERT INTO `item_avail` (`category`, `menu_item`, `quantity`) VALUES
('Starter', 'Chicken Momo', 90),
('Biryani', 'Chicken Normal Biryani', 140),
('Kabab', 'Paneer Tikka Kabab', 8),
('Curries', 'Butter Chicken Boneless', 520);

-- --------------------------------------------------------

--
-- Table structure for table `login_details`
--

CREATE TABLE `login_details` (
  `ID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_details`
--

INSERT INTO `login_details` (`ID`, `username`, `password`) VALUES
(1, 'sh_sa', 'shsa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login_details`
--
ALTER TABLE `login_details`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login_details`
--
ALTER TABLE `login_details`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
