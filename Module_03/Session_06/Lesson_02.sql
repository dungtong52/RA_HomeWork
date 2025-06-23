DROP DATABASE `sales_db`;
CREATE DATABASE `sales_db`;
USE `sales_db`;

-- CREATE TABLE
CREATE TABLE `customers`(
	`customer_id` INT AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`customer_id`)
);
CREATE TABLE `promotions`(
	`promotion_id` INT AUTO_INCREMENT,
    `promotion_name` VARCHAR(100) NOT NULL,
    `discount_percentage` DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (`promotion_id`)
);
CREATE TABLE `products`(
	`product_id` INT AUTO_INCREMENT,
    `product_name` VARCHAR(100),
    `price` DECIMAL(10, 2),
    `promotion_id` INT,
    PRIMARY KEY (`product_id`),
    CONSTRAINT `fk_prom_prod`
    FOREIGN KEY (`promotion_id`)
    REFERENCES `promotions`(`promotion_id`)
);
CREATE TABLE `orders`(
	`order_id` INT AUTO_INCREMENT,
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
	`order_detail_id` INT AUTO_INCREMENT,
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
	`sale_id` INT AUTO_INCREMENT,
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
INSERT INTO `customers` (`first_name`, `last_name`, `email`) VALUES
('Nguyen', 'An', 'an.nguyen@example.com'),
('Tran', 'Binh', 'binh.tran@example.com');
INSERT INTO `promotions` (`promotion_name`, `discount_percentage`) VALUES
('Summer Sale', 10.00),
('Black Friday', 20.00);
INSERT INTO `products` (`product_name`, `price`, `promotion_id`) VALUES
('Laptop', 1500.00, 1),
('Mouse', 25.00, 2),
('Keyboard', 45.00, NULL);
INSERT INTO `orders` (`customer_id`, `order_date`, `total_amount`) VALUES
(1, '2024-06-05', 1570.00),
(1, '2024-06-20', 45.00),
(2, '2024-07-01', 150.00);
INSERT INTO `order_details` (`order_id`, `product_id`, `quantity`, `unit_price`) VALUES
(1, 1, 1, 1500.00),
(1, 2, 1, 25.00),
(2, 3, 1, 45.00),
(3, 1, 1, 150.00);
INSERT INTO `sales` (`order_id`, `sale_date`, `sale_amount`) VALUES
(1, '2024-06-05', 1570.00),
(2, '2024-06-20', 45.00),
(3, '2024-07-01', 150.00);

-- Tạo stored procedure AddNewCustomer với các tham số:
	-- inFirstName (VARCHAR) – Tên của khách hàng.
	-- inLastName (VARCHAR) – Họ của khách hàng.
	-- inEmail (VARCHAR) – Email của khách hàng.
-- Procedure sẽ thêm một bản ghi mới vào bảng Customers.
DELIMITER //
CREATE PROCEDURE AddNewCustomer(
	IN FirstName VARCHAR(50),
    IN LastName VARCHAR(50),
    IN Email VARCHAR(100)
)
BEGIN
	INSERT INTO `customers`(`first_name`, `last_name`, `email`)
    VALUES (FirstName, LastName, Email);
END //
DELIMITER ;

CALL AddNewCustomer('Nguyen', 'Viet', 'viet.nguyen@example.com');