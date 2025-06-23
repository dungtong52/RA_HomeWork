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

-- Tạo Trigger BeforeProductDelete để kiểm tra số lượng sản phẩm trước khi xóa
DELIMITER //
CREATE TRIGGER `BeforeProductDelete`
AFTER DELETE ON `products`
FOR EACH ROW
BEGIN
	IF OLD.quantity > 10 THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không được xóa sản phẩm này';
	END IF;
END //
DELIMITER ;

INSERT INTO `products` (`product_id`, `product_name`, `quantity`)
VALUES 
(1, 'Bút bi', 5),
(2, 'Bút chì', 20);

DELETE FROM `products` WHERE `product_id` = 1;
DELETE FROM `products` WHERE `product_id` = 2;
