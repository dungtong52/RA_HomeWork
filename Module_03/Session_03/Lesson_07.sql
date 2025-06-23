DROP DATABASE `session03_db`;

CREATE DATABASE `session03_db`;
USE `session03_db`;

CREATE TABLE `customers`(
	`customerID` INT,
    `name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `joinDate` DATE,
    PRIMARY KEY (`customerID`)
);

CREATE TABLE `orders`(
	`orderID` INT,
    `customerID` INT NOT NULL,
    `orderDate` DATETIME,
    `totalAmount` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`orderID`),
    CONSTRAINT `orders_fk`
    FOREIGN KEY (`customerID`)
    REFERENCES `customers`(`customerID`)
);

-- Insert
INSERT INTO `customers`
VALUES
(1, 'Khánh', 'khanh@gmail.com', '2025-06-01'),
(2, 'Huấn', 'huan@gmail.com', '2025-05-28'),
(3, 'Tiến', 'tien@gmail.com', '2025-05-26'),
(4, 'Quyết', 'quyet@gmail.com', '2025-05-20');

INSERT INTO `orders`
VALUES
(1, 3, '2025-06-10 08:00:06', 1000.05),
(2, 2, '2025-06-10 08:15:26', 1026.28),
(3, 1, '2025-06-10 09:11:16', 1045.12),
(4, 2, '2025-06-10 12:02:03', 1115.33),
(5, 1, '2025-06-10 16:05:04', 899.49);

--  Update
UPDATE `orders` SET `totalAmount` = 350.00 WHERE `orderID` = 3;
UPDATE `customers` SET `email` = 'huanhoahong@gmail.com' WHERE `customerID` = 2;

-- Delete
DELETE FROM `customers` WHERE `customerID` = 4;
DELETE FROM `orders` WHERE `orderID` = 1;

-- Query
SELECT `orders`.*, `customers`.`name` 
FROM `orders`
JOIN `customers` ON `orders`.`customerID` = `customers`.`customerID`;

SELECT `cu`.`name`, SUM(`o`.`totalAmount`) AS `total_order`
FROM `customers` AS `cu`
JOIN `orders` AS `o` ON `cu`.`customerID` = `o`.`customerID`
GROUP BY `cu`.`name`;
