CREATE DATABASE `sales_db`;
USE `sales_db`;

-- CREATE TABLE
CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(255) NOT NULL,
    `category` VARCHAR(100),
    `price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `customers`(
	`customer_id` INT,
    `customer_name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100),
    `create_at` DATETIME,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE `orders`(
	`order_id` INT,
    `customer_id` INT,
    `total_amount` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`order_id`),
    CONSTRAINT `customers_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`customer_id`)
    ON DELETE CASCADE
);

CREATE TABLE `order_details`(
	`order_detail_id` INT,
    `order_id` INT,
    `product_id` INT,
    `quantity` INT NOT NULL,
    `unit_price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`order_detail_id`),
    CONSTRAINT `orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`order_id`)
    ON DELETE CASCADE,
    CONSTRAINT `products_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`product_id`)
    ON DELETE CASCADE
);

ALTER TABLE `orders`
ADD COLUMN `order_date` DATE;

-- Thêm chỉ số cho cột Email trong bảng Customers và cột OrderDate trong bảng Orders.
CREATE INDEX `index_email`
ON `customers`(`email`);

CREATE INDEX `index_order_date`
ON `orders`(`order_date`);

SHOW INDEXES FROM `customers`;
SHOW INDEXES FROM `orders`;

DROP INDEX `index_email`
ON `customers`;