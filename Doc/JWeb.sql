-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 24 Décembre 2014 à 13:28
-- Version du serveur: 5.6.22
-- Version de PHP: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `JWeb`
--

-- --------------------------------------------------------

--
-- Structure de la table `Cart`
--

CREATE TABLE IF NOT EXISTS `Cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `date_add` date DEFAULT NULL,
  `date_buy` date DEFAULT NULL,
  `is_buy` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `CartProduct`
--

CREATE TABLE IF NOT EXISTS `CartProduct` (
  `id_cart` int(11) DEFAULT NULL,
  `id_product` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  UNIQUE KEY `id_product_4` (`id_product`),
  KEY `id_cart` (`id_cart`),
  KEY `id_product` (`id_product`),
  KEY `id_product_2` (`id_product`),
  KEY `id_product_3` (`id_product`),
  KEY `id_product_5` (`id_product`),
  KEY `id_product_6` (`id_product`),
  KEY `id_cart_2` (`id_cart`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Comment`
--

CREATE TABLE IF NOT EXISTS `Comment` (
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `comment` text,
  `stars` int(11) DEFAULT NULL,
  KEY `user_id` (`user_id`,`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Image`
--

CREATE TABLE IF NOT EXISTS `Image` (
  `id_product` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  KEY `id_product` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Product`
--

CREATE TABLE IF NOT EXISTS `Product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `pseudo` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `newsletter` tinyint(1) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `User`
--

INSERT INTO `User` (`id`, `email`, `pseudo`, `role`, `newsletter`, `salt`, `password`) VALUES
(3, 'admin@admin.fr', 'admin', 'ADMIN', 0, '42', 'admin');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Cart`
--
ALTER TABLE `Cart`
  ADD CONSTRAINT `Cart_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Contraintes pour la table `CartProduct`
--
ALTER TABLE `CartProduct`
  ADD CONSTRAINT `CartProduct_ibfk_1` FOREIGN KEY (`id_cart`) REFERENCES `Cart` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `CartProduct_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `Product` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Contraintes pour la table `Comment`
--
ALTER TABLE `Comment`
  ADD CONSTRAINT `Comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `Comment_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Contraintes pour la table `Image`
--
ALTER TABLE `Image`
  ADD CONSTRAINT `Image_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `Product` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
