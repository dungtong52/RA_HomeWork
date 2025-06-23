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

-- Tạo Trigger AfterProductUpdate để ghi lại các thay đổi số lượng hàng
DELIMITER //
CREATE TRIGGER `AfterProductUpdate`
AFTER UPDATE ON `products`
FOR EACH ROW
BEGIN
	IF OLD.quantity <> NEW.quantity THEN
		INSERT INTO `inventory_changes`(`product_id`, `old_quantity`, `new_quantity`, `change_date`)
        VALUES (OLD.`product_id`, OLD.`quantity`, NEW.`quantity`, NOW());
	END IF;
END //
DELIMITER ;

INSERT INTO `products` (`product_id`, `product_name`, `quantity`)
VALUES (1, 'Bút bi', 100);
INSERT INTO `products` (`product_id`, `product_name`, `quantity`)
VALUES (2, 'Bút chì', 150);

UPDATE `products` SET `quantity` = 100 WHERE `product_id` = 2;

SELECT * FROM `inventory_changes`;