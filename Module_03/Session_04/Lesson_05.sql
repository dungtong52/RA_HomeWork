DROP DATABASE `session04_db`;
CREATE DATABASE `session04_db`;
USE `session04_db`;

--  CREATE TABLE
CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `order_details`(
	`order_detail_id` INT,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`order_detail_id`),
    CONSTRAINT `product_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`product_id`)
);

--  INSERT
INSERT INTO `products`
VALUES
(1, 'Laptop Dell XPS 13'),
(2, 'iPhone 14 Pro Max'),
(3, 'Samsung Galaxy S24'),
(4, 'Tai nghe Sony WH-1000XM5'),
(5, 'Chuột Logitech MX Master 3S');

INSERT INTO `order_details`
VALUES
(101, 1, 2),
(102, 2, 1),
(103, 3, 3),
(104, 1, 1),
(105, 5, 4);

-- QUERY
SELECT p.`product_name`, SUM(o.`quantity`) as SL
FROM `products` p
LEFT JOIN `order_details` o ON o.`product_id` = p.`product_id`
GROUP BY p.`product_name`
ORDER BY SL DESC;