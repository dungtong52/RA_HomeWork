DROP  DATABASE `sales_db`;
CREATE DATABASE `sales_db`;
USE `sales_db`;

-- CREATE TABLE
CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(100),
    `price` DECIMAL(10, 2),
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `customers`(
	`customer_id` INT,
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50),
    `email` VARCHAR(100),
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE `orders`(
	`order_id` INT,
    `customer_id` INT,
	`order_date` DATE,
    `total_amount` DECIMAL(10, 2),
    PRIMARY KEY (`order_id`),
    CONSTRAINT `customers_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`customer_id`)
    ON DELETE CASCADE
);

CREATE TABLE `order_items`(
	`order_item_id` INT,
    `order_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity` INT,
    `price` DECIMAL(10, 2),
    PRIMARY KEY (`order_item_id`),
    CONSTRAINT `orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`order_id`)
    ON DELETE CASCADE,
    CONSTRAINT `products_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`product_id`)
    ON DELETE CASCADE
);

-- Tạo chỉ số cho cột OrderID trong bảng OrderItems
CREATE INDEX `idx_order_id`
ON `order_items`(`order_id`);

SHOW INDEXES FROM `order_items`;
DROP INDEX `idx_order_id`
ON `order_items`;