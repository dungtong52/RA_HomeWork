DROP DATABASE `session03_db`;

CREATE DATABASE `session03_db`;
USE `session03_db`;

CREATE TABLE `invoices`(
	`invoice_id` INT,
    `invoice_date` DATETIME,
    `customer_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`invoice_id`)
);

CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(100) NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `invoice_details`(
	`detail_id` INT,
    `invoice_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity` INT NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`detail_id`),
    CONSTRAINT `invoice_detail_fk`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `invoices`(`invoice_id`),
    CONSTRAINT `products_detail_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`product_id`)
    ON DELETE CASCADE
);

-- Insert
INSERT INTO `products`
VALUES
(1, 'pen', 5.00),
(2, 'pencil', 3.10),
(3, 'ruler', 6.20);

INSERT INTO `invoices`
VALUES
(1, '2025-06-11 08:00:00', 'A'),
(2, '2025-06-11 08:00:01', 'B');

INSERT INTO `invoice_details`
VALUES
(1, 2, 1, 5, (SELECT `price` FROM `products` WHERE `product_id` = 1)),
(2, 1, 3, 6, (SELECT `price` FROM `products` WHERE `product_id` = 3)),
(3, 1, 1, 8, (SELECT `price` FROM `products` WHERE `product_id` = 1)),
(4, 2, 2, 2, (SELECT `price` FROM `products` WHERE `product_id` = 2));

-- Update
UPDATE `products` SET `price` = 55.00 WHERE `product_id` = 1;
UPDATE `invoice_details` SET `quantity` = 10 WHERE `detail_id` = 2;

-- Delete
DELETE FROM `products` WHERE `product_id` = 3;
DELETE FROM `invoice_details` WHERE `detail_id` = 1;

-- Query
SELECT `de`.`detail_id`, SUM(`de`.`quantity` * `de`.`price`) AS `total_price`
FROM `invoice_details` AS `de`
GROUP BY `de`.`detail_id`;

SELECT `in`.*, `de`.`product_id`, `pr`.`product_name`, `de`.`quantity`, `de`.`price`, (`de`.`quantity` * `de`.`price`) AS `total_price`
FROM `invoices` AS `in`
JOIN `invoice_details` AS `de` ON `in`.`invoice_id` = `de`.`invoice_id`
JOIN `products` AS `pr` ON `de`.`product_id` = `pr`.`product_id`;

