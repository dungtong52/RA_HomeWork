CREATE DATABASE `lesson06_db`;

USE `lesson06_db`;
CREATE TABLE `Customers`(
	`CustomerID` INT,
	`CustomerName` VARCHAR(100),
	`Email` VARCHAR(100),
	`Phone` VARCHAR(15),
	PRIMARY KEY (`CustomerID`)
);

CREATE TABLE `Products`(
	`ProductID` INT NOT NULL,
    `ProductName` VARCHAR(100) NOT NULL,
    `Category` VARCHAR(100) NOT NULL,
    `Price` FLOAT NOT NULL,
    `StockQuantity` INT NOT NULL,
    PRIMARY KEY (`ProductID`)
);

CREATE TABLE `orders`(
	`OrderID` INT NOT NULL,
	`OrderDate` DATE NOT NULL,
	`CustomerID` INT NOT NULL,
	PRIMARY KEY (`OrderID`),
    CONSTRAINT `customers_order_fk`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `Customers`(`CustomerID`)
);

CREATE TABLE `OrdersDetails`(
	`OrderDetailsID` INT NOT NULL,
	`OrderID` INT NOT NULL,
	`ProductID` INT NOT NULL,
    `Quantity` INT NOT NULL,
    `UnitPrice` INT NOT NULL,
	PRIMARY KEY (`OrderDetailsID`),
    CONSTRAINT `order_fk`
    FOREIGN KEY (`OrderID`)
    REFERENCES `orders`(`OrderID`),
    CONSTRAINT `product_fk`
    FOREIGN KEY (`ProductID`)
    REFERENCES `Products`(`ProductID`)
);
ALTER TABLE `OrdersDetails`
MODIFY COLUMN `UnitPrice` DECIMAL(10, 2);

