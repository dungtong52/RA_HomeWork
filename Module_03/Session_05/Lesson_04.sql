DROP  DATABASE `sales_db`;
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
    `phone` VARCHAR(15),
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

-- Tạo một view CustomerOrders với các cột: OrderID, CustomerName (họ và tên của khách hàng), OrderDate, TotalAmount 
ALTER TABLE `orders`
ADD COLUMN `order_date` DATE;

CREATE VIEW `order_view` AS
SELECT o.`order_id`, c.`customer_name`, o.`order_date`, o.`total_amount`
FROM `orders` o
JOIN `customers` c ON c.`customer_id` = o.`customer_id`;

SELECT * FROM `order_view`;

-- Cập nhật TotalAmount cho đơn hàng có OrderID là 1 thành 250.00.
UPDATE `orders`
SET `total_amount` = 250
WHERE `order_id` = 1;