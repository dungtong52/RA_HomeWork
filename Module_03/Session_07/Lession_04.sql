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
CREATE TABLE `product_summary`(
	`summary_id` INT,
    `total_quantity` INT,
    PRIMARY KEY (`summary_id`)
);

INSERT INTO `product_summary` (`summary_id`, `total_quantity`)
VALUES (1, 0);

-- Tạo Trigger AfterProductUpdateSummary để cập nhật tổng số lượng hàng trong ProductSummary mỗi khi có thay đổi số lượng hàng trong Products:
DELIMITER //
CREATE TRIGGER `AfterProductUpdateSummary`
AFTER UPDATE ON `products`
FOR EACH ROW
BEGIN
	IF NEW.quantity <> OLD.quantity THEN
		UPDATE `product_summary`
		SET `total_quantity` = (
			SELECT SUM(`quantity`) FROM `products`
        )
        WHERE `summary_id` = 1;
	END IF;
END //
DELIMITER ;

-- Cập nhật số lượng
UPDATE `products`
SET `quantity` = 20
WHERE `product_id` = 1;

SELECT * FROM `product_summary`;