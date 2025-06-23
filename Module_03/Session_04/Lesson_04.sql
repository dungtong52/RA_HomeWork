DROP DATABASE `session04_db`;
CREATE DATABASE `session04_db`;
USE `session04_db`;

--  CREATE TABLE
CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(100) NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`product_id`)
);

--  INSERT
INSERT INTO `products` VALUES
(1, 'Laptop Dell Inspiron 15', 15499.99),
(2, 'Smartphone Samsung Galaxy S21', 12499.50),
(3, 'Tai nghe Bluetooth Sony WH-1000XM4', 5999.00),
(4, 'Chuột không dây Logitech M331', 499.90),
(5, 'Bàn phím cơ AKKO 3084', 1899.00);

-- QUERY
SELECT `product_name` AS 'SẢN PHẨM GIÁ CAO NHẤT', `price`
FROM `products`
WHERE `price` = (
	SELECT MAX(`price`)
	FROM `products`
);

SELECT `product_name` AS 'SẢN PHẨM GIÁ THẤP NHẤT', `price` 
FROM `products`
WHERE `price` = (
	SELECT MIN(`price`)
	FROM `products`
);
