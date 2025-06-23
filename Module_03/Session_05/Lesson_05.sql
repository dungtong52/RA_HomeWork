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

CREATE TABLE `sales`(
	`sale_id` INT,
    `order_id` INT NOT NULL,
    `sale_date` DATE,
    `sale_amount` DECIMAL(10, 2),
    PRIMARY KEY (`sale_id`),
    CONSTRAINT `orders_sales_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`order_id`)
    ON DELETE CASCADE
);

-- Hãy tạo view CustomerOrderSummary để hiển thị: 
-- CustomerID
-- CustomerName (tên đầy đủ của khách hàng)
-- TotalOrders (số lượng đơn hàng của khách hàng)
-- TotalAmountSpent (tổng số tiền đã chi tiêu)
CREATE VIEW `customer_order_summary` AS
SELECT 
	c.`customer_id`,
    CONCAT(c.`first_name`, ' ', c.`last_name`) AS `CustomerName`,
    COUNT(o.`order_id`) AS `TotalOrders`,
	SUM(o.`total_amount`) AS `TotalAmountSpent`
FROM `customers` c
LEFT JOIN `orders` o ON c.`customer_id` = o.`customer_id`
GROUP BY c.`customer_id`, c.`first_name`, c.`last_name`;

-- Truy vấn từ view CustomerOrderSummary để tìm các khách hàng có tổng số tiền chi tiêu trên 5000
SELECT * FROM `customer_order_summary`
WHERE `TotalAmountSpent` > 5000;