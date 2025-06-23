CREATE DATABASE `session04_db`;
USE `session04_db`;

-- CREATE TABLE
CREATE TABLE `products` (
    `product_id` INT,
    `product_name` VARCHAR(100) NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `sales`(
	`sale_id` INT,
    `product_id` INT NOT NULL,
    `sale_date` DATE NOT NULL,
    `quantity` INT NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`sale_id`),
    CONSTRAINT `products_sales_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`product_id`)
);

--  INSERT
INSERT INTO `products`
VALUES
(101, 'Laptop', 1500.00),
(102, 'Điện thoại', 800.00),
(103, 'Tai nghe', 120.00);

INSERT INTO `sales`
VALUES
(2001, 101, '2025-06-01', 1, 1500.00),
(2002, 103, '2025-06-05', 2, 240.00),
(2003, 102, '2025-06-07', 1, 800.00),
(2004, 101, '2025-06-08', 1, 900.00),
(2005, 103, '2025-06-09', 1, 1000.00);

-- QUERY
SELECT p.`product_id`, COUNT(s.`sale_id`) AS 'Số đơn hàng'
FROM `products` p
JOIN `sales` s ON s.`product_id` = p.`product_id`
GROUP BY p.`product_id`;