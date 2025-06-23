CREATE DATABASE `sell_management`;

USE `sell_management`;
CREATE TABLE `product`(
	`ProductID` INT,
	`ProductName` VARCHAR(100),
	`Category` VARCHAR(50),
	`Price` DECIMAL(10, 2),
	`StockQuantity` INT,
	PRIMARY KEY (`ProductID`)
);

CREATE TABLE `orders`(
	`OrderID` INT,
	`OrderDate` DATE,
	`ProductID` INT,
	`Quantity` INT,
	`TotalAmount` DECIMAL(10, 2),
	PRIMARY KEY (`OrderID`),
    CONSTRAINT `product_order_fk`
    FOREIGN KEY (`ProductID`)
    REFERENCES `product`(`ProductID`)
);
