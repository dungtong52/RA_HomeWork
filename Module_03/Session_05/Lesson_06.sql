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

-- Tạo chỉ số cho cột ProductID trong bảng OrderItems
CREATE INDEX `idx_product_id`
ON `order_items`(`product_id`);

SHOW INDEXES FROM `order_items`;

DROP INDEX `idx_product_id`
ON `order_items`;

-- Tạo view ProductRevenueSummary để tổng hợp doanh thu theo sản phẩm, bao gồm:
-- ProductID
-- ProductName
-- TotalQuantitySold (tổng số lượng bán ra)
-- TotalRevenue (tổng doanh thu)
CREATE VIEW `product_revenue_summary` AS
SELECT 
	p.`product_id` AS `ProductID`,
    p.`product_name` AS `ProductName`,
    SUM(o.`quantity`) AS `TotalQuantitySold`,
    SUM(o.`quantity` * o.`price`) AS `TotalRevenue`
FROM `products` p
JOIN `order_items` o ON o.`product_id` = p.`product_id`
GROUP BY p.`product_id`, p.`product_name`;

-- Truy vấn từ view ProductRevenueSummary để tìm các sản phẩm có tổng doanh thu lớn hơn 10000 và sắp xếp theo doanh thu giảm dần
SELECT * FROM `product_revenue_summary`
WHERE `TotalRevenue` > 10000
ORDER BY `TotalRevenue` DESC;
