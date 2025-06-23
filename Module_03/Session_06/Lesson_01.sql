DROP DATABASE `sales_db`;
CREATE DATABASE `sales_db`;
USE `sales_db`;

-- CREATE TABLE
CREATE TABLE `customers`(
	`customer_id` INT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`customer_id`)
);
CREATE TABLE `promotions`(
	`promotion_id` INT,
    `promotion_name` VARCHAR(100) NOT NULL,
    `discount_percentage` DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (`promotion_id`)
);
CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(100),
    `price` DECIMAL(10, 2),
    `promotion_id` INT,
    PRIMARY KEY (`product_id`),
    CONSTRAINT `fk_prom_prod`
    FOREIGN KEY (`promotion_id`)
    REFERENCES `promotions`(`promotion_id`)
);
CREATE TABLE `orders`(
	`order_id` INT,
    `customer_id` INT,
	`order_date` DATE,
    `total_amount` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`order_id`),
    CONSTRAINT `fk_cus_ord`
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
    CONSTRAINT `fk_ord_ordde`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`order_id`)
    ON DELETE CASCADE,
    CONSTRAINT `fk_prod_ordde`
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`product_id`)
    ON DELETE CASCADE
);
CREATE TABLE `sales`(
	`sale_id` INT,
    `order_id` INT,
    `sale_date` DATE,
    `sale_amount` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`sale_id`),
    CONSTRAINT `fk_ord_sal`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`order_id`)
    ON DELETE CASCADE
);
-- INSERT
INSERT INTO `customers` (`customer_id`, `first_name`, `last_name`, `email`) VALUES
(1, 'Nguyen', 'An', 'an.nguyen@example.com'),
(2, 'Tran', 'Binh', 'binh.tran@example.com');
INSERT INTO `promotions` (`promotion_id`, `promotion_name`, `discount_percentage`) VALUES
(1, 'Summer Sale', 10.00),
(2, 'Black Friday', 20.00);
INSERT INTO `products` (`product_id`, `product_name`, `price`, `promotion_id`) VALUES
(1, 'Laptop', 1500.00, 1),
(2, 'Mouse', 25.00, 2),
(3, 'Keyboard', 45.00, NULL);
INSERT INTO `orders` (`order_id`, `customer_id`, `order_date`, `total_amount`) VALUES
(1, 1, '2024-06-05', 1570.00),
(2, 1, '2024-06-20', 45.00),
(3, 2, '2024-07-01', 150.00);
INSERT INTO `order_details` (`order_detail_id`, `order_id`, `product_id`, `quantity`, `unit_price`) VALUES
(1, 1, 1, 1, 1500.00),
(2, 1, 2, 1, 25.00),
(3, 2, 3, 1, 45.00),
(4, 3, 1, 1, 150.00);
INSERT INTO `sales` (`sale_id`, `order_id`, `sale_date`, `sale_amount`) VALUES
(1, 1, '2024-06-05', 1570.00),
(2, 2, '2024-06-20', 45.00),
(3, 3, '2024-07-01', 150.00);

-- thêm chỉ số vào một số cột để cải thiện hiệu suất truy vấn
CREATE INDEX `idx_order_date`
ON `orders`(`order_date`);

CREATE INDEX `idx_sale_date`
ON `sales`(`sale_date`);

-- Tạo stored procedure GetCustomerTotalRevenue với các tham số:
	-- inCustomerID (INT) – ID của khách hàng.
	-- inStartDate (DATE) – Ngày bắt đầu của khoảng thời gian.
	-- inEndDate (DATE) – Ngày kết thúc của khoảng thời gian.
-- Procedure sẽ tính tổng doanh thu của khách hàng trong khoảng thời gian từ inStartDate đến inEndDate.
-- Procedure sẽ trả về tổng doanh thu.

DELIMITER //
CREATE PROCEDURE GetCustomerTotalRevenue(
	IN inCustomerID INT,
    IN inStartDate DATE,
    IN inEndDate DATE,
	OUT total INT,
	OUT customerName VARCHAR(100)
)
BEGIN
	SELECT 
		CONCAT(c.`first_name`, ' ', c.`last_name`) AS 'tên khách hàng',
        SUM(o.`total_amount`) AS 'tổng doanh thu'
	INTO customerName, total
    FROM `customers` c
    JOIN `orders` o ON o.`customer_id` = c.`customer_id`
    WHERE c.`customer_id` = inCustomerID 
		AND o.`order_date` BETWEEN inStartDate AND inEndDate
    GROUP BY c.`customer_id`;
END //
DELIMITER ;
-- DROP PROCEDURE GetCustomerTotalRevenue;

SET @customer_id = 1;
SET @start_date = '2024-06-01';
SET @end_date = '2024-06-30';
SET @total_revenue = 0;
SET @customer_name = '';

CALL GetCustomerTotalRevenue(@customer_id, @start_date, @end_date, @total_revenue, @customer_name);

SELECT @customer_name AS 'Tên khách hàng', @total_revenue AS 'Tổng doanh thu';
