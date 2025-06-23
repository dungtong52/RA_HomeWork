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


-- Tạo chỉ số cho cột OrderDate trong bảng Orders và SaleDate trong bảng Sales
CREATE INDEX `idx_order_date`
ON `orders`(`order_date`);

CREATE INDEX `idx_sale_date`
ON `sales`(`sale_date`);

-- Tạo view CustomerMonthlySales để tổng hợp doanh thu hàng tháng theo khách hàng, bao gồm: 
-- CustomerID
-- CustomerName
-- MonthYear (tháng và năm)
-- TotalSales (tổng doanh thu)
CREATE VIEW `customer_monthly_sales` AS
SELECT
	c.`customer_id` AS `CustomerID`,
    CONCAT(c.`first_name`, ' ', c.`last_name`) AS `CustomerName`,
    DATE_FORMAT(o.`order_date`, '%Y-%m') AS `MonthYear`,
    SUM(o.`total_amount`) AS `TotalSales`
FROM `customers` c
JOIN `orders` o ON o.`customer_id` = c.`customer_id`
GROUP BY c.`customer_id`, c.`first_name`, c.`last_name`, DATE_FORMAT(o.`order_date`, '%Y-%m');

-- Truy vấn từ view CustomerMonthlySales để tìm các khách hàng có tổng doanh thu trong tháng 2024-07 lớn hơn 2000 và sắp xếp theo doanh thu giảm dần
SELECT * FROM `customer_monthly_sales`
WHERE `MonthYear` = '2024-07' AND `TotalSales` > 2000
ORDER BY `TotalSales` DESC;

