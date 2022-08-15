-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jul 13, 2022 at 03:10 PM
-- Server version: 5.7.34
-- PHP Version: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airbnb`
--

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `price` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`id`, `user_id`, `room_id`, `start_date`, `end_date`, `price`, `total`, `created_at`, `updated_at`) VALUES
(2, 2, 1, '2022-06-21 15:10:08', '2022-06-21 15:10:08', 10000, 10000, '2022-06-21 15:10:08', '2022-06-21 15:10:08'),
(3, 3, 1, '2022-07-05 16:51:16', '2022-07-05 16:51:16', 222, 222, '2022-07-05 16:51:16', '2022-07-05 16:51:16'),
(4, 4, 1, '2022-07-05 16:51:16', '2022-07-05 16:51:16', 111, 111, '2022-07-05 16:51:16', '2022-07-05 16:51:16');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `comment` varchar(1000) COLLATE utf16_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `reservation_id`, `rating`, `comment`) VALUES
(2, 2, 9, 'UKI');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `room_name` varchar(300) COLLATE utf16_unicode_ci NOT NULL,
  `home_type` varchar(50) COLLATE utf16_unicode_ci NOT NULL,
  `room_type` varchar(50) COLLATE utf16_unicode_ci NOT NULL,
  `total_occupancy` int(11) NOT NULL,
  `total_bedrooms` int(11) NOT NULL,
  `total_bathrooms` int(11) NOT NULL,
  `summary` varchar(1000) COLLATE utf16_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf16_unicode_ci NOT NULL,
  `has_tv` tinyint(1) NOT NULL,
  `has_kitchen` tinyint(1) NOT NULL,
  `has_air_con` tinyint(1) NOT NULL,
  `has_heating` tinyint(1) NOT NULL,
  `has_internet` tinyint(1) NOT NULL,
  `price` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `image` varchar(200) COLLATE utf16_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `room_name`, `home_type`, `room_type`, `total_occupancy`, `total_bedrooms`, `total_bathrooms`, `summary`, `address`, `has_tv`, `has_kitchen`, `has_air_con`, `has_heating`, `has_internet`, `price`, `owner_id`, `created_at`, `image`) VALUES
(1, 'ad1', 'Apartment', 'Private rooms', 100, 3, 21, '10 diem!!!!!?', 'New York', 0, 1, 1, 0, 1, 99, 1, '2022-06-21 22:05:45', NULL),
(3, 'Phong 2', 'condo', 'Entire place', 4, 4, 4, 'dep lem lem', '9 Hoang Minh Giam', 0, 0, 0, 0, 0, 2001, 4, '2022-06-23 22:35:02', NULL),
(4, '123', 'Apartment', 'Private rooms', 2, 2, 2, 'fff', '11', 0, 1, 1, 0, 0, 2, 4, '2022-07-11 00:18:58', NULL),
(5, 'numb', 'House', 'Shared rooms', 2, 1, 1, 'gg', 'Ly Ching Thang', 0, 0, 1, 1, 0, 3, 2, '2022-07-11 13:36:10', NULL),
(6, 'sang', 'Apartment', 'Entire place', 2, 2, 2, '2', '222', 0, 1, 0, 1, 0, 22, 1, '2022-07-12 05:59:58', NULL),
(7, 'nha tui', 'House', 'Private rooms', 2, 2, 2, 'xjanks', '23r', 0, 0, 0, 1, 0, 1000, 1, '2022-07-12 06:06:36', NULL),
(8, 'hello', 'Apartment', 'Private rooms', 2, 2, 2, 'hellooo', '23r', 0, 0, 1, 0, 0, 1000, 5, '2022-07-12 06:33:07', NULL),
(9, 'nha my', 'House', 'Private rooms', 10, 10, 2, 'nha cua my', '42/7', 0, 0, 0, 0, 0, 100, 5, '2022-07-12 19:27:47', NULL),
(10, '2', 'Bed and breakfast', 'Hotel rooms', 2, 2, 2, '22', '2', 0, 0, 0, 0, 0, 2, 2, '2022-07-12 19:47:12', NULL),
(11, '22222', 'Unique space', 'Private rooms', 222, 2, 22, '2', '2', 0, 0, 0, 0, 0, 2, 2, '2022-07-12 20:08:28', NULL),
(12, '22222', 'Unique space', 'Private rooms', 222, 2, 22, '2', '2', 0, 0, 0, 0, 0, 2, 2, '2022-07-12 20:10:44', NULL),
(21, '22222', 'Secondary unit', 'Private rooms', 222, 22, 2, '2222', '22', 0, 0, 0, 0, 0, 22, 2, '2022-07-12 21:21:15', 'images/itec_62cd835bdf01d.jpeg'),
(22, 'new house', 'House', 'Hotel rooms', 100, 100, 100, 'welcome', '42/2', 0, 1, 0, 0, 0, 100, 5, '2022-07-13 21:44:08', 'images/itec_62ceda3838df1.jpeg'),
(27, '22', 'Secondary unit', 'Hotel rooms', 22, 22, 22, '22', '2', 0, 0, 0, 0, 0, 2, 2, '2022-07-13 22:06:36', 'images/itec_62cedf7c13c8d.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf16_unicode_ci NOT NULL,
  `email` varchar(200) COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf16_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `phone_number` varchar(50) COLLATE utf16_unicode_ci NOT NULL,
  `profile_img` varchar(200) COLLATE utf16_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `created_at`, `phone_number`, `profile_img`) VALUES
(1, 'Nhat', 'nhat.caominh.32@gmail.com', 'password', '2022-06-21 14:48:39', '0909123987', 'https://hero.fandom.com/wiki/Gumball_Watterson'),
(2, 'My', 'myxinhgai@gmail.com', 'password', '2022-06-21 15:01:46', '0123456780', 'https://theamazingworldofgumball.fandom.com/wiki/Penny_Fitzgerald'),
(3, '123456', 'thisismehaha@protonmail.com', '$2y$10$0sBidJH5S3.tLwvyzi1yw.c2IHl2wwmCfuTwuE5UDYu0Fd3U4IFA2', '2022-07-07 22:00:42', '11111111', NULL),
(4, 'nhat123', 'nhat.caominh.32@gmail.com', '$2y$10$Kmc/cG3J/Ncv9w2m.cjIKO9rLrN.Rp.YuQfdOKFp1PQOwrLySc3Re', '2022-07-07 23:15:48', '09099090', NULL),
(5, 'admin', 'nhm8606@gmail.com', '$2y$10$OxUoizgxXxAKUl17Fn2ZZ./FHDBujJ8P6dEnVnmAldEgdVZxQ.k4e', '2022-07-12 06:30:55', '1234', 'images/itec_62cd8329c87ac.jpeg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `reservation_id` (`reservation_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservation_room_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_users_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_reservations_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_users_id` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
