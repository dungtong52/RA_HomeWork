CREATE DATABASE `session04_db`;
USE `session04_db`;

-- CREATE TABLE
CREATE TABLE `customers`(
	`customer_id` INT,
    `customer_name` VARCHAR(100) NOT NULL,
    `contact_email` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE `orders`(
	`order_id` INT,
    `customer_id` INT NOT NULL,
    `order_date` DATE,
    `total_amount` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`order_id`),
    CONSTRAINT `customer_order_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`customer_id`)
);

--  INSERT
INSERT INTO `customers`
VALUES
(1, 'Nguyễn Văn A', 'a.nguyen@example.com'),
(2, 'Trần Thị B', 'b.tran@example.com'),
(3, 'Lê Văn C', 'c.le@example.com');

INSERT INTO `orders`
VALUE
(101, 1, '2025-06-01', 120000.50),
(102, 1, '2025-06-10', 89000.00),
(103, 2, '2025-06-05', 45000.00);

-- QUERY
SELECT o.`order_id`, c.`customer_name`, c.`contact_email`
FROM `orders` o
LEFT JOIN `customers` c ON c.`customer_id` = o.`customer_id`;

