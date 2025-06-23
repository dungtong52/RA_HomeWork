DROP DATABASE `session07_db`;
CREATE DATABASE `session07_db`;
USE `session07_db`;

-- CREATE TABLE
CREATE TABLE `products`(
	`product_id` INT,
    `product_name` VARCHAR(100),
    `quantity` INT,
    PRIMARY KEY (`product_id`)
);
CREATE TABLE `inventory_changes`(
	`change_id` INT AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `old_quantity` INT,
    `new_quantity` INT,
    `change_date` DATETIME,
    PRIMARY KEY (`change_id`)
);
-- Thay đổi cấu trúc bảng Products để bao gồm một trường LastUpdated
ALTER TABLE `products`
ADD COLUMN `LastUpdated` DATETIME;

-- Tạo Trigger AfterProductUpdateSetDate để cập nhật trường LastUpdated khi có thay đổi
DELIMITER //
CREATE TRIGGER `AfterProductUpdateSetDate`
BEFORE UPDATE ON `products`
FOR EACH ROW
BEGIN
    SET NEW.`LastUpdated` = NOW();
END //
DELIMITER ;

-- Thêm dữ liệu
INSERT INTO `products` (`product_id`, `product_name`, `quantity`)
VALUES (1, 'Bút bi', 10);

-- Cập nhật
UPDATE `products`
SET `quantity` = 50
WHERE `product_id` = 1;

SELECT * FROM `products`;