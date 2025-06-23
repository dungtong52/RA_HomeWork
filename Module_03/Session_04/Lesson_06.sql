DROP DATABASE `session04_db`;
CREATE DATABASE `session04_db`;
USE `session04_db`;

--  CREATE TABLE
CREATE TABLE `sales`(
	`sale_id` INT,
    `sale_date` DATE NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`sale_id`)
);

--  INSERT
INSERT INTO `sales`
VALUES
(1, '2025-06-01', 1299.99),
(2, '2025-06-03', 999.50),
(3, '2025-06-05', 149.00),
(4, '2025-06-07', 75.25),
(5, '2025-05-09', 2300.00),
(6, '2025-05-10', 420.99);

-- QUERY
SELECT
    DATE_FORMAT(`sale_date`, '%Y-%m') AS `tháng`,
    COUNT(*) AS `số đơn hàng`,
    SUM(`price`) AS `doanh thu`,
    AVG(`price`) AS `doanh thu TB`
FROM `sales`
GROUP BY DATE_FORMAT(`sale_date`, '%Y-%m')
ORDER BY `tháng`;