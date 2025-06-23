DROP DATABASE `session04_db`;
CREATE DATABASE `session04_db`;
USE `session04_db`;

--  CREATE TABLE
CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `regions`(
	`region_id` INT,
    `region_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`region_id`)
);

CREATE TABLE `sales`(
	`sale_id` INT,
    `product_id` INT NOT NULL,
    `region_id` INT NOT NULL,
    `sale_date` DATE NOT NULL,
    `quantity` INT NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`sale_id`),
    CONSTRAINT `products_fk`
        FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`),
    CONSTRAINT `regions_fk`
        FOREIGN KEY (`region_id`) REFERENCES `regions`(`region_id`)
);

--  INSERT
INSERT INTO `products`
VALUES
(1, 'iPhone 14 Pro Max'),
(2, 'MacBook Air M3'),
(3, 'Samsung Galaxy S24'),
(4, 'Sony WH-1000XM5');

INSERT INTO `regions`
VALUES
(1, 'Hanoi'),
(2, 'Ho Chi Minh City'),
(3, 'Da Nang'),
(4, 'Can Tho');

INSERT INTO `sales`
VALUES
(101, 1, 1, '2025-06-01', 2, 2399.98),
(102, 2, 2, '2025-06-02', 1, 1199.99),
(103, 3, 3, '2025-06-03', 3, 2699.97),
(104, 1, 2, '2025-06-04', 1, 1199.99),
(105, 4, 4, '2025-06-05', 2, 799.98),
(106, 2, 1, '2025-06-06', 1, 1199.99);

-- QUERY
SELECT
    r.`region_name` AS 'Khu vực',
    SUM(s.`quantity`) AS 'Tổng số lượng',
    SUM(s.`amount`) AS 'Tổng doanh thu'
FROM `sales` s
JOIN `regions` r ON s.`region_id` = r.`region_id`
GROUP BY r.`region_id`, r.`region_name`
ORDER BY r.`region_name`;
