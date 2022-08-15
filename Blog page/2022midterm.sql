-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 02, 2022 at 03:18 AM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2022midterm`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` text NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `meme_id` int(11) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `comment_text`, `user_id`, `meme_id`, `date_created`) VALUES
(1, 'Classic  meme', 1, 1, '2022-06-02 08:16:16'),
(2, 'Cringe memee', 1, 4, '2022-06-02 08:16:16'),
(3, 'Love yield farming', 2, 1, '2022-06-02 08:16:46'),
(4, 'What this is top memee', 1, 4, '2022-06-02 08:16:46'),
(5, 'Hope my funds are safu', 1, 3, '2022-06-02 08:17:32'),
(6, 'What this is top memee', 1, 8, '2022-06-02 08:17:32'),
(7, 'This kid ate my funds!', 1, 12, '2022-06-02 08:18:36'),
(8, 'Good meme', 1, 9, '2022-06-02 08:18:36'),
(9, 'feel good meme', 10, 10, '2022-06-02 08:19:53'),
(10, 'Zoomer so silly', 1, 6, '2022-06-02 08:19:53'),
(11, 'Random', 1, 5, '2022-06-02 08:20:47'),
(12, 'Something something', 1, 5, '2022-06-02 08:20:47');

-- --------------------------------------------------------

--
-- Table structure for table `memes`
--

DROP TABLE IF EXISTS `memes`;
CREATE TABLE IF NOT EXISTS `memes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meme_title` varchar(100) NOT NULL,
  `meme_body` text NOT NULL,
  `meme_img` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `memes`
--

INSERT INTO `memes` (`id`, `meme_title`, `meme_body`, `meme_img`, `user_id`, `date_created`) VALUES
(1, 'Wojak', 'The lovable not so normal normie', 'images/meme1.jpg', 10, '2022-06-01 16:42:43'),
(2, 'Yield Farmer', 'Hard working crypto yield farmer.', 'images/meme2.jpg\r\n', 1, '2022-06-01 16:42:43'),
(3, 'Binance Meme', 'Are your funds SAFU?', 'images/meme3.jpg', 1, '2022-06-01 16:44:12'),
(4, 'Dump it!', 'So you just bought your first crypto?', 'images/meme4.jpg', 1, '2022-06-01 16:44:12'),
(5, 'Zoomer Life', 'Tide pods were never so delicious!', 'images/meme6.gif', 1, '2022-06-02 07:47:29'),
(6, 'McDonalds Wojak', 'Life after shattered crypto dreams :(', 'images/meme5.jpg\n', 1, '2022-06-02 07:47:29'),
(7, 'Boomer Tier', 'Boomer like Monster', 'images/meme6.jpg', 1, '2022-06-02 07:49:42'),
(8, 'Whaaaaaaaat?', 'Sometimes Jackie Chan isn\'t even sure what he\'s doing... ', 'images/meme7.jpg', 10, '2022-06-02 07:49:42'),
(9, 'Everything\'s Good-', 'Casual thumbs up', 'images/meme8.gif\r\n', 1, '2022-06-02 07:53:12'),
(10, 'Wonka Meme', 'Classic snarky retort', 'images/meme9.jpg', 1, '2022-06-02 07:53:12'),
(11, 'DogeCoin', 'Even Elon can\'t save Dogee Coin from the bears. ', 'images/meme10.jpg', 1, '2022-06-02 08:04:09'),
(12, 'So hungry...', 'That kid who steals all the pizza.', 'images/meme12.png', 1, '2022-06-02 08:04:09');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(55) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` tinyint(4) NOT NULL DEFAULT '2',
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password_hash`, `role`, `date_created`, `date_updated`) VALUES
(1, 'admin', 'sam@gmail.com', '$2y$10$xkZ9CFoY/NDZOL7V7XdxOOPD5ojc826bHMcqYSFVL4LwMEGG9K5/K', 1, '2022-05-21 14:28:07', NULL),
(9, 'sam10', 'sam@gmail.com', '$2y$10$SFEXoinwiTG8l8dvjg4Ep.nYBv5hFWGSV21XcQ3EisyqigO5Fxuo.', 2, '2022-05-25 16:31:01', NULL),
(10, 'wojack', 'wojak@gmail.com', '$2y$10$xkZ9CFoY/NDZOL7V7XdxOOPD5ojc826bHMcqYSFVL4LwMEGG9K5/K', 2, '2022-06-01 16:41:07', '2022-06-01 09:40:42');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
